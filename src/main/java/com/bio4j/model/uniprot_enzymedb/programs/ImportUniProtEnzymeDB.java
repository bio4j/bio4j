package com.bio4j.model.uniprot_enzymedb.programs;

import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_enzymedb.UniProtEnzymeDBGraph;
import com.bio4j.angulillos.UntypedGraph;
import com.ohnosequences.xml.api.model.XMLElement;
import org.jdom2.Element;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class ImportUniProtEnzymeDB<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

	private static final Logger logger = Logger.getLogger("ImportUniProtEnzymeDB");
	private static FileHandler fh;

	protected abstract UniProtEnzymeDBGraph<I,RV,RVT,RE,RET> config(String dbFolder);

	public static final String ENTRY_TAG_NAME = "entry";
	public static final String ENTRY_ACCESSION_TAG_NAME = "accession";
	public static final String DB_REFERENCE_TAG_NAME = "dbReference";
	public static final String DB_REFERENCE_TYPE_ATTRIBUTE = "type";
	public static final String DB_REFERENCE_ID_ATTRIBUTE = "id";
	public static final String ENZYME_REFERENCE_TYPE = "EC";


	public void importUniProtEnzymeDB(String[] args) {

		if (args.length != 2) {
			System.out.println("This program expects the following parameters: \n"
					+ "1. UniProt xml filename \n"
					+ "2. Bio4j DB folder \n");
		} else {

			long initTime = System.nanoTime();

			File inFile = new File(args[0]);
			String dbFolder = args[1];


			UniProtEnzymeDBGraph<I,RV,RVT,RE,RET> uniprotEnzymeDBGraph = config(dbFolder);

			BufferedWriter statsBuff = null;

			int proteinCounter = 0;
			int limitForPrintingOut = 10000;

			try {

				// This block configures the logger with handler and formatter
				fh = new FileHandler("ImportUniProtEnzymeDB" + args[0].split("\\.")[0].replaceAll("/", "_") + ".log", false);

				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);

				//---creating writer for stats file-----
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportUniProtEnzymeDBStats_" + inFile.getName().split("\\.")[0].replaceAll("/", "_") + ".txt")));

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

						Protein<I,RV,RVT,RE,RET> protein = null;

						//-----db references-------------
						List<Element> dbReferenceList = entryXMLElem.asJDomElement().getChildren(DB_REFERENCE_TAG_NAME);

						for (Element dbReferenceElem : dbReferenceList) {

							//-------------------ENZYME DB -----------------------------
							if (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).toUpperCase().equals(ENZYME_REFERENCE_TYPE)) {

								if(protein == null){
									protein = uniprotEnzymeDBGraph.uniprotGraph().proteinAccessionIndex().getVertex(accessionSt).get();
								}

								String enzymeID = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);

								if(enzymeID != null){

									//uniprotEnzymeDBGraph.enzymeDBGraph().enzymeIdIndex()

									Optional<Enzyme<I,RV,RVT,RE,RET>> enzymeOptional = uniprotEnzymeDBGraph.enzymeDBGraph().enzymeIdIndex().getVertex(enzymeID);

									if(enzymeOptional.isPresent()){
										protein.addOutEdge(uniprotEnzymeDBGraph.EnzymaticActivity(), enzymeOptional.get());
									}else{
										logger.log(Level.INFO, "The enzyme with id: " + enzymeID + " could not be found... :|");
									}
								}else{
									logger.log(Level.INFO, "Null enzyme id found for protein: " + accessionSt);
								}

							}

						}
						//---------------------------------------------------------------------------------------


						proteinCounter++;
						if ((proteinCounter % limitForPrintingOut) == 0) {
							String countProteinsSt = proteinCounter + " proteins updated!!";
							logger.log(Level.INFO, countProteinsSt);
						}

					}
				}

			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
				StackTraceElement[] trace = e.getStackTrace();
				for (StackTraceElement stackTraceElement : trace) {
					logger.log(Level.SEVERE, stackTraceElement.toString());
				}
			} finally {

				try {
					//------closing writers-------

					// shutdown, makes sure all changes are written to disk
					uniprotEnzymeDBGraph.raw().shutdown();

					// closing logger file handler
					fh.close();

					//-----------------writing stats file---------------------
					long elapsedTime = System.nanoTime() - initTime;
					long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
					long hours = elapsedSeconds / 3600;
					long minutes = (elapsedSeconds % 3600) / 60;
					long seconds = (elapsedSeconds % 3600) % 60;

					statsBuff.write("Statistics for program ImportUniProtEnzymeDB:\nInput file: " + inFile.getName()
							+ "\nThere were " + proteinCounter + " proteins analyzed.\n"
							+ "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

					//---closing stats writer---
					statsBuff.close();


				} catch (IOException ex) {
					Logger.getLogger(ImportUniProtEnzymeDB.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		}

	}

}