package com.bio4j.model.uniprot_go.programs;

import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_go.UniProtGoGraph;
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
public abstract class ImportUniProtGo<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

	private static final Logger logger = Logger.getLogger("ImportUniProtGo");
	private static FileHandler fh;

	public static final String ENTRY_TAG_NAME = "entry";
	public static final String ENTRY_ACCESSION_TAG_NAME = "accession";

	public static final String DB_REFERENCE_TAG_NAME = "dbReference";
	public static final String DB_REFERENCE_TYPE_ATTRIBUTE = "type";
	public static final String DB_REFERENCE_ID_ATTRIBUTE = "id";
	public static final String DB_REFERENCE_VALUE_ATTRIBUTE = "value";
	public static final String DB_REFERENCE_PROPERTY_TAG_NAME = "property";

	public static final String GO_DB_REFERENCE_TYPE = "GO";
	public static final String EVIDENCE_TYPE_ATTRIBUTE = "evidence";

	protected abstract UniProtGoGraph<I,RV,RVT,RE,RET> config(String dbFolder, String propertiesFile);


	public void importUniProtGo(String[] args) {

		if (args.length != 3) {
			System.out.println("This program expects the following parameters: \n"
					+ "1. UniProt xml filename \n"
					+ "2. Bio4j DB folder \n"
					+ "3. DB Properties file (.properties)");
		} else {

			long initTime = System.nanoTime();

			File inFile = new File(args[0]);
			String dbFolder = args[1];
			String propertiesFile = args[2];

			UniProtGoGraph<I,RV,RVT,RE,RET> uniprotGoGraph = config(dbFolder, propertiesFile);

			BufferedWriter statsBuff = null;

			int proteinCounter = 0;
			int limitForPrintingOut = 10000;

			try {

				// This block configures the logger with handler and formatter
				fh = new FileHandler("ImportUniProtGo" + args[0].split("\\.")[0].replaceAll("/", "_") + ".log", false);

				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);

				//---creating writer for stats file-----
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportUniProtGoStats_" + inFile.getName().split("\\.")[0].replaceAll("/", "_") + ".txt")));

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

							//-------------------GO -----------------------------
							if (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).toUpperCase().equals(GO_DB_REFERENCE_TYPE)) {

								if(protein == null){
									Optional<Protein<I,RV,RVT,RE,RET>> proteinOptional = uniprotGoGraph.uniProtGraph().proteinAccessionIndex().getVertex(accessionSt);
									if(proteinOptional.isPresent()){
										protein = proteinOptional.get();
									}else{
										logger.log(Level.INFO, "Protein with id " + accessionSt + " not found...");
										break;
									}
								}

								String goId = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);

								Optional<GoTerm<I,RV,RVT,RE,RET>> goTermOptional = uniprotGoGraph.goGraph().goTermIdIndex().getVertex(goId);
								if(goTermOptional.isPresent()){
									protein.addOutEdge(uniprotGoGraph.GoAnnotation(), goTermOptional.get());
								}else{
									logger.log(Level.INFO, "GO term with id " + goId + " not found...");
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
					uniprotGoGraph.raw().shutdown();

					// closing logger file handler
					fh.close();

					//-----------------writing stats file---------------------
					long elapsedTime = System.nanoTime() - initTime;
					long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
					long hours = elapsedSeconds / 3600;
					long minutes = (elapsedSeconds % 3600) / 60;
					long seconds = (elapsedSeconds % 3600) % 60;

					statsBuff.write("Statistics for program ImportUniProtGO:\nInput file: " + inFile.getName()
							+ "\nThere were " + proteinCounter + " proteins inserted.\n"
							+ "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

					//---closing stats writer---
					statsBuff.close();


				} catch (IOException ex) {
					Logger.getLogger(ImportUniProtGo.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		}

	}

}