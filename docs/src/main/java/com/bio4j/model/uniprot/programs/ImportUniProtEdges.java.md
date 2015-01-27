
```java
package com.bio4j.model.uniprot.programs;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.*;
import com.bio4j.model.uniprot.edges.*;
import com.bio4j.angulillos.UntypedGraph;
import com.ohnosequences.xml.api.model.XMLElement;
import com.ohnosequences.xml.model.bio4j.UniprotDataXML;
import org.apache.commons.lang.mutable.MutableInt;
import org.jdom2.Element;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class ImportUniProtEdges<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

	private static final Logger logger = Logger.getLogger("ImportUniProtEdges");
	private static FileHandler fh;

	public static final String ENTRY_TAG_NAME = "entry";
	public static final String ENTRY_ACCESSION_TAG_NAME = "accession";
	public static final String ENTRY_NAME_TAG_NAME = "name";
	public static final String ENTRY_MODIFIED_DATE_ATTRIBUTE = "modified";
	public static final String ENTRY_CREATED_DATE_ATTRIBUTE = "created";
	public static final String ENTRY_VERSION_ATTRIBUTE = "version";
	public static final String ENTRY_DATASET_ATTRIBUTE = "dataset";
	public static final String ENTRY_SEQUENCE_TAG_NAME = "sequence";

	public static final String KEYWORD_TAG_NAME = "keyword";
	public static final String KEYWORD_ID_ATTRIBUTE = "id";

	public static final String REFERENCE_TAG_NAME = "reference";
	public static final String CITATION_TAG_NAME = "citation";

	public static final String GENE_LOCATION_TAG_NAME = "geneLocation";

	public static final String ORGANISM_TAG_NAME = "organism";
	public static final String ORGANISM_NAME_TAG_NAME = "name";
	public static final String ORGANISM_NAME_TYPE_ATTRIBUTE = "type";
	public static final String ORGANISM_SCIENTIFIC_NAME_TYPE = "scientific";
	public static final String ORGANISM_COMMON_NAME_TYPE = "common";
	public static final String ORGANISM_SYNONYM_NAME_TYPE = "synonym";

	public static final String DB_REFERENCE_TAG_NAME = "dbReference";
	public static final String DB_REFERENCE_TYPE_ATTRIBUTE = "type";
	public static final String DB_REFERENCE_ID_ATTRIBUTE = "id";
	public static final String DB_REFERENCE_VALUE_ATTRIBUTE = "value";
	public static final String DB_REFERENCE_PROPERTY_TAG_NAME = "property";

	public static final String INTERPRO_DB_REFERENCE_TYPE = "InterPro";
	public static final String INTERPRO_ENTRY_NAME = "entry name";

	public static final String GO_DB_REFERENCE_TYPE = "GO";
	public static final String EVIDENCE_TYPE_ATTRIBUTE = "evidence";

	public static final String SEQUENCE_MASS_ATTRIBUTE = "mass";
	public static final String SEQUENCE_LENGTH_ATTRIBUTE = "length";
	public static final String PROTEIN_TAG_NAME = "protein";
	public static final String PROTEIN_RECOMMENDED_NAME_TAG_NAME = "recommendedName";
	public static final String PROTEIN_FULL_NAME_TAG_NAME = "fullName";
	public static final String PROTEIN_SHORT_NAME_TAG_NAME = "shortName";
	public static final String GENE_TAG_NAME = "gene";
	public static final String GENE_NAME_TAG_NAME = "name";
	public static final String COMMENT_TAG_NAME = "comment";
	public static final String COMMENT_TYPE_ATTRIBUTE = "type";
	public static final String COMMENT_ALTERNATIVE_PRODUCTS_TYPE = "alternative products";
	public static final String COMMENT_SEQUENCE_CAUTION_TYPE = "sequence caution";
	public static final String SUBCELLULAR_LOCATION_TAG_NAME = "subcellularLocation";
	public static final String LOCATION_TAG_NAME = "location";
	public static final String COMMENT_TEXT_TAG_NAME = "text";
	public static final String FEATURE_TAG_NAME = "feature";
	public static final String FEATURE_TYPE_ATTRIBUTE = "type";
	public static final String FEATURE_DESCRIPTION_ATTRIBUTE = "description";
	public static final String STATUS_ATTRIBUTE = "status";
	public static final String FEATURE_REF_ATTRIBUTE = "ref";
	public static final String FEATURE_ID_ATTRIBUTE = "id";
	public static final String EVIDENCE_ATTRIBUTE = "evidence";
	public static final String FEATURE_LOCATION_TAG_NAME = "location";
	public static final String FEATURE_ORIGINAL_TAG_NAME = "original";
	public static final String FEATURE_VARIATION_TAG_NAME = "variation";
	public static final String FEATURE_POSITION_TAG_NAME = "position";
	public static final String FEATURE_LOCATION_BEGIN_TAG_NAME = "begin";
	public static final String FEATURE_LOCATION_END_TAG_NAME = "end";
	public static final String FEATURE_LOCATION_POSITION_ATTRIBUTE = "position";
	public static final String FEATURE_POSITION_POSITION_ATTRIBUTE = "position";

	public static final String THESIS_CITATION_TYPE = "thesis";
	public static final String PATENT_CITATION_TYPE = "patent";
	public static final String SUBMISSION_CITATION_TYPE = "submission";
	public static final String ARTICLE_CITATION_TYPE = "journal article";
	public static final String ONLINE_ARTICLE_CITATION_TYPE = "online journal article";
	public static final String BOOK_CITATION_TYPE = "book";
	public static final String UNPUBLISHED_OBSERVATION_CITATION_TYPE = "unpublished observations";

	public static final String COMMENT_TYPE_DISEASE = "disease";
	public static final String COMMENT_TYPE_FUNCTION = "function";
	public static final String COMMENT_TYPE_COFACTOR = "cofactor";
	public static final String COMMENT_TYPE_CATALYTIC_ACTIVITY = "catalytic activity";
	public static final String COMMENT_TYPE_ENZYME_REGULATION = "enzyme regulation";
	public static final String COMMENT_TYPE_BIOPHYSICOCHEMICAL_PROPERTIES = "biophysicochemical properties";
	public static final String COMMENT_TYPE_SUBUNIT = "subunit";
	public static final String COMMENT_TYPE_PATHWAY = "pathway";
	public static final String COMMENT_TYPE_SUBCELLULAR_LOCATION = "subcellular location";
	public static final String COMMENT_TYPE_TISSUE_SPECIFICITY = "tissue specificity";
	public static final String COMMENT_TYPE_DEVELOPMENTAL_STAGE = "developmental stage";
	public static final String COMMENT_TYPE_INDUCTION = "induction";
	public static final String COMMENT_TYPE_DOMAIN = "domain";
	public static final String COMMENT_TYPE_POST_TRANSLATIONAL_MODIFICATION = "PTM";
	public static final String COMMENT_TYPE_RNA_EDITING = "RNA editing";
	public static final String COMMENT_TYPE_MASS_SPECTROMETRY = "mass spectrometry";
	public static final String COMMENT_TYPE_POLYMORPHISM = "polymorphism";
	public static final String COMMENT_TYPE_DISRUPTION_PHENOTYPE = "disruption phenotype";
	public static final String COMMENT_TYPE_ALLERGEN = "allergen";
	public static final String COMMENT_TYPE_TOXIC_DOSE = "toxic dose";
	public static final String COMMENT_TYPE_BIOTECHNOLOGY = "biotechnology";
	public static final String COMMENT_TYPE_PHARMACEUTICAL = "pharmaceutical";
	public static final String COMMENT_TYPE_MISCELLANEOUS = "miscellaneous";
	public static final String COMMENT_TYPE_SIMILARITY = "similarity";

	protected SimpleDateFormat dateFormat;


	protected abstract UniProtGraph<I,RV,RVT,RE,RET> config(String dbFolder, String propertiesFile);

	protected void importUniProtEdges(String[] args) {

		if (args.length != 4) {
			System.out.println("This program expects the following parameters: \n"
					+ "1. UniProt xml filename \n"
					+ "2. Bio4j DB folder \n"
					+ "3. Config XML file \n"
					+ "4. DB properties file (.properties)");
		} else {

			long initTime = System.nanoTime();

			File inFile = new File(args[0]);
			String dbFolder = args[1];
			File configFile = new File(args[2]);
			String propertiesFile = args[3];

			String currentAccessionId = "";

			//-------creating graph handlers---------------------
			UniProtGraph<I,RV,RVT,RE,RET> graph = config(dbFolder, propertiesFile);

			BufferedWriter statsBuff = null;

			int proteinCounter = 0;
			MutableInt edgeCounter = new MutableInt(0);
			MutableInt vertexIndexCalls = new MutableInt(0);
			int limitForPrintingOut = 10000;

			dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			try {

				// This block configures the logger with handler and formatter
				fh = new FileHandler("ImportUniProtEdges" + args[0].split("\\.")[0].replaceAll("/", "_") + ".log", false);

				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);

				System.out.println("Reading conf file...");
				BufferedReader reader = new BufferedReader(new FileReader(configFile));
				String line;
				StringBuilder stBuilder = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					stBuilder.append(line);
				}
				reader.close();

				UniprotDataXML uniprotDataXML = new UniprotDataXML(stBuilder.toString());

				//---creating writer for stats file-----
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportUniProtEdgesStats_" + inFile.getName().split("\\.")[0].replaceAll("/", "_") + ".txt")));

				reader = new BufferedReader(new FileReader(inFile));
				StringBuilder entryStBuilder = new StringBuilder();

				HashSet<String> subcellularLocationParentEdgesAlreadyCreated = new HashSet<>(); //target nodes
				HashSet<String> taxonParentEdgesAlreadyCreated = new HashSet<>(); //target nodes
				HashSet<String> organismTaxonEdgesAlreadyCreated = new HashSet<>();

				while ((line = reader.readLine()) != null) {
					if (line.trim().startsWith("<" + ENTRY_TAG_NAME)) {

						while (!line.trim().startsWith("</" + ENTRY_TAG_NAME + ">")) {
							entryStBuilder.append(line);
							line = reader.readLine();
						}
						//linea final del organism
						entryStBuilder.append(line);

						XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
						entryStBuilder.delete(0, entryStBuilder.length());

						String accessionSt = entryXMLElem.asJDomElement().getChildText(ENTRY_ACCESSION_TAG_NAME);

						currentAccessionId = accessionSt;

						Optional<Protein<I,RV,RVT,RE,RET>> optionalProtein = graph.proteinAccessionIndex().getVertex(accessionSt);
						vertexIndexCalls.add(1);

						if(optionalProtein.isPresent()){
							Protein<I,RV,RVT,RE,RET> protein = optionalProtein.get();

							//-----db references-------------
							List<Element> dbReferenceList = entryXMLElem.asJDomElement().getChildren(DB_REFERENCE_TAG_NAME);
							ArrayList<String> ensemblPlantsReferences = new ArrayList<>();
							HashMap<String, String> reactomeReferences = new HashMap<>();

							for (Element dbReferenceElem : dbReferenceList) {
								String refId = dbReferenceElem.getAttributeValue("id");
								switch (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE)) {
									case "Ensembl":
										//looking for Ensembl node
										Optional<Ensembl<I,RV,RVT,RE,RET>> ensemblOptional = graph.ensemblIdIndex().getVertex(refId);
										vertexIndexCalls.add(1);
										if(ensemblOptional.isPresent()){
											protein.addOutEdge(graph.ProteinEnsembl(), ensemblOptional.get());
											edgeCounter.add(1);
										}
										break;
									case "PIR":
										//looking for PIR node
										Optional<PIR<I,RV,RVT,RE,RET>> optionalPIR = graph.pIRIdIndex().getVertex(refId);
										vertexIndexCalls.add(1);
										if(optionalPIR.isPresent()){
											protein.addOutEdge(graph.ProteinPIR(), optionalPIR.get());
											edgeCounter.add(1);
										}
										break;
									case "UniGene":
										//looking for UniGene node
										Optional<UniGene<I,RV,RVT,RE,RET>> uniGeneOptional = graph.uniGeneIdIndex().getVertex(refId);
										vertexIndexCalls.add(1);
										if(uniGeneOptional.isPresent()){
											protein.addOutEdge(graph.ProteinUniGene(), uniGeneOptional.get());
											edgeCounter.add(1);
										}
										break;
									case "KEGG":
										//looking for Kegg node
										Optional<Kegg<I,RV,RVT,RE,RET>> optionalKegg = graph.keggIdIndex().getVertex(refId);
										vertexIndexCalls.add(1);
										if(optionalKegg.isPresent()){
											protein.addOutEdge(graph.ProteinKegg(), optionalKegg.get());
											edgeCounter.add(1);
										}
										break;
									case "EMBL":
										//looking for EMBL node
										Optional<EMBL<I,RV,RVT,RE,RET>> optionalEMBL = graph.eMBLIdIndex().getVertex(refId);
										vertexIndexCalls.add(1);
										if(optionalEMBL.isPresent()){
											protein.addOutEdge(graph.ProteinEMBL(), optionalEMBL.get());
											edgeCounter.add(1);
										}
										break;
									case "RefSeq":
										//looking for RefSeq node
										Optional<RefSeq<I,RV,RVT,RE,RET>> optionalRefSeq = graph.refSeqIdIndex().getVertex(refId);
										vertexIndexCalls.add(1);
										if(optionalRefSeq.isPresent()){
											protein.addOutEdge(graph.ProteinRefSeq(), optionalRefSeq.get());
											edgeCounter.add(1);
										}
										break;
									case "Reactome":
										if (uniprotDataXML.getReactome()) {
											Optional<ReactomeTerm<I,RV,RVT,RE,RET>> optionalReactomeTerm = graph.reactomeTermIdIndex().getVertex(refId);
											vertexIndexCalls.add(1);
											if (optionalReactomeTerm.isPresent()) {
												protein.addOutEdge(graph.ProteinReactomeTerm(), optionalReactomeTerm.get());
												edgeCounter.add(1);
											}
										}
										break;
									case "EnsemblPlants":
										ensemblPlantsReferences.add(refId);
										break;
								}
							}

							//-----comments import---
							if (uniprotDataXML.getComments()) {
								importProteinComments(entryXMLElem,
										graph,
										protein,
										uniprotDataXML,
										subcellularLocationParentEdgesAlreadyCreated,
										vertexIndexCalls,
										edgeCounter);
							}

							//-----features import----
							if (uniprotDataXML.getFeatures()) {
								importProteinFeatures(entryXMLElem,
										graph,
										protein,
										vertexIndexCalls,
										edgeCounter);
							}

							//--------------------------------datasets--------------------------------------------------
							String proteinDataSetSt = entryXMLElem.asJDomElement().getAttributeValue(ENTRY_DATASET_ATTRIBUTE);

							Optional<Dataset<I,RV,RVT,RE,RET>> optionalDataset = graph.datasetNameIndex().getVertex(proteinDataSetSt);
							vertexIndexCalls.add(1);
							if (optionalDataset.isPresent()) {
								protein.addOutEdge(graph.ProteinDataset(), optionalDataset.get());
								edgeCounter.add(1);
							}

							//---------------------------------------------------------------------------------------------
							if (uniprotDataXML.getCitations()) {
								importProteinCitations(entryXMLElem,
										graph,
										protein,
										uniprotDataXML,
										vertexIndexCalls,
										edgeCounter);
							}

							//-------------------------------keywords------------------------------------------------------
							if (uniprotDataXML.getKeywords()) {
								List<Element> keywordsList = entryXMLElem.asJDomElement().getChildren(KEYWORD_TAG_NAME);
								for (Element keywordElem : keywordsList) {

									String keywordId = keywordElem.getAttributeValue(KEYWORD_ID_ATTRIBUTE);

									Optional<Keyword<I,RV,RVT,RE,RET> > optionalKeyword = graph.keywordIdIndex().getVertex(keywordId);
									vertexIndexCalls.add(1);
									if (optionalKeyword.isPresent()) {
										protein.addOutEdge(graph.ProteinKeyword(), optionalKeyword.get());
										edgeCounter.add(1);
									}
								}
							}
							//---------------------------------------------------------------------------------------


							for (Element dbReferenceElem : dbReferenceList) {

								//-------------------------------INTERPRO------------------------------------------------------
								if (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals(INTERPRO_DB_REFERENCE_TYPE)) {

									if (uniprotDataXML.getInterpro()) {
										String interproId = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);
										Optional<InterPro<I,RV,RVT,RE,RET>> optionalInterPro = graph.interproIdIndex().getVertex(interproId);
										vertexIndexCalls.add(1);
										if (optionalInterPro.isPresent()) {
											protein.addOutEdge(graph.ProteinInterPro(), optionalInterPro.get());
											edgeCounter.add(1);
										}
									}

								} //-------------------------------PFAM------------------------------------------------------
								else if (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals("Pfam")) {

									if (uniprotDataXML.getPfam()) {

										String pfamId = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);
										Optional<Pfam<I,RV,RVT,RE,RET>> optionalPfam = graph.pfamIdIndex().getVertex(pfamId);
										vertexIndexCalls.add(1);

										if (optionalPfam.isPresent()) {
											protein.addOutEdge(graph.ProteinPfam(), optionalPfam.get());
											edgeCounter.add(1);
										}
									}
								}
							}
							//---------------------------------------------------------------------------------------

							//---------------------------------------------------------------------------------------
							//--------------------------------geneLocation-------------------------------------------
							List<Element> geneLocationElements = entryXMLElem.asJDomElement().getChildren(GENE_LOCATION_TAG_NAME);

							for (Element geneLocationElem : geneLocationElements){

								String geneLocationTypeSt = geneLocationElem.getAttributeValue("type");
								String geneLocationNameSt = geneLocationElem.getChildText("name");
								if(geneLocationNameSt == null){
									geneLocationNameSt = "";
								}

								Optional<GeneLocation<I,RV,RVT,RE,RET>> optionalGeneLocation = graph.geneLocationNameIndex().getVertex(geneLocationTypeSt);
								vertexIndexCalls.add(1);

								if(optionalGeneLocation.isPresent()){
									ProteinGeneLocation<I,RV,RVT,RE,RET> proteinGeneLocation = protein.addOutEdge(graph.ProteinGeneLocation(), optionalGeneLocation.get());
									edgeCounter.add(1);
									proteinGeneLocation.set(graph.ProteinGeneLocation().name, geneLocationNameSt);
								}
							}


							//---------------------------------------------------------------------------------------
							//--------------------------------gene names-------------------------------------------

							Element geneElement = entryXMLElem.asJDomElement().getChild(GENE_TAG_NAME);
							if (geneElement != null) {
								List<Element> geneNamesList = geneElement.getChildren(GENE_NAME_TAG_NAME);

								for (Element geneNameElem : geneNamesList) {
									String geneNameSt = geneNameElem.getText();
									String typeSt = geneNameElem.getAttributeValue("type");

									Optional<GeneName<I,RV,RVT,RE,RET>> optionalGeneName = graph.geneNameNameIndex().getVertex(geneNameSt);
									vertexIndexCalls.add(1);

									if(optionalGeneName.isPresent()){
										GeneName<I,RV,RVT,RE,RET> geneName = optionalGeneName.get();
										ProteinGeneName<I,RV,RVT,RE,RET> proteinGeneName = protein.addOutEdge(graph.ProteinGeneName(), geneName);
										edgeCounter.add(1);
										proteinGeneName.set(graph.ProteinGeneName().geneNameType, typeSt);
									}

								}
							}
							//---------------------------------------------------------------------------------------


							//---------------------------------------------------------------------------------------
							//--------------------------------organism-----------------------------------------------

							String scName = "";

							Element organismElem = entryXMLElem.asJDomElement().getChild(ORGANISM_TAG_NAME);

							List<Element> organismNames = organismElem.getChildren(ORGANISM_NAME_TAG_NAME);
							for (Element element : organismNames) {
								String type = element.getAttributeValue(ORGANISM_NAME_TYPE_ATTRIBUTE);
								if (type.equals(ORGANISM_SCIENTIFIC_NAME_TYPE)) {
									scName = element.getText();
								}
							}

							Optional<Organism<I,RV,RVT,RE,RET>> organismOptional = graph.organismScientificNameIndex().getVertex(scName);
							vertexIndexCalls.add(1);

							if (organismOptional.isPresent()) {
								Organism<I,RV,RVT,RE,RET> organism = organismOptional.get();

								protein.addOutEdge(graph.ProteinOrganism(), organism);
								edgeCounter.add(1);

								Element lineage = entryXMLElem.asJDomElement().getChild("organism").getChild("lineage");
								List<Element> taxons = lineage.getChildren("taxon");

								Element firstTaxonElem = taxons.get(0);
								Optional<Taxon<I,RV,RVT,RE,RET>> firstTaxonOptional = graph.taxonNameIndex().getVertex(firstTaxonElem.getText());
								vertexIndexCalls.add(1);

								if (firstTaxonOptional.isPresent()) {
									Taxon<I,RV,RVT,RE,RET> lastTaxon = firstTaxonOptional.get();

									for (int i = 1; i < taxons.size(); i++) {
										String taxonName = taxons.get(i).getText();
										Taxon<I,RV,RVT,RE,RET> currentTaxon = null;
										Optional<Taxon<I,RV,RVT,RE,RET>> currentTaxonOptional = graph.taxonNameIndex().getVertex(taxonName);
										vertexIndexCalls.add(1);

										if(currentTaxonOptional.isPresent()){
											currentTaxon = currentTaxonOptional.get();
											if(!taxonParentEdgesAlreadyCreated.contains(currentTaxon.name())){
												taxonParentEdgesAlreadyCreated.add(currentTaxon.name());
												try{
													currentTaxon.taxonParent_in();
												}catch(NoSuchElementException e){
													lastTaxon.addOutEdge(graph.TaxonParent(), currentTaxon);
													edgeCounter.add(1);
												}
											}
										}

										lastTaxon = currentTaxon;
									}

									if(!organismTaxonEdgesAlreadyCreated.contains(organism.scientificName())){
										organismTaxonEdgesAlreadyCreated.add(organism.scientificName());
										try{
											organism.organismTaxon_out();
										}catch(NoSuchElementException e){
											organism.addOutEdge(graph.OrganismTaxon(), lastTaxon);
											edgeCounter.add(1);
										}
									}

								}
							}
							//---------------------------------------------------------------------------------------
							//---------------------------------------------------------------------------------------
						}

						proteinCounter++;
						if ((proteinCounter % limitForPrintingOut) == 0) {
							String logSt = proteinCounter + " proteins inserted!!";
							logSt += "\n" + edgeCounter + " edges were created so far...";
							logSt += "\n" + vertexIndexCalls + " queries were performed to vertex indices...";
							logger.log(Level.INFO, logSt);
							graph.raw().commit();
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

					statsBuff.write("Statistics for program ImportUniProtEdges:\nInput file: " + inFile.getName()
							+ "\nThere were " + proteinCounter + " proteins inserted.\n"
							+ "\nThere were " + edgeCounter + " edges created.\n"
							+ "\nThere were " + vertexIndexCalls + " vertex index calls on the whole process.\n"
							+ "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

					//---closing stats writer---
					statsBuff.close();


				} catch (IOException ex) {
					Logger.getLogger(ImportUniProt.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		}

	}

	private void importProteinFeatures(XMLElement entryXMLElem,
	                                   UniProtGraph<I,RV,RVT,RE,RET> graph,
	                                   Protein<I,RV,RVT,RE,RET> protein,
	                                   MutableInt vertexIndexCalls,
	                                   MutableInt edgeCounter) {

		//--------------------------------features----------------------------------------------------
		List<Element> featuresList = entryXMLElem.asJDomElement().getChildren(FEATURE_TAG_NAME);

		for (Element featureElem : featuresList) {

			String featureTypeSt = featureElem.getAttributeValue(FEATURE_TYPE_ATTRIBUTE);

			Optional<FeatureType<I,RV,RVT,RE,RET>> optionalFeature = graph.featureTypeNameIndex().getVertex(featureTypeSt);
			vertexIndexCalls.add(1);

			if (!optionalFeature.isPresent()) {

				FeatureType<I,RV,RVT,RE,RET> feature = optionalFeature.get();

				String featureDescSt = featureElem.getAttributeValue(FEATURE_DESCRIPTION_ATTRIBUTE);
				if (featureDescSt == null) {
					featureDescSt = "";
				}
				String featureIdSt = featureElem.getAttributeValue(FEATURE_ID_ATTRIBUTE);
				if (featureIdSt == null) {
					featureIdSt = "";
				}
				String featureStatusSt = featureElem.getAttributeValue(STATUS_ATTRIBUTE);
				if (featureStatusSt == null) {
					featureStatusSt = "";
				}
				String featureEvidenceSt = featureElem.getAttributeValue(EVIDENCE_ATTRIBUTE);
				if (featureEvidenceSt == null) {
					featureEvidenceSt = "";
				}

				Element locationElem = featureElem.getChild(FEATURE_LOCATION_TAG_NAME);
				Element positionElem = locationElem.getChild(FEATURE_POSITION_TAG_NAME);
				Integer beginFeature = null;
				Integer endFeature = null;
				if (positionElem != null) {
					String tempValue = positionElem.getAttributeValue(FEATURE_POSITION_POSITION_ATTRIBUTE);
					if (tempValue == null) {
						beginFeature = -1;
					}else{
						beginFeature = Integer.parseInt(tempValue);
					}
					endFeature = beginFeature;
				} else {
					String tempValue = locationElem.getChild(FEATURE_LOCATION_BEGIN_TAG_NAME).getAttributeValue(FEATURE_LOCATION_POSITION_ATTRIBUTE);
					if(tempValue == null){
						beginFeature = null;
					}else{
						beginFeature = Integer.parseInt(tempValue);
					}
					tempValue = locationElem.getChild(FEATURE_LOCATION_END_TAG_NAME).getAttributeValue(FEATURE_LOCATION_POSITION_ATTRIBUTE);
					if(tempValue == null){
						endFeature = null;
					}else{
						endFeature = Integer.parseInt(tempValue);
					}
				}

				if (beginFeature == null) {
					beginFeature = -1;
				}
				if (endFeature == null) {
					endFeature = -1;
				}

				String originalSt = featureElem.getChildText(FEATURE_ORIGINAL_TAG_NAME);
				String variationSt = featureElem.getChildText(FEATURE_VARIATION_TAG_NAME);
				if (originalSt == null) {
					originalSt = "";
				}
				if (variationSt == null) {
					variationSt = "";
				}
				String featureRefSt = featureElem.getAttributeValue(FEATURE_REF_ATTRIBUTE);
				if (featureRefSt == null) {
					featureRefSt = "";
				}

				ProteinFeature<I,RV,RVT,RE,RET> proteinFeature = protein.addOutEdge(graph.ProteinFeature(), feature);
				addPropertiesToProteinFeatureRelationship(graph, proteinFeature, featureIdSt, featureDescSt, featureEvidenceSt,
						featureStatusSt, beginFeature, endFeature, originalSt, variationSt, featureRefSt);
				edgeCounter.add(1);

			}

		}

	}

	private void importProteinComments(XMLElement entryXMLElem,
	                                   UniProtGraph<I,RV,RVT,RE,RET> graph,
	                                   Protein<I,RV,RVT,RE,RET> protein,
	                                   UniprotDataXML uniprotDataXML,
	                                   HashSet<String> subcellularLocationParentEdgesAlreadyCreated,
	                                   MutableInt vertexIndexCalls,
	                                   MutableInt edgeCounter) {


		List<Element> comments = entryXMLElem.asJDomElement().getChildren(COMMENT_TAG_NAME);

		for (Element commentElem : comments) {

			String commentTypeSt = commentElem.getAttributeValue(COMMENT_TYPE_ATTRIBUTE);

			Element textElem = commentElem.getChild("text");
			String commentTextSt = "";
			String commentStatusSt = "";
			String commentEvidenceSt = "";
			if (textElem != null) {
				commentTextSt = textElem.getText();
				commentStatusSt = textElem.getAttributeValue("status");
				if (commentStatusSt == null) {
					commentStatusSt = "";
				}
				commentEvidenceSt = textElem.getAttributeValue("evidence");
				if (commentEvidenceSt == null) {
					commentEvidenceSt = "";
				}
			}

			//-----------------COMMENT TYPE NODE RETRIEVING/CREATION----------------------
			Optional<CommentType<I,RV,RVT,RE,RET>> commentOptional =  graph.commentTypeNameIndex().getVertex(commentTypeSt);
			vertexIndexCalls.add(1);

			if(!commentOptional.isPresent()){

				CommentType<I,RV,RVT,RE,RET> comment = commentOptional.get();
				boolean createStandardProteinComment = false;

				switch (commentTypeSt) {

					case COMMENT_TYPE_FUNCTION:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_COFACTOR:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_CATALYTIC_ACTIVITY:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_ENZYME_REGULATION:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_DISEASE:

						Element diseaseElement = commentElem.getChild("disease");

						if(diseaseElement != null){
							String diseaseId = diseaseElement.getAttributeValue("id");
							String diseaseName = diseaseElement.getChildText("name");
							String diseaseDescription = diseaseElement.getChildText("description");
							String diseaseAcronym = diseaseElement.getChildText("acronym");

							if(diseaseId != null){

								Optional<Disease<I,RV,RVT,RE,RET>> diseaseOptional =  graph.diseaseIdIndex().getVertex(diseaseId);
								vertexIndexCalls.add(1);

								if(diseaseOptional.isPresent()){
									Disease<I,RV,RVT,RE,RET> disease = diseaseOptional.get();
									ProteinDisease<I,RV,RVT,RE,RET> proteinDisease = protein.addOutEdge(graph.ProteinDisease(), disease);
									proteinDisease.set(graph.ProteinDisease().text, commentTextSt);
									proteinDisease.set(graph.ProteinDisease().status, commentStatusSt);
									proteinDisease.set(graph.ProteinDisease().evidence, commentEvidenceSt);
									edgeCounter.add(1);
								}
							}
						}
						break;

					case COMMENT_TYPE_TISSUE_SPECIFICITY:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_TOXIC_DOSE:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_BIOTECHNOLOGY:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_SUBUNIT:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_POLYMORPHISM:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_DOMAIN:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_POST_TRANSLATIONAL_MODIFICATION:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_DISRUPTION_PHENOTYPE:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_BIOPHYSICOCHEMICAL_PROPERTIES:

						String phDependenceSt = commentElem.getChildText("phDependence");
						String temperatureDependenceSt = commentElem.getChildText("temperatureDependence");
						if (phDependenceSt == null) {
							phDependenceSt = "";
						}
						if (temperatureDependenceSt == null) {
							temperatureDependenceSt = "";
						}
						String absorptionMaxSt = "";
						String absorptionTextSt = "";
						Element absorptionElem = commentElem.getChild("absorption");
						if (absorptionElem != null) {
							absorptionMaxSt = absorptionElem.getChildText("max");
							absorptionTextSt = absorptionElem.getChildText("text");
							if (absorptionMaxSt == null) {
								absorptionMaxSt = "";
							}
							if (absorptionTextSt == null) {
								absorptionTextSt = "";
							}
						}
						String kineticsSt = "";
						Element kineticsElem = commentElem.getChild("kinetics");
						if (kineticsElem != null) {
							kineticsSt = new XMLElement(kineticsElem).toString();
						}
						String redoxPotentialSt = "";
						String redoxPotentialEvidenceSt = "";
						Element redoxPotentialElem = commentElem.getChild("redoxPotential");
						if (redoxPotentialElem != null) {
							redoxPotentialSt = redoxPotentialElem.getText();
							redoxPotentialEvidenceSt = redoxPotentialElem.getAttributeValue("evidence");
							if (redoxPotentialSt == null) {
								redoxPotentialSt = "";
							}
							if (redoxPotentialEvidenceSt == null) {
								redoxPotentialEvidenceSt = "";
							}
						}

						ProteinComment<I,RV,RVT,RE,RET> proteinComment = protein.addOutEdge(graph.ProteinComment(), comment);
						proteinComment.set(graph.ProteinComment().text, commentTextSt);
						proteinComment.set(graph.ProteinComment().status, commentStatusSt);
						proteinComment.set(graph.ProteinComment().evidence, commentEvidenceSt);
						proteinComment.set(graph.ProteinComment().temperatureDependence, temperatureDependenceSt);
						proteinComment.set(graph.ProteinComment().phDependence, phDependenceSt);
						proteinComment.set(graph.ProteinComment().kineticsXML, kineticsSt);
						proteinComment.set(graph.ProteinComment().absorptionMax, absorptionMaxSt);
						proteinComment.set(graph.ProteinComment().absorptionText, absorptionTextSt);
						proteinComment.set(graph.ProteinComment().redoxPotentialEvidence, redoxPotentialEvidenceSt);
						proteinComment.set(graph.ProteinComment().redoxPotential, redoxPotentialSt);
						edgeCounter.add(1);

						break;
					case COMMENT_TYPE_ALLERGEN:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_PATHWAY:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_INDUCTION:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_SUBCELLULAR_LOCATION:

						if (uniprotDataXML.getSubcellularLocations()) {
							List<Element> subcLocations = commentElem.getChildren(SUBCELLULAR_LOCATION_TAG_NAME);

							for (Element subcLocation : subcLocations) {

								List<Element> locations = subcLocation.getChildren(LOCATION_TAG_NAME);
								Element firstLocationElem = locations.get(0);

								String firstLocationSt = firstLocationElem.getTextTrim();
								Optional<SubcellularLocation<I,RV,RVT,RE,RET>> lastLocationOptional =  graph.subcellularLocationNameIndex().getVertex(firstLocationSt);
								vertexIndexCalls.add(1);

								if(lastLocationOptional.isPresent()){
									SubcellularLocation<I,RV,RVT,RE,RET> lastLocation = lastLocationOptional.get();

									for (int i = 1; i < locations.size(); i++) {

										String tempLocationSt = locations.get(i).getTextTrim();
										Optional<SubcellularLocation<I,RV,RVT,RE,RET>> tempLocationOptional =  graph.subcellularLocationNameIndex().getVertex(tempLocationSt);
										vertexIndexCalls.add(1);

										if(tempLocationOptional.isPresent()){
											SubcellularLocation<I,RV,RVT,RE,RET> tempLocation = tempLocationOptional.get();

											if(!subcellularLocationParentEdgesAlreadyCreated.contains(tempLocation.name())){
												subcellularLocationParentEdgesAlreadyCreated.add(tempLocation.name());
												try{
													tempLocation.subcellularLocationParent_out();
												}catch (NoSuchElementException e){
													tempLocation.addOutEdge(graph.SubcellularLocationParent(), lastLocation);
													edgeCounter.add(1);
												}
											}
											lastLocation = tempLocation;
										}else{
											break;
										}

									}

									Element lastLocationElem = locations.get(locations.size() - 1);
									String evidenceSt = lastLocationElem.getAttributeValue(EVIDENCE_ATTRIBUTE);
									String statusSt = lastLocationElem.getAttributeValue(STATUS_ATTRIBUTE);
									String topologyStatusSt = "";
									String topologySt = "";
									Element topologyElem = subcLocation.getChild("topology");
									if (topologyElem != null) {
										topologySt = topologyElem.getText();
										topologyStatusSt = topologyElem.getAttributeValue("status");
									}
									if (topologyStatusSt == null) {
										topologyStatusSt = "";
									}
									if (topologySt == null) {
										topologySt = "";
									}
									if (evidenceSt == null) {
										evidenceSt = "";
									}
									if (statusSt == null) {
										statusSt = "";
									}

									ProteinSubcellularLocation<I,RV,RVT,RE,RET> proteinSubcellularLocation = protein.addOutEdge(graph.ProteinSubcellularLocation(), lastLocation);
									proteinSubcellularLocation.set(graph.ProteinSubcellularLocation().evidence, evidenceSt);
									proteinSubcellularLocation.set(graph.ProteinSubcellularLocation().status, statusSt);
									proteinSubcellularLocation.set(graph.ProteinSubcellularLocation().topology, topologySt);
									proteinSubcellularLocation.set(graph.ProteinSubcellularLocation().topologyStatus, topologyStatusSt);
									edgeCounter.add(1);
								}
							}
						}
						break;
					case COMMENT_ALTERNATIVE_PRODUCTS_TYPE:

						if (uniprotDataXML.getIsoforms()) {
							List<Element> eventList = commentElem.getChildren("event");
							List<Element> isoformList = commentElem.getChildren("isoform");

							for (Element isoformElem : isoformList) {
								String isoformIdSt = isoformElem.getChildText("id");


								Optional<Isoform<I,RV,RVT,RE,RET>> isoformOptional = graph.isoformIdIndex().getVertex(isoformIdSt);
								vertexIndexCalls.add(1);

								if(isoformOptional.isPresent()){
									Isoform<I,RV,RVT,RE,RET> isoform = isoformOptional.get();
									protein.addOutEdge(graph.ProteinIsoform(), isoform);
									edgeCounter.add(1);

									for (Element eventElem : eventList) {

										String eventTypeSt = eventElem.getAttributeValue("type");

										Optional<AlternativeProduct<I,RV,RVT,RE,RET>> alternativeProductOptional = graph.alternativeProductNameIndex().getVertex(eventTypeSt);
										vertexIndexCalls.add(1);

										if(alternativeProductOptional.isPresent()){
											AlternativeProduct<I,RV,RVT,RE,RET> alternativeProduct = alternativeProductOptional.get();
											isoform.addOutEdge(graph.IsoformEventGenerator(), alternativeProduct);
											edgeCounter.add(1);
										}
									}
								}
							}
						}
						break;
					case COMMENT_SEQUENCE_CAUTION_TYPE:

						Element conflictElem = commentElem.getChild("conflict");
						if (conflictElem != null) {

							String conflictTypeSt = conflictElem.getAttributeValue("type");
							String resourceSt = "";
							String idSt = "";
							String versionSt = "";

							ArrayList<String> positionsList = new ArrayList<>();

							Element sequenceElem = conflictElem.getChild("sequence");
							if (sequenceElem != null) {
								resourceSt = sequenceElem.getAttributeValue("resource");
								if (resourceSt == null) {
									resourceSt = "";
								}
								idSt = sequenceElem.getAttributeValue("id");
								if (idSt == null) {
									idSt = "";
								}
								versionSt = sequenceElem.getAttributeValue("version");
								if (versionSt == null) {
									versionSt = "";
								}
							}

							Element locationElem = commentElem.getChild("location");
							if (locationElem != null) {
								Element positionElem = locationElem.getChild("position");
								if (positionElem != null) {
									String tempPos = positionElem.getAttributeValue("position");
									if (tempPos != null) {
										positionsList.add(tempPos);
									}
								}
							}

							Optional<SequenceCaution<I,RV,RVT,RE,RET>> sequenceCautionOptional =  graph.sequenceCautionNameIndex().getVertex(conflictTypeSt);
							vertexIndexCalls.add(1);

							if(sequenceCautionOptional.isPresent()){

								SequenceCaution<I,RV,RVT,RE,RET> sequenceCaution = sequenceCautionOptional.get();

								if (positionsList.size() > 0) {
									for (String tempPosition : positionsList) {
										ProteinSequenceCaution<I,RV,RVT,RE,RET> proteinSequenceCaution = protein.addOutEdge(graph.ProteinSequenceCaution(), sequenceCaution);
										proteinSequenceCaution.set(graph.ProteinSequenceCaution().evidence, commentEvidenceSt);
										proteinSequenceCaution.set(graph.ProteinSequenceCaution().status, commentStatusSt);
										proteinSequenceCaution.set(graph.ProteinSequenceCaution().text, commentTextSt);
										proteinSequenceCaution.set(graph.ProteinSequenceCaution().id, idSt);
										proteinSequenceCaution.set(graph.ProteinSequenceCaution().resource, resourceSt);
										proteinSequenceCaution.set(graph.ProteinSequenceCaution().version, versionSt);
										proteinSequenceCaution.set(graph.ProteinSequenceCaution().position, tempPosition);
										edgeCounter.add(1);
									}
								} else {
									ProteinSequenceCaution<I,RV,RVT,RE,RET> proteinSequenceCaution = protein.addOutEdge(graph.ProteinSequenceCaution(), sequenceCaution);
									proteinSequenceCaution.set(graph.ProteinSequenceCaution().evidence, commentEvidenceSt);
									proteinSequenceCaution.set(graph.ProteinSequenceCaution().status, commentStatusSt);
									proteinSequenceCaution.set(graph.ProteinSequenceCaution().text, commentTextSt);
									proteinSequenceCaution.set(graph.ProteinSequenceCaution().id, idSt);
									proteinSequenceCaution.set(graph.ProteinSequenceCaution().resource, resourceSt);
									proteinSequenceCaution.set(graph.ProteinSequenceCaution().version, versionSt);
									proteinSequenceCaution.set(graph.ProteinSequenceCaution().position, "");
									edgeCounter.add(1);
								}
							}
						}
						break;
					case COMMENT_TYPE_DEVELOPMENTAL_STAGE:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_MISCELLANEOUS:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_SIMILARITY:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_RNA_EDITING:

						List<Element> locationsList = commentElem.getChildren("location");

						for (Element tempLoc : locationsList) {
							String positionSt = tempLoc.getChild("position").getAttributeValue("position");

							proteinComment = protein.addOutEdge(graph.ProteinComment(), comment);
							proteinComment.set(graph.ProteinComment().text, commentTextSt);
							proteinComment.set(graph.ProteinComment().status, commentStatusSt);
							proteinComment.set(graph.ProteinComment().evidence, commentEvidenceSt);
							proteinComment.set(graph.ProteinComment().position, positionSt);
							edgeCounter.add(1);
						}
						break;
					case COMMENT_TYPE_PHARMACEUTICAL:
						createStandardProteinComment = true;
						break;
					case COMMENT_TYPE_MASS_SPECTROMETRY:
						String methodSt = commentElem.getAttributeValue("method");
						String massSt = commentElem.getAttributeValue("mass");
						if (methodSt == null) {
							methodSt = "";
						}
						if (massSt == null) {
							massSt = "";
						}


						locationsList = commentElem.getChildren("location");

						for (Element tempLoc : locationsList) {

							String positionSt = "";

							Element positionElem = tempLoc.getChild("position");

							if(positionElem != null){
								positionSt = positionElem.getAttributeValue("position");
								if(positionSt == null){
									positionSt = "";
								}
							}


							String beginSt = "";
							String endSt = "";

							if (tempLoc != null) {
								Element beginElem = tempLoc.getChild("begin");
								Element endElem = tempLoc.getChild("end");
								if (beginElem != null) {
									beginSt = beginElem.getAttributeValue("position");
								}

								if (endElem != null) {
									endSt = endElem.getAttributeValue("position");
								}
								if(endSt == null || endSt.isEmpty()){
									endSt = "-1";
								}
								if(beginSt == null || beginSt.isEmpty()){
									beginSt = "-1";
								}
							}

							proteinComment = protein.addOutEdge(graph.ProteinComment(), comment);
							proteinComment.set(graph.ProteinComment().text, commentTextSt);
							proteinComment.set(graph.ProteinComment().status, commentStatusSt);
							proteinComment.set(graph.ProteinComment().evidence, commentEvidenceSt);
							proteinComment.set(graph.ProteinComment().position, positionSt);
							proteinComment.set(graph.ProteinComment().end, Integer.parseInt(endSt));
							proteinComment.set(graph.ProteinComment().begin, Integer.parseInt(beginSt));
							proteinComment.set(graph.ProteinComment().method, methodSt);
							proteinComment.set(graph.ProteinComment().mass, massSt);
							edgeCounter.add(1);
						}
						break;
				}

				if(createStandardProteinComment){
					ProteinComment<I,RV,RVT,RE,RET> proteinComment = protein.addOutEdge(graph.ProteinComment(), comment);
					proteinComment.set(graph.ProteinComment().text, commentTextSt);
					proteinComment.set(graph.ProteinComment().status, commentStatusSt);
					proteinComment.set(graph.ProteinComment().evidence, commentEvidenceSt);
					edgeCounter.add(1);
				}

			}
		}
	}

	private void addPropertiesToProteinFeatureRelationship(UniProtGraph<I,RV,RVT,RE,RET> graph, ProteinFeature<I,RV,RVT,RE,RET> proteinFeature,
	                                                       String id, String description, String evidence, String status, int begin, int end,
	                                                       String original, String variation, String ref){

		proteinFeature.set(graph.ProteinFeature().description, description);
		proteinFeature.set(graph.ProteinFeature().id, id);
		proteinFeature.set(graph.ProteinFeature().evidence, evidence);
		proteinFeature.set(graph.ProteinFeature().status, status);
		proteinFeature.set(graph.ProteinFeature().begin, begin);
		proteinFeature.set(graph.ProteinFeature().end, end);
		proteinFeature.set(graph.ProteinFeature().original, original);
		proteinFeature.set(graph.ProteinFeature().variation, variation);
		proteinFeature.set(graph.ProteinFeature().ref, ref);

	}

	private static String getProteinFullName(Element proteinElement) {
		if (proteinElement == null) {
			return "";
		} else {
			Element recElem = proteinElement.getChild(PROTEIN_RECOMMENDED_NAME_TAG_NAME);
			if (recElem == null) {
				return "";
			} else {
				return recElem.getChildText(PROTEIN_FULL_NAME_TAG_NAME);
			}
		}
	}

	private static String getProteinShortName(Element proteinElement) {
		if (proteinElement == null) {
			return "";
		} else {
			Element recElem = proteinElement.getChild(PROTEIN_RECOMMENDED_NAME_TAG_NAME);
			if (recElem == null) {
				return "";
			} else {
				return recElem.getChildText(PROTEIN_SHORT_NAME_TAG_NAME);
			}
		}
	}


	private void importProteinCitations(XMLElement entryXMLElem,
	                                    UniProtGraph<I,RV,RVT,RE,RET> graph,
	                                    Protein<I,RV,RVT,RE,RET> protein,
	                                    UniprotDataXML uniprotDataXML,
	                                    MutableInt vertexIndexCalls,
	                                    MutableInt edgeCounter) {

		List<Element> referenceList = entryXMLElem.asJDomElement().getChildren(REFERENCE_TAG_NAME);

		for (Element referenceElement : referenceList) {
			List<Element> citationsList = referenceElement.getChildren(CITATION_TAG_NAME);
			for (Element citation : citationsList) {

				String citationType = citation.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE);

				List<Person<I,RV,RVT,RE,RET>> authorsPerson = new ArrayList<>();
				List<Consortium<I,RV,RVT,RE,RET>> authorsConsortium = new ArrayList<>();

				List<Element> authorPersonElems = citation.getChild("authorList").getChildren("person");
				List<Element> authorConsortiumElems = citation.getChild("authorList").getChildren("consortium");

				for (Element personElement : authorPersonElems) {

					String personName = personElement.getAttributeValue("name");
					Optional<Person<I,RV,RVT,RE,RET>> optionalPerson = graph.personNameIndex().getVertex(personName);
					vertexIndexCalls.add(1);
					if(optionalPerson.isPresent()){
						Person<I,RV,RVT,RE,RET> person = optionalPerson.get();
						authorsPerson.add(person);
					}
				}

				for (Element consortiumElement : authorConsortiumElems) {

					String consortiumName = consortiumElement.getAttributeValue("name");
					Optional<Consortium<I,RV,RVT,RE,RET>> optionalConsortium = graph.consortiumNameIndex().getVertex(consortiumName);
					vertexIndexCalls.add(1);
					if(optionalConsortium.isPresent()){
						Consortium<I,RV,RVT,RE,RET> consortium = optionalConsortium.get();
						authorsConsortium.add(consortium);
					}
				}
				//----------------------------------------------------------------------------
				//-----------------------------THESIS-----------------------------------------
				switch (citationType) {
					case THESIS_CITATION_TYPE:
						if (uniprotDataXML.getThesis()) {
							String titleSt = citation.getChildText("title");

							if (titleSt == null) {
								titleSt = "";
							}else{

								Optional<Thesis<I,RV,RVT,RE,RET>> optionalThesis = graph.thesisTitleIndex().getVertex(titleSt);
								Optional<Reference<I,RV,RVT,RE,RET>> optionalReference = graph.referenceIdIndex().getVertex((titleSt + graph.Thesis().name()));
								vertexIndexCalls.add(2);


								if(optionalReference.isPresent() && optionalThesis.isPresent()){

									Reference<I,RV,RVT,RE,RET> reference = optionalReference.get();
									Thesis<I,RV,RVT,RE,RET> thesis = optionalThesis.get();

									//-----------institute-----------------------------
									String instituteSt = citation.getAttributeValue("institute");
									String countrySt = citation.getAttributeValue("country");

									//---authors association-----
									for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
										reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
										edgeCounter.add(1);
									}

									if (instituteSt != null) {

										Optional<Institute<I,RV,RVT,RE,RET>> optionalInstitute = graph.instituteNameIndex().getVertex(instituteSt);
										vertexIndexCalls.add(1);

										if(optionalInstitute.isPresent()){
											thesis.addOutEdge(graph.ThesisInstitute(), optionalInstitute.get());
											edgeCounter.add(1);
											if (countrySt != null) {

												Optional<Country<I,RV,RVT,RE,RET>> optionalCountry = graph.countryNameIndex().getVertex(countrySt);
												vertexIndexCalls.add(1);
												if(!optionalCountry.isPresent()){
													optionalInstitute.get().addOutEdge(graph.InstituteCountry(), optionalCountry.get());
													edgeCounter.add(1);
												}
											}
										}
									}

									//--protein reference citation relationship
									protein.addOutEdge(graph.ProteinReference(), reference);
									edgeCounter.add(1);

								}
							}

						}

						//----------------------------------------------------------------------------
						//-----------------------------PATENT-----------------------------------------
						break;
					case PATENT_CITATION_TYPE:
						if (uniprotDataXML.getPatents()) {
							String numberSt = citation.getAttributeValue("number");
							String dateSt = citation.getAttributeValue("date");
							if (dateSt == null) {
								dateSt = "";
							}
							if (numberSt == null) {
								numberSt = "";
							}

							if (!numberSt.equals("")) {

								Optional<Reference<I, RV, RVT, RE, RET>> optionalReference = graph.referenceIdIndex().getVertex(numberSt + graph.Patent().name());
								vertexIndexCalls.add(1);

								if (optionalReference.isPresent()) {

									Reference<I, RV, RVT, RE, RET> reference = optionalReference.get();

									//---authors association-----
									for (Person<I, RV, RVT, RE, RET> person : authorsPerson) {
										reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
										edgeCounter.add(1);
									}

									//--protein citation relationship
									protein.addOutEdge(graph.ProteinReference(), reference);
									edgeCounter.add(1);

								}
							}
						}

						//----------------------------------------------------------------------------
						//-----------------------------SUBMISSION-----------------------------------------
						break;
					case SUBMISSION_CITATION_TYPE:
						if (uniprotDataXML.getSubmissions()) {
							String dateSt = citation.getAttributeValue("date");
							String titleSt = citation.getChildText("title");
							String dbSt = citation.getAttributeValue("db");
							if (dateSt == null) {
								dateSt = "";
							}
							if (titleSt != null) {

								Optional<Reference<I,RV,RVT,RE,RET>> optionalReference = graph.referenceIdIndex().getVertex(titleSt + graph.Submission().name());
								Optional<Submission<I,RV,RVT,RE,RET>> optionalSubmission = graph.submissionTitleIndex().getVertex(titleSt);
								vertexIndexCalls.add(2);

								if(optionalSubmission.isPresent() && optionalReference.isPresent()){

									Submission<I,RV,RVT,RE,RET> submission = optionalSubmission.get();
									Reference<I,RV,RVT,RE,RET> reference = optionalReference.get();

									//---authors association-----
									for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
										reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
										edgeCounter.add(1);
									}
									for(Consortium<I,RV,RVT,RE,RET> consortium : authorsConsortium){
										reference.addOutEdge(graph.ReferenceAuthorConsortium(), consortium);
										edgeCounter.add(1);
									}

									if (dbSt != null) {
										Optional<DB<I,RV,RVT,RE,RET>> optionalDB = graph.dbNameIndex().getVertex(dbSt);
										vertexIndexCalls.add(1);
										if(optionalDB.isPresent()){
											submission.addOutEdge(graph.SubmissionDB(), optionalDB.get());
											edgeCounter.add(1);
										}
									}

									reference.addOutEdge(graph.ReferenceSubmission(), submission);
									edgeCounter.add(1);

									//--protein citation relationship
									protein.addOutEdge(graph.ProteinReference(), reference);
									edgeCounter.add(1);

								}
							}
						}

						//----------------------------------------------------------------------------
						//-----------------------------BOOK-----------------------------------------
						break;
					case BOOK_CITATION_TYPE:
						if (uniprotDataXML.getBooks()) {
							String nameSt = citation.getAttributeValue("name");
							String dateSt = citation.getAttributeValue("date");
							String titleSt = citation.getChildText("title");
							String publisherSt = citation.getAttributeValue("publisher");
							String firstSt = citation.getAttributeValue("first");
							String lastSt = citation.getAttributeValue("last");
							String citySt = citation.getAttributeValue("city");
							String volumeSt = citation.getAttributeValue("volume");
							if (nameSt == null) {
								nameSt = "";
							}
							if (dateSt == null) {
								dateSt = "";
							}
							if (titleSt == null) {
								titleSt = "";
							}
							if (publisherSt == null) {
								publisherSt = "";
							}
							if (firstSt == null) {
								firstSt = "";
							}
							if (lastSt == null) {
								lastSt = "";
							}
							if (citySt == null) {
								citySt = "";
							}
							if (volumeSt == null) {
								volumeSt = "";
							}

							Optional<Book<I,RV,RVT,RE,RET>> optionalBook = graph.bookNameIndex().getVertex(nameSt);
							Optional<Reference<I,RV,RVT,RE,RET>> optionalReference = graph.referenceIdIndex().getVertex(nameSt + graph.Book().name());
							vertexIndexCalls.add(2);

							if(optionalBook.isPresent() && optionalReference.isPresent()){

								Book<I,RV,RVT,RE,RET> book = optionalBook.get();
								Reference<I,RV,RVT,RE,RET> reference = optionalReference.get();

								//---authors association-----
								for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
									reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
									edgeCounter.add(1);
								}

								//---editor association-----
								Element editorListElem = citation.getChild("editorList");
								if (editorListElem != null) {
									List<Element> editorsElems = editorListElem.getChildren("person");
									for (Element personElement : editorsElems) {

										String personName = personElement.getAttributeValue("name");
										Optional<Person<I,RV,RVT,RE,RET>> optionalPerson = graph.personNameIndex().getVertex(personName);
										vertexIndexCalls.add(1);

										if(optionalPerson.isPresent()){
											book.addOutEdge(graph.BookEditor(), optionalPerson.get());
											edgeCounter.add(1);
										}
									}
								}
								//----publisher--
								if (!publisherSt.equals("")) {

									Optional<Publisher<I,RV,RVT,RE,RET>> optionalPublisher = graph.publisherNameIndex().getVertex(publisherSt);
									vertexIndexCalls.add(1);
									if(optionalPublisher.isPresent()){
										book.addOutEdge(graph.BookPublisher(), optionalPublisher.get());
										edgeCounter.add(1);
									}
								}
								//-----city-----
								if (!citySt.equals("")) {

									Optional<City<I,RV,RVT,RE,RET>> optionalCity = graph.cityNameIndex().getVertex(citySt);
									vertexIndexCalls.add(1);

									if(optionalCity.isPresent()){
										book.addOutEdge(graph.BookCity(), optionalCity.get());
										edgeCounter.add(1);
									}
								}

								//--protein citation relationship
								protein.addOutEdge(graph.ProteinReference(), reference);
								edgeCounter.add(1);

//                          TODO see if these fields can somehow be included
//							bookProteinCitationProperties.put(BookProteinCitationRel.FIRST_PROPERTY, firstSt);
//							bookProteinCitationProperties.put(BookProteinCitationRel.LAST_PROPERTY, lastSt);
//							bookProteinCitationProperties.put(BookProteinCitationRel.VOLUME_PROPERTY, volumeSt);
//							bookProteinCitationProperties.put(BookProteinCitationRel.TITLE_PROPERTY, titleSt);

							}

						}

						//----------------------------------------------------------------------------
						//-----------------------------ONLINE ARTICLE-----------------------------------------
						break;
					case ONLINE_ARTICLE_CITATION_TYPE:
						if (uniprotDataXML.getOnlineArticles()) {
							String locatorSt = citation.getChildText("locator");
							String nameSt = citation.getAttributeValue("name");
							String titleSt = citation.getChildText("title");
							String dateSt = citation.getAttributeValue("date");

							if (titleSt == null) {
								titleSt = "";
							}
							if (nameSt == null) {
								nameSt = "";
							}
							if (locatorSt == null) {
								locatorSt = "";
							}
							if (dateSt == null) {
								dateSt = "";
							}

							if (!titleSt.equals("")) {

								Optional<OnlineArticle<I,RV,RVT,RE,RET>> optionalOnlineArticle = graph.onlineArticleTitleIndex().getVertex(titleSt);
								Optional<Reference<I,RV,RVT,RE,RET>> optionalReference = graph.referenceIdIndex().getVertex(titleSt + graph.OnlineArticle().name());
								vertexIndexCalls.add(2);

								if(optionalOnlineArticle.isPresent() && optionalReference.isPresent()){

									OnlineArticle<I,RV,RVT,RE,RET> onlineArticle = optionalOnlineArticle.get();
									Reference<I,RV,RVT,RE,RET> reference = optionalReference.get();

									//---authors association-----
									for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
										reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
										edgeCounter.add(1);
									}
									//---consortiums association----
									for(Consortium<I,RV,RVT,RE,RET> consortium : authorsConsortium){
										reference.addOutEdge(graph.ReferenceAuthorConsortium(), consortium);
										edgeCounter.add(1);
									}

									//------online journal-----------
									if (!nameSt.equals("")) {

										Optional<OnlineJournal<I,RV,RVT,RE,RET>> optionalOnlineJournal = graph.onlineJournalNameIndex().getVertex(nameSt);
										vertexIndexCalls.add(1);

										if(optionalOnlineJournal.isPresent()){
											OnlineArticleOnlineJournal<I,RV,RVT,RE,RET> onlineArticleOnlineJournal = onlineArticle.addOutEdge(graph.OnlineArticleOnlineJournal(), optionalOnlineJournal.get());
											onlineArticleOnlineJournal.set(graph.OnlineArticleOnlineJournal().locator, locatorSt);
											edgeCounter.add(1);
										}

									}
									//----------------------------

									//protein citation
									protein.addOutEdge(graph.ProteinReference(), reference);
									edgeCounter.add(1);

								}
							}
						}
						//----------------------------------------------------------------------------
						//-----------------------------ARTICLE-----------------------------------------
						break;
					case ARTICLE_CITATION_TYPE:

						if (uniprotDataXML.getArticles()) {

							String journalNameSt = citation.getAttributeValue("name");
							String dateSt = citation.getAttributeValue("date");
							String titleSt = citation.getChildText("title");
							String firstSt = citation.getAttributeValue("first");
							String lastSt = citation.getAttributeValue("last");
							String volumeSt = citation.getAttributeValue("volume");

							if (journalNameSt == null) {
								journalNameSt = "";
							}
							if (dateSt == null) {
								dateSt = "";
							}
							if (firstSt == null) {
								firstSt = "";
							}
							if (lastSt == null) {
								lastSt = "";
							}
							if (volumeSt == null) {
								volumeSt = "";
							}
							if (titleSt == null) {
								titleSt = "";
							}


							if (titleSt != "") {

								Optional<Article<I,RV,RVT,RE,RET>> optionalArticle = graph.articleTitleIndex().getVertex(titleSt);
								Optional<Reference<I,RV,RVT,RE,RET>> optionalReference = graph.referenceIdIndex().getVertex(titleSt + graph.Article().name());
								vertexIndexCalls.add(2);

								if(optionalArticle.isPresent() && optionalReference.isPresent()){

									Article<I,RV,RVT,RE,RET> article = optionalArticle.get();
									Reference<I,RV,RVT,RE,RET> reference = optionalReference.get();

									String pubmedId = "";
									List<Element> dbReferences = citation.getChildren("dbReference");
									for (Element tempDbRef : dbReferences) {
										if(tempDbRef.getAttributeValue("type").equals("PubMed")) {
											pubmedId = tempDbRef.getAttributeValue("id");
										}
									}

									if(pubmedId != ""){
										Optional<Pubmed<I,RV,RVT,RE,RET>> optionalPubmed = graph.pubmedIdIndex().getVertex(pubmedId);
										vertexIndexCalls.add(1);
										if(optionalPubmed.isPresent()){
											article.addOutEdge(graph.ArticlePubmed(), optionalPubmed.get());
											edgeCounter.add(1);
										}
									}

									//---authors association-----
									for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
										reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
										edgeCounter.add(1);
									}
									//---consortiums association----
									for(Consortium<I,RV,RVT,RE,RET> consortium : authorsConsortium){
										reference.addOutEdge(graph.ReferenceAuthorConsortium(), consortium);
										edgeCounter.add(1);
									}

									//------journal-----------
									if (!journalNameSt.equals("")) {
										Optional<Journal<I,RV,RVT,RE,RET>> optionalJournal = graph.journalNameIndex().getVertex(journalNameSt);
										vertexIndexCalls.add(1);
										if(optionalJournal.isPresent()){
											ArticleJournal<I,RV,RVT,RE,RET> articleJournal = article.addOutEdge(graph.ArticleJournal(), optionalJournal.get());
											articleJournal.set(graph.ArticleJournal().volume, volumeSt);
											articleJournal.set(graph.ArticleJournal().first, firstSt);
											articleJournal.set(graph.ArticleJournal().last, lastSt);
											edgeCounter.add(1);
										}
									}
									//----------------------------

									//protein citation
									protein.addOutEdge(graph.ProteinReference(), reference);
									edgeCounter.add(1);
								}
							}
						}
						//----------------------------------------------------------------------------
						//----------------------UNPUBLISHED OBSERVATIONS-----------------------------------------
						break;
					case UNPUBLISHED_OBSERVATION_CITATION_TYPE:
						if (uniprotDataXML.getUnpublishedObservations()) {

							String dateSt = citation.getAttributeValue("date");
							String scopeSt = referenceElement.getChildText("scope");
							if (dateSt == null) {
								dateSt = "";
							}
							if (scopeSt == null){
								scopeSt = "";
							}


							UnpublishedObservation<I,RV,RVT,RE,RET> unpublishedObservation = graph.addVertex(graph.UnpublishedObservation());
							unpublishedObservation.set(graph.UnpublishedObservation().scope, scopeSt);

							Reference<I,RV,RVT,RE,RET> reference = graph.addVertex(graph.Reference());
							reference.set(graph.Reference().date, dateSt);
							reference.addOutEdge(graph.ReferenceUnpublishedObservation(), unpublishedObservation);
							edgeCounter.add(1);

							//---authors association-----
							for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
								reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
								edgeCounter.add(1);
							}

							//protein citation
							protein.addOutEdge(graph.ProteinReference(), reference);
							edgeCounter.add(1);

						}
						break;
				}
			}
		}


	}

	protected Date parseDate(String date) throws ParseException {
		return dateFormat.parse(date);
	}
}

```


------

### Index

+ src
  + main
    + java
      + com
        + bio4j
          + model
            + uniref
              + vertices
                + [UniRef100Cluster.java][main/java/com/bio4j/model/uniref/vertices/UniRef100Cluster.java]
                + [UniRef90Cluster.java][main/java/com/bio4j/model/uniref/vertices/UniRef90Cluster.java]
                + [UniRef50Cluster.java][main/java/com/bio4j/model/uniref/vertices/UniRef50Cluster.java]
              + [UniRefGraph.java][main/java/com/bio4j/model/uniref/UniRefGraph.java]
              + programs
                + [ImportUniRef.java][main/java/com/bio4j/model/uniref/programs/ImportUniRef.java]
            + uniprot_uniref
              + [UniProtUniRefGraph.java][main/java/com/bio4j/model/uniprot_uniref/UniProtUniRefGraph.java]
              + edges
                + [UniRef100Member.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]
                + [UniRef50Member.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]
                + [UniRef100Representant.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]
                + [UniRef90Representant.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]
                + [UniRef50Representant.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]
                + [UniRef90Member.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]
              + programs
                + [ImportUniProtUniRef.java][main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniProtUniRef.java]
            + uniprot_enzymedb
              + edges
                + [EnzymaticActivity.java][main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]
              + [UniProtEnzymeDBGraph.java][main/java/com/bio4j/model/uniprot_enzymedb/UniProtEnzymeDBGraph.java]
              + programs
                + [ImportUniProtEnzymeDB.java][main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java]
            + enzymedb
              + [EnzymeDBGraph.java][main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]
              + vertices
                + [Enzyme.java][main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]
              + programs
                + [ImportEnzymeDB.java][main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]
            + ncbiTaxonomy
              + [NCBITaxonomyGraph.java][main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]
              + edges
                + [NCBITaxonParent.java][main/java/com/bio4j/model/ncbiTaxonomy/edges/NCBITaxonParent.java]
              + vertices
                + [NCBITaxon.java][main/java/com/bio4j/model/ncbiTaxonomy/vertices/NCBITaxon.java]
              + programs
                + [ImportNCBITaxonomy.java][main/java/com/bio4j/model/ncbiTaxonomy/programs/ImportNCBITaxonomy.java]
            + go
              + edges
                + goSlims
                  + [GoSlim.java][main/java/com/bio4j/model/go/edges/goSlims/GoSlim.java]
                  + [PlantSlim.java][main/java/com/bio4j/model/go/edges/goSlims/PlantSlim.java]
                + [PositivelyRegulates.java][main/java/com/bio4j/model/go/edges/PositivelyRegulates.java]
                + [HasPartOf.java][main/java/com/bio4j/model/go/edges/HasPartOf.java]
                + [Regulates.java][main/java/com/bio4j/model/go/edges/Regulates.java]
                + [PartOf.java][main/java/com/bio4j/model/go/edges/PartOf.java]
                + [IsA.java][main/java/com/bio4j/model/go/edges/IsA.java]
                + [NegativelyRegulates.java][main/java/com/bio4j/model/go/edges/NegativelyRegulates.java]
                + [SubOntology.java][main/java/com/bio4j/model/go/edges/SubOntology.java]
              + [GoGraph.java][main/java/com/bio4j/model/go/GoGraph.java]
              + vertices
                + [SubOntologies.java][main/java/com/bio4j/model/go/vertices/SubOntologies.java]
                + [GoTerm.java][main/java/com/bio4j/model/go/vertices/GoTerm.java]
                + [GoSlims.java][main/java/com/bio4j/model/go/vertices/GoSlims.java]
              + programs
                + [ImportGO.java][main/java/com/bio4j/model/go/programs/ImportGO.java]
            + uniprot
              + [UniProtGraph.java][main/java/com/bio4j/model/uniprot/UniProtGraph.java]
              + edges
                + [BookCity.java][main/java/com/bio4j/model/uniprot/edges/BookCity.java]
                + [ProteinReference.java][main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]
                + [ProteinIsoform.java][main/java/com/bio4j/model/uniprot/edges/ProteinIsoform.java]
                + [ProteinFeature.java][main/java/com/bio4j/model/uniprot/edges/ProteinFeature.java]
                + [ProteinKeyword.java][main/java/com/bio4j/model/uniprot/edges/ProteinKeyword.java]
                + [ProteinInterPro.java][main/java/com/bio4j/model/uniprot/edges/ProteinInterPro.java]
                + [ReferenceThesis.java][main/java/com/bio4j/model/uniprot/edges/ReferenceThesis.java]
                + [ArticlePubmed.java][main/java/com/bio4j/model/uniprot/edges/ArticlePubmed.java]
                + [ReferenceAuthorConsortium.java][main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorConsortium.java]
                + [ProteinDataset.java][main/java/com/bio4j/model/uniprot/edges/ProteinDataset.java]
                + [ReferencePatent.java][main/java/com/bio4j/model/uniprot/edges/ReferencePatent.java]
                + [TaxonParent.java][main/java/com/bio4j/model/uniprot/edges/TaxonParent.java]
                + [ProteinReactomeTerm.java][main/java/com/bio4j/model/uniprot/edges/ProteinReactomeTerm.java]
                + [ProteinDisease.java][main/java/com/bio4j/model/uniprot/edges/ProteinDisease.java]
                + [BookPublisher.java][main/java/com/bio4j/model/uniprot/edges/BookPublisher.java]
                + [InstituteCountry.java][main/java/com/bio4j/model/uniprot/edges/InstituteCountry.java]
                + [ReferenceOnlineArticle.java][main/java/com/bio4j/model/uniprot/edges/ReferenceOnlineArticle.java]
                + [BookEditor.java][main/java/com/bio4j/model/uniprot/edges/BookEditor.java]
                + [ProteinOrganism.java][main/java/com/bio4j/model/uniprot/edges/ProteinOrganism.java]
                + [ReferenceSubmission.java][main/java/com/bio4j/model/uniprot/edges/ReferenceSubmission.java]
                + [ProteinPfam.java][main/java/com/bio4j/model/uniprot/edges/ProteinPfam.java]
                + [ProteinEnsembl.java][main/java/com/bio4j/model/uniprot/edges/ProteinEnsembl.java]
                + [SubcellularLocationParent.java][main/java/com/bio4j/model/uniprot/edges/SubcellularLocationParent.java]
                + [ProteinGeneName.java][main/java/com/bio4j/model/uniprot/edges/ProteinGeneName.java]
                + [ProteinComment.java][main/java/com/bio4j/model/uniprot/edges/ProteinComment.java]
                + [ArticleJournal.java][main/java/com/bio4j/model/uniprot/edges/ArticleJournal.java]
                + [ProteinPIR.java][main/java/com/bio4j/model/uniprot/edges/ProteinPIR.java]
                + [SubmissionDB.java][main/java/com/bio4j/model/uniprot/edges/SubmissionDB.java]
                + [OnlineArticleOnlineJournal.java][main/java/com/bio4j/model/uniprot/edges/OnlineArticleOnlineJournal.java]
                + [OrganismTaxon.java][main/java/com/bio4j/model/uniprot/edges/OrganismTaxon.java]
                + [ThesisInstitute.java][main/java/com/bio4j/model/uniprot/edges/ThesisInstitute.java]
                + [ProteinUniGene.java][main/java/com/bio4j/model/uniprot/edges/ProteinUniGene.java]
                + [ProteinKegg.java][main/java/com/bio4j/model/uniprot/edges/ProteinKegg.java]
                + [ProteinProteinInteraction.java][main/java/com/bio4j/model/uniprot/edges/ProteinProteinInteraction.java]
                + [ProteinRefSeq.java][main/java/com/bio4j/model/uniprot/edges/ProteinRefSeq.java]
                + [ProteinSubcellularLocation.java][main/java/com/bio4j/model/uniprot/edges/ProteinSubcellularLocation.java]
                + [ReferenceUnpublishedObservation.java][main/java/com/bio4j/model/uniprot/edges/ReferenceUnpublishedObservation.java]
                + [ReferenceBook.java][main/java/com/bio4j/model/uniprot/edges/ReferenceBook.java]
                + [IsoformEventGenerator.java][main/java/com/bio4j/model/uniprot/edges/IsoformEventGenerator.java]
                + [IsoformProteinInteraction.java][main/java/com/bio4j/model/uniprot/edges/IsoformProteinInteraction.java]
                + [ProteinIsoformInteraction.java][main/java/com/bio4j/model/uniprot/edges/ProteinIsoformInteraction.java]
                + [ReferenceArticle.java][main/java/com/bio4j/model/uniprot/edges/ReferenceArticle.java]
                + [ProteinEMBL.java][main/java/com/bio4j/model/uniprot/edges/ProteinEMBL.java]
                + [ProteinSequenceCaution.java][main/java/com/bio4j/model/uniprot/edges/ProteinSequenceCaution.java]
                + [ReferenceAuthorPerson.java][main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorPerson.java]
                + [ProteinGeneLocation.java][main/java/com/bio4j/model/uniprot/edges/ProteinGeneLocation.java]
              + vertices
                + [Article.java][main/java/com/bio4j/model/uniprot/vertices/Article.java]
                + [Taxon.java][main/java/com/bio4j/model/uniprot/vertices/Taxon.java]
                + [Publisher.java][main/java/com/bio4j/model/uniprot/vertices/Publisher.java]
                + [Book.java][main/java/com/bio4j/model/uniprot/vertices/Book.java]
                + [OnlineArticle.java][main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]
                + [GeneLocation.java][main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]
                + [Person.java][main/java/com/bio4j/model/uniprot/vertices/Person.java]
                + [SubcellularLocation.java][main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]
                + [FeatureType.java][main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]
                + [PIR.java][main/java/com/bio4j/model/uniprot/vertices/PIR.java]
                + [InterPro.java][main/java/com/bio4j/model/uniprot/vertices/InterPro.java]
                + [ReactomeTerm.java][main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]
                + [Thesis.java][main/java/com/bio4j/model/uniprot/vertices/Thesis.java]
                + [Ensembl.java][main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]
                + [Keyword.java][main/java/com/bio4j/model/uniprot/vertices/Keyword.java]
                + [Protein.java][main/java/com/bio4j/model/uniprot/vertices/Protein.java]
                + [Submission.java][main/java/com/bio4j/model/uniprot/vertices/Submission.java]
                + [CommentType.java][main/java/com/bio4j/model/uniprot/vertices/CommentType.java]
                + [Pubmed.java][main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]
                + [Reference.java][main/java/com/bio4j/model/uniprot/vertices/Reference.java]
                + [UniGene.java][main/java/com/bio4j/model/uniprot/vertices/UniGene.java]
                + [SequenceCaution.java][main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]
                + [GeneName.java][main/java/com/bio4j/model/uniprot/vertices/GeneName.java]
                + [EMBL.java][main/java/com/bio4j/model/uniprot/vertices/EMBL.java]
                + [DB.java][main/java/com/bio4j/model/uniprot/vertices/DB.java]
                + [City.java][main/java/com/bio4j/model/uniprot/vertices/City.java]
                + [AlternativeProduct.java][main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]
                + [Institute.java][main/java/com/bio4j/model/uniprot/vertices/Institute.java]
                + [Isoform.java][main/java/com/bio4j/model/uniprot/vertices/Isoform.java]
                + [RefSeq.java][main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]
                + [Kegg.java][main/java/com/bio4j/model/uniprot/vertices/Kegg.java]
                + [Consortium.java][main/java/com/bio4j/model/uniprot/vertices/Consortium.java]
                + [Pfam.java][main/java/com/bio4j/model/uniprot/vertices/Pfam.java]
                + [Disease.java][main/java/com/bio4j/model/uniprot/vertices/Disease.java]
                + [OnlineJournal.java][main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]
                + [Patent.java][main/java/com/bio4j/model/uniprot/vertices/Patent.java]
                + [UnpublishedObservation.java][main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]
                + [Organism.java][main/java/com/bio4j/model/uniprot/vertices/Organism.java]
                + [Dataset.java][main/java/com/bio4j/model/uniprot/vertices/Dataset.java]
                + [Journal.java][main/java/com/bio4j/model/uniprot/vertices/Journal.java]
                + [Country.java][main/java/com/bio4j/model/uniprot/vertices/Country.java]
              + programs
                + [ImportUniProtEdges.java][main/java/com/bio4j/model/uniprot/programs/ImportUniProtEdges.java]
                + [ImportUniProtVertices.java][main/java/com/bio4j/model/uniprot/programs/ImportUniProtVertices.java]
                + [ImportUniProt.java][main/java/com/bio4j/model/uniprot/programs/ImportUniProt.java]
                + [ImportIsoformSequences.java][main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]
                + [ImportProteinInteractions.java][main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]
            + uniprot_ncbiTaxonomy
              + edges
                + [ProteinNCBITaxon.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]
              + [UniProtNCBITaxonomyGraph.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]
              + programs
                + [ImportUniProtNCBITaxonomy.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java]
            + geninfo
              + vertices
                + [GenInfo.java][main/java/com/bio4j/model/geninfo/vertices/GenInfo.java]
              + [GenInfoGraph.java][main/java/com/bio4j/model/geninfo/GenInfoGraph.java]
            + ncbiTaxonomy_geninfo
              + [NCBITaxonomyGenInfoGraph.java][main/java/com/bio4j/model/ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java]
              + edges
                + [GenInfoNCBITaxon.java][main/java/com/bio4j/model/ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java]
              + programs
                + [ImportGenInfoNCBITaxonIndex.java][main/java/com/bio4j/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java]
            + uniprot_go
              + edges
                + [GoAnnotation.java][main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]
              + tests
                + [ImportUniProtGoTest.java][main/java/com/bio4j/model/uniprot_go/tests/ImportUniProtGoTest.java]
              + programs
                + [ImportUniProtGo.java][main/java/com/bio4j/model/uniprot_go/programs/ImportUniProtGo.java]
              + [UniProtGoGraph.java][main/java/com/bio4j/model/uniprot_go/UniProtGoGraph.java]

[main/java/com/bio4j/model/uniref/vertices/UniRef100Cluster.java]: ../../uniref/vertices/UniRef100Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef90Cluster.java]: ../../uniref/vertices/UniRef90Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef50Cluster.java]: ../../uniref/vertices/UniRef50Cluster.java.md
[main/java/com/bio4j/model/uniref/UniRefGraph.java]: ../../uniref/UniRefGraph.java.md
[main/java/com/bio4j/model/uniref/programs/ImportUniRef.java]: ../../uniref/programs/ImportUniRef.java.md
[main/java/com/bio4j/model/uniprot_uniref/UniProtUniRefGraph.java]: ../../uniprot_uniref/UniProtUniRefGraph.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]: ../../uniprot_uniref/edges/UniRef100Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]: ../../uniprot_uniref/edges/UniRef50Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]: ../../uniprot_uniref/edges/UniRef100Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]: ../../uniprot_uniref/edges/UniRef90Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]: ../../uniprot_uniref/edges/UniRef50Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]: ../../uniprot_uniref/edges/UniRef90Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniProtUniRef.java]: ../../uniprot_uniref/programs/ImportUniProtUniRef.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]: ../../uniprot_enzymedb/edges/EnzymaticActivity.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/UniProtEnzymeDBGraph.java]: ../../uniprot_enzymedb/UniProtEnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java]: ../../uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java.md
[main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]: ../../enzymedb/EnzymeDBGraph.java.md
[main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]: ../../enzymedb/vertices/Enzyme.java.md
[main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]: ../../enzymedb/programs/ImportEnzymeDB.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]: ../../ncbiTaxonomy/NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/edges/NCBITaxonParent.java]: ../../ncbiTaxonomy/edges/NCBITaxonParent.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/vertices/NCBITaxon.java]: ../../ncbiTaxonomy/vertices/NCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/programs/ImportNCBITaxonomy.java]: ../../ncbiTaxonomy/programs/ImportNCBITaxonomy.java.md
[main/java/com/bio4j/model/go/edges/goSlims/GoSlim.java]: ../../go/edges/goSlims/GoSlim.java.md
[main/java/com/bio4j/model/go/edges/goSlims/PlantSlim.java]: ../../go/edges/goSlims/PlantSlim.java.md
[main/java/com/bio4j/model/go/edges/PositivelyRegulates.java]: ../../go/edges/PositivelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/HasPartOf.java]: ../../go/edges/HasPartOf.java.md
[main/java/com/bio4j/model/go/edges/Regulates.java]: ../../go/edges/Regulates.java.md
[main/java/com/bio4j/model/go/edges/PartOf.java]: ../../go/edges/PartOf.java.md
[main/java/com/bio4j/model/go/edges/IsA.java]: ../../go/edges/IsA.java.md
[main/java/com/bio4j/model/go/edges/NegativelyRegulates.java]: ../../go/edges/NegativelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/SubOntology.java]: ../../go/edges/SubOntology.java.md
[main/java/com/bio4j/model/go/GoGraph.java]: ../../go/GoGraph.java.md
[main/java/com/bio4j/model/go/vertices/SubOntologies.java]: ../../go/vertices/SubOntologies.java.md
[main/java/com/bio4j/model/go/vertices/GoTerm.java]: ../../go/vertices/GoTerm.java.md
[main/java/com/bio4j/model/go/vertices/GoSlims.java]: ../../go/vertices/GoSlims.java.md
[main/java/com/bio4j/model/go/programs/ImportGO.java]: ../../go/programs/ImportGO.java.md
[main/java/com/bio4j/model/uniprot/UniProtGraph.java]: ../UniProtGraph.java.md
[main/java/com/bio4j/model/uniprot/edges/BookCity.java]: ../edges/BookCity.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]: ../edges/ProteinReference.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoform.java]: ../edges/ProteinIsoform.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinFeature.java]: ../edges/ProteinFeature.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKeyword.java]: ../edges/ProteinKeyword.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinInterPro.java]: ../edges/ProteinInterPro.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceThesis.java]: ../edges/ReferenceThesis.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticlePubmed.java]: ../edges/ArticlePubmed.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorConsortium.java]: ../edges/ReferenceAuthorConsortium.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDataset.java]: ../edges/ProteinDataset.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferencePatent.java]: ../edges/ReferencePatent.java.md
[main/java/com/bio4j/model/uniprot/edges/TaxonParent.java]: ../edges/TaxonParent.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReactomeTerm.java]: ../edges/ProteinReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDisease.java]: ../edges/ProteinDisease.java.md
[main/java/com/bio4j/model/uniprot/edges/BookPublisher.java]: ../edges/BookPublisher.java.md
[main/java/com/bio4j/model/uniprot/edges/InstituteCountry.java]: ../edges/InstituteCountry.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceOnlineArticle.java]: ../edges/ReferenceOnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/BookEditor.java]: ../edges/BookEditor.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinOrganism.java]: ../edges/ProteinOrganism.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceSubmission.java]: ../edges/ReferenceSubmission.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPfam.java]: ../edges/ProteinPfam.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEnsembl.java]: ../edges/ProteinEnsembl.java.md
[main/java/com/bio4j/model/uniprot/edges/SubcellularLocationParent.java]: ../edges/SubcellularLocationParent.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneName.java]: ../edges/ProteinGeneName.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinComment.java]: ../edges/ProteinComment.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticleJournal.java]: ../edges/ArticleJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPIR.java]: ../edges/ProteinPIR.java.md
[main/java/com/bio4j/model/uniprot/edges/SubmissionDB.java]: ../edges/SubmissionDB.java.md
[main/java/com/bio4j/model/uniprot/edges/OnlineArticleOnlineJournal.java]: ../edges/OnlineArticleOnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/OrganismTaxon.java]: ../edges/OrganismTaxon.java.md
[main/java/com/bio4j/model/uniprot/edges/ThesisInstitute.java]: ../edges/ThesisInstitute.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinUniGene.java]: ../edges/ProteinUniGene.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKegg.java]: ../edges/ProteinKegg.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinProteinInteraction.java]: ../edges/ProteinProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinRefSeq.java]: ../edges/ProteinRefSeq.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSubcellularLocation.java]: ../edges/ProteinSubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceUnpublishedObservation.java]: ../edges/ReferenceUnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceBook.java]: ../edges/ReferenceBook.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformEventGenerator.java]: ../edges/IsoformEventGenerator.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformProteinInteraction.java]: ../edges/IsoformProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoformInteraction.java]: ../edges/ProteinIsoformInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceArticle.java]: ../edges/ReferenceArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEMBL.java]: ../edges/ProteinEMBL.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSequenceCaution.java]: ../edges/ProteinSequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorPerson.java]: ../edges/ReferenceAuthorPerson.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneLocation.java]: ../edges/ProteinGeneLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Article.java]: ../vertices/Article.java.md
[main/java/com/bio4j/model/uniprot/vertices/Taxon.java]: ../vertices/Taxon.java.md
[main/java/com/bio4j/model/uniprot/vertices/Publisher.java]: ../vertices/Publisher.java.md
[main/java/com/bio4j/model/uniprot/vertices/Book.java]: ../vertices/Book.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]: ../vertices/OnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]: ../vertices/GeneLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Person.java]: ../vertices/Person.java.md
[main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]: ../vertices/SubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]: ../vertices/FeatureType.java.md
[main/java/com/bio4j/model/uniprot/vertices/PIR.java]: ../vertices/PIR.java.md
[main/java/com/bio4j/model/uniprot/vertices/InterPro.java]: ../vertices/InterPro.java.md
[main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]: ../vertices/ReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/vertices/Thesis.java]: ../vertices/Thesis.java.md
[main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]: ../vertices/Ensembl.java.md
[main/java/com/bio4j/model/uniprot/vertices/Keyword.java]: ../vertices/Keyword.java.md
[main/java/com/bio4j/model/uniprot/vertices/Protein.java]: ../vertices/Protein.java.md
[main/java/com/bio4j/model/uniprot/vertices/Submission.java]: ../vertices/Submission.java.md
[main/java/com/bio4j/model/uniprot/vertices/CommentType.java]: ../vertices/CommentType.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]: ../vertices/Pubmed.java.md
[main/java/com/bio4j/model/uniprot/vertices/Reference.java]: ../vertices/Reference.java.md
[main/java/com/bio4j/model/uniprot/vertices/UniGene.java]: ../vertices/UniGene.java.md
[main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]: ../vertices/SequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneName.java]: ../vertices/GeneName.java.md
[main/java/com/bio4j/model/uniprot/vertices/EMBL.java]: ../vertices/EMBL.java.md
[main/java/com/bio4j/model/uniprot/vertices/DB.java]: ../vertices/DB.java.md
[main/java/com/bio4j/model/uniprot/vertices/City.java]: ../vertices/City.java.md
[main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]: ../vertices/AlternativeProduct.java.md
[main/java/com/bio4j/model/uniprot/vertices/Institute.java]: ../vertices/Institute.java.md
[main/java/com/bio4j/model/uniprot/vertices/Isoform.java]: ../vertices/Isoform.java.md
[main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]: ../vertices/RefSeq.java.md
[main/java/com/bio4j/model/uniprot/vertices/Kegg.java]: ../vertices/Kegg.java.md
[main/java/com/bio4j/model/uniprot/vertices/Consortium.java]: ../vertices/Consortium.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pfam.java]: ../vertices/Pfam.java.md
[main/java/com/bio4j/model/uniprot/vertices/Disease.java]: ../vertices/Disease.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]: ../vertices/OnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Patent.java]: ../vertices/Patent.java.md
[main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]: ../vertices/UnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Organism.java]: ../vertices/Organism.java.md
[main/java/com/bio4j/model/uniprot/vertices/Dataset.java]: ../vertices/Dataset.java.md
[main/java/com/bio4j/model/uniprot/vertices/Journal.java]: ../vertices/Journal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Country.java]: ../vertices/Country.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtEdges.java]: ImportUniProtEdges.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtVertices.java]: ImportUniProtVertices.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProt.java]: ImportUniProt.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]: ImportIsoformSequences.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]: ImportProteinInteractions.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]: ../../uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]: ../../uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java]: ../../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java.md
[main/java/com/bio4j/model/geninfo/vertices/GenInfo.java]: ../../geninfo/vertices/GenInfo.java.md
[main/java/com/bio4j/model/geninfo/GenInfoGraph.java]: ../../geninfo/GenInfoGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java]: ../../ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java]: ../../ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java]: ../../ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java.md
[main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]: ../../uniprot_go/edges/GoAnnotation.java.md
[main/java/com/bio4j/model/uniprot_go/tests/ImportUniProtGoTest.java]: ../../uniprot_go/tests/ImportUniProtGoTest.java.md
[main/java/com/bio4j/model/uniprot_go/programs/ImportUniProtGo.java]: ../../uniprot_go/programs/ImportUniProtGo.java.md
[main/java/com/bio4j/model/uniprot_go/UniProtGoGraph.java]: ../../uniprot_go/UniProtGoGraph.java.md