package com.bio4j.model.uniprot.programs;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.*;
import com.bio4j.model.uniprot.edges.*;
import com.ohnosequences.typedGraphs.UntypedGraph;
import com.ohnosequences.xml.api.model.XMLElement;
import org.jdom2.Element;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class ImportProteinInteractions<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

	private static final Logger logger = Logger.getLogger("ImportProteinInteractions");
	private static FileHandler fh;

	public static final String ENTRY_TAG_NAME = "entry";
	public static final String ENTRY_ACCESSION_TAG_NAME = "accession";

	public static final String COMMENT_TAG_NAME = "comment";
	public static final String COMMENT_TYPE_ATTRIBUTE = "type";
	public static final String LOCATION_TAG_NAME = "location";
	public static final String COMMENT_TEXT_TAG_NAME = "text";

	public static final String COMMENT_TYPE_INTERACTION = "interaction";

	protected abstract UniprotGraph<I,RV,RVT,RE,RET> config(String dbFolder);

	protected void importProteinInteractions(String[] args) {

		if (args.length != 2) {
			System.out.println("This program expects the following parameters: \n"
					+ "1. Uniprot xml filename \n"
					+ "2. Bio4j DB folder \n");
		} else {

			long initTime = System.nanoTime();

			File inFile = new File(args[0]);
			String dbFolder = args[1];

			String currentAccessionId = "";

			//-------creating graph handlers---------------------
			UniprotGraph<I,RV,RVT,RE,RET> graph = config(dbFolder);

			BufferedWriter statsBuff = null;

			int proteinCounter = 0;
			int limitForPrintingOut = 10000;

			try {

				// This block configures the logger with handler and formatter
				fh = new FileHandler("ImportProteinInteractions" + args[0].split("\\.")[0] + ".log", false);

				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);

				//---creating writer for stats file-----
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportProteinInteractionsStats_" + inFile.getName().split("\\.")[0] + ".txt")));

				BufferedReader reader = new BufferedReader(new FileReader(inFile));
				StringBuilder entryStBuilder = new StringBuilder();
				String line;

				while ((line = reader.readLine()) != null) {
					if (line.trim().startsWith("<" + ENTRY_TAG_NAME)) {

						while (!line.trim().startsWith("</" + ENTRY_TAG_NAME + ">")) {
							entryStBuilder.append(line);
							line = reader.readLine();
						}
						//linea final del organism
						entryStBuilder.append(line);
						//System.out.println("organismStBuilder.toString() = " + organismStBuilder.toString());
						XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
						entryStBuilder.delete(0, entryStBuilder.length());

						String accessionSt = entryXMLElem.asJDomElement().getChildText(ENTRY_ACCESSION_TAG_NAME);

						Optional<Protein<I,RV,RVT,RE,RET>> proteinOptional = graph.proteinAccessionIndex().getVertex(accessionSt);

						if(proteinOptional.isPresent()){

							Protein<I,RV,RVT,RE,RET> protein = proteinOptional.get();

							List<Element> comments = entryXMLElem.asJDomElement().getChildren(COMMENT_TAG_NAME);

							for (Element commentElem : comments) {

								String commentTypeSt = commentElem.getAttributeValue(COMMENT_TYPE_ATTRIBUTE);

								//----------interaction----------------
								if (commentTypeSt.equals(COMMENT_TYPE_INTERACTION)) {

									List<Element> interactants = commentElem.getChildren("interactant");
									Element interactant1 = interactants.get(0);
									Element interactant2 = interactants.get(1);
									Element organismsDiffer = commentElem.getChild("organismsDiffer");
									Element experiments = commentElem.getChild("experiments");
									String intactId1St = interactant1.getAttributeValue("intactId");
									String intactId2St = interactant2.getAttributeValue("intactId");
									String organismsDifferSt = "";
									String experimentsSt = "";
									if (intactId1St == null) {
										intactId1St = "";
									}
									if (intactId2St == null) {
										intactId2St = "";
									}
									if (organismsDiffer != null) {
										organismsDifferSt = organismsDiffer.getText();
									}
									if (experiments != null) {
										experimentsSt = experiments.getText();
									}

									//----now we try to retrieve the interactant 2 accession--
									String interactant2AccessionSt = interactant2.getChildText("id");
									long protein2Id = -1;
									if (interactant2AccessionSt != null) {

										Optional<Protein<I,RV,RVT,RE,RET>> protein2Optional = graph.proteinAccessionIndex().getVertex(interactant2AccessionSt);

										if(!protein2Optional.isPresent()){

											Optional<Isoform<I,RV,RVT,RE,RET>> isoformOptional = graph.isoformIdIndex().getVertex(interactant2AccessionSt);

											if(isoformOptional.isPresent()){

												ProteinIsoformInteraction<I,RV,RVT,RE,RET> proteinIsoformInteraction = protein.addOutEdge(graph.ProteinIsoformInteraction(), isoformOptional.get());
												proteinIsoformInteraction.set(graph.ProteinIsoformInteraction().experiments, experimentsSt);
												proteinIsoformInteraction.set(graph.ProteinIsoformInteraction().organismsDiffer, organismsDifferSt);
												proteinIsoformInteraction.set(graph.ProteinIsoformInteraction().intActId1, intactId1St);
												proteinIsoformInteraction.set(graph.ProteinIsoformInteraction().intActId2, intactId2St);
											}

										}else{

											ProteinProteinInteraction<I,RV,RVT,RE,RET> proteinProteinInteraction = protein.addOutEdge(graph.ProteinProteinInteraction(), protein2Optional.get());
											proteinProteinInteraction.set(graph.ProteinProteinInteraction().experiments, experimentsSt);
											proteinProteinInteraction.set(graph.ProteinProteinInteraction().organismsDiffer, organismsDifferSt);
											proteinProteinInteraction.set(graph.ProteinProteinInteraction().intActId1, intactId1St);
											proteinProteinInteraction.set(graph.ProteinProteinInteraction().intActId2, intactId2St);
										}

									}

								}

							}

							proteinCounter++;
							if ((proteinCounter % limitForPrintingOut) == 0) {
								logger.log(Level.INFO, (proteinCounter + " proteins updated with interactions!!"));
							}

						}


					}
				}

			} catch (Exception e) {
				logger.log(Level.SEVERE, ("Exception retrieving protein " + currentAccessionId));
				logger.log(Level.SEVERE, e.getMessage());
				StackTraceElement[] trace = e.getStackTrace();
				for (StackTraceElement stackTraceElement : trace) {
					logger.log(Level.SEVERE, stackTraceElement.toString());
				}
			} finally {

				try {

					// shutdown, makes sure all changes are written to disk
					graph.raw().shutdown();

					// closing logger file handler
					fh.close();

					//-----------------writing stats file---------------------
					long elapsedTime = System.nanoTime() - initTime;
					long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
					long hours = elapsedSeconds / 3600;
					long minutes = (elapsedSeconds % 3600) / 60;
					long seconds = (elapsedSeconds % 3600) % 60;

					statsBuff.write("Statistics for program ImportProteinInteractions:\nInput file: " + inFile.getName()
							+ "\nThere were " + proteinCounter + " proteins inserted.\n"
							+ "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

					//---closing stats writer---
					statsBuff.close();


				} catch (IOException ex) {
					Logger.getLogger(ImportUniprot.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		}

	}

}
