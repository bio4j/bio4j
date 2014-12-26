
```java
package com.bio4j.model.uniprot.programs;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.*;
import com.bio4j.model.uniprot.edges.*;
import com.bio4j.angulillos.UntypedGraph;
import com.ohnosequences.xml.api.model.XMLElement;
import com.ohnosequences.xml.model.bio4j.UniprotDataXML;
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
public abstract class ImportUniProtVertices<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

	private static final Logger logger = Logger.getLogger("ImportUniProtVertices");
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
	public static final String EVIDENCE_ATTRIBUTE = "evidence";

	public static final String THESIS_CITATION_TYPE = "thesis";
	public static final String PATENT_CITATION_TYPE = "patent";
	public static final String SUBMISSION_CITATION_TYPE = "submission";
	public static final String ARTICLE_CITATION_TYPE = "journal article";
	public static final String ONLINE_ARTICLE_CITATION_TYPE = "online journal article";
	public static final String BOOK_CITATION_TYPE = "book";

	public static final String COMMENT_TYPE_DISEASE = "disease";
	public static final String COMMENT_TYPE_SUBCELLULAR_LOCATION = "subcellular location";

	protected SimpleDateFormat dateFormat;

	//---------------Sets where IDs already stored in the DB are temporarily saved-----------------
	HashSet<String> alternativeProductTypeNameSet;
	HashSet<String> articleTitleNameSet;
	HashSet<String> bookNameSet;
	HashSet<String> cityNameSet;
	HashSet<String> commentTypeNameSet;
	HashSet<String> consortiumNameSet;
	HashSet<String> countryNameSet;
	HashSet<String> datasetNameSet;
	HashSet<String> dbNameSet;
	HashSet<String> diseaseIdSet;
	HashSet<String> eMBLIdSet;
	HashSet<String> ensemblIdSet;
	HashSet<String> featureTypeNameSet;
	HashSet<String> geneLocationNameSet;
	HashSet<String> geneNameSet;
	HashSet<String> instituteNameSet;
	HashSet<String> interproIdSet;
	HashSet<String> isoformIdSet;
	HashSet<String> journalNameSet;
	HashSet<String> keggIdSet;
	HashSet<String> keywordIdSet;
	HashSet<String> onlineArticleTitleSet;
	HashSet<String> onlineJournalNameSet;
	HashSet<String> organismScientificNameSet;
	HashSet<String> patentNumberSet;
	HashSet<String> personNameSet;
	HashSet<String> pfamIdSet;
	HashSet<String> pIRIdSet;
	HashSet<String> publisherNameSet;
	HashSet<String> pubmedIdSet;
	HashSet<String> reactomeTermIdSet;
	HashSet<String> refSeqIdSet;
	HashSet<String> sequenceCautionNameSet;
	HashSet<String> subcellularLocationNameSet;
	HashSet<String> submissionTitleSet;
	HashSet<String> taxonNameSet;
	HashSet<String> thesisTitleSet;
	HashSet<String> uniGeneIdSet;

	protected abstract UniProtGraph<I,RV,RVT,RE,RET> config(String dbFolder);

	protected void importUniProtVertices(String[] args) {

		if (args.length != 3) {
			System.out.println("This program expects the following parameters: \n"
					+ "1. UniProt xml filename \n"
					+ "2. Bio4j DB folder \n"
					+ "3. Config XML file");
		} else {

			long initTime = System.nanoTime();

			File inFile = new File(args[0]);
			File configFile = new File(args[2]);
			String dbFolder = args[1];

			String currentAccessionId = "";

			//-------creating graph handlers---------------------
			UniProtGraph<I,RV,RVT,RE,RET> graph = config(dbFolder);

			//------Initializing hash sets---------------
			alternativeProductTypeNameSet = new HashSet<String>();
			articleTitleNameSet = new HashSet<String>();
			bookNameSet = new HashSet<String>();
			cityNameSet = new HashSet<String>();
			commentTypeNameSet = new HashSet<String>();
			consortiumNameSet = new HashSet<String>();
			countryNameSet = new HashSet<String>();
			datasetNameSet = new HashSet<String>();
			dbNameSet = new HashSet<String>();
			diseaseIdSet = new HashSet<String>();
			eMBLIdSet = new HashSet<String>();
			ensemblIdSet = new HashSet<String>();
			featureTypeNameSet = new HashSet<String>();
			geneLocationNameSet = new HashSet<String>();
			geneNameSet = new HashSet<String>();
			instituteNameSet = new HashSet<String>();
			interproIdSet = new HashSet<String>();
			isoformIdSet = new HashSet<String>();
			journalNameSet = new HashSet<String>();
			keggIdSet = new HashSet<String>();
			keywordIdSet = new HashSet<String>();
			onlineArticleTitleSet = new HashSet<String>();
			onlineJournalNameSet = new HashSet<String>();
			organismScientificNameSet = new HashSet<String>();
			patentNumberSet = new HashSet<String>();
			personNameSet = new HashSet<String>();
			pfamIdSet = new HashSet<String>();
			pIRIdSet = new HashSet<String>();
			publisherNameSet = new HashSet<String>();
			pubmedIdSet = new HashSet<String>();
			reactomeTermIdSet = new HashSet<String>();
			refSeqIdSet = new HashSet<String>();
			sequenceCautionNameSet = new HashSet<String>();
			subcellularLocationNameSet = new HashSet<String>();
			submissionTitleSet = new HashSet<String>();
			taxonNameSet = new HashSet<String>();
			thesisTitleSet = new HashSet<String>();
			uniGeneIdSet = new HashSet<String>();
			//-------------------------------------------------------------------------

			BufferedWriter enzymeIdsNotFoundBuff = null;
			BufferedWriter statsBuff = null;

			int proteinCounter = 0;
			int limitForPrintingOut = 10000;

			dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			try {

				// This block configures the logger with handler and formatter
				fh = new FileHandler("ImportUniProtVertices" + args[0].split("\\.")[0].replaceAll("/", "_") + ".log", false);

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
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportUniProtVerticesStats_" + inFile.getName().split("\\.")[0] + ".txt")));

				reader = new BufferedReader(new FileReader(inFile));
				StringBuilder entryStBuilder = new StringBuilder();

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

						String modifiedDateSt = entryXMLElem.asJDomElement().getAttributeValue(ENTRY_MODIFIED_DATE_ATTRIBUTE);
						String createdDateSt = entryXMLElem.asJDomElement().getAttributeValue(ENTRY_CREATED_DATE_ATTRIBUTE);
						Integer version = Integer.parseInt(entryXMLElem.asJDomElement().getAttributeValue(ENTRY_VERSION_ATTRIBUTE));

						String accessionSt = entryXMLElem.asJDomElement().getChildText(ENTRY_ACCESSION_TAG_NAME);
						String nameSt = entryXMLElem.asJDomElement().getChildText(ENTRY_NAME_TAG_NAME);
						String fullNameSt = getProteinFullName(entryXMLElem.asJDomElement().getChild(PROTEIN_TAG_NAME));
						String shortNameSt = getProteinShortName(entryXMLElem.asJDomElement().getChild(PROTEIN_TAG_NAME));

						if (shortNameSt == null) {
							shortNameSt = "";
						}
						if (fullNameSt == null) {
							fullNameSt = "";
						}

						currentAccessionId = accessionSt;

						Element sequenceElem = entryXMLElem.asJDomElement().getChild(ENTRY_SEQUENCE_TAG_NAME);
						String sequenceSt = sequenceElem.getText();
						int seqLength = Integer.parseInt(sequenceElem.getAttributeValue(SEQUENCE_LENGTH_ATTRIBUTE));
						float seqMass = Float.parseFloat(sequenceElem.getAttributeValue(SEQUENCE_MASS_ATTRIBUTE));


						Protein<I,RV,RVT,RE,RET> protein = graph.addVertex(graph.Protein());

						protein.set(graph.Protein().modifiedDate, parseDate(modifiedDateSt));
						protein.set(graph.Protein().createdDate, parseDate(createdDateSt));
						protein.set(graph.Protein().accession, accessionSt);
						protein.set(graph.Protein().name, nameSt);
						protein.set(graph.Protein().fullName, fullNameSt);
						protein.set(graph.Protein().shortName, shortNameSt);
						protein.set(graph.Protein().sequence, sequenceSt);
						protein.set(graph.Protein().length, seqLength);
						protein.set(graph.Protein().mass, String.valueOf(seqMass));
						protein.set(graph.Protein().version, version);

						//-----db references-------------
						List<Element> dbReferenceList = entryXMLElem.asJDomElement().getChildren(DB_REFERENCE_TAG_NAME);

						for (Element dbReferenceElem : dbReferenceList) {
							String refId = dbReferenceElem.getAttributeValue("id");
							switch (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE)) {

								case "Ensembl":

									//looking for Ensembl vertex
									if(!ensemblIdSet.contains(refId)){

										ensemblIdSet.add(refId);

										if(!graph.ensemblIdIndex().getVertex(refId).isPresent()){
											String moleculeIdSt = "";
											String proteinSequenceIdSt = "";
											String geneIdSt = "";
											List<Element> children = dbReferenceElem.getChildren("property");
											for (Element propertyElem : children) {
												if (propertyElem.getAttributeValue("type").equals("protein sequence ID")) {
													proteinSequenceIdSt = propertyElem.getAttributeValue("value");
												}
												if (propertyElem.getAttributeValue("type").equals("gene ID")) {
													geneIdSt = propertyElem.getAttributeValue("value");
												}
											}
											Element moleculeTag = dbReferenceElem.getChild("molecule");
											if(moleculeTag != null){
												moleculeIdSt = moleculeTag.getAttributeValue("id");
												if(moleculeIdSt == null){
													moleculeTag.getText();
													if(moleculeIdSt == null){
														moleculeIdSt = "";
													}
												}
											}

											Ensembl<I,RV,RVT,RE,RET> ensembl = graph.addVertex(graph.Ensembl());
											ensembl.set(graph.Ensembl().id, refId);
											ensembl.set(graph.Ensembl().proteinSequenceId, proteinSequenceIdSt);
											ensembl.set(graph.Ensembl().moleculeId, moleculeIdSt);
											ensembl.set(graph.Ensembl().geneId, geneIdSt);
										}

									}
									break;

								case "PIR":

									//looking for PIR vertex

									if(!pIRIdSet.contains(refId)){

										pIRIdSet.add(refId);

										if(!graph.pIRIdIndex().getVertex(refId).isPresent()){
											String entryNameSt = "";
											List<Element> children = dbReferenceElem.getChildren("property");
											for (Element propertyElem : children) {
												if (propertyElem.getAttributeValue("type").equals("entry name")) {
													entryNameSt = propertyElem.getAttributeValue("value");
												}
											}
											PIR<I,RV,RVT,RE,RET> pIR = graph.addVertex(graph.PIR());
											pIR.set(graph.PIR().entryName, entryNameSt);
											pIR.set(graph.PIR().id, refId);
										}
									}

									break;

								case "UniGene":

									//looking for UniGene vertex
									if(!uniGeneIdSet.contains(refId)){

										uniGeneIdSet.add(refId);

										if(!graph.uniGeneIdIndex().getVertex(refId).isPresent()){
											UniGene<I,RV,RVT,RE,RET> uniGene = graph.addVertex(graph.UniGene());
											uniGene.set(graph.UniGene().id, refId);
										}
									}
									break;

								case "KEGG":

									//looking for Kegg vertex
									if(!keggIdSet.contains(refId)){

										keggIdSet.add(refId);

										if(!graph.keggIdIndex().getVertex(refId).isPresent()){
											Kegg<I,RV,RVT,RE,RET> kegg = graph.addVertex(graph.Kegg());
											kegg.set(graph.Kegg().id, refId);
										}
									}
									break;

								case "EMBL":

									//looking for EMBL vertex

									if(!eMBLIdSet.contains(refId)){

										eMBLIdSet.add(refId);

										if(!graph.eMBLIdIndex().getVertex(refId).isPresent()){

											String moleculeTypeSt = "";
											String proteinSequenceIdSt = "";
											List<Element> children = dbReferenceElem.getChildren("property");
											for (Element propertyElem : children) {
												if (propertyElem.getAttributeValue("type").equals("protein sequence ID")) {
													proteinSequenceIdSt = propertyElem.getAttributeValue("value");
												}
												if (propertyElem.getAttributeValue("type").equals("molecule type")) {
													moleculeTypeSt = propertyElem.getAttributeValue("value");
												}
											}

											EMBL<I,RV,RVT,RE,RET> embl = graph.addVertex(graph.EMBL());
											embl.set(graph.EMBL().id, refId);
											embl.set(graph.EMBL().proteinSequenceId, proteinSequenceIdSt);
											embl.set(graph.EMBL().moleculeType, moleculeTypeSt);
										}
									}
									break;

								case "RefSeq":

									//looking for RefSeq vertex

									if(!refSeqIdSet.contains(refId)){

										refSeqIdSet.add(refId);

										if(!graph.refSeqIdIndex().getVertex(refId).isPresent()){
											String nucleotideSequenceIdSt = "";
											List<Element> children = dbReferenceElem.getChildren("property");
											for (Element propertyElem : children) {
												if (propertyElem.getAttributeValue("type").equals("nucleotide sequence ID")) {
													nucleotideSequenceIdSt = propertyElem.getAttributeValue("value");
												}
											}

											RefSeq<I,RV,RVT,RE,RET> refSeq = graph.addVertex(graph.RefSeq());
											refSeq.set(graph.RefSeq().id, refId);
											refSeq.set(graph.RefSeq().nucleotideSequenceId, nucleotideSequenceIdSt);
										}
									}

									break;

								case "Reactome":


									if (!reactomeTermIdSet.contains(refId)) {

										reactomeTermIdSet.add(refId);

										if(!graph.reactomeTermIdIndex().getVertex(refId).isPresent()){

											Element propertyElem = dbReferenceElem.getChild("property");
											String pathwayName = "";
											if (propertyElem.getAttributeValue("type").equals("pathway name")) {
												pathwayName = propertyElem.getAttributeValue("value");
											}


											ReactomeTerm<I,RV,RVT,RE,RET> reactomeTerm = graph.addVertex(graph.ReactomeTerm());
											reactomeTerm.set(graph.ReactomeTerm().id, refId);
											reactomeTerm.set(graph.ReactomeTerm().pathwayName, pathwayName);
										}
									}

									break;
							}

						}


						//-----comments import---
						if (uniprotDataXML.getComments()) {
							importProteinComments(entryXMLElem, graph, protein, sequenceSt, uniprotDataXML);
						}

						//-----features import----
						if (uniprotDataXML.getFeatures()) {
							importProteinFeatures(entryXMLElem, graph, protein);
						}

						//--------------------------------datasets--------------------------------------------------
						String proteinDataSetSt = entryXMLElem.asJDomElement().getAttributeValue(ENTRY_DATASET_ATTRIBUTE);

						if (!datasetNameSet.contains(proteinDataSetSt)) {

							datasetNameSet.add(proteinDataSetSt);

							if(!graph.datasetNameIndex().getVertex(proteinDataSetSt).isPresent()){
								Dataset<I,RV,RVT,RE,RET> dataset = graph.addVertex(graph.Dataset());
								dataset.set(graph.Dataset().name, proteinDataSetSt);
							}
						}
						//---------------------------------------------------------------------------------------------


						if (uniprotDataXML.getCitations()) {
							importProteinCitations(entryXMLElem,
									graph,
									protein,
									uniprotDataXML);
						}


						//-------------------------------keywords------------------------------------------------------
						if (uniprotDataXML.getKeywords()) {
							List<Element> keywordsList = entryXMLElem.asJDomElement().getChildren(KEYWORD_TAG_NAME);
							for (Element keywordElem : keywordsList) {
								String keywordId = keywordElem.getAttributeValue(KEYWORD_ID_ATTRIBUTE);

								if (!keywordIdSet.contains(keywordId)) {

									keywordIdSet.add(keywordId);

									if(graph.keywordIdIndex().getVertex(keywordId).isPresent()){
										String keywordName = keywordElem.getText();
										Keyword<I,RV,RVT,RE,RET>  keyword = graph.addVertex(graph.Keyword());
										keyword.set(graph.Keyword().id, keywordId);
										keyword.set(graph.Keyword().name, keywordName);
									}
								}
							}
						}
						//---------------------------------------------------------------------------------------


						for (Element dbReferenceElem : dbReferenceList) {

							//-------------------------------INTERPRO------------------------------------------------------
							if (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals(INTERPRO_DB_REFERENCE_TYPE)) {

								if (uniprotDataXML.getInterpro()) {
									String interproId = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);

									if (!interproIdSet.contains(interproId)) {

										interproIdSet.add(interproId);

										if(!graph.interproIdIndex().getVertex(interproId).isPresent()){

											String interproEntryNameSt = "";
											List<Element> properties = dbReferenceElem.getChildren(DB_REFERENCE_PROPERTY_TAG_NAME);
											for (Element prop : properties) {
												if (prop.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals(INTERPRO_ENTRY_NAME)) {
													interproEntryNameSt = prop.getAttributeValue(DB_REFERENCE_VALUE_ATTRIBUTE);
													break;
												}
											}
											InterPro<I,RV,RVT,RE,RET> interpro = graph.addVertex(graph.InterPro());
											interpro.set(graph.InterPro().id, interproId);
											interpro.set(graph.InterPro().name, interproEntryNameSt);
										}
									}
								}

							} //-------------------------------PFAM------------------------------------------------------
							else if (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals("Pfam")) {

								if (uniprotDataXML.getPfam()) {

									String pfamId = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);

									if (!pfamIdSet.contains(pfamId)) {

										pfamIdSet.add(pfamId);

										if(!graph.pfamIdIndex().getVertex(pfamId).isPresent()){

											String pfamEntryNameSt = "";
											List<Element> properties = dbReferenceElem.getChildren(DB_REFERENCE_PROPERTY_TAG_NAME);
											for (Element prop : properties) {
												if (prop.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals("entry name")) {
													pfamEntryNameSt = prop.getAttributeValue(DB_REFERENCE_VALUE_ATTRIBUTE);
													break;
												}
											}

											Pfam<I,RV,RVT,RE,RET> pfam = graph.addVertex(graph.Pfam());
											pfam.set(graph.Pfam().id, pfamId);
											pfam.set(graph.Pfam().name, pfamEntryNameSt);
										}
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

							if(!geneLocationNameSet.contains(geneLocationTypeSt)){

								geneLocationNameSet.add(geneLocationTypeSt);

								if(!graph.geneLocationNameIndex().getVertex(geneLocationTypeSt).isPresent()){
									GeneLocation<I,RV,RVT,RE,RET> geneLocation = graph.addVertex(graph.GeneLocation());
									geneLocation.set(graph.GeneLocation().name, geneLocationTypeSt);
								}
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
								if(!geneNameSet.contains(geneNameSt)){

									geneNameSet.add(geneNameSt);

									if(!graph.geneNameNameIndex().getVertex(geneNameSt).isPresent()){
										GeneName<I,RV,RVT,RE,RET> geneName = graph.addVertex(graph.GeneName());
										geneName.set(graph.GeneName().name, geneNameSt);
									}
								}

							}
						}
						//---------------------------------------------------------------------------------------

						//---------------------------------------------------------------------------------------
						//--------------------------------organism-----------------------------------------------

						String scName, commName, synName;
						scName = "";
						commName = "";
						synName = "";

						Element organismElem = entryXMLElem.asJDomElement().getChild(ORGANISM_TAG_NAME);

						List<Element> organismNames = organismElem.getChildren(ORGANISM_NAME_TAG_NAME);
						for (Element element : organismNames) {
							String type = element.getAttributeValue(ORGANISM_NAME_TYPE_ATTRIBUTE);
							switch (type) {
								case ORGANISM_SCIENTIFIC_NAME_TYPE:
									scName = element.getText();
									break;
								case ORGANISM_COMMON_NAME_TYPE:
									commName = element.getText();
									break;
								case ORGANISM_SYNONYM_NAME_TYPE:
									synName = element.getText();
									break;
							}
						}

						if (!organismScientificNameSet.contains(scName)) {

							organismScientificNameSet.add(scName);

							if(!graph.organismScientificNameIndex().getVertex(scName).isPresent()){

								Organism<I,RV,RVT,RE,RET> organism = graph.addVertex(graph.Organism());
								organism.set(graph.Organism().scientificName, scName);
								organism.set(graph.Organism().commonName, commName);
								organism.set(graph.Organism().synonymName, synName);
```

TODO see what to do with the NCBI taxonomy ID, just link to the NCBI tax node or also store
 the id as an attribute


```java
	//							List<Element> organismDbRefElems = organismElem.getChildren(DB_REFERENCE_TAG_NAME);
	//							boolean ncbiIdFound = false;
	//							if (organismDbRefElems != null) {
	//								for (Element dbRefElem : organismDbRefElems) {
	//									String t = dbRefElem.getAttributeValue("type");
	//									if (t.equals("NCBI Taxonomy")) {
	//										organismProperties.put(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, dbRefElem.getAttributeValue("id"));
	//										ncbiIdFound = true;
	//										break;
	//									}
	//								}
	//							}
	//							if (!ncbiIdFound) {
	//								organismProperties.put(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, "");
	//							}

								Element lineage = entryXMLElem.asJDomElement().getChild("organism").getChild("lineage");
								List<Element> taxons = lineage.getChildren("taxon");

								Element firstTaxonElem = taxons.get(0);

								if (!taxonNameSet.contains(firstTaxonElem.getText())) {

									taxonNameSet.add(firstTaxonElem.getText());

									if(!graph.taxonNameIndex().getVertex(firstTaxonElem.getText()).isPresent()){
										String firstTaxonName = firstTaxonElem.getText();
										Taxon<I,RV,RVT,RE,RET> firstTaxon = graph.addVertex(graph.Taxon());
										firstTaxon.set(graph.Taxon().name, firstTaxonName);
									}
								}


								for (int i = 1; i < taxons.size(); i++) {
									String taxonName = taxons.get(i).getText();

									if (!taxonNameSet.contains(taxonName)) {

										taxonNameSet.add(taxonName);

										if(!graph.taxonNameIndex().getVertex(taxonName).isPresent()){
											Taxon<I,RV,RVT,RE,RET> currentTaxon = graph.addVertex(graph.Taxon());
											currentTaxon.set(graph.Taxon().name, taxonName);
										}
									}
								}
							}
						}

						//---------------------------------------------------------------------------------------
						//---------------------------------------------------------------------------------------

						proteinCounter++;
						if ((proteinCounter % limitForPrintingOut) == 0) {
							String countProteinsSt = proteinCounter + " proteins inserted!!";
							logger.log(Level.INFO, countProteinsSt);
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

					statsBuff.write("Statistics for program ImportUniProtVertices:\nInput file: " + inFile.getName()
							+ "\nThere were " + proteinCounter + " proteins inserted.\n"
							+ "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

					//---closing stats writer---
					statsBuff.close();


				} catch (IOException ex) {
					Logger.getLogger(ImportUniProtVertices.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		}

	}

	private void importProteinFeatures(XMLElement entryXMLElem,
	                                   UniProtGraph<I,RV,RVT,RE,RET> graph,
	                                   Protein<I,RV,RVT,RE,RET> protein) {

		//--------------------------------features----------------------------------------------------
		List<Element> featuresList = entryXMLElem.asJDomElement().getChildren(FEATURE_TAG_NAME);

		for (Element featureElem : featuresList) {

			String featureTypeSt = featureElem.getAttributeValue(FEATURE_TYPE_ATTRIBUTE);

			if (!featureTypeNameSet.contains(featureTypeSt)) {

				featureTypeNameSet.add(featureTypeSt);

				if(!graph.featureTypeNameIndex().getVertex(featureTypeSt).isPresent()){
					FeatureType<I,RV,RVT,RE,RET> feature = graph.addVertex(graph.FeatureType());
					feature.set(graph.FeatureType().name, featureTypeSt);
				}
			}

		}

	}

	private void importProteinComments(XMLElement entryXMLElem,
	                                   UniProtGraph<I,RV,RVT,RE,RET> graph,
	                                   Protein<I,RV,RVT,RE,RET> protein,
	                                   String proteinSequence,
	                                   UniprotDataXML uniprotDataXML) {


		List<Element> comments = entryXMLElem.asJDomElement().getChildren(COMMENT_TAG_NAME);

		for (Element commentElem : comments) {

			String commentTypeSt = commentElem.getAttributeValue(COMMENT_TYPE_ATTRIBUTE);

			//-----------------COMMENT TYPE NODE RETRIEVING/CREATION----------------------

			if(!commentTypeNameSet.contains(commentTypeSt)){

				commentTypeNameSet.add(commentTypeSt);

				if(!graph.commentTypeNameIndex().getVertex(commentTypeSt).isPresent()){
					CommentType<I,RV,RVT,RE,RET> comment = graph.addVertex(graph.CommentType());
					comment.set(graph.CommentType().name, commentTypeSt);
				}
			}

			switch (commentTypeSt) {

				case COMMENT_TYPE_DISEASE:

					Element diseaseElement = commentElem.getChild("disease");

					if(diseaseElement != null){
						String diseaseId = diseaseElement.getAttributeValue("id");
						String diseaseName = diseaseElement.getChildText("name");
						String diseaseDescription = diseaseElement.getChildText("description");
						String diseaseAcronym = diseaseElement.getChildText("acronym");

						if(diseaseId != null){

							if(!diseaseIdSet.contains(diseaseId)){

								diseaseIdSet.add(diseaseId);

								if(!graph.diseaseIdIndex().getVertex(diseaseId).isPresent()){
									Disease<I,RV,RVT,RE,RET> disease = graph.addVertex(graph.Disease());
									disease.set(graph.Disease().name, diseaseName);
									disease.set(graph.Disease().id, diseaseId);
									disease.set(graph.Disease().acronym, diseaseAcronym);
									disease.set(graph.Disease().description, diseaseDescription);
								}
							}
						}
					}
					break;


				case COMMENT_TYPE_SUBCELLULAR_LOCATION:

					if (uniprotDataXML.getSubcellularLocations()) {
						List<Element> subcLocations = commentElem.getChildren(SUBCELLULAR_LOCATION_TAG_NAME);

						for (Element subcLocation : subcLocations) {

							List<Element> locations = subcLocation.getChildren(LOCATION_TAG_NAME);

							for (int i = 0; i < locations.size(); i++) {

								String tempLocationSt = locations.get(i).getTextTrim();
								Optional<SubcellularLocation<I,RV,RVT,RE,RET>> tempLocationOptional =  graph.subcellularLocationNameIndex().getVertex(tempLocationSt);

								if(!subcellularLocationNameSet.contains(tempLocationSt)){

									subcellularLocationNameSet.add(tempLocationSt);

									if(!graph.subcellularLocationNameIndex().getVertex(tempLocationSt).isPresent()){
										SubcellularLocation<I,RV,RVT,RE,RET> tempLocation = graph.addVertex(graph.SubcellularLocation());
										tempLocation.set(graph.SubcellularLocation().name, tempLocationSt);
									}
								}
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
							String isoformNoteSt = isoformElem.getChildText("note");
							String isoformNameSt = isoformElem.getChildText("name");
							String isoformSeqSt = "";
							Element isoSeqElem = isoformElem.getChild("sequence");
							if (isoSeqElem != null) {
								String isoSeqTypeSt = isoSeqElem.getAttributeValue("type");
								if (isoSeqTypeSt.equals("displayed")) {
									isoformSeqSt = proteinSequence;
								}
							}
							if (isoformNoteSt == null) {
								isoformNoteSt = "";
							}
							if (isoformNameSt == null) {
								isoformNameSt = "";
							}

							if(!isoformIdSet.contains(isoformIdSt)){

								isoformIdSet.add(isoformIdSt);

								if(!graph.isoformIdIndex().getVertex(isoformIdSt).isPresent()){

									Isoform<I,RV,RVT,RE,RET> isoform = graph.addVertex(graph.Isoform());
									isoform.set(graph.Isoform().name, isoformNameSt);
									isoform.set(graph.Isoform().note, isoformNoteSt);
									isoform.set(graph.Isoform().sequence, isoformSeqSt);
									isoform.set(graph.Isoform().id, isoformIdSt);
								}
							}

							for (Element eventElem : eventList) {

								String eventTypeSt = eventElem.getAttributeValue("type");

								if(!alternativeProductTypeNameSet.contains(eventTypeSt)){

									alternativeProductTypeNameSet.add(eventTypeSt);

									if(!graph.alternativeProductNameIndex().getVertex(eventTypeSt).isPresent()){
										AlternativeProduct<I,RV,RVT,RE,RET> alternativeProduct = graph.addVertex(graph.AlternativeProduct());
										alternativeProduct.set(graph.AlternativeProduct().name, eventTypeSt);
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

						if(!sequenceCautionNameSet.contains(conflictTypeSt)){

							sequenceCautionNameSet.add(conflictTypeSt);

							if(!graph.sequenceCautionNameIndex().getVertex(conflictTypeSt).isPresent()){
								SequenceCaution<I,RV,RVT,RE,RET> sequenceCaution = graph.addVertex(graph.SequenceCaution());
								sequenceCaution.set(graph.SequenceCaution().name, conflictTypeSt);
							}
						}
					}
					break;
			}
		}
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
	                                    UniprotDataXML uniprotDataXML) {

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
					if(!personNameSet.contains(personName)){
						personNameSet.add(personName);
						if(!graph.personNameIndex().getVertex(personName).isPresent()){
							Person<I,RV,RVT,RE,RET> person = graph.addVertex(graph.Person());
							person.set(graph.Person().name, personName);
						}
					}
				}

				for (Element consortiumElement : authorConsortiumElems) {

					String consortiumName = consortiumElement.getAttributeValue("name");

					if(!consortiumNameSet.contains(consortiumName)){
						consortiumNameSet.add(consortiumName);
						if(!graph.consortiumNameIndex().getVertex(consortiumName).isPresent()){
							Consortium<I,RV,RVT,RE,RET> consortium = graph.addVertex(graph.Consortium());
							consortium.set(graph.Consortium().name, consortiumName);
						}
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

								if(!thesisTitleSet.contains(titleSt)){

									thesisTitleSet.add(titleSt);

									if(!graph.thesisTitleIndex().getVertex(titleSt).isPresent()){
										Thesis<I,RV,RVT,RE,RET> thesis = graph.addVertex(graph.Thesis());
										thesis.set(graph.Thesis().title, titleSt);
									}

									//-----------institute-----------------------------
									String instituteSt = citation.getAttributeValue("institute");
									String countrySt = citation.getAttributeValue("country");

									if (instituteSt != null) {
										if(!instituteNameSet.contains(instituteSt)){

											instituteNameSet.add(instituteSt);

											if(!graph.instituteNameIndex().getVertex(instituteSt).isPresent()){
												Institute<I,RV,RVT,RE,RET> institute = graph.addVertex(graph.Institute());
												institute.set(graph.Institute().name, instituteSt);
											}
										}

										if (countrySt != null) {

											if(!countryNameSet.contains(countrySt)){
												countryNameSet.add(countrySt);
												if(!graph.countryNameIndex().getVertex(countrySt).isPresent()){
													Country<I,RV,RVT,RE,RET> country = graph.addVertex(graph.Country());
													country.set(graph.Country().name, countrySt);
												}
											}
										}
									}
								}
							}
						}

						//----------------------------------------------------------------------------
						//-----------------------------PATENT-----------------------------------------
						break;
					case PATENT_CITATION_TYPE:
						if (uniprotDataXML.getPatents()) {
							String numberSt = citation.getAttributeValue("number");
							String titleSt = citation.getChildText("title");
							if (titleSt == null) {
								titleSt = "";
							}
							if (numberSt == null) {
								numberSt = "";
							}

							if (!numberSt.equals("")) {
								if(!patentNumberSet.contains(numberSt)){
									patentNumberSet.add(numberSt);

									if(!graph.patentNumberIndex().getVertex(numberSt).isPresent()){
										Patent<I,RV,RVT,RE,RET> patent = graph.addVertex(graph.Patent());
										patent.set(graph.Patent().number, numberSt);
										patent.set(graph.Patent().title, titleSt);
									}
								}
							}
						}

						//----------------------------------------------------------------------------
						//-----------------------------SUBMISSION-----------------------------------------
						break;
					case SUBMISSION_CITATION_TYPE:
						if (uniprotDataXML.getSubmissions()) {
							String titleSt = citation.getChildText("title");
							String dbSt = citation.getAttributeValue("db");
							if (titleSt == null) {
								titleSt = "";
							}else{

								if(!submissionTitleSet.contains(titleSt)){
									submissionTitleSet.add(titleSt);

									if(!graph.submissionTitleIndex().getVertex(titleSt).isPresent()){
										Submission<I,RV,RVT,RE,RET> submission = graph.addVertex(graph.Submission());
										submission.set(graph.Submission().title, titleSt);
									}

									if (dbSt != null) {
										if(!dbNameSet.contains(dbSt)){
											dbNameSet.add(dbSt);
											if(!graph.dbNameIndex().getVertex(dbSt).isPresent()){
												DB<I,RV,RVT,RE,RET> db = graph.addVertex(graph.DB());
												db.set(graph.DB().name, dbSt);
											}
										}
									}
								}
							}
						}

						//----------------------------------------------------------------------------
						//-----------------------------BOOK-----------------------------------------
						break;
					case BOOK_CITATION_TYPE:
						if (uniprotDataXML.getBooks()) {
							String nameSt = citation.getAttributeValue("name");
							String titleSt = citation.getChildText("title");
							String publisherSt = citation.getAttributeValue("publisher");
							String citySt = citation.getAttributeValue("city");
							if (nameSt == null) {
								nameSt = "";
							}
							if (titleSt == null) {
								titleSt = "";
							}
							if (publisherSt == null) {
								publisherSt = "";
							}
							if (citySt == null) {
								citySt = "";
							}

							if(!bookNameSet.contains(nameSt)){

								bookNameSet.add(nameSt);

								if(!graph.bookNameIndex().getVertex(nameSt).isPresent()){

									Book<I,RV,RVT,RE,RET> book = graph.addVertex(graph.Book());
									book.set(graph.Book().name, nameSt);

									//---editor association-----
									Element editorListElem = citation.getChild("editorList");
									if (editorListElem != null) {
										List<Element> editorsElems = editorListElem.getChildren("person");
										for (Element personElement : editorsElems) {
											String personName = personElement.getAttributeValue("name");

											if(!personNameSet.contains(personName)){
												personNameSet.add(personName);
												if(!graph.personNameIndex().getVertex(personName).isPresent()){
													Person<I,RV,RVT,RE,RET> editor = graph.addVertex(graph.Person());
													editor.set(graph.Person().name, personName);
												}
											}
										}
									}

									//----publisher--
									if (!publisherSt.equals("")) {
										if(!publisherNameSet.contains(publisherSt)){
											publisherNameSet.add(publisherSt);

											if(!graph.publisherNameIndex().getVertex(publisherSt).isPresent()){
												Publisher<I,RV,RVT,RE,RET> publisher = graph.addVertex(graph.Publisher());
												publisher.set(graph.Publisher().name, publisherSt);
											}
										}
									}

									//-----city-----
									if (!citySt.equals("")) {
										if(!cityNameSet.contains(citySt)){
											cityNameSet.add(citySt);

											if(!graph.cityNameIndex().getVertex(citySt).isPresent()){
												City<I,RV,RVT,RE,RET> city = graph.addVertex(graph.City());
												city.set(graph.City().name, citySt);
											}
										}
									}
								}

							}
						}

						//----------------------------------------------------------------------------
						//-----------------------------ONLINE ARTICLE-----------------------------------------
						break;
					case ONLINE_ARTICLE_CITATION_TYPE:
						if (uniprotDataXML.getOnlineArticles()) {
							String nameSt = citation.getAttributeValue("name");
							String titleSt = citation.getChildText("title");

							if (titleSt == null) {
								titleSt = "";
							}
							if (nameSt == null) {
								nameSt = "";
							}

							if (!titleSt.equals("")) {

								if(!onlineArticleTitleSet.contains(titleSt)){

									onlineArticleTitleSet.add(titleSt);

									if(!graph.onlineArticleTitleIndex().getVertex(titleSt).isPresent()){
										OnlineArticle<I,RV,RVT,RE,RET> onlineArticle = graph.addVertex(graph.OnlineArticle());
										onlineArticle.set(graph.OnlineArticle().title, titleSt);

										//------online journal-----------
										if (!nameSt.equals("")) {

											if(!onlineJournalNameSet.contains(nameSt)){
												onlineJournalNameSet.add(nameSt);

												if(!graph.onlineJournalNameIndex().getVertex(nameSt).isPresent()){
													OnlineJournal<I,RV,RVT,RE,RET> onlineJournal = graph.addVertex(graph.OnlineJournal());
													onlineJournal.set(graph.OnlineJournal().name, nameSt);
												}
											}
										}
										//----------------------------
									}
								}
							}
						}

						//----------------------------------------------------------------------------
						//-----------------------------ARTICLE-----------------------------------------
						break;
					case ARTICLE_CITATION_TYPE:

						if (uniprotDataXML.getArticles()) {

							String journalNameSt = citation.getAttributeValue("name");
							String titleSt = citation.getChildText("title");
							String doiSt = "";
							String medlineSt = "";
							String pubmedId = "";

							if (journalNameSt == null) {
								journalNameSt = "";
							}
							if (titleSt == null) {
								titleSt = "";
							}

							List<Element> dbReferences = citation.getChildren("dbReference");
							for (Element tempDbRef : dbReferences) {
								switch (tempDbRef.getAttributeValue("type")) {
									case "DOI":
										doiSt = tempDbRef.getAttributeValue("id");
										break;
									case "MEDLINE":
										medlineSt = tempDbRef.getAttributeValue("id");
										break;
									case "PubMed":
										pubmedId = tempDbRef.getAttributeValue("id");
										break;
								}
							}

							if (titleSt != "") {

								if(!articleTitleNameSet.contains(titleSt)){
									articleTitleNameSet.add(titleSt);

									if(!graph.articleTitleIndex().getVertex(titleSt).isPresent()){

										Article<I,RV,RVT,RE,RET> article = graph.addVertex(graph.Article());
										article.set(graph.Article().title, titleSt);
										article.set(graph.Article().doId, doiSt);

										if(pubmedId != ""){

											if(!pubmedIdSet.contains(pubmedId)){
												pubmedIdSet.add(pubmedId);

												if(!graph.pubmedIdIndex().getVertex(pubmedId).isPresent()){
													Pubmed<I,RV,RVT,RE,RET> pubmed = graph.addVertex(graph.Pubmed());
													pubmed.set(graph.Pubmed().id, pubmedId);
												}

											}
										}

										//------journal-----------
										if (!journalNameSt.equals("")) {

											if(!journalNameSet.contains(journalNameSt)){
												journalNameSet.add(journalNameSt);
												if(!graph.journalNameIndex().getVertex(journalNameSt).isPresent()){
													Journal<I,RV,RVT,RE,RET> journal = graph.addVertex(graph.Journal());
													journal.set(graph.Journal().name, journalNameSt);
												}
											}
										}
										//----------------------------
									}
								}
							}
						}

						//----------------------------------------------------------------------------
						//----------------------UNPUBLISHED OBSERVATIONS-----------------------------------------
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