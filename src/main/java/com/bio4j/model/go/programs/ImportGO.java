
package com.bio4j.model.go.programs;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.go.nodes.SubOntologies;
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
public abstract class ImportGO {

	public static final String TERM_TAG_NAME = "term";
	public static final String ID_TAG_NAME = "id";
	public static final String NAME_TAG_NAME = "name";
	public static final String DEF_TAG_NAME = "def";
	public static final String DEFSTR_TAG_NAME = "defstr";
	public static final String IS_ROOT_TAG_NAME = "is_root";
	public static final String IS_OBSOLETE_TAG_NAME = "is_obsolete";
	public static final String COMMENT_TAG_NAME = "comment";
	public static final String NAMESPACE_TAG_NAME = "namespace";
	public static final String RELATIONSHIP_TAG_NAME = "relationship";

	public static final String REGULATES_OBOXML_RELATIONSHIP_NAME = "regulates";
	public static final String POSITIVELY_REGULATES_OBOXML_RELATIONSHIP_NAME = "positively_regulates";
	public static final String NEGATIVELY_REGULATES_OBOXML_RELATIONSHIP_NAME = "negatively_regulates";
	public static final String PART_OF_OBOXML_RELATIONSHIP_NAME = "part_of";
	public static final String HAS_PART_OF_OBOXML_RELATIONSHIP_NAME = "has_part";
	public static final String IS_A_OBOXML_RELATIONSHIP_NAME = "is_a";

	private static final Logger logger = Logger.getLogger("ImportGO");
	private static FileHandler fh;


	protected abstract GoGraph config();

	protected void importGO(String[] args){
		if (args.length != 2) {
			System.out.println("This program expects the following parameters: \n"
					+ "1. Gene ontology xml filename \n"
					+ "2. Bio4j DB folder \n");

		} else {

			int termCounter = 0;
			int limitForPrintingOut = 10000;
			long initTime = System.nanoTime();

			File inFile = new File(args[0]);

			BufferedWriter statsBuff = null;

			GoGraph goGraph = config();

			try {

				//This block configures the logger with handler and formatter
				fh = new FileHandler("ImportGO.log", true);
				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);

				//---creating writer for stats file-----
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportGOStats.txt")));

				Map<String, ArrayList<String>> termParentsMap = new HashMap<>();
				Map<String, ArrayList<String>> regulatesMap = new HashMap<>();
				Map<String, ArrayList<String>> negativelyRegulatesMap = new HashMap<>();
				Map<String, ArrayList<String>> positivelyRegulatesMap = new HashMap<>();
				Map<String, ArrayList<String>> partOfMap = new HashMap<>();
				Map<String, ArrayList<String>> hasPartMap = new HashMap<>();


				BufferedReader reader = new BufferedReader(new FileReader(inFile));
				String line;
				StringBuilder termStBuilder = new StringBuilder();

				logger.log(Level.INFO, "inserting subontologies nodes....");
				//---biological process---
				SubOntologies subOntologiesBP = graph.subOntologiesT.from(graph.rawGraph().addVertex(null));
				subOntologiesBP.set(graph.subOntologiesT.name, "biological_process");
				SubOntologies subOntologiesCC = graph.subOntologiesT.from(graph.rawGraph().addVertex(null));
				subOntologiesCC.set(graph.subOntologiesT.name, "cellular_component");
				SubOntologies subOntologiesMM = graph.subOntologiesT.from(graph.rawGraph().addVertex(null));
				subOntologiesMM.set(graph.subOntologiesT.name, "molecular_function");

				logger.log(Level.INFO, "inserting term nodes....");

				//-----first I create all the elements whitout their relationships-------------

				while ((line = reader.readLine()) != null) {
					if (line.trim().startsWith("<" + TERM_TAG_NAME)) {

						while (!line.trim().startsWith("</" + TERM_TAG_NAME + ">")) {
							termStBuilder.append(line);
							line = reader.readLine();
						}
						//organism final line
						termStBuilder.append(line);
						XMLElement termXMLElement = new XMLElement(termStBuilder.toString());
						termStBuilder.delete(0, termStBuilder.length());

						String goId = termXMLElement.asJDomElement().getChildText(ID_TAG_NAME);
						String goName = termXMLElement.asJDomElement().getChildText(NAME_TAG_NAME);
						if (goName == null) {
							goName = "";
						}
						String goNamespace = termXMLElement.asJDomElement().getChildText(NAMESPACE_TAG_NAME);
						if (goNamespace == null) {
							goNamespace = "";
						}
						String goDefinition = "";
						Element defElem = termXMLElement.asJDomElement().getChild(DEF_TAG_NAME);
						if (defElem != null) {
							Element defstrElem = defElem.getChild(DEFSTR_TAG_NAME);
							if (defstrElem != null) {
								goDefinition = defstrElem.getText();
							}
						}
						String goComment = termXMLElement.asJDomElement().getChildText(COMMENT_TAG_NAME);
						if (goComment == null) {
							goComment = "";
						}
						String goIsObsolete = termXMLElement.asJDomElement().getChildText(IS_OBSOLETE_TAG_NAME);
						if (goIsObsolete == null) {
							goIsObsolete = "";
						} else {
							if (goIsObsolete.equals("1")) {
								goIsObsolete = "true";
							} else {
								goIsObsolete = "false";
							}
						}


						//----term parents----
						List<Element> termParentTerms = termXMLElement.asJDomElement().getChildren(IS_A_OBOXML_RELATIONSHIP_NAME);
						ArrayList<String> array = new ArrayList<>();
						for (Element elem : termParentTerms) {
							array.add(elem.getText().trim());
						}
						termParentsMap.put(goId, array);
						//---------------------

						//-------relationship tags-----------
						List<Element> relationshipTags = termXMLElement.asJDomElement().getChildren(RELATIONSHIP_TAG_NAME);

						for (Element relationshipTag : relationshipTags) {

							String relType = relationshipTag.getChildText("type");
							String toSt = relationshipTag.getChildText("to");

							if (relType.equals(REGULATES_OBOXML_RELATIONSHIP_NAME)) {

								ArrayList<String> tempArray = regulatesMap.get(goId);
								if (tempArray == null) {
									tempArray = new ArrayList<>();
									regulatesMap.put(goId, tempArray);
								}
								tempArray.add(toSt);

							} else if (relType.equals(POSITIVELY_REGULATES_OBOXML_RELATIONSHIP_NAME)) {

								ArrayList<String> tempArray = positivelyRegulatesMap.get(goId);
								if (tempArray == null) {
									tempArray = new ArrayList<>();
									positivelyRegulatesMap.put(goId, tempArray);
								}
								tempArray.add(toSt);

							} else if (relType.equals(NEGATIVELY_REGULATES_OBOXML_RELATIONSHIP_NAME)) {

								ArrayList<String> tempArray = negativelyRegulatesMap.get(goId);
								if (tempArray == null) {
									tempArray = new ArrayList<>();
									negativelyRegulatesMap.put(goId, tempArray);
								}
								tempArray.add(toSt);

							} else if (relType.equals(PART_OF_OBOXML_RELATIONSHIP_NAME)) {

								ArrayList<String> tempArray = partOfMap.get(goId);
								if (tempArray == null) {
									tempArray = new ArrayList<>();
									partOfMap.put(goId, tempArray);
								}
								tempArray.add(toSt);

							} else if (relType.equals(HAS_PART_OF_OBOXML_RELATIONSHIP_NAME)) {

								ArrayList<String> tempArray = hasPartMap.get(goId);
								if (tempArray == null) {
									tempArray = new ArrayList<>();
									hasPartMap.put(goId, tempArray);
								}
								tempArray.add(toSt);

							}
						}
						//-------------------------------------


						GoTerm term = goGraph.GoTerm().from(goGraph.raw().addVertex(goGraph.GoTerm()));

						term.set(goGraph.GoTerm().id, goId);
						term.set(goGraph.GoTerm().name, goName);
						term.set(goGraph.GoTerm().definition, goDefinition);
						//term.set(goGraph.GoTerm().obso, goIsObsolete);
						term.set(goGraph.GoTerm().comment, goComment);
						//----------------------

						//g.commit();

						//----namespace---

						GoTerm tempGoTerm = goGraph.goTermIdIndex().getVertex(goId).get();
						//SubOntologies subOntologies = goGraph.
						TitanSubOntologies titanSubontologies = graph.subOntologiesNameIndex.getNode(goNamespace).get();
						tempGoTerm.addOut(graph.subOntologyT, titanSubontologies);


					}
					termCounter++;
					if ((termCounter % limitForPrintingOut) == 0) {
						logger.log(Level.INFO, (termCounter + " terms inserted!!"));
					}
				}
				reader.close();

				//----committing transaction---
				goGraph.raw().commit();

				//-----------------------------------------------------------------------

				logger.log(Level.INFO, "Inserting relationships....");

				logger.log(Level.INFO, "'is_a' relationships....");

				//-------------------'is_a' relationships-----------------
				Set<String> keys = termParentsMap.keySet();
				for (String key : keys) {

					TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode(key).get();
					//System.out.println("id: " + tempGoTerm.id() + " name: " + tempGoTerm.name());
					ArrayList<String> tempArray = termParentsMap.get(key);

					for (String string : tempArray) {
						//System.out.println("string: " + string);
						TitanGoTerm tempGoTerm2 = graph.goTermIdIndex.getNode(string).get();
						//System.out.println("id2: " + tempGoTerm2.id() + " name2: " + tempGoTerm2.name());
						tempGoTerm.addOut(graph.isAT, tempGoTerm2);
					}
				}

				logger.log(Level.INFO, "'regulates' relationships....");
				//-------------------'regulates' relationships----------------------
				keys = regulatesMap.keySet();
				for (String key : keys) {
					TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode(key).get();
					ArrayList<String> tempArray = regulatesMap.get(key);
					for (String string : tempArray) {
						TitanGoTerm tempGoTerm2 = graph.goTermIdIndex.getNode(string).get();
						tempGoTerm.addOut(graph.regulatesT, tempGoTerm2);
					}
				}

				logger.log(Level.INFO, "'negatively_regulates' relationships....");
				//-------------------'negatively regulates' relationships----------------------
				keys = negativelyRegulatesMap.keySet();
				for (String key : keys) {
					TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode(key).get();
					ArrayList<String> tempArray = negativelyRegulatesMap.get(key);
					for (String string : tempArray) {
						TitanGoTerm tempGoTerm2 = graph.goTermIdIndex.getNode(string).get();
						tempGoTerm.addOut(graph.negativelyRegulatesT, tempGoTerm2);
					}
				}

				logger.log(Level.INFO, "'positively_regulates' relationships....");
				//-------------------'positively regulates' relationships----------------------
				keys = positivelyRegulatesMap.keySet();
				for (String key : keys) {
					TitanGoTerm tempGoTerm =  graph.goTermIdIndex.getNode(key).get();
					ArrayList<String> tempArray = positivelyRegulatesMap.get(key);
					for (String string : tempArray) {
						TitanGoTerm tempGoTerm2 =  graph.goTermIdIndex.getNode(string).get();
						tempGoTerm.addOut(graph.positivelyRegulatesT, tempGoTerm2);
					}
				}

				logger.log(Level.INFO, "'part_of' relationships....");
				//-------------------'parf of' relationships----------------------
				keys = partOfMap.keySet();
				for (String key : keys) {
					TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode(key).get();
					ArrayList<String> tempArray = partOfMap.get(key);
					for (String string : tempArray) {
						TitanGoTerm tempGoTerm2 = graph.goTermIdIndex.getNode(string).get();
						tempGoTerm.addOut(graph.partOfT, tempGoTerm2);
					}
				}

				logger.log(Level.INFO, "'has_part_of' relationships....");
				//-------------------'has part of' relationships----------------------
				keys = hasPartMap.keySet();
				for (String key : keys) {
					TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode(key).get();
					ArrayList<String> tempArray = hasPartMap.get(key);
					for (String string : tempArray) {
						TitanGoTerm tempGoTerm2 = graph.goTermIdIndex.getNode(string).get();
						tempGoTerm.addOut(graph.hasPartOfT, tempGoTerm2);
					}
				}

				logger.log(Level.INFO, "Done! :)");


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
					logger.log(Level.INFO, "Closing up manager....");
					//shutdown, makes sure all changes are written to disk
					graph.rawGraph().shutdown();

					//-----------------writing stats file---------------------
					long elapsedTime = System.nanoTime() - initTime;
					long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
					long hours = elapsedSeconds / 3600;
					long minutes = (elapsedSeconds % 3600) / 60;
					long seconds = (elapsedSeconds % 3600) % 60;

					statsBuff.write("Statistics for program ImportGeneOntology:\nInput file: " + inFile.getName()
							+ "\nThere were " + termCounter + " terms inserted.\n"
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
		}
	}



}
