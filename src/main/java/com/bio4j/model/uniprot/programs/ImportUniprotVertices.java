package com.bio4j.model.uniprot.programs;

import com.bio4j.model.uniprot.UniprotGraph;
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
public abstract class ImportUniprotVertices<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

	private static final Logger logger = Logger.getLogger("ImportUniprotVertices");
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
	HashSet<String> instituteNameSet;
	HashSet<String> interproIdSet;
	HashSet<String> isoformIdSet;
	HashSet<String> journalNameSet;
	HashSet<String> keggIdSet;
	HashSet<String> keywordIdSet;
	HashSet<String> onlineArticleTitleSet;
	HashSet<String> onlineJournalNameSet;
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

	protected abstract UniprotGraph<I,RV,RVT,RE,RET> config(String dbFolder);

	protected void importUniprot(String[] args) {

		if (args.length != 3) {
			System.out.println("This program expects the following parameters: \n"
					+ "1. Uniprot xml filename \n"
					+ "2. Bio4j DB folder \n"
					+ "3. Config XML file");
		} else {

			long initTime = System.nanoTime();

			File inFile = new File(args[0]);
			File configFile = new File(args[2]);
			String dbFolder = args[1];

			String currentAccessionId = "";

			//-------creating graph handlers---------------------
			UniprotGraph<I,RV,RVT,RE,RET> graph = config(dbFolder);

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
			instituteNameSet = new HashSet<String>();
			interproIdSet = new HashSet<String>();
			isoformIdSet = new HashSet<String>();
			journalNameSet = new HashSet<String>();
			keggIdSet = new HashSet<String>();
			keywordIdSet = new HashSet<String>();
			onlineArticleTitleSet = new HashSet<String>();
			onlineJournalNameSet = new HashSet<String>();
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
				fh = new FileHandler("ImportUniprot" + args[0].split("\\.")[0].replaceAll("/", "_") + ".log", false);

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
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportUniprotVerticesStats_" + inFile.getName().split("\\.")[0] + ".txt")));

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
									break;

								case "PIR":

									//looking for PIR vertex

									if(!pIRIdSet.contains(refId)){

										pIRIdSet.add(refId);

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

									break;

								case "UniGene":

									//looking for UniGene vertex
									if(!uniGeneIdSet.contains(refId)){

										uniGeneIdSet.add(refId);

										UniGene<I,RV,RVT,RE,RET> uniGene = graph.addVertex(graph.UniGene());
										uniGene.set(graph.UniGene().id, refId);

									}
									break;

								case "KEGG":

									//looking for Kegg vertex
									if(!keggIdSet.contains(refId)){
										keggIdSet.add(refId);
										Kegg<I,RV,RVT,RE,RET> kegg = graph.addVertex(graph.Kegg());
										kegg.set(graph.Kegg().id, refId);
									}
									break;

								case "EMBL":

									//looking for EMBL vertex

									if(!eMBLIdSet.contains(refId)){

										eMBLIdSet.add(refId);

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
									break;

								case "RefSeq":

									//looking for RefSeq vertex

									if(!refSeqIdSet.contains(refId)){

										refSeqIdSet.add(refId);

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

									break;

								case "Reactome":

									Element propertyElem = dbReferenceElem.getChild("property");
									String pathwayName = "";
									if (propertyElem.getAttributeValue("type").equals("pathway name")) {
										pathwayName = propertyElem.getAttributeValue("value");
									}

									if (!reactomeTermIdSet.contains(refId)) {

										reactomeTermIdSet.add(refId);

										ReactomeTerm<I,RV,RVT,RE,RET> reactomeTerm = graph.addVertex(graph.ReactomeTerm());
										reactomeTerm.set(graph.ReactomeTerm().id, refId);
										reactomeTerm.set(graph.ReactomeTerm().pathwayName, pathwayName);
									}

									break;
							}

						}


						// TODO we need to decide how to store this
//						//---------------gene-names-------------------
//						Element geneElement = entryXMLElem.asJDomElement().getChild(GENE_TAG_NAME);
//						ArrayList<String> geneNames = new ArrayList<>();
//						if (geneElement != null) {
//							List<Element> genesList = geneElement.getChildren(GENE_NAME_TAG_NAME);
//							for (Element geneNameElem : genesList) {
//								geneNames.add(geneNameElem.getText());
//							}
//						}
//						//-----------------------------------------

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

						Dataset<I,RV,RVT,RE,RET> dataset = null;
						Optional<Dataset<I,RV,RVT,RE,RET>> optionalDataset = graph.datasetNameIndex().getVertex(proteinDataSetSt);

						if (!optionalDataset.isPresent()) {
							dataset = graph.addVertex(graph.Dataset());
							dataset.set(graph.Dataset().name, proteinDataSetSt);
							graph.raw().commit();
						}else{
							dataset = optionalDataset.get();
						}
						protein.addOutEdge(graph.ProteinDataset(), dataset);
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
								String keywordName = keywordElem.getText();

								Keyword<I,RV,RVT,RE,RET>  keyword = null;
								Optional<Keyword<I,RV,RVT,RE,RET> > optionalKeyword = graph.keywordIdIndex().getVertex(keywordId);

								if (!optionalKeyword.isPresent()) {
									keyword = graph.addVertex(graph.Keyword());
									keyword.set(graph.Keyword().id, keywordId);
									keyword.set(graph.Keyword().name, keywordName);
									graph.raw().commit();
								}else{
									keyword = optionalKeyword.get();
								}
								protein.addOutEdge(graph.ProteinKeyword(), keyword);
							}
						}
						//---------------------------------------------------------------------------------------


						for (Element dbReferenceElem : dbReferenceList) {

							//-------------------------------INTERPRO------------------------------------------------------
							if (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals(INTERPRO_DB_REFERENCE_TYPE)) {

								if (uniprotDataXML.getInterpro()) {
									String interproId = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);

									Interpro<I,RV,RVT,RE,RET> interpro = null;
									Optional<Interpro<I,RV,RVT,RE,RET>> optionalInterpro = graph.interproIdIndex().getVertex(interproId);

									if (!optionalInterpro.isPresent()) {

										String interproEntryNameSt = "";
										List<Element> properties = dbReferenceElem.getChildren(DB_REFERENCE_PROPERTY_TAG_NAME);
										for (Element prop : properties) {
											if (prop.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals(INTERPRO_ENTRY_NAME)) {
												interproEntryNameSt = prop.getAttributeValue(DB_REFERENCE_VALUE_ATTRIBUTE);
												break;
											}
										}

										interpro = graph.addVertex(graph.Interpro());
										interpro.set(graph.Interpro().id, interproId);
										interpro.set(graph.Interpro().name, interproEntryNameSt);
										graph.raw().commit();
									}else{
										interpro = optionalInterpro.get();
									}

									protein.addOutEdge(graph.ProteinInterpro(), interpro);
								}

							} //-------------------------------PFAM------------------------------------------------------
							else if (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals("Pfam")) {

								if (uniprotDataXML.getPfam()) {

									String pfamId = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);
									Pfam<I,RV,RVT,RE,RET> pfam = null;
									Optional<Pfam<I,RV,RVT,RE,RET>> optionalPfam = graph.pfamIdIndex().getVertex(pfamId);

									if (!optionalPfam.isPresent()) {
										String pfamEntryNameSt = "";
										List<Element> properties = dbReferenceElem.getChildren(DB_REFERENCE_PROPERTY_TAG_NAME);
										for (Element prop : properties) {
											if (prop.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals("entry name")) {
												pfamEntryNameSt = prop.getAttributeValue(DB_REFERENCE_VALUE_ATTRIBUTE);
												break;
											}
										}

										pfam = graph.addVertex(graph.Pfam());
										pfam.set(graph.Pfam().id, pfamId);
										pfam.set(graph.Pfam().name, pfamEntryNameSt);
										graph.raw().commit();
									}else{
										pfam = optionalPfam.get();
									}

									protein.addOutEdge(graph.ProteinPfam(), pfam);
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
							GeneLocation<I,RV,RVT,RE,RET> geneLocation = null;

							if(optionalGeneLocation.isPresent()){

								geneLocation = optionalGeneLocation.get();

							}else{

								geneLocation = graph.addVertex(graph.GeneLocation());
								geneLocation.set(graph.GeneLocation().name, geneLocationTypeSt);
								graph.raw().commit();

							}

							ProteinGeneLocation<I,RV,RVT,RE,RET> proteinGeneLocation = protein.addOutEdge(graph.ProteinGeneLocation(), geneLocation);
							proteinGeneLocation.set(graph.ProteinGeneLocation().name, geneLocationNameSt);
						}

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

						Organism<I,RV,RVT,RE,RET> organism = null;
						Optional<Organism<I,RV,RVT,RE,RET>> organismOptional = graph.organismScientificNameIndex().getVertex(scName);

						if (!organismOptional.isPresent()) {

							organism = graph.addVertex(graph.Organism());
							organism.set(graph.Organism().scientificName, scName);
							organism.set(graph.Organism().commonName, commName);
							organism.set(graph.Organism().synonymName, synName);
							graph.raw().commit();

							/* TODO see what to do with the NCBI taxonomy ID, just link to the NCBI tax node or also store
							    the id as an attribute
							*/
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

							Taxon<I,RV,RVT,RE,RET> firstTaxon = null;
							Optional<Taxon<I,RV,RVT,RE,RET>> firstTaxonOptional = graph.taxonNameIndex().getVertex(firstTaxonElem.getText());

							if (!firstTaxonOptional.isPresent()) {

								String firstTaxonName = firstTaxonElem.getText();
								firstTaxon = graph.addVertex(graph.Taxon());
								firstTaxon.set(graph.Taxon().name, firstTaxonName);
								graph.raw().commit();

							}else{
								firstTaxon = firstTaxonOptional.get();
							}

							Taxon<I,RV,RVT,RE,RET> lastTaxon = firstTaxon;

							for (int i = 1; i < taxons.size(); i++) {
								String taxonName = taxons.get(i).getText();
								Taxon<I,RV,RVT,RE,RET> currentTaxon = null;
								Optional<Taxon<I,RV,RVT,RE,RET>> currentTaxonOptional = graph.taxonNameIndex().getVertex(taxonName);

								if (!currentTaxonOptional.isPresent()) {

									currentTaxon = graph.addVertex(graph.Taxon());
									currentTaxon.set(graph.Taxon().name, taxonName);
									graph.raw().commit();
									lastTaxon.addOutEdge(graph.TaxonParent(), currentTaxon);
								}else{
									currentTaxon = currentTaxonOptional.get();
								}
								lastTaxon = currentTaxon;
							}


							organism.addOutEdge(graph.OrganismTaxon(), lastTaxon);

						}else{
							organism = organismOptional.get();
						}


						//---------------------------------------------------------------------------------------
						//---------------------------------------------------------------------------------------

						protein.addOutEdge(graph.ProteinOrganism(), organism);

						proteinCounter++;
						if ((proteinCounter % limitForPrintingOut) == 0) {
							String countProteinsSt = proteinCounter + " proteins inserted!!";
							logger.log(Level.INFO, countProteinsSt);
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

					statsBuff.write("Statistics for program ImportUniprot:\nInput file: " + inFile.getName()
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

	private void importProteinFeatures(XMLElement entryXMLElem,
	                                   UniprotGraph<I,RV,RVT,RE,RET> graph,
	                                   Protein<I,RV,RVT,RE,RET> protein) {

		//--------------------------------features----------------------------------------------------
		List<Element> featuresList = entryXMLElem.asJDomElement().getChildren(FEATURE_TAG_NAME);

		for (Element featureElem : featuresList) {

			String featureTypeSt = featureElem.getAttributeValue(FEATURE_TYPE_ATTRIBUTE);

			FeatureType<I,RV,RVT,RE,RET> feature = null;
			Optional<FeatureType<I,RV,RVT,RE,RET>> optionalFeature = graph.featureTypeNameIndex().getVertex(featureTypeSt);

			if (!optionalFeature.isPresent()) {

				feature = graph.addVertex(graph.FeatureType());
				feature.set(graph.FeatureType().name, featureTypeSt);

			}else{
				feature = optionalFeature.get();
			}

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

		}

	}

	private void importProteinComments(XMLElement entryXMLElem,
	                                   UniprotGraph<I,RV,RVT,RE,RET> graph,
	                                   Protein<I,RV,RVT,RE,RET> protein,
	                                   String proteinSequence,
	                                   UniprotDataXML uniprotDataXML) {


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
			CommentType<I,RV,RVT,RE,RET> comment = null;

			if(!commentOptional.isPresent()){

				comment = graph.addVertex(graph.CommentType());
				comment.set(graph.CommentType().name, commentTypeSt);


			}else{
				comment = commentOptional.get();
			}

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

							Disease<I,RV,RVT,RE,RET> disease = null;
							Optional<Disease<I,RV,RVT,RE,RET>> diseaseOptional =  graph.diseaseIdIndex().getVertex(diseaseId);

							if(!diseaseOptional.isPresent()){

								disease = graph.addVertex(graph.Disease());
								disease.set(graph.Disease().name, diseaseName);
								disease.set(graph.Disease().id, diseaseId);
								disease.set(graph.Disease().acronym, diseaseAcronym);
								disease.set(graph.Disease().description, diseaseDescription);


							}else{
								disease = diseaseOptional.get();
							}

							ProteinDisease<I,RV,RVT,RE,RET> proteinDisease = protein.addOutEdge(graph.ProteinDisease(), disease);
							proteinDisease.set(graph.ProteinDisease().text, commentTextSt);
							proteinDisease.set(graph.ProteinDisease().status, commentStatusSt);
							proteinDisease.set(graph.ProteinDisease().evidence, commentEvidenceSt);
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
							SubcellularLocation<I,RV,RVT,RE,RET> lastLocation = null;
							Optional<SubcellularLocation<I,RV,RVT,RE,RET>> lastLocationOptional =  graph.subcellularLocationNameIndex().getVertex(firstLocationSt);

							if(!lastLocationOptional.isPresent()){
								lastLocation = graph.addVertex(graph.SubcellularLocation());
								lastLocation.set(graph.SubcellularLocation().name, firstLocationSt);

							}else{
								lastLocation = lastLocationOptional.get();
							}

							for (int i = 1; i < locations.size(); i++) {

								SubcellularLocation<I,RV,RVT,RE,RET> tempLocation = null;
								String tempLocationSt = locations.get(i).getTextTrim();
								Optional<SubcellularLocation<I,RV,RVT,RE,RET>> tempLocationOptional =  graph.subcellularLocationNameIndex().getVertex(tempLocationSt);

								if(!tempLocationOptional.isPresent()){
									tempLocation = graph.addVertex(graph.SubcellularLocation());
									tempLocation.set(graph.SubcellularLocation().name, tempLocationSt);

								}else{
									tempLocation = tempLocationOptional.get();
								}

								tempLocation.addOutEdge(graph.SubcellularLocationParent(), lastLocation);
								lastLocation = tempLocation;

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

							Optional<Isoform<I,RV,RVT,RE,RET>> isoformOptional = graph.isoformIdIndex().getVertex(isoformIdSt);
							Isoform<I,RV,RVT,RE,RET> isoform;
							if(!isoformOptional.isPresent()){
								isoform = graph.addVertex(graph.Isoform());
								isoform.set(graph.Isoform().name, isoformNameSt);
								isoform.set(graph.Isoform().note, isoformNoteSt);
								isoform.set(graph.Isoform().sequence, isoformSeqSt);
								isoform.set(graph.Isoform().id, isoformIdSt);

								//Adding edge from Protein to Isoform
								protein.addOutEdge(graph.ProteinIsoform(), isoform);
							}else{
								isoform = isoformOptional.get();
							}



							for (Element eventElem : eventList) {

								String eventTypeSt = eventElem.getAttributeValue("type");

								Optional<AlternativeProduct<I,RV,RVT,RE,RET>> alternativeProductOptional = graph.alternativeProductNameIndex().getVertex(eventTypeSt);
								AlternativeProduct<I,RV,RVT,RE,RET> alternativeProduct;

								if(alternativeProductOptional.isPresent()){
									alternativeProduct = alternativeProductOptional.get();
								}else{
									alternativeProduct = graph.addVertex(graph.AlternativeProduct());
									alternativeProduct.set(graph.AlternativeProduct().name, eventTypeSt);

								}

								isoform.addOutEdge(graph.IsoformEventGenerator(), alternativeProduct);
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

//						System.out.println("conflictTypeSt = " + conflictTypeSt);
//						System.out.println("sequenceCautionNameIndex = " + graph.sequenceCautionNameIndex());
						Optional<SequenceCaution<I,RV,RVT,RE,RET>> sequenceCautionOptional =  graph.sequenceCautionNameIndex().getVertex(conflictTypeSt);
						SequenceCaution<I,RV,RVT,RE,RET> sequenceCaution;

						if(!sequenceCautionOptional.isPresent()){

							sequenceCaution = graph.addVertex(graph.SequenceCaution());
							sequenceCaution.set(graph.SequenceCaution().name, conflictTypeSt);


						}else{
							sequenceCaution = sequenceCautionOptional.get();
						}


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
					}
					break;
			}

			if(createStandardProteinComment){
				ProteinComment<I,RV,RVT,RE,RET> proteinComment = protein.addOutEdge(graph.ProteinComment(), comment);
				proteinComment.set(graph.ProteinComment().text, commentTextSt);
				proteinComment.set(graph.ProteinComment().status, commentStatusSt);
				proteinComment.set(graph.ProteinComment().evidence, commentEvidenceSt);
			}

		}


	}

	private void addPropertiesToProteinFeatureRelationship(UniprotGraph<I,RV,RVT,RE,RET> graph, ProteinFeature<I,RV,RVT,RE,RET> proteinFeature,
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
	                                    UniprotGraph<I,RV,RVT,RE,RET> graph,
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

					Person<I,RV,RVT,RE,RET> person = null;
					String personName = personElement.getAttributeValue("name");
					Optional<Person<I,RV,RVT,RE,RET>> optionalPerson = graph.personNameIndex().getVertex(personName);
					if(!optionalPerson.isPresent()){
						person = graph.addVertex(graph.Person());
						person.set(graph.Person().name, personName);

					}else{
						person = optionalPerson.get();
					}
				}

				for (Element consortiumElement : authorConsortiumElems) {

					Consortium<I,RV,RVT,RE,RET> consortium = null;
					String consortiumName = consortiumElement.getAttributeValue("name");
					Optional<Consortium<I,RV,RVT,RE,RET>> optionalConsortium = graph.consortiumNameIndex().getVertex(consortiumName);
					if(!optionalConsortium.isPresent()){
						consortium = graph.addVertex(graph.Consortium());
						consortium.set(graph.Consortium().name, consortiumName);

					}else{
						consortium = optionalConsortium.get();
					}
				}
				//----------------------------------------------------------------------------
				//-----------------------------THESIS-----------------------------------------
				switch (citationType) {
					case THESIS_CITATION_TYPE:
						if (uniprotDataXML.getThesis()) {
							String dateSt = citation.getAttributeValue("date");
							String titleSt = citation.getChildText("title");
							if (dateSt == null) {
								dateSt = "";
							}
							if (titleSt == null) {
								titleSt = "";
							}else{

								Thesis<I,RV,RVT,RE,RET> thesis = null;
								Optional<Thesis<I,RV,RVT,RE,RET>> optionalThesis = graph.thesisTitleIndex().getVertex(titleSt);
								Reference<I,RV,RVT,RE,RET> reference = null;

								if(!optionalThesis.isPresent()){

									thesis = graph.addVertex(graph.Thesis());
									thesis.set(graph.Thesis().title, titleSt);

									//-----------institute-----------------------------
									String instituteSt = citation.getAttributeValue("institute");
									String countrySt = citation.getAttributeValue("country");

									reference = graph.addVertex(graph.Reference());
									reference.set(graph.Reference().date, dateSt);
									reference.addOutEdge(graph.ReferenceThesis(), thesis);

									//---authors association-----
									for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
										reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
									}

									if (instituteSt != null) {

										Institute<I,RV,RVT,RE,RET> institute = null;
										Optional<Institute<I,RV,RVT,RE,RET>> optionalInstitute = graph.instituteNameIndex().getVertex(instituteSt);


										if(!optionalInstitute.isPresent()){

											institute = graph.addVertex(graph.Institute());
											institute.set(graph.Institute().name, instituteSt);


										}else{
											institute = optionalInstitute.get();
										}

										if (countrySt != null) {

											Country<I,RV,RVT,RE,RET> country = null;
											Optional<Country<I,RV,RVT,RE,RET>> optionalCountry = graph.countryNameIndex().getVertex(countrySt);
											if(!optionalCountry.isPresent()){
												country = graph.addVertex(graph.Country());
												country.set(graph.Country().name, countrySt);

											}else{
												country = optionalCountry.get();
											}

											institute.addOutEdge(graph.InstituteCountry(), country);
										}
										thesis.addOutEdge(graph.ThesisInstitute(), institute);
									}

								}else{
									thesis = optionalThesis.get();
									reference = thesis.referenceThesis_inV();
								}

								//--protein reference citation relationship
								protein.addOutEdge(graph.ProteinReference(), reference);


							}

						}

						//----------------------------------------------------------------------------
						//-----------------------------PATENT-----------------------------------------
						break;
					case PATENT_CITATION_TYPE:
						if (uniprotDataXML.getPatents()) {
							String numberSt = citation.getAttributeValue("number");
							String dateSt = citation.getAttributeValue("date");
							String titleSt = citation.getChildText("title");
							if (dateSt == null) {
								dateSt = "";
							}
							if (titleSt == null) {
								titleSt = "";
							}
							if (numberSt == null) {
								numberSt = "";
							}

							if (!numberSt.equals("")) {

								Patent<I,RV,RVT,RE,RET> patent = null;
								Optional<Patent<I,RV,RVT,RE,RET>> optionalPatent = graph.patentNumberIndex().getVertex(numberSt);
								Reference<I,RV,RVT,RE,RET> reference = null;

								if(!optionalPatent.isPresent()){

									patent = graph.addVertex(graph.Patent());
									patent.set(graph.Patent().number, numberSt);
									patent.set(graph.Patent().title, titleSt);


									reference = graph.addVertex(graph.Reference());
									reference.set(graph.Reference().date, dateSt);
									reference.addOutEdge(graph.ReferencePatent(), patent);

									//---authors association-----
									for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
										reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
									}

								}else{
									patent = optionalPatent.get();
									reference = patent.referencePatent_inV();
								}

								//--protein citation relationship
								protein.addOutEdge(graph.ProteinReference(), reference);

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
							if (titleSt == null) {
								titleSt = "";
							}else{

								Submission<I,RV,RVT,RE,RET> submission = null;
								Optional<Submission<I,RV,RVT,RE,RET>> optionalSubmission = graph.submissionTitleIndex().getVertex(titleSt);
								Reference<I,RV,RVT,RE,RET> reference = null;

								if(!optionalSubmission.isPresent()){

									submission = graph.addVertex(graph.Submission());
									submission.set(graph.Submission().title, titleSt);


									reference = graph.addVertex(graph.Reference());
									reference.set(graph.Reference().date, dateSt);
									//---authors association-----
									for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
										reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
									}
									for(Consortium<I,RV,RVT,RE,RET> consortium : authorsConsortium){
										reference.addOutEdge(graph.ReferenceAuthorConsortium(), consortium);
									}

									if (dbSt != null) {

										DB<I,RV,RVT,RE,RET> db = null;
										Optional<DB<I,RV,RVT,RE,RET>> optionalDB = graph.dbNameIndex().getVertex(dbSt);
										if(!optionalDB.isPresent()){
											db = graph.addVertex(graph.DB());
											db.set(graph.DB().name, dbSt);

										}else{
											db = optionalDB.get();
										}

										//-----submission db relationship-----
										submission.addOutEdge(graph.SubmissionDB(), db);
									}

									reference.addOutEdge(graph.ReferenceSubmission(), submission);

								}else{
									submission = optionalSubmission.get();
									reference = submission.referenceSubmission_inV();
								}

								//--protein citation relationship
								protein.addOutEdge(graph.ProteinReference(), reference);


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

							Book<I,RV,RVT,RE,RET> book = null;
							Optional<Book<I,RV,RVT,RE,RET>> optionalBook = graph.bookNameIndex().getVertex(nameSt);
							Reference<I,RV,RVT,RE,RET> reference = null;

							if(!optionalBook.isPresent()){

								book = graph.addVertex(graph.Book());
								book.set(graph.Book().name, nameSt);


								reference = graph.addVertex(graph.Reference());
								reference.set(graph.Reference().date, dateSt);
								reference.addOutEdge(graph.ReferenceBook(), book);

								//---authors association-----
								for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
									reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
								}

								//---editor association-----
								Element editorListElem = citation.getChild("editorList");
								if (editorListElem != null) {
									List<Element> editorsElems = editorListElem.getChildren("person");
									for (Element personElement : editorsElems) {

										Person<I,RV,RVT,RE,RET> editor = null;
										String personName = personElement.getAttributeValue("name");
										Optional<Person<I,RV,RVT,RE,RET>> optionalPerson = graph.personNameIndex().getVertex(personName);

										if(!optionalPerson.isPresent()){
											editor = graph.addVertex(graph.Person());
											editor.set(graph.Person().name, personName);

										}else{
											editor = optionalPerson.get();
										}
										book.addOutEdge(graph.BookEditor(), editor);

									}
								}

								//----publisher--
								if (!publisherSt.equals("")) {

									Publisher<I,RV,RVT,RE,RET> publisher = null;
									Optional<Publisher<I,RV,RVT,RE,RET>> optionalPublisher = graph.publisherNameIndex().getVertex(publisherSt);

									if(!optionalPublisher.isPresent()){

										publisher = graph.addVertex(graph.Publisher());
										publisher.set(graph.Publisher().name, publisherSt);


									}else{
										publisher = optionalPublisher.get();
									}

									book.addOutEdge(graph.BookPublisher(), publisher);
								}

								//-----city-----
								if (!citySt.equals("")) {

									City<I,RV,RVT,RE,RET> city = null;
									Optional<City<I,RV,RVT,RE,RET>> optionalCity = graph.cityNameIndex().getVertex(citySt);

									if(!optionalCity.isPresent()){

										city = graph.addVertex(graph.City());
										city.set(graph.City().name, citySt);


									}else{
										city = optionalCity.get();
									}

									book.addOutEdge(graph.BookCity(), city);
								}

							}else{
								book = optionalBook.get();
								reference = book.referenceBook_inV();
							}

							//--protein citation relationship
							protein.addOutEdge(graph.ProteinReference(), reference);

//                          TODO see if these fields can somehow be included
//							bookProteinCitationProperties.put(BookProteinCitationRel.FIRST_PROPERTY, firstSt);
//							bookProteinCitationProperties.put(BookProteinCitationRel.LAST_PROPERTY, lastSt);
//							bookProteinCitationProperties.put(BookProteinCitationRel.VOLUME_PROPERTY, volumeSt);
//							bookProteinCitationProperties.put(BookProteinCitationRel.TITLE_PROPERTY, titleSt);

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

								OnlineArticle<I,RV,RVT,RE,RET> onlineArticle = null;
								Optional<OnlineArticle<I,RV,RVT,RE,RET>> optionalOnlineArticle = graph.onlineArticleTitleIndex().getVertex(titleSt);
								Reference<I,RV,RVT,RE,RET> reference = null;

								if(!optionalOnlineArticle.isPresent()){

									onlineArticle = graph.addVertex(graph.OnlineArticle());
									onlineArticle.set(graph.OnlineArticle().title, titleSt);


									reference = graph.addVertex(graph.Reference());
									reference.set(graph.Reference().date, dateSt);
									reference.addOutEdge(graph.ReferenceOnlineArticle(), onlineArticle);

									//---authors association-----
									for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
										reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
									}
									//---consortiums association----
									for(Consortium<I,RV,RVT,RE,RET> consortium : authorsConsortium){
										reference.addOutEdge(graph.ReferenceAuthorConsortium(), consortium);
									}

									//------online journal-----------
									if (!nameSt.equals("")) {

										OnlineJournal<I,RV,RVT,RE,RET> onlineJournal = null;
										Optional<OnlineJournal<I,RV,RVT,RE,RET>> optionalOnlineJournal = graph.onlineJournalNameIndex().getVertex(nameSt);

										if(!optionalOnlineJournal.isPresent()){

											onlineJournal = graph.addVertex(graph.OnlineJournal());
											onlineJournal.set(graph.OnlineJournal().name, nameSt);


										}else{
											onlineJournal = optionalOnlineJournal.get();
										}

										OnlineArticleOnlineJournal<I,RV,RVT,RE,RET> onlineArticleOnlineJournal = onlineArticle.addOutEdge(graph.OnlineArticleOnlineJournal(), onlineJournal);
										onlineArticleOnlineJournal.set(graph.OnlineArticleOnlineJournal().locator, locatorSt);

									}
									//----------------------------

								}else{
									onlineArticle = optionalOnlineArticle.get();
									reference = onlineArticle.referenceOnlineArticle_inV();
								}

								//protein citation
								protein.addOutEdge(graph.ProteinReference(), reference);
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
							String doiSt = "";
							String medlineSt = "";
							String pubmedId = "";

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
								Article<I,RV,RVT,RE,RET> article = null;
								Optional<Article<I,RV,RVT,RE,RET>> optionalArticle = graph.articleTitleIndex().getVertex(titleSt);
								Reference<I,RV,RVT,RE,RET> reference = null;

								if(!optionalArticle.isPresent()){

									article = graph.addVertex(graph.Article());
									article.set(graph.Article().title, titleSt);
									article.set(graph.Article().doId, doiSt);


									if(pubmedId != ""){

										Pubmed<I,RV,RVT,RE,RET> pubmed = null;
										Optional<Pubmed<I,RV,RVT,RE,RET>> optionalPubmed = graph.pubmedIdIndex().getVertex(pubmedId);

										if(!optionalPubmed.isPresent()){
											pubmed = graph.addVertex(graph.Pubmed());
											pubmed.set(graph.Pubmed().id, pubmedId);

										}else{
											pubmed = optionalPubmed.get();
										}
										article.addOutEdge(graph.ArticlePubmed(), pubmed);
									}

									reference = graph.addVertex(graph.Reference());
									reference.set(graph.Reference().date, dateSt);
									reference.addOutEdge(graph.ReferenceArticle(), article);

									//---authors association-----
									for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
										reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
									}
									//---consortiums association----
									for(Consortium<I,RV,RVT,RE,RET> consortium : authorsConsortium){
										reference.addOutEdge(graph.ReferenceAuthorConsortium(), consortium);
									}

									//------journal-----------
									if (!journalNameSt.equals("")) {

										Journal<I,RV,RVT,RE,RET> journal = null;
										Optional<Journal<I,RV,RVT,RE,RET>> optionalJournal = graph.journalNameIndex().getVertex(journalNameSt);

										if(!optionalJournal.isPresent()){
											journal = graph.addVertex(graph.Journal());
											journal.set(graph.Journal().name, journalNameSt);

										}else{
											journal = optionalJournal.get();
										}

										ArticleJournal<I,RV,RVT,RE,RET> articleJournal = article.addOutEdge(graph.ArticleJournal(), journal);
										articleJournal.set(graph.ArticleJournal().volume, volumeSt);
										articleJournal.set(graph.ArticleJournal().first, firstSt);
										articleJournal.set(graph.ArticleJournal().last, lastSt);
									}
									//----------------------------

								}else{
									article = optionalArticle.get();
									reference = article.referenceArticle_inV();
								}

								//protein citation
								protein.addOutEdge(graph.ProteinReference(), reference);

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

							//---authors association-----
							for (Person<I,RV,RVT,RE,RET> person : authorsPerson) {
								reference.addOutEdge(graph.ReferenceAuthorPerson(), person);
							}

							//protein citation
							protein.addOutEdge(graph.ProteinReference(), reference);

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
