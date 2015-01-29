package com.bio4j.model.uniprot_uniref.programs;

import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_uniref.UniProtUniRefGraph;
import com.bio4j.model.uniprot_uniref.edges.*;
import com.bio4j.model.uniref.vertices.UniRef100Cluster;
import com.bio4j.model.uniref.vertices.UniRef50Cluster;
import com.bio4j.model.uniref.vertices.UniRef90Cluster;
import com.bio4j.angulillos.UntypedGraph;
import com.ohnosequences.xml.api.model.XMLElement;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.jdom2.Element;

/**
 * Imports uniref(100,90,50) clusters info into Bio4j
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class ImportUniProtUniRef<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET>  {

	public static final String ENTRY_TAG_NAME = "entry";

	private static final Logger logger = Logger.getLogger("ImportUniProtUniRef");
	private static FileHandler fh;

	protected abstract UniProtUniRefGraph<I,RV,RVT,RE,RET> config(String propertiesFile);

	public void importUniProtUniRef(String[] args) {

		if (args.length != 3) {
			System.out.println("This program expects the following parameters: \n"
					+ "1. UniRef XML filename \n"
					+ "2. UniRef type (50/90/100) \n"
					+ "3. DB Properties file (.properties)");
		} else {

			long initTime = System.nanoTime();

			File uniRefFile = new File(args[0]);
			String uniRefType = args[1];
			String propertiesFile = args[2];

			UniProtUniRefGraph<I,RV,RVT,RE,RET> uniprotUniRefGraph = config(propertiesFile);

			BufferedWriter statsBuff = null;

			int unirefEntryCounter = 0;

			try {

				// This block configure the logger with handler and formatter
				fh = new FileHandler("ImportUniProtUniRef" + args[0].split("\\.")[0].replaceAll("/", "_") + ".log", false);
				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);

				//---creating writer for stats file-----
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportUniProtUniRefStats" + uniRefFile.getName().split("\\.")[0] + ".txt")));

				if(!uniRefType.equals("50") && !uniRefType.equals("90") && !uniRefType.equals("100")){

					logger.log(Level.SEVERE, "The value for the UniRef type is not right, it should be one of the following values: 50, 90, 100");

				}else{
					logger.log(Level.INFO, "Reading UniRef file...");
					unirefEntryCounter = importUniProtUniRefFile(uniprotUniRefGraph, uniRefFile, uniRefType);
					logger.log(Level.INFO, "Done! :)");
				}


			} catch (Exception ex) {
				logger.log(Level.SEVERE, ex.getMessage());
				StackTraceElement[] trace = ex.getStackTrace();
				for (StackTraceElement stackTraceElement : trace) {
					logger.log(Level.SEVERE, stackTraceElement.toString());
				}
			} finally {
				try {
					//committing last transaction
					uniprotUniRefGraph.raw().commit();
					//closing logger file handler
					fh.close();
					//closing neo4j managers
					uniprotUniRefGraph.raw().shutdown();

					//-----------------writing stats file---------------------
					long elapsedTime = System.nanoTime() - initTime;
					long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
					long hours = elapsedSeconds / 3600;
					long minutes = (elapsedSeconds % 3600) / 60;
					long seconds = (elapsedSeconds % 3600) % 60;

					statsBuff.write("Statistics for program ImportUniProtUniRefTitan:\nInput files: " +
							"\nUniRef file: " + uniRefFile.getName()
							+ "\nThe following number of entries was parsed:\n"
							+ unirefEntryCounter + " entries\n"
							+ "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

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

			System.out.println("Program finished!! :D");


		}
	}

	private static String getRepresentantAccession(Element elem) {
		String result = null;
		Element dbReference = elem.getChild("dbReference");
		List<Element> properties = dbReference.getChildren("property");
		for (Element prop : properties) {
			if (prop.getAttributeValue("type").equals("UniProtKB accession")) {
				result = prop.getAttributeValue("value");
			}
		}

		return result;
	}

	private int importUniProtUniRefFile(UniProtUniRefGraph<I,RV,RVT,RE,RET> uniprotUniRefGraph,
	                                           File unirefFile,
	                                           String unirefClusterNumber) throws Exception {

		StringBuilder entryStBuilder = new StringBuilder();

		BufferedReader reader = new BufferedReader(new FileReader(unirefFile));
		String line;

		int entryCounter = 0;
		int limitForPrintingOut = 1000;

		while ((line = reader.readLine()) != null) {
			//----we reached a entry line-----
			if (line.trim().startsWith("<" + ENTRY_TAG_NAME)) {

				while (!line.trim().startsWith("</" + ENTRY_TAG_NAME + ">")) {
					entryStBuilder.append(line);
					line = reader.readLine();
				}
				//organism last line
				entryStBuilder.append(line);

				XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
				entryStBuilder.delete(0, entryStBuilder.length());

				ArrayList<String> membersAccessionList = new ArrayList<String>();
				Element representativeMember = entryXMLElem.asJDomElement().getChild("representativeMember");
				String representantAccession = getRepresentantAccession(representativeMember);
				//----obtaining cluster members---
				List<Element> members = entryXMLElem.asJDomElement().getChildren("member");
				for (Element member : members) {
					Element memberDbReference = member.getChild("dbReference");
					List<Element> memberProperties = memberDbReference.getChildren("property");
					for (Element prop : memberProperties) {
						if (prop.getAttributeValue("type").equals("UniProtKB accession")) {
							String memberAccession = prop.getAttributeValue("value");
							membersAccessionList.add(memberAccession);
						}
					}
				}
				//----retrieving TitanProtein members----
				List<Protein<I,RV,RVT,RE,RET>> proteinMembers = new LinkedList<>();
				for (String proteinAccession : membersAccessionList){
					Optional<Protein<I,RV,RVT,RE,RET>> optionalProtein = uniprotUniRefGraph.uniProtGraph().proteinAccessionIndex().getVertex(proteinAccession);
					if(optionalProtein.isPresent()){
						proteinMembers.add(optionalProtein.get());
					}
				}
				//-----------------------------------------------------

				if(unirefClusterNumber.equals("50")){
					Optional<UniRef50Cluster<I,RV,RVT,RE,RET>> optionalCluster = uniprotUniRefGraph.uniRefGraph().uniRef50ClusterIdIndex().getVertex(representantAccession);
					if(optionalCluster.isPresent()){
						UniRef50Cluster<I,RV,RVT,RE,RET> cluster = optionalCluster.get();

						Optional<Protein<I,RV,RVT,RE,RET>> optionalRepresentant = uniprotUniRefGraph.uniProtGraph().proteinAccessionIndex().getVertex(representantAccession);
						if(optionalRepresentant.isPresent()){
							Protein<I,RV,RVT,RE,RET> representant = optionalRepresentant.get();

							UniRef50Member<I,RV,RVT,RE,RET> uniRef50Member = representant.addOutEdge(uniprotUniRefGraph.UniRef50Member(), cluster);
							uniRef50Member.set(uniprotUniRefGraph.UniRef50Member().proteinAccession, representant.accession());

							UniRef50Representant<I,RV,RVT,RE,RET> uniRef50Representant = representant.addOutEdge(uniprotUniRefGraph.UniRef50Representant(), cluster);
							uniRef50Representant.set(uniprotUniRefGraph.UniRef50Representant().proteinAccession, representant.accession());
						}
						for (Protein<I,RV,RVT,RE,RET> protein : proteinMembers){
							UniRef50Member<I,RV,RVT,RE,RET> uniRef50Member = protein.addOutEdge(uniprotUniRefGraph.UniRef50Member(), cluster);
							uniRef50Member.set(uniprotUniRefGraph.UniRef50Member().proteinAccession, protein.accession());
						}

					}else{
						logger.log(Level.INFO, (representantAccession + " cluster not found... :|"));
					}

				}else if(unirefClusterNumber.equals("90")){
					Optional<UniRef90Cluster<I,RV,RVT,RE,RET>> optionalCluster = uniprotUniRefGraph.uniRefGraph().uniRef90ClusterIdIndex().getVertex(representantAccession);
					if(optionalCluster.isPresent()){
						UniRef90Cluster<I,RV,RVT,RE,RET> cluster = optionalCluster.get();

						Optional<Protein<I,RV,RVT,RE,RET>> optionalRepresentant = uniprotUniRefGraph.uniProtGraph().proteinAccessionIndex().getVertex(representantAccession);
						if(optionalRepresentant.isPresent()){
							Protein<I,RV,RVT,RE,RET> representant = optionalRepresentant.get();

							UniRef90Member<I,RV,RVT,RE,RET> uniRef90Member = representant.addOutEdge(uniprotUniRefGraph.UniRef90Member(), cluster);
							uniRef90Member.set(uniprotUniRefGraph.UniRef90Member().proteinAccession, representant.accession());

							UniRef90Representant<I,RV,RVT,RE,RET> uniRef90Representant = representant.addOutEdge(uniprotUniRefGraph.UniRef90Representant(), cluster);
							uniRef90Representant.set(uniprotUniRefGraph.UniRef90Representant().proteinAccession, representant.accession());
						}
						for (Protein<I,RV,RVT,RE,RET> protein : proteinMembers){
							UniRef90Member<I,RV,RVT,RE,RET> uniRef90Member = protein.addOutEdge(uniprotUniRefGraph.UniRef90Member(), cluster);
							uniRef90Member.set(uniprotUniRefGraph.UniRef90Member().proteinAccession, protein.accession());
						}

					}else{
						logger.log(Level.INFO, (representantAccession + " cluster not found... :|"));
					}

				}else if(unirefClusterNumber.equals("100")){
					Optional<UniRef100Cluster<I,RV,RVT,RE,RET>> optionalCluster = uniprotUniRefGraph.uniRefGraph().uniRef100ClusterIdIndex().getVertex(representantAccession);
					if(optionalCluster.isPresent()){
						UniRef100Cluster<I,RV,RVT,RE,RET> cluster = optionalCluster.get();

						Optional<Protein<I,RV,RVT,RE,RET>> optionalRepresentant = uniprotUniRefGraph.uniProtGraph().proteinAccessionIndex().getVertex(representantAccession);
						if(optionalRepresentant.isPresent()){
							Protein<I,RV,RVT,RE,RET> representant = optionalRepresentant.get();

							UniRef100Member<I,RV,RVT,RE,RET> uniRef100Member = representant.addOutEdge(uniprotUniRefGraph.UniRef100Member(), cluster);
							uniRef100Member.set(uniprotUniRefGraph.UniRef100Member().proteinAccession, representant.accession());

							UniRef100Representant<I,RV,RVT,RE,RET> uniRef100Representant = representant.addOutEdge(uniprotUniRefGraph.UniRef100Representant(), cluster);
							uniRef100Representant.set(uniprotUniRefGraph.UniRef100Representant().proteinAccession, representant.accession());
						}
						for (Protein<I,RV,RVT,RE,RET> protein : proteinMembers){
							UniRef100Member<I,RV,RVT,RE,RET> uniRef100Member = protein.addOutEdge(uniprotUniRefGraph.UniRef100Member(), cluster);
							uniRef100Member.set(uniprotUniRefGraph.UniRef100Member().proteinAccession, protein.accession());
						}

					}else{
						logger.log(Level.INFO, (representantAccession + " cluster not found... :|"));
					}
				}

			}

			entryCounter++;
			if ((entryCounter % limitForPrintingOut) == 0) {
				logger.log(Level.INFO, (entryCounter + " entries parsed!!"));
				uniprotUniRefGraph.raw().commit();
			}

		}
		reader.close();

		return entryCounter;
	}
}