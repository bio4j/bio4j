/*
 * Copyright (C) 2010-2013  "Bio4j"
 *
 * This file is part of Bio4j
 *
 * Bio4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.bio4j.model.enzymedb.programs;

import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.angulillos.UntypedGraph;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * Imports Expasy Enzyme DB into Bio4j
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class ImportEnzymeDB<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

	public static final String IDENTIFICATION_LINE_CODE = "ID";
	public static final String OFFICIAL_NAME_LINE_CODE = "DE";
	public static final String ALTERNATE_NAME_LINE_CODE = "AN";
	public static final String CATALYTIC_ACTIVITY_LINE_CODE = "CA";
	public static final String COMMENTS_LINE_CODE = "CC";
	public static final String COFACTORS_LINE_CODE = "CF";
	public static final String PROSITE_CROSS_REFERENCES_LINE_CODE = "PR";
	public static final String SWISS_PROT_CROSS_REFERENCES_LINE_CODE = "DR";
	public static final String TERMINATION_LINE_CODE = "//";
	private static final Logger logger = Logger.getLogger("ImportEnzymeDB");
	private static FileHandler fh;

	protected abstract EnzymeDBGraph<I,RV,RVT,RE,RET> config(String dbFolder);

	public void importEnzymeDB(String[] args) {

		if (args.length != 2) {
			System.out.println("This program expects the following parameters: \n"
					+ "1. Enzyme DB data file (.dat) \n"
					+ "2. Bio4j DB folder \n");

		} else {

			long initTime = System.nanoTime();

			File inFile = new File(args[0]);
			String dbFolder = args[1];

			BufferedWriter statsBuff = null;

			EnzymeDBGraph<I,RV,RVT,RE,RET> enzymeDBGraph = config(dbFolder);

			int enzymeCounter = 0;

			try {

				// This block configures the logger with handler and formatter
				fh = new FileHandler("ImportEnzymeDB.log", true);
				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);

				//---creating writer for stats file-----
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportEnzymeDBStats.txt")));

				BufferedReader reader = new BufferedReader(new FileReader(inFile));
				String line;

				boolean enzymeFound = false;
				String officialName = "";
				String enzymeId = "";
				String commentsSt = "";
				String catalyticActivity = "";
				List<String> cofactors = new LinkedList<>();
				List<String> prositeCrossRefs = new LinkedList<>();
				boolean deletedEntry = false;
				boolean transferredEntry = false;

				System.out.println("Reading file....");

				while ((line = reader.readLine()) != null) {
					if (line.startsWith(IDENTIFICATION_LINE_CODE)) {
						enzymeFound = true;
						enzymeId = line.substring(5).trim();


					} else if (enzymeFound) {

						if (line.startsWith(OFFICIAL_NAME_LINE_CODE)) {

							officialName += line.substring(5).trim();

							if (officialName.contains("Deleted entry.")) {
								deletedEntry = true;
							} else if (officialName.contains("Transferred entry:")) {
								transferredEntry = true;
							}

						}
						else if (line.startsWith(COFACTORS_LINE_CODE)) {

							String[] cofs = line.substring(5).trim().split(";");
							for (String cofactorSt : cofs) {
								cofactors.add(cofactorSt.trim());
							}

						} else if (line.startsWith(PROSITE_CROSS_REFERENCES_LINE_CODE)) {

							String[] proRefs = line.substring(5).trim().split(";");
							for (String prositeSt : proRefs) {
								if (!prositeSt.equals("PROSITE")) {
									prositeCrossRefs.add(prositeSt.trim());
								}
							}

						} else if (line.startsWith(COMMENTS_LINE_CODE)) {

							commentsSt += line.substring(5).trim() + " ";

						} else if (line.startsWith(CATALYTIC_ACTIVITY_LINE_CODE)) {

							catalyticActivity += line.substring(5).trim() + " ";

						} else if (line.startsWith(TERMINATION_LINE_CODE)) {
							if (enzymeFound) {

								if (deletedEntry) {
									logger.log(Level.INFO, ("Entry with id " + enzymeId + " was deleted. It won't be stored..."));
									deletedEntry = false;
								} else if (transferredEntry) {
									logger.log(Level.INFO, ("Entry with id " + enzymeId + " was transferred. It won't be stored..."));
									transferredEntry = false;
								}

								Enzyme<I,RV,RVT,RE,RET> enzyme = enzymeDBGraph.addVertex(enzymeDBGraph.Enzyme());

								enzyme.set(enzymeDBGraph.Enzyme().id, enzymeId);
								enzyme.set(enzymeDBGraph.Enzyme().officialName, officialName);
								enzyme.set(enzymeDBGraph.Enzyme().cofactors, cofactors.toArray(new String[0]));
								enzyme.set(enzymeDBGraph.Enzyme().prositeCrossReferences, prositeCrossRefs.toArray(new String[0]));
								enzyme.set(enzymeDBGraph.Enzyme().catalyticActivity, catalyticActivity);
								enzyme.set(enzymeDBGraph.Enzyme().comment, commentsSt);

								enzymeCounter++;
								if (enzymeCounter % 100 == 0) {
									System.out.println(enzymeCounter + " enzymes inserted...");
								}

							}
							enzymeFound = false;
							officialName = "";
							enzymeId = "";
							commentsSt = "";
							catalyticActivity = "";
							cofactors.clear();
							prositeCrossRefs.clear();
						}

					}

				}

				reader.close();


			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
				StackTraceElement[] trace = e.getStackTrace();
				for (StackTraceElement stackTraceElement : trace) {
					logger.log(Level.SEVERE, stackTraceElement.toString());
				}
			} finally {

				try {
					//closing logger file handler
					fh.close();
					logger.log(Level.INFO, "Closing up graph service....");
					// shutdown, makes sure all changes are written to disk
					enzymeDBGraph.raw().shutdown();

					//-----------------writing stats file---------------------
					long elapsedTime = System.nanoTime() - initTime;
					long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
					long hours = elapsedSeconds/3600;
					long minutes = (elapsedSeconds % 3600)/60;
					long seconds = (elapsedSeconds % 3600)%60;

					statsBuff.write("Statistics for program ImportEnzymeDB:\nInput file: " + inFile.getName()
							+ "\nThere were " + enzymeCounter + " enzymes inserted.\n" +
							"The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

					//---closing stats writer---
					statsBuff.close();


				} catch (Exception e) {
					logger.log(Level.SEVERE, e.getMessage());
					StackTraceElement[] trace = e.getStackTrace();
					for (StackTraceElement stackTraceElement : trace) {
						logger.log(Level.SEVERE, stackTraceElement.toString());
					}
				}

			}

		}
	}
}