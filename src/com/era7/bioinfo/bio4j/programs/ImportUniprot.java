/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4jmodel.relationships.protein.*;
import com.era7.bioinfo.bio4jmodel.relationships.sc.*;
import com.era7.bioinfo.bio4jmodel.relationships.*;
import com.era7.bioinfo.bio4jmodel.relationships.comment.*;
import com.era7.bioinfo.bio4jmodel.relationships.aproducts.*;

import com.era7.bioinfo.bio4jmodel.nodes.*;
import com.era7.bioinfo.bio4jmodel.nodes.citation.*;
import com.era7.bioinfo.bio4jmodel.relationships.citation.thesis.*;
import com.era7.bioinfo.bio4jmodel.relationships.citation.book.*;
import com.era7.bioinfo.bio4jmodel.relationships.citation.article.*;
import com.era7.bioinfo.bio4jmodel.relationships.citation.onarticle.*;
import com.era7.bioinfo.bio4jmodel.relationships.citation.patent.PatentAuthorRel;
import com.era7.bioinfo.bio4jmodel.relationships.citation.patent.PatentProteinCitationRel;
import com.era7.bioinfo.bio4jmodel.relationships.citation.uo.*;
import com.era7.bioinfo.bio4jmodel.relationships.citation.submission.*;
import com.era7.bioinfo.uniprotneo4jmodel.model.neo4j.relationships.sc.ErroneousInitiationRel;

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.era7xmlapi.model.XMLElement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.jdom.Element;
import org.neo4j.index.lucene.LuceneIndexBatchInserter;
import org.neo4j.index.lucene.LuceneIndexBatchInserterImpl;
import org.neo4j.kernel.impl.batchinsert.BatchInserter;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;

/**
 *
 * @author ppareja
 */
public class ImportUniprot implements Executable {

    private static final Logger logger = Logger.getLogger("ImportUniprot");
    private static FileHandler fh;
    //------------------nodes properties maps-----------------------------------
    public static Map<String, Object> organismProperties = new HashMap<String, Object>();
    public static Map<String, Object> proteinProperties = new HashMap<String, Object>();
    public static Map<String, Object> keywordProperties = new HashMap<String, Object>();
    public static Map<String, Object> subcellularLocationProperties = new HashMap<String, Object>();
    public static Map<String, Object> interproProperties = new HashMap<String, Object>();
    public static Map<String, Object> taxonProperties = new HashMap<String, Object>();
    public static Map<String, Object> datasetProperties = new HashMap<String, Object>();
    public static Map<String, Object> personProperties = new HashMap<String, Object>();
    public static Map<String, Object> consortiumProperties = new HashMap<String, Object>();
    public static Map<String, Object> instituteProperties = new HashMap<String, Object>();
    public static Map<String, Object> thesisProperties = new HashMap<String, Object>();
    public static Map<String, Object> bookProperties = new HashMap<String, Object>();
    public static Map<String, Object> patentProperties = new HashMap<String, Object>();
    public static Map<String, Object> articleProperties = new HashMap<String, Object>();
    public static Map<String, Object> submissionProperties = new HashMap<String, Object>();
    public static Map<String, Object> onlineArticleProperties = new HashMap<String, Object>();
    public static Map<String, Object> unpublishedObservationProperties = new HashMap<String, Object>();
    public static Map<String, Object> publisherProperties = new HashMap<String, Object>();
    public static Map<String, Object> cityProperties = new HashMap<String, Object>();
    public static Map<String, Object> journalProperties = new HashMap<String, Object>();
    public static Map<String, Object> onlineJournalProperties = new HashMap<String, Object>();
    public static Map<String, Object> countryProperties = new HashMap<String, Object>();
    public static Map<String, Object> isoformProperties = new HashMap<String, Object>();
    public static Map<String, Object> alternativeProductProperties = new HashMap<String, Object>();
    public static Map<String, Object> commentTypeProperties = new HashMap<String, Object>();
    public static Map<String, Object> sequenceCautionProperties = new HashMap<String, Object>();
    //---------------------------------------------------------------------
    //-------------------relationships properties maps--------------------------
    public static Map<String, Object> proteinGoProperties = new HashMap<String, Object>();
    public static Map<String, Object> proteinSubcellularLocationProperties = new HashMap<String, Object>();
    public static Map<String, Object> bookProteinCitationProperties = new HashMap<String, Object>();
    public static Map<String, Object> articleJournalProperties = new HashMap<String, Object>();
    public static Map<String, Object> onlineArticleJournalProperties = new HashMap<String, Object>();
    public static Map<String, Object> commentProperties = new HashMap<String, Object>();
    public static Map<String, Object> onlineInformationCommentProperties = new HashMap<String, Object>();
    public static Map<String, Object> biophysicochemicalCommentProperties = new HashMap<String, Object>();
    public static Map<String, Object> rnaEditingCommentProperties = new HashMap<String, Object>();
    public static Map<String, Object> massSpectrometryCommentProperties = new HashMap<String, Object>();
    //----------------------------------------------------------------------------
    //--------------------------------relationships------------------------------------------
    public static ProteinGoRel proteinGoRel = new ProteinGoRel(null);
    public static ProteinOrganismRel proteinOrganismRel = new ProteinOrganismRel(null);
    public static TaxonParentRel taxonParentRel = new TaxonParentRel(null);
    public static MainTaxonRel mainTaxonRel = new MainTaxonRel(null);
    public static ProteinKeywordRel proteinKeywordRel = new ProteinKeywordRel(null);
    public static MainDatasetRel mainDatasetRel = new MainDatasetRel(null);
    public static ProteinDatasetRel proteinDatasetRel = new ProteinDatasetRel(null);
    public static ProteinInterproRel proteinInterproRel = new ProteinInterproRel(null);
    public static ProteinSubcellularLocationRel proteinSubcellularLocationRel = new ProteinSubcellularLocationRel(null);
    public static SubcellularLocationParentRel subcellularLocationParentRel = new SubcellularLocationParentRel(null);
    public static ThesisAuthorRel thesisAuthorRel = new ThesisAuthorRel(null);
    public static ThesisInstituteRel thesisInstituteRel = new ThesisInstituteRel(null);
    public static ThesisProteinCitationRel thesisProteinCitationRel = new ThesisProteinCitationRel(null);
    public static PatentAuthorRel patentAuthorRel = new PatentAuthorRel(null);
    public static PatentProteinCitationRel patentProteinCitationRel = new PatentProteinCitationRel(null);
    public static SubmissionAuthorPersonRel submissionAuthorPersonRel = new SubmissionAuthorPersonRel(null);
    public static SubmissionAuthorConsortiumRel submissionAuthorConsortiumRel = new SubmissionAuthorConsortiumRel(null);
    public static SubmissionProteinCitationRel submissionProteinCitationRel = new SubmissionProteinCitationRel(null);
    public static BookAuthorRel bookAuthorRel = new BookAuthorRel(null);
    public static BookProteinCitationRel bookProteinCitationRel = new BookProteinCitationRel(null);
    public static BookEditorRel bookEditorRel = new BookEditorRel(null);
    public static BookCityRel bookCityRel = new BookCityRel(null);
    public static BookPublisherRel bookPublisherRel = new BookPublisherRel(null);
    public static ArticleAuthorPersonRel articleAuthorPersonRel = new ArticleAuthorPersonRel(null);
    public static ArticleAuthorConsortiumRel articleAuthorConsortiumRel = new ArticleAuthorConsortiumRel(null);
    public static ArticleJournalRel articleJournalRel = new ArticleJournalRel(null);
    public static ArticleProteinCitationRel articleProteinCitationRel = new ArticleProteinCitationRel(null);
    public static OnlineArticleAuthorPersonRel onlineArticleAuthorPersonRel = new OnlineArticleAuthorPersonRel(null);
    public static OnlineArticleAuthorConsortiumRel onlineArticleAuthorConsortiumRel = new OnlineArticleAuthorConsortiumRel(null);
    public static OnlineArticleJournalRel onlineArticleJournalRel = new OnlineArticleJournalRel(null);
    public static OnlineArticleProteinCitationRel onlineArticleProteinCitationRel = new OnlineArticleProteinCitationRel(null);
    public static UnpublishedObservationAuthorRel unpublishedObservationAuthorRel = new UnpublishedObservationAuthorRel(null);
    public static UnpublishedObservationProteinCitationRel unpublishedObservationProteinCitationRel = new UnpublishedObservationProteinCitationRel(null);
    public static InstituteCountryRel instituteCountryRel = new InstituteCountryRel(null);
    public static IsoformEventGeneratorRel isoformEventGeneratorRel = new IsoformEventGeneratorRel(null);
    public static AlternativeProductInitiationRel alternativeProductInitiationRel = new AlternativeProductInitiationRel(null);
    public static AlternativeProductPromoterRel alternativeProductPromoterRel = new AlternativeProductPromoterRel(null);
    public static AlternativeProductSplicingRel alternativeProductSplicingRel = new AlternativeProductSplicingRel(null);
    public static AlternativeProductRibosomalFrameshiftingRel alternativeProductRibosomalFrameshiftingRel = new AlternativeProductRibosomalFrameshiftingRel(null);
    public static ProteinIsoformRel proteinIsoformRel = new ProteinIsoformRel(null);
    public static ProteinErroneousGeneModelPredictionRel proteinErroneousGeneModelPredictionRel = new ProteinErroneousGeneModelPredictionRel(null);
    public static ProteinErroneousInitiationRel proteinErroneousInitiationRel = new ProteinErroneousInitiationRel(null);
    public static ProteinErroneousTerminationRel proteinErroneousTerminationRel = new ProteinErroneousTerminationRel(null);
    public static ProteinErroneousTranslationRel proteinErroneousTranslationRel = new ProteinErroneousTranslationRel(null);
    public static ProteinFrameshiftRel proteinFrameshiftRel = new ProteinFrameshiftRel(null);
    public static ProteinMiscellaneousDiscrepancyRel proteinMiscellaneousDiscrepancyRel = new ProteinMiscellaneousDiscrepancyRel(null);
    //----comment relationships-----
    public static AllergenCommentRel allergenCommentRel = new AllergenCommentRel(null);
    public static BioPhysicoChemicalPropertiesCommentRel bioPhysicoChemicalPropertiesCommentRel = new BioPhysicoChemicalPropertiesCommentRel(null);
    public static BiotechnologyCommentRel biotechnologyCommentRel = new BiotechnologyCommentRel(null);
    public static CatalyticActivityCommentRel catalyticActivityCommentRel = new CatalyticActivityCommentRel(null);
    public static CautionCommentRel cautionCommentRel = new CautionCommentRel(null);
    public static CofactorCommentRel cofactorCommentRel = new CofactorCommentRel(null);
    public static DevelopmentalStageCommentRel developmentalStageCommentRel = new DevelopmentalStageCommentRel(null);
    public static DiseaseCommentRel diseaseCommentRel = new DiseaseCommentRel(null);
    public static DisruptionPhenotypeCommentRel disruptionPhenotypeCommentRel = new DisruptionPhenotypeCommentRel(null);
    public static DomainCommentRel domainCommentRel = new DomainCommentRel(null);
    public static EnzymeRegulationCommentRel enzymeRegulationCommentRel = new EnzymeRegulationCommentRel(null);
    public static FunctionCommentRel functionCommentRel = new FunctionCommentRel(null);
    public static InductionCommentRel inductionCommentRel = new InductionCommentRel(null);
    public static MassSpectrometryCommentRel massSpectrometryCommentRel = new MassSpectrometryCommentRel(null);
    public static MiscellaneousCommentRel miscellaneousCommentRel = new MiscellaneousCommentRel(null);
    public static OnlineInformationCommentRel onlineInformationCommentRel = new OnlineInformationCommentRel(null);
    public static PathwayCommentRel pathwayCommentRel = new PathwayCommentRel(null);
    public static PharmaceuticalCommentRel pharmaceuticalCommentRel = new PharmaceuticalCommentRel(null);
    public static PolymorphismCommentRel polymorphismCommentRel = new PolymorphismCommentRel(null);
    public static PostTransactionalModificationCommentRel postTransactionalModificationCommentRel = new PostTransactionalModificationCommentRel(null);
    public static RnaEditingCommentRel rnaEditingCommentRel = new RnaEditingCommentRel(null);
    public static SimilarityCommentRel similarityCommentRel = new SimilarityCommentRel(null);
    public static SubunitCommentRel subunitCommentRel = new SubunitCommentRel(null);
    public static TissueSpecificityCommentRel tissueSpecificityCommentRel = new TissueSpecificityCommentRel(null);
    public static ToxicDoseCommentRel toxicDoseCommentRel = new ToxicDoseCommentRel(null);
    public static ErroneousGeneModelPredictionRel erroneousGeneModelPredictionRel = new ErroneousGeneModelPredictionRel(null);
    public static ErroneousInitiationRel erroneousInitiationRel = new ErroneousInitiationRel(null);
    public static ErroneousTerminationRel erroneousTerminationRel = new ErroneousTerminationRel(null);
    public static ErroneousTranslationRel erroneousTranslationRel = new ErroneousTranslationRel(null);
    public static FrameshiftRel frameshiftRel = new FrameshiftRel(null);
    public static MiscellaneousDiscrepancyRel miscellaneousDiscrepancyRel = new MiscellaneousDiscrepancyRel(null);
    //------------------------------------------------------------------------------------------------
    //-----other things....------
    public static long alternativeProductInitiationId;
    public static long alternativeProductPromoterId;
    public static long alternativeProductSplicingId;
    public static long alternativeProductRibosomalFrameshiftingId;
    public static long seqCautionErroneousInitiationId;
    public static long seqCautionErroneousTranslationId;
    public static long seqCautionFrameshiftId;
    public static long seqCautionErroneousTerminationId;
    public static long seqCautionMiscellaneousDiscrepancyId;
    public static long seqCautionErroneousGeneModelPredictionId;
    //---------------------------------


    @Override
    public void execute(ArrayList<String> array) {
        String[] args = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            args[i] = array.get(i);
        }
        main(args);
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("This program expects one parameter: \n"
                    + "1. Uniprot xml filename \n");
        } else {
            File inFile = new File(args[0]);

            String currentAccessionId = "";

            BatchInserter inserter = null;
            LuceneIndexBatchInserter indexService = null;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImportUniprot" + args[0].split("\\.")[0] + ".log", false);

                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                // create the batch inserter
                inserter = new BatchInserterImpl(CommonData.DATABASE_FOLDER, BatchInserterImpl.loadProperties(CommonData.PROPERTIES_FILE_NAME));

                // create the batch index service
                indexService = new LuceneIndexBatchInserterImpl(inserter);


                //----------------------------------------------------------------------------------------------------------------
                //Before starting reading the file there are a few relationships/nodes which
                //must be initialized first
                //------------------ALTERNATIVE PRODUCTS--------------------
                alternativeProductProperties.put(AlternativeProductNode.NAME_PROPERTY, AlternativeProductInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                alternativeProductInitiationId = inserter.createNode(alternativeProductProperties);
                inserter.createRelationship(inserter.getReferenceNode(), alternativeProductInitiationId, alternativeProductInitiationRel, null);

                alternativeProductProperties.put(AlternativeProductNode.NAME_PROPERTY, AlternativeProductPromoterRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                alternativeProductPromoterId = inserter.createNode(alternativeProductProperties);
                inserter.createRelationship(inserter.getReferenceNode(), alternativeProductPromoterId, alternativeProductPromoterRel, null);

                alternativeProductProperties.put(AlternativeProductNode.NAME_PROPERTY, AlternativeProductSplicingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                alternativeProductSplicingId = inserter.createNode(alternativeProductProperties);
                inserter.createRelationship(inserter.getReferenceNode(), alternativeProductSplicingId, alternativeProductSplicingRel, null);

                alternativeProductProperties.put(AlternativeProductNode.NAME_PROPERTY, AlternativeProductRibosomalFrameshiftingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                alternativeProductRibosomalFrameshiftingId = inserter.createNode(alternativeProductProperties);
                inserter.createRelationship(inserter.getReferenceNode(), alternativeProductRibosomalFrameshiftingId, alternativeProductRibosomalFrameshiftingRel, null);

                //---------------------SEQUENCE CAUTION------------------------
                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionErroneousInitiationId = inserter.createNode(sequenceCautionProperties);
                inserter.createRelationship(inserter.getReferenceNode(), seqCautionErroneousInitiationId, erroneousInitiationRel, null);

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousTranslationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionErroneousTranslationId = inserter.createNode(sequenceCautionProperties);
                inserter.createRelationship(inserter.getReferenceNode(), seqCautionErroneousTranslationId, erroneousTranslationRel, null);

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinFrameshiftRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionFrameshiftId = inserter.createNode(sequenceCautionProperties);
                inserter.createRelationship(inserter.getReferenceNode(), seqCautionFrameshiftId, frameshiftRel, null);

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousTerminationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionErroneousTerminationId = inserter.createNode(sequenceCautionProperties);
                inserter.createRelationship(inserter.getReferenceNode(), seqCautionErroneousTerminationId, proteinErroneousTerminationRel, null);

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinMiscellaneousDiscrepancyRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionMiscellaneousDiscrepancyId = inserter.createNode(sequenceCautionProperties);
                inserter.createRelationship(inserter.getReferenceNode(), seqCautionMiscellaneousDiscrepancyId, proteinMiscellaneousDiscrepancyRel, null);

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousGeneModelPredictionRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionErroneousGeneModelPredictionId = inserter.createNode(sequenceCautionProperties);
                inserter.createRelationship(inserter.getReferenceNode(), seqCautionErroneousGeneModelPredictionId, proteinErroneousGeneModelPredictionRel, null);
                //---------------------------------------------------------------------------------------------------------------


                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line = null;
                StringBuilder entryStBuilder = new StringBuilder();


                int counter = 1;
                int limitForPrintingOut = 10000;

                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith("<" + CommonData.ENTRY_TAG_NAME)) {

                        while (!line.trim().startsWith("</" + CommonData.ENTRY_TAG_NAME + ">")) {
                            entryStBuilder.append(line);
                            line = reader.readLine();
                        }
                        //linea final del organism
                        entryStBuilder.append(line);
                        //System.out.println("organismStBuilder.toString() = " + organismStBuilder.toString());
                        XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
                        entryStBuilder.delete(0, entryStBuilder.length());

                        String modifiedDateSt = entryXMLElem.asJDomElement().getAttributeValue(CommonData.ENTRY_MODIFIED_DATE_ATTRIBUTE);

                        String accessionSt = entryXMLElem.asJDomElement().getChildText(CommonData.ENTRY_ACCESSION_TAG_NAME);
                        String nameSt = entryXMLElem.asJDomElement().getChildText(CommonData.ENTRY_NAME_TAG_NAME);
                        String fullNameSt = getProteinFullName(entryXMLElem.asJDomElement().getChild(CommonData.PROTEIN_TAG_NAME));

                        currentAccessionId = accessionSt;


                        Element sequenceElem = entryXMLElem.asJDomElement().getChild(CommonData.ENTRY_SEQUENCE_TAG_NAME);
                        String sequenceSt = sequenceElem.getText();
                        int seqLength = Integer.parseInt(sequenceElem.getAttributeValue(CommonData.SEQUENCE_LENGTH_ATTRIBUTE));
                        float seqMass = Float.parseFloat(sequenceElem.getAttributeValue(CommonData.SEQUENCE_MASS_ATTRIBUTE));


                        //System.out.println("lalala " + seqMass);
                        proteinProperties.put(ProteinNode.MODIFIED_DATE_PROPERTY, modifiedDateSt);
                        proteinProperties.put(ProteinNode.ACCESSION_PROPERTY, accessionSt);
                        proteinProperties.put(ProteinNode.NAME_PROPERTY, nameSt);
                        proteinProperties.put(ProteinNode.FULL_NAME_PROPERTY, fullNameSt);
                        proteinProperties.put(ProteinNode.SEQUENCE_PROPERTY, sequenceSt);
                        proteinProperties.put(ProteinNode.LENGTH_PROPERTY, seqLength);
                        proteinProperties.put(ProteinNode.MASS_PROPERTY, seqMass);

                        //---------------gene-names-------------------
                        Element geneElement = entryXMLElem.asJDomElement().getChild(CommonData.GENE_TAG_NAME);
                        if (geneElement != null) {
                            String geneNamesSt = "";
                            List<Element> genesList = geneElement.getChildren(CommonData.GENE_NAME_TAG_NAME);
                            for (Element geneNameElem : genesList) {
                                geneNamesSt += geneNameElem.getText() + ProteinNode.GENE_NAMES_SEPARATOR;
                            }
                            proteinProperties.put(ProteinNode.GENE_NAMES_PROPERTY, geneNamesSt.substring(0, geneNamesSt.length() - 1));
                        } else {
                            proteinProperties.put(ProteinNode.GENE_NAMES_PROPERTY, "");
                        }
                        //-----------------------------------------

                        long currentProteinId = inserter.createNode(proteinProperties);
                        indexService.index(currentProteinId, ProteinNode.PROTEIN_ACCESSION_INDEX, accessionSt);

                        importProteinComments(entryXMLElem, inserter, indexService, currentProteinId);

                        //--------------------------------datasets--------------------------------------------------
                        String proteinDataSetSt = entryXMLElem.asJDomElement().getAttributeValue(CommonData.ENTRY_DATASET_ATTRIBUTE);
                        long datasetId = indexService.getSingleNode(DatasetNode.DATASET_NAME_INDEX, proteinDataSetSt);
                        if (datasetId < 0) {
                            datasetProperties.put(DatasetNode.NAME_PROPERTY, proteinDataSetSt);
                            datasetId = inserter.createNode(datasetProperties);
                            inserter.createRelationship(inserter.getReferenceNode(), datasetId, mainDatasetRel, null);
                        }
                        inserter.createRelationship(currentProteinId, datasetId, proteinDatasetRel, null);
                        //---------------------------------------------------------------------------------------------


                        importProteinCitations(entryXMLElem,
                                inserter,
                                indexService,
                                currentProteinId);

                        //-------------------------------keywords------------------------------------------------------
                        List<Element> keywordsList = entryXMLElem.asJDomElement().getChildren(CommonData.KEYWORD_TAG_NAME);
                        for (Element keywordElem : keywordsList) {
                            String keywordId = keywordElem.getAttributeValue(CommonData.KEYWORD_ID_ATTRIBUTE);
                            String keywordName = keywordElem.getText();
                            long keywordNodeId = indexService.getSingleNode(KeywordNode.KEYWORD_ID_INDEX, keywordId);
                            if (keywordNodeId < 0) {

                                keywordProperties.put(KeywordNode.ID_PROPERTY, keywordId);
                                keywordProperties.put(KeywordNode.NAME_PROPERTY, keywordName);

                                keywordNodeId = inserter.createNode(keywordProperties);

                                indexService.index(keywordNodeId, KeywordNode.KEYWORD_ID_INDEX, keywordId);
                                indexService.index(keywordNodeId, KeywordNode.KEYWORD_NAME_INDEX, keywordName);
                                //indexService.optimize();
                            }
                            inserter.createRelationship(currentProteinId, keywordNodeId, proteinKeywordRel, null);
                        }
                        //---------------------------------------------------------------------------------------

                        //-------------------------------interpro------------------------------------------------------
                        List<Element> dbReferenceList = entryXMLElem.asJDomElement().getChildren(CommonData.DB_REFERENCE_TAG_NAME);
                        for (Element dbReferenceElem : dbReferenceList) {
                            if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals(CommonData.INTERPRO_DB_REFERENCE_TYPE)) {

                                String interproId = dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_ID_ATTRIBUTE);
                                long interproNodeId = indexService.getSingleNode(InterproNode.INTERPRO_ID_INDEX, interproId);

                                if (interproNodeId < 0) {
                                    String interproEntryNameSt = "";
                                    List<Element> properties = dbReferenceElem.getChildren(CommonData.DB_REFERENCE_PROPERTY_TAG_NAME);
                                    for (Element prop : properties) {
                                        if (prop.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals(CommonData.INTERPRO_ENTRY_NAME)) {
                                            interproEntryNameSt = prop.getAttributeValue(CommonData.DB_REFERENCE_VALUE_ATTRIBUTE);
                                            break;
                                        }
                                    }

                                    interproProperties.put(InterproNode.ID_PROPERTY, interproId);
                                    interproProperties.put(InterproNode.NAME_PROPERTY, interproEntryNameSt);
                                    interproNodeId = inserter.createNode(interproProperties);
                                    indexService.index(interproNodeId, InterproNode.INTERPRO_ID_INDEX, interproId);
                                }

                                inserter.createRelationship(currentProteinId, interproNodeId, proteinInterproRel, null);
                            } else if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals(CommonData.GO_DB_REFERENCE_TYPE)) {

                                String goId = dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_ID_ATTRIBUTE);
                                String evidenceSt = "";
                                List<Element> props = dbReferenceElem.getChildren(CommonData.DB_REFERENCE_PROPERTY_TAG_NAME);
                                for (Element element : props) {
                                    if (element.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals(CommonData.EVIDENCE_TYPE_ATTRIBUTE)) {
                                        evidenceSt = element.getAttributeValue("value");
                                        break;
                                    }
                                }
                                long goTermNodeId = indexService.getSingleNode(GoTermNode.GO_TERM_ID_INDEX, goId);
                                proteinGoProperties.put(ProteinGoRel.EVIDENCE_PROPERTY, evidenceSt);
                                inserter.createRelationship(currentProteinId, goTermNodeId, proteinGoRel, proteinGoProperties);
                            }

                        }
                        //---------------------------------------------------------------------------------------

                        //---------------------------------------------------------------------------------------
                        //--------------------------------organism-----------------------------------------------

                        String scName, commName, synName;
                        scName = "";
                        commName = "";
                        synName = "";

                        Element organismElem = entryXMLElem.asJDomElement().getChild(CommonData.ORGANISM_TAG_NAME);

                        List<Element> organismNames = organismElem.getChildren(CommonData.ORGANISM_NAME_TAG_NAME);
                        for (Element element : organismNames) {
                            String type = element.getAttributeValue(CommonData.ORGANISM_NAME_TYPE_ATTRIBUTE);
                            if (type.equals(CommonData.ORGANISM_SCIENTIFIC_NAME_TYPE)) {
                                scName = element.getText();
                            } else if (type.equals(CommonData.ORGANISM_COMMON_NAME_TYPE)) {
                                commName = element.getText();
                            } else if (type.equals(CommonData.ORGANISM_SYNONYM_NAME_TYPE)) {
                                synName = element.getText();
                            }
                        }

                        long organismNodeId = indexService.getSingleNode(OrganismNode.ORGANISM_SCIENTIFIC_NAME_INDEX, scName);
                        if (organismNodeId < 0) {

                            organismProperties.put(OrganismNode.COMMON_NAME_PROPERTY, commName);
                            organismProperties.put(OrganismNode.SCIENTIFIC_NAME_PROPERTY, scName);
                            organismProperties.put(OrganismNode.SYNONYM_NAME_PROPERTY, synName);


                            List<Element> organismDbRefElems = organismElem.getChildren(CommonData.DB_REFERENCE_TAG_NAME);
                            boolean ncbiIdFound = false;
                            if (organismDbRefElems != null) {
                                for (Element dbRefElem : organismDbRefElems) {
                                    String t = dbRefElem.getAttributeValue("type");
                                    if (t.equals("NCBI Taxonomy")) {
                                        organismProperties.put(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, dbRefElem.getAttributeValue("id"));
                                        ncbiIdFound = true;
                                    }
                                    break;
                                }
                            }
                            if (!ncbiIdFound) {
                                organismProperties.put(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, "");
                            }
                            organismNodeId = inserter.createNode(organismProperties);

                            indexService.index(organismNodeId, OrganismNode.ORGANISM_SCIENTIFIC_NAME_INDEX, scName);
                            indexService.index(organismNodeId, OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, organismProperties.get(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY));

                            Element lineage = entryXMLElem.asJDomElement().getChild("organism").getChild("lineage");
                            List<Element> taxons = lineage.getChildren("taxon");

                            Element firstTaxonElem = taxons.get(0);

                            long firstTaxonId = indexService.getSingleNode(TaxonNode.TAXON_NAME_INDEX, firstTaxonElem.getText());

                            if (firstTaxonId < 0) {

                                String firstTaxonName = firstTaxonElem.getText();
                                taxonProperties.put(TaxonNode.NAME_PROPERTY, firstTaxonElem.getText());
                                firstTaxonId = inserter.createNode(taxonProperties);
                                indexService.index(firstTaxonId, TaxonNode.TAXON_NAME_INDEX, firstTaxonName);
                                inserter.createRelationship(inserter.getReferenceNode(), firstTaxonId, mainTaxonRel, null);

                            } else {
                            }

                            long lastTaxonId = firstTaxonId;
                            for (int i = 1; i < taxons.size(); i++) {
                                String taxonName = taxons.get(i).getText();
                                long currentTaxonId = indexService.getSingleNode(TaxonNode.TAXON_NAME_INDEX, taxonName);
                                if (currentTaxonId < 0) {

                                    taxonProperties.put(TaxonNode.NAME_PROPERTY, taxonName);
                                    currentTaxonId = inserter.createNode(taxonProperties);
                                    indexService.index(currentTaxonId, TaxonNode.TAXON_NAME_INDEX, taxonName);
                                    //indexService.optimize();
                                    inserter.createRelationship(lastTaxonId, currentTaxonId, taxonParentRel, null);


                                } else {
                                }
                                lastTaxonId = currentTaxonId;
                            }

                            inserter.createRelationship(lastTaxonId, organismNodeId, taxonParentRel, null);

                        } else {
                        }


                        //---------------------------------------------------------------------------------------
                        //---------------------------------------------------------------------------------------

                        inserter.createRelationship(currentProteinId, organismNodeId, proteinOrganismRel, null);

                        counter++;
                        if ((counter % limitForPrintingOut) == 0) {
                            String countProteinsSt = counter + " proteins inserted!!";
                            //System.out.println(countProteinsSt);
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
            } finally{
                // closing logger file handler
                fh.close();
                // shutdown, makes sure all changes are written to disk
                inserter.shutdown();
                indexService.shutdown();
            }
        }

    }

    private static void importProteinComments(XMLElement entryXMLElem,
            BatchInserter inserter,
            LuceneIndexBatchInserter indexService,
            long currentProteinId) {

        List<Element> comments = entryXMLElem.asJDomElement().getChildren(CommonData.COMMENT_TAG_NAME);

        for (Element commentElem : comments) {

            String commentTypeSt = commentElem.getAttributeValue(CommonData.COMMENT_TYPE_ATTRIBUTE);

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

            commentProperties.put(BasicCommentRel.TEXT_PROPERTY, commentTextSt);
            commentProperties.put(BasicCommentRel.STATUS_PROPERTY, commentStatusSt);
            commentProperties.put(BasicCommentRel.EVIDENCE_PROPERTY, commentEvidenceSt);

            //-----------------COMMENT TYPE NODE RETRIEVING/CREATION---------------------- 
            long commentTypeId = indexService.getSingleNode(CommentTypeNode.COMMENT_TYPE_NAME_INDEX, commentTypeSt);
            if (commentTypeId < 0) {
                commentTypeProperties.put(CommentTypeNode.NAME_PROPERTY, commentTypeSt);
                commentTypeId = inserter.createNode(commentTypeProperties);
                indexService.index(commentTypeId, CommentTypeNode.COMMENT_TYPE_NAME_INDEX, commentTypeSt);
            }

            //-----toxic dose----------------
            if (commentTypeSt.equals(ToxicDoseCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, toxicDoseCommentRel, commentProperties);
            } //-----caution---------
            else if (commentTypeSt.equals(CautionCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, cautionCommentRel, commentProperties);
            } //-----cofactor---------
            else if (commentTypeSt.equals(CofactorCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, cofactorCommentRel, commentProperties);
            } //-----disease---------
            else if (commentTypeSt.equals(DiseaseCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, diseaseCommentRel, commentProperties);
            } //-----online information---------
            else if (commentTypeSt.equals(OnlineInformationCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                onlineInformationCommentProperties.put(OnlineInformationCommentRel.STATUS_PROPERTY, commentStatusSt);
                onlineInformationCommentProperties.put(OnlineInformationCommentRel.EVIDENCE_PROPERTY, commentEvidenceSt);
                onlineInformationCommentProperties.put(OnlineInformationCommentRel.TEXT_PROPERTY, commentTextSt);
                String nameSt = commentElem.getAttributeValue("name");
                if (nameSt == null) {
                    nameSt = "";
                }
                String linkSt = "";
                Element linkElem = commentElem.getChild("link");
                if (linkElem != null) {
                    String uriSt = linkElem.getAttributeValue("uri");
                    if (uriSt != null) {
                        linkSt = uriSt;
                    }
                }
                onlineInformationCommentProperties.put(OnlineInformationCommentRel.NAME_PROPERTY, nameSt);
                onlineInformationCommentProperties.put(OnlineInformationCommentRel.LINK_PROPERTY, linkSt);
                inserter.createRelationship(currentProteinId, commentTypeId, onlineInformationCommentRel, onlineInformationCommentProperties);
            } //-----tissue specificity---------
            else if (commentTypeSt.equals(TissueSpecificityCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, tissueSpecificityCommentRel, commentProperties);
            } //----------function----------------
            else if (commentTypeSt.equals(FunctionCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, functionCommentRel, commentProperties);
            } //----------biotechnology----------------
            else if (commentTypeSt.equals(BiotechnologyCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, biotechnologyCommentRel, commentProperties);
            } //----------subunit----------------
            else if (commentTypeSt.equals(SubunitCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, subunitCommentRel, commentProperties);
            } //----------polymorphism----------------
            else if (commentTypeSt.equals(PolymorphismCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, polymorphismCommentRel, commentProperties);
            } //----------domain----------------
            else if (commentTypeSt.equals(DomainCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, domainCommentRel, commentProperties);
            } //----------post transactional modification----------------
            else if (commentTypeSt.equals(PostTransactionalModificationCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, postTransactionalModificationCommentRel, commentProperties);
            } //----------catalytic activity----------------
            else if (commentTypeSt.equals(CatalyticActivityCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, catalyticActivityCommentRel, commentProperties);
            } //----------disruption phenotype----------------
            else if (commentTypeSt.equals(DisruptionPhenotypeCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, disruptionPhenotypeCommentRel, commentProperties);
            } //----------biophysicochemical properties----------------
            else if (commentTypeSt.equals(BioPhysicoChemicalPropertiesCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.STATUS_PROPERTY, commentStatusSt);
                biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.EVIDENCE_PROPERTY, commentEvidenceSt);
                biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.TEXT_PROPERTY, commentTextSt);
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

                biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.TEMPERATURE_DEPENDENCE_PROPERTY, temperatureDependenceSt);
                biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.PH_DEPENDENCE_PROPERTY, phDependenceSt);
                biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.KINETICS_XML_PROPERTY, kineticsSt);
                biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.ABSORPTION_MAX_PROPERTY, absorptionMaxSt);
                biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.ABSORPTION_TEXT_PROPERTY, absorptionTextSt);
                biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.REDOX_POTENTIAL_EVIDENCE_PROPERTY, redoxPotentialEvidenceSt);
                biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.REDOX_POTENTIAL_PROPERTY, redoxPotentialSt);

                inserter.createRelationship(currentProteinId, commentTypeId, bioPhysicoChemicalPropertiesCommentRel, biophysicochemicalCommentProperties);

            } //----------allergen----------------
            else if (commentTypeSt.equals(AllergenCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, allergenCommentRel, commentProperties);
            } //----------pathway----------------
            else if (commentTypeSt.equals(PathwayCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, pathwayCommentRel, commentProperties);
            } //----------induction----------------
            else if (commentTypeSt.equals(InductionCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, inductionCommentRel, commentProperties);
            } //----- subcellular location---------
            else if (commentTypeSt.equals(ProteinSubcellularLocationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                List<Element> subcLocations = commentElem.getChildren(CommonData.SUBCELLULAR_LOCATION_TAG_NAME);
                for (Element subcLocation : subcLocations) {
                    List<Element> locations = subcLocation.getChildren(CommonData.LOCATION_TAG_NAME);
                    Element firstLocation = locations.get(0);
                    long firstLocationId = indexService.getSingleNode(SubcellularLocationNode.SUBCELLULAR_LOCATION_NAME_INDEX, firstLocation.getTextTrim());
                    long lastLocationId = firstLocationId;
                    if (firstLocationId < 0) {
                        subcellularLocationProperties.put(SubcellularLocationNode.NAME_PROPERTY, firstLocation.getTextTrim());
                        lastLocationId = inserter.createNode(subcellularLocationProperties);
                    }
                    for (int i = 1; i < locations.size(); i++) {
                        subcellularLocationProperties.put(SubcellularLocationNode.NAME_PROPERTY, locations.get(i).getTextTrim());
                        long tempLocationId = inserter.createNode(subcellularLocationProperties);
                        inserter.createRelationship(tempLocationId, lastLocationId, subcellularLocationParentRel, null);
                        lastLocationId = tempLocationId;
                    }
                    Element lastLocation = locations.get(locations.size() - 1);
                    String evidenceSt = lastLocation.getAttributeValue(CommonData.EVIDENCE_ATTRIBUTE);
                    String statusSt = lastLocation.getAttributeValue(CommonData.STATUS_ATTRIBUTE);
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
                    proteinSubcellularLocationProperties.put(ProteinSubcellularLocationRel.EVIDENCE_PROPERTY, evidenceSt);
                    proteinSubcellularLocationProperties.put(ProteinSubcellularLocationRel.STATUS_PROPERTY, statusSt);
                    proteinSubcellularLocationProperties.put(ProteinSubcellularLocationRel.TOPOLOGY_PROPERTY, topologySt);
                    proteinSubcellularLocationProperties.put(ProteinSubcellularLocationRel.TOPOLOGY_STATUS_PROPERTY, topologyStatusSt);
                    inserter.createRelationship(currentProteinId, lastLocationId, proteinSubcellularLocationRel, proteinSubcellularLocationProperties);

                }
            } //----- alternative products---------
            else if (commentTypeSt.equals(CommonData.COMMENT_ALTERNATIVE_PRODUCTS_TYPE)) {
                List<Element> eventList = commentElem.getChildren("event");
                List<Element> isoformList = commentElem.getChildren("isoform");

                for (Element isoformElem : isoformList) {
                    String isoformIdSt = isoformElem.getChildText("id");
                    String isoformNoteSt = isoformElem.getChildText("note");
                    if (isoformNoteSt == null) {
                        isoformNoteSt = "";
                    }
                    isoformProperties.put(IsoformNode.ID_PROPERTY, isoformIdSt);
                    isoformProperties.put(IsoformNode.NOTE_PROPERTY, isoformNoteSt);
                    long isoformId = createIsoformNode(isoformProperties, inserter, indexService);

                    for (Element eventElem : eventList) {

                        String eventTypeSt = eventElem.getAttributeValue("type");
                        if (eventTypeSt.equals(AlternativeProductInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                            inserter.createRelationship(isoformId, alternativeProductInitiationId, isoformEventGeneratorRel, null);
                        } else if (eventTypeSt.equals(AlternativeProductPromoterRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                            inserter.createRelationship(isoformId, alternativeProductPromoterId, isoformEventGeneratorRel, null);
                        } else if (eventTypeSt.equals(AlternativeProductRibosomalFrameshiftingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                            inserter.createRelationship(isoformId, alternativeProductRibosomalFrameshiftingId, isoformEventGeneratorRel, null);
                        } else if (eventTypeSt.equals(AlternativeProductSplicingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                            inserter.createRelationship(isoformId, alternativeProductSplicingId, isoformEventGeneratorRel, null);
                        }
                    }

                    //protein isoform relationship
                    inserter.createRelationship(currentProteinId, isoformId, proteinIsoformRel, null);

                }
            } //----- sequence caution---------
            else if (commentTypeSt.equals(CommonData.COMMENT_SEQUENCE_CAUTION_TYPE)) {

                sequenceCautionProperties.put(BasicProteinSequenceCautionRel.EVIDENCE_PROPERTY, commentEvidenceSt);
                sequenceCautionProperties.put(BasicProteinSequenceCautionRel.STATUS_PROPERTY, commentStatusSt);
                sequenceCautionProperties.put(BasicProteinSequenceCautionRel.TEXT_PROPERTY, commentTextSt);

                Element conflictElem = commentElem.getChild("conflict");
                if (conflictElem != null) {

                    String conflictTypeSt = conflictElem.getAttributeValue("type");
                    String resourceSt = "";
                    String idSt = "";
                    String versionSt = "";

                    ArrayList<String> positionsList = new ArrayList<String>();

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

                    sequenceCautionProperties.put(BasicProteinSequenceCautionRel.RESOURCE_PROPERTY, resourceSt);
                    sequenceCautionProperties.put(BasicProteinSequenceCautionRel.ID_PROPERTY, idSt);
                    sequenceCautionProperties.put(BasicProteinSequenceCautionRel.VERSION_PROPERTY, versionSt);

                    if (conflictTypeSt.equals(ProteinErroneousGeneModelPredictionRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                        if (positionsList.size() > 0) {
                            for (String tempPosition : positionsList) {
                                sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, tempPosition);
                                inserter.createRelationship(currentProteinId, seqCautionErroneousGeneModelPredictionId, proteinErroneousGeneModelPredictionRel, sequenceCautionProperties);
                            }
                        } else {
                            sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, "");
                            inserter.createRelationship(currentProteinId, seqCautionErroneousGeneModelPredictionId, proteinErroneousGeneModelPredictionRel, sequenceCautionProperties);
                        }

                    } else if (conflictTypeSt.equals(ProteinErroneousInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                        if (positionsList.size() > 0) {
                            for (String tempPosition : positionsList) {
                                sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, tempPosition);
                                inserter.createRelationship(currentProteinId, seqCautionErroneousInitiationId, proteinErroneousInitiationRel, sequenceCautionProperties);
                            }
                        } else {
                            sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, "");
                            inserter.createRelationship(currentProteinId, seqCautionErroneousInitiationId, proteinErroneousInitiationRel, sequenceCautionProperties);
                        }

                    } else if (conflictTypeSt.equals(ProteinErroneousTranslationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                        if (positionsList.size() > 0) {
                            for (String tempPosition : positionsList) {
                                sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, tempPosition);
                                inserter.createRelationship(currentProteinId, seqCautionErroneousTranslationId, proteinErroneousTranslationRel, sequenceCautionProperties);
                            }
                        } else {
                            sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, "");
                            inserter.createRelationship(currentProteinId, seqCautionErroneousTranslationId, proteinErroneousTranslationRel, sequenceCautionProperties);
                        }

                    } else if (conflictTypeSt.equals(ProteinErroneousTerminationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                        if (positionsList.size() > 0) {
                            for (String tempPosition : positionsList) {
                                sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, tempPosition);
                                inserter.createRelationship(currentProteinId, seqCautionErroneousTerminationId, proteinErroneousTerminationRel, sequenceCautionProperties);
                            }
                        } else {
                            sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, "");
                            inserter.createRelationship(currentProteinId, seqCautionErroneousTerminationId, proteinErroneousTerminationRel, sequenceCautionProperties);
                        }

                    } else if (conflictTypeSt.equals(ProteinFrameshiftRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                        if (positionsList.size() > 0) {
                            for (String tempPosition : positionsList) {
                                sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, tempPosition);
                                inserter.createRelationship(currentProteinId, seqCautionFrameshiftId, proteinFrameshiftRel, sequenceCautionProperties);
                            }
                        } else {
                            sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, "");
                            inserter.createRelationship(currentProteinId, seqCautionFrameshiftId, proteinFrameshiftRel, sequenceCautionProperties);
                        }

                    } else if (conflictTypeSt.equals(ProteinMiscellaneousDiscrepancyRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                        if (positionsList.size() > 0) {
                            for (String tempPosition : positionsList) {
                                sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, tempPosition);
                                inserter.createRelationship(currentProteinId, seqCautionMiscellaneousDiscrepancyId, proteinMiscellaneousDiscrepancyRel, sequenceCautionProperties);
                            }
                        } else {
                            sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, "");
                            inserter.createRelationship(currentProteinId, seqCautionMiscellaneousDiscrepancyId, proteinMiscellaneousDiscrepancyRel, sequenceCautionProperties);
                        }

                    }
                }


            } //----------developmental stage----------------
            else if (commentTypeSt.equals(DevelopmentalStageCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, developmentalStageCommentRel, commentProperties);
            } //----------miscellaneous----------------
            else if (commentTypeSt.equals(MiscellaneousCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, miscellaneousCommentRel, commentProperties);
            } //----------similarity----------------
            else if (commentTypeSt.equals(SimilarityCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, similarityCommentRel, commentProperties);
            } //----------RNA editing----------------
            else if (commentTypeSt.equals(RnaEditingCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                rnaEditingCommentProperties.put(RnaEditingCommentRel.STATUS_PROPERTY, commentStatusSt);
                rnaEditingCommentProperties.put(RnaEditingCommentRel.EVIDENCE_PROPERTY, commentEvidenceSt);
                rnaEditingCommentProperties.put(RnaEditingCommentRel.TEXT_PROPERTY, commentTextSt);

                List<Element> locationsList = commentElem.getChildren("location");
                for (Element tempLoc : locationsList) {
                    String positionSt = tempLoc.getChild("position").getAttributeValue("position");
                    rnaEditingCommentProperties.put(RnaEditingCommentRel.POSITION_PROPERTY, positionSt);
                    inserter.createRelationship(currentProteinId, commentTypeId, rnaEditingCommentRel, rnaEditingCommentProperties);
                }

            } //----------pharmaceutical----------------
            else if (commentTypeSt.equals(PharmaceuticalCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, pharmaceuticalCommentRel, commentProperties);
            } //----------enzyme regulation----------------
            else if (commentTypeSt.equals(EnzymeRegulationCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                inserter.createRelationship(currentProteinId, commentTypeId, enzymeRegulationCommentRel, commentProperties);
            } //----------mass spectrometry----------------
            else if (commentTypeSt.equals(MassSpectrometryCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                String methodSt = commentElem.getAttributeValue("method");
                String massSt = commentElem.getAttributeValue("mass");
                if (methodSt == null) {
                    methodSt = "";
                }
                if (massSt == null) {
                    massSt = "";
                }
                String beginSt = "";
                String endSt = "";
                Element locationElem = commentElem.getChild("location");
                if (locationElem != null) {
                    Element beginElem = commentElem.getChild("begin");
                    Element endElem = commentElem.getChild("end");
                    if (beginElem != null) {
                        beginSt = beginElem.getAttributeValue("position");
                    }
                    if (endElem != null) {
                        endSt = endElem.getAttributeValue("position");
                    }
                }

                massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.STATUS_PROPERTY, commentStatusSt);
                massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.EVIDENCE_PROPERTY, commentEvidenceSt);
                massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.TEXT_PROPERTY, commentTextSt);
                massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.METHOD_PROPERTY, methodSt);
                massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.MASS_PROPERTY, massSt);
                massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.BEGIN_PROPERTY, beginSt);
                massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.END_PROPERTY, endSt);

                inserter.createRelationship(currentProteinId, commentTypeId, massSpectrometryCommentRel, massSpectrometryCommentProperties);

            }

        }


    }

    private static String getProteinFullName(Element proteinElement) {
        if (proteinElement == null) {
            return "";
        } else {
            Element recElem = proteinElement.getChild(CommonData.PROTEIN_RECOMMENDED_NAME_TAG_NAME);
            if (recElem == null) {
                return "";
            } else {
                return recElem.getChildText(CommonData.PROTEIN_FULL_NAME_TAG_NAME);
            }
        }
    }

    private static long createIsoformNode(Map<String, Object> isoformProperties,
            BatchInserter inserter,
            LuceneIndexBatchInserter indexService) {
        long isoformId = inserter.createNode(isoformProperties);
        indexService.index(isoformId, IsoformNode.ISOFORM_ID_INDEX, isoformProperties.get(IsoformNode.ID_PROPERTY));
        return isoformId;
    }

    private static long createPersonNode(Map<String, Object> personProperties,
            BatchInserter inserter,
            LuceneIndexBatchInserter indexService) {
        long personId = inserter.createNode(personProperties);
        indexService.index(personId, PersonNode.PERSON_NAME_INDEX, personProperties.get(PersonNode.NAME_PROPERTY));
        return personId;
    }

    private static long createConsortiumNode(Map<String, Object> consortiumProperties,
            BatchInserter inserter,
            LuceneIndexBatchInserter indexService) {
        long consortiumId = inserter.createNode(consortiumProperties);
        indexService.index(consortiumId, ConsortiumNode.CONSORTIUM_NAME_INDEX, consortiumProperties.get(ConsortiumNode.NAME_PROPERTY));
        return consortiumId;
    }

    private static long createInstituteNode(Map<String, Object> instituteProperties,
            BatchInserter inserter,
            LuceneIndexBatchInserter indexService) {
        long instituteId = inserter.createNode(instituteProperties);
        indexService.index(instituteId, InstituteNode.INSTITUTE_NAME_INDEX, instituteProperties.get(InstituteNode.NAME_PROPERTY));
        return instituteId;
    }

    private static long createCountryNode(Map<String, Object> countryProperties,
            BatchInserter inserter,
            LuceneIndexBatchInserter indexService) {
        long countryId = inserter.createNode(countryProperties);
        indexService.index(countryId, CountryNode.COUNTRY_NAME_INDEX, countryProperties.get(CountryNode.NAME_PROPERTY));
        return countryId;
    }

    private static long createCityNode(Map<String, Object> cityProperties,
            BatchInserter inserter,
            LuceneIndexBatchInserter indexService) {
        long cityId = inserter.createNode(cityProperties);
        indexService.index(cityId, CityNode.CITY_NAME_INDEX, cityProperties.get(CityNode.NAME_PROPERTY));
        return cityId;
    }

    private static void importProteinCitations(XMLElement entryXMLElem,
            BatchInserter inserter,
            LuceneIndexBatchInserter indexService,
            long currentProteinId) {

        List<Element> referenceList = entryXMLElem.asJDomElement().getChildren(CommonData.REFERENCE_TAG_NAME);
        for (Element reference : referenceList) {
            List<Element> citationsList = reference.getChildren(CommonData.CITATION_TAG_NAME);
            for (Element citation : citationsList) {

                String citationType = citation.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE);

                List<Long> authorsPersonNodesIds = new ArrayList<Long>();
                List<Long> authorsConsortiumNodesIds = new ArrayList<Long>();

                List<Element> authorPersonElems = citation.getChild("authorList").getChildren("person");
                List<Element> authorConsortiumElems = citation.getChild("authorList").getChildren("consortium");

                for (Element person : authorPersonElems) {
                    long personId = indexService.getSingleNode(PersonNode.PERSON_NAME_INDEX, person.getAttributeValue("name"));
                    if (personId < 0) {
                        personProperties.put(PersonNode.NAME_PROPERTY, person.getAttributeValue("name"));
                        personId = createPersonNode(personProperties, inserter, indexService);
                    }
                    authorsPersonNodesIds.add(personId);
                }

                for (Element consortium : authorConsortiumElems) {
                    long consortiumId = indexService.getSingleNode(ConsortiumNode.CONSORTIUM_NAME_INDEX, consortium.getAttributeValue("name"));
                    if (consortiumId < 0) {
                        consortiumProperties.put(ConsortiumNode.NAME_PROPERTY, consortium.getAttributeValue("name"));
                        consortiumId = createConsortiumNode(consortiumProperties, inserter, indexService);
                    }
                    authorsConsortiumNodesIds.add(consortiumId);
                }

                //----------------------------------------------------------------------------
                //-----------------------------THESIS-----------------------------------------
                if (citationType.equals(ThesisNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                    String dateSt = citation.getAttributeValue("date");
                    String titleSt = citation.getChildText("title");
                    if (dateSt == null) {
                        dateSt = "";
                    }
                    if (titleSt == null) {
                        titleSt = "";
                    }
                    thesisProperties.put(ThesisNode.DATE_PROPERTY, dateSt);
                    thesisProperties.put(ThesisNode.TITLE_PROPERTY, titleSt);
                    //---thesis node creation and indexing
                    long thesisId = inserter.createNode(thesisProperties);
                    indexService.index(thesisId, ThesisNode.THESIS_TITLE_INDEX, titleSt);
                    //---authors association-----
                    for (long personId : authorsPersonNodesIds) {
                        inserter.createRelationship(thesisId, personId, thesisAuthorRel, null);
                    }

                    //-----------institute-----------------------------
                    String instituteSt = citation.getAttributeValue("institute");
                    String countrySt = citation.getAttributeValue("country");
                    if (instituteSt != null) {
                        long instituteId = indexService.getSingleNode(InstituteNode.INSTITUTE_NAME_INDEX, instituteSt);
                        if (instituteId < 0) {
                            instituteProperties.put(InstituteNode.NAME_PROPERTY, instituteSt);
                            instituteId = createInstituteNode(instituteProperties, inserter, indexService);
                        }
                        if (countrySt != null) {
                            long countryId = indexService.getSingleNode(CountryNode.COUNTRY_NAME_INDEX, countrySt);
                            if (countryId < 0) {
                                countryProperties.put(CountryNode.NAME_PROPERTY, countrySt);
                                countryId = createCountryNode(countryProperties, inserter, indexService);
                            }
                            inserter.createRelationship(instituteId, countryId, instituteCountryRel, null);
                        }
                        inserter.createRelationship(thesisId, instituteId, thesisInstituteRel, null);
                    }

                    //--protein citation relationship
                    inserter.createRelationship(thesisId, currentProteinId, thesisProteinCitationRel, null);


                    //----------------------------------------------------------------------------
                    //-----------------------------PATENT-----------------------------------------
                } else if (citationType.equals(PatentNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

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
                        long patentId = indexService.getSingleNode(PatentNode.PATENT_NUMBER_INDEX, numberSt);

                        if (patentId < 0) {
                            patentProperties.put(PatentNode.NUMBER_PROPERTY, numberSt);
                            patentProperties.put(PatentNode.DATE_PROPERTY, dateSt);
                            patentProperties.put(PatentNode.TITLE_PROPERTY, titleSt);
                            //---patent node creation and indexing
                            patentId = inserter.createNode(patentProperties);
                            indexService.index(patentId, PatentNode.PATENT_NUMBER_INDEX, numberSt);
                            //---authors association-----
                            for (long personId : authorsPersonNodesIds) {
                                inserter.createRelationship(patentId, personId, patentAuthorRel, null);
                            }
                        }

                        //--protein citation relationship
                        inserter.createRelationship(patentId, currentProteinId, patentProteinCitationRel, null);
                    }


                    //----------------------------------------------------------------------------
                    //-----------------------------SUBMISSION-----------------------------------------
                } else if (citationType.equals(SubmissionNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                    String dateSt = citation.getAttributeValue("date");
                    String titleSt = citation.getChildText("title");
                    if (dateSt == null) {
                        dateSt = "";
                    }
                    if (titleSt == null) {
                        titleSt = "";
                    }
                    submissionProperties.put(SubmissionNode.DATE_PROPERTY, dateSt);
                    submissionProperties.put(SubmissionNode.TITLE_PROPERTY, titleSt);

                    //---submission node creation and indexing
                    long submissionId = inserter.createNode(submissionProperties);
                    //indexService.index(submissionId, PatentNode.PATENT_NUMBER_INDEX, numberSt); --> There's no need for indexing in principle
                    //---authors association-----
                    for (long personId : authorsPersonNodesIds) {
                        inserter.createRelationship(submissionId, personId, submissionAuthorPersonRel, null);
                    }

                    //--protein citation relationship
                    inserter.createRelationship(submissionId, currentProteinId, submissionProteinCitationRel, null);



                    //----------------------------------------------------------------------------
                    //-----------------------------BOOK-----------------------------------------
                } else if (citationType.equals(BookNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

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

                    long bookId = indexService.getSingleNode(BookNode.BOOK_NAME_INDEX, nameSt);

                    if (bookId < 0) {
                        bookProperties.put(BookNode.NAME_PROPERTY, nameSt);
                        bookProperties.put(BookNode.DATE_PROPERTY, dateSt);
                        //---book node creation and indexing
                        bookId = inserter.createNode(bookProperties);
                        indexService.index(bookId, BookNode.BOOK_NAME_INDEX, nameSt);
                        //---authors association-----
                        for (long personId : authorsPersonNodesIds) {
                            inserter.createRelationship(bookId, personId, bookAuthorRel, null);
                        }

                        //---editor association-----
                        Element editorListElem = citation.getChild("editorList");
                        if (editorListElem != null) {
                            List<Element> editorsElems = editorListElem.getChildren("person");
                            for (Element person : editorsElems) {
                                long editorId = indexService.getSingleNode(PersonNode.PERSON_NAME_INDEX, person.getAttributeValue("name"));
                                if (editorId < 0) {
                                    personProperties.put(PersonNode.NAME_PROPERTY, person.getAttributeValue("name"));
                                    editorId = createPersonNode(personProperties, inserter, indexService);
                                }
                                //editor association
                                inserter.createRelationship(bookId, editorId, bookEditorRel, null);
                            }
                        }


                        //----publisher--
                        if (!publisherSt.equals("")) {
                            long publisherId = indexService.getSingleNode(PublisherNode.PUBLISHER_NAME_INDEX, publisherSt);
                            if (publisherId < 0) {
                                publisherProperties.put(PublisherNode.NAME_PROPERTY, publisherSt);
                                publisherId = inserter.createNode(publisherProperties);
                                indexService.index(publisherId, PublisherNode.PUBLISHER_NAME_INDEX, publisherSt);
                            }
                            inserter.createRelationship(bookId, publisherId, bookPublisherRel, null);
                        }

                        //-----city-----
                        if (!citySt.equals("")) {
                            long cityId = indexService.getSingleNode(CityNode.CITY_NAME_INDEX, citySt);
                            if (cityId < 0) {
                                cityProperties.put(CityNode.NAME_PROPERTY, citySt);
                                cityId = createCityNode(cityProperties, inserter, indexService);
                            }
                            inserter.createRelationship(bookId, cityId, bookCityRel, null);
                        }
                    }

                    bookProteinCitationProperties.put(BookProteinCitationRel.FIRST_PROPERTY, firstSt);
                    bookProteinCitationProperties.put(BookProteinCitationRel.LAST_PROPERTY, lastSt);
                    bookProteinCitationProperties.put(BookProteinCitationRel.VOLUME_PROPERTY, volumeSt);
                    bookProteinCitationProperties.put(BookProteinCitationRel.TITLE_PROPERTY, titleSt);
                    //--protein citation relationship
                    inserter.createRelationship(bookId, currentProteinId, bookProteinCitationRel, bookProteinCitationProperties);


                    //----------------------------------------------------------------------------
                    //-----------------------------ONLINE ARTICLE-----------------------------------------
                } else if (citationType.equals(OnlineArticleNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                    String locatorSt = citation.getChildText("locator");
                    String nameSt = citation.getAttributeValue("name");
                    String titleSt = citation.getChildText("title");

                    if (titleSt == null) {
                        titleSt = "";
                    }
                    if (nameSt == null) {
                        nameSt = "";
                    }
                    if (locatorSt == null) {
                        locatorSt = "";
                    }

                    long onlineArticleId = indexService.getSingleNode(OnlineArticleNode.ONLINE_ARTICLE_TITLE_INDEX, titleSt);
                    if (onlineArticleId < 0) {
                        onlineArticleProperties.put(OnlineArticleNode.TITLE_PROPERTY, titleSt);
                        onlineArticleId = inserter.createNode(onlineArticleProperties);
                        if (!titleSt.equals("")) {
                            indexService.index(onlineArticleId, OnlineArticleNode.ONLINE_ARTICLE_TITLE_INDEX, titleSt);
                        }

                        //---authors person association-----
                        for (long personId : authorsPersonNodesIds) {
                            inserter.createRelationship(onlineArticleId, personId, onlineArticleAuthorPersonRel, null);
                        }
                        //---authors consortium association-----
                        for (long consortiumId : authorsConsortiumNodesIds) {
                            inserter.createRelationship(onlineArticleId, consortiumId, onlineArticleAuthorConsortiumRel, null);
                        }

                        //------journal-----------
                        if (!nameSt.equals("")) {
                            long onlineJournalId = indexService.getSingleNode(OnlineJournalNode.ONLINE_JOURNAL_NAME_INDEX, nameSt);
                            if (onlineJournalId < 0) {
                                onlineJournalProperties.put(OnlineJournalNode.NAME_PROPERTY, nameSt);
                                onlineJournalId = inserter.createNode(onlineJournalProperties);
                                indexService.index(onlineJournalId, OnlineJournalNode.ONLINE_JOURNAL_NAME_INDEX, nameSt);
                            }

                            onlineArticleJournalProperties.put(OnlineArticleJournalRel.LOCATOR_PROPERTY, locatorSt);
                            inserter.createRelationship(onlineArticleId, onlineJournalId, onlineArticleJournalRel, onlineArticleJournalProperties);
                        }
                        //----------------------------
                    }
                    //protein citation
                    inserter.createRelationship(onlineArticleId, currentProteinId, onlineArticleProteinCitationRel, null);


                    //----------------------------------------------------------------------------
                    //-----------------------------ARTICLE-----------------------------------------
                } else if (citationType.equals(ArticleNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                    String journalNameSt = citation.getAttributeValue("name");
                    String dateSt = citation.getAttributeValue("date");
                    String titleSt = citation.getChildText("title");
                    String firstSt = citation.getAttributeValue("first");
                    String lastSt = citation.getAttributeValue("last");
                    String volumeSt = citation.getAttributeValue("volume");
                    String doiSt = "";
                    String medlineSt = "";
                    String pubmedSt = "";

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
                        if (tempDbRef.getAttributeValue("type").equals("DOI")) {
                            doiSt = tempDbRef.getAttributeValue("id");
                        } else if (tempDbRef.getAttributeValue("type").equals("MEDLINE")) {
                            medlineSt = tempDbRef.getAttributeValue("id");
                        } else if (tempDbRef.getAttributeValue("type").equals("PubMed")) {
                            pubmedSt = tempDbRef.getAttributeValue("id");
                        }
                    }

                    long articleId = indexService.getSingleNode(ArticleNode.ARTICLE_TITLE_INDEX, titleSt);
                    if (articleId < 0) {
                        articleProperties.put(ArticleNode.TITLE_PROPERTY, titleSt);
                        articleProperties.put(ArticleNode.DOI_ID_PROPERTY, doiSt);
                        articleProperties.put(ArticleNode.MEDLINE_ID_PROPERTY, medlineSt);
                        articleProperties.put(ArticleNode.PUBMED_ID_PROPERTY, pubmedSt);
                        articleId = inserter.createNode(articleProperties);
                        if (!titleSt.equals("")) {
                            indexService.index(articleId, ArticleNode.ARTICLE_TITLE_INDEX, titleSt);
                        }

                        //---authors person association-----
                        for (long personId : authorsPersonNodesIds) {
                            inserter.createRelationship(articleId, personId, articleAuthorPersonRel, null);
                        }
                        //---authors consortium association-----
                        for (long consortiumId : authorsConsortiumNodesIds) {
                            inserter.createRelationship(articleId, consortiumId, articleAuthorConsortiumRel, null);
                        }

                        //------journal-----------
                        if (!journalNameSt.equals("")) {
                            long journalId = indexService.getSingleNode(JournalNode.JOURNAL_NAME_INDEX, journalNameSt);
                            if (journalId < 0) {
                                journalProperties.put(JournalNode.NAME_PROPERTY, journalNameSt);
                                journalId = inserter.createNode(journalProperties);
                                indexService.index(journalId, JournalNode.JOURNAL_NAME_INDEX, journalNameSt);
                            }

                            articleJournalProperties.put(ArticleJournalRel.DATE_PROPERTY, dateSt);
                            articleJournalProperties.put(ArticleJournalRel.FIRST_PROPERTY, firstSt);
                            articleJournalProperties.put(ArticleJournalRel.LAST_PROPERTY, lastSt);
                            articleJournalProperties.put(ArticleJournalRel.VOLUME_PROPERTY, volumeSt);
                            inserter.createRelationship(articleId, journalId, articleJournalRel, articleJournalProperties);
                        }
                        //----------------------------
                    }
                    //protein citation
                    inserter.createRelationship(articleId, currentProteinId, articleProteinCitationRel, null);


                    //----------------------------------------------------------------------------
                    //----------------------UNPUBLISHED OBSERVATIONS-----------------------------------------
                } else if (citationType.equals(UnpublishedObservationNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                    String dateSt = citation.getAttributeValue("date");
                    if (dateSt != null) {
                        dateSt = "";
                    }

                    unpublishedObservationProperties.put(UnpublishedObservationNode.DATE_PROPERTY, dateSt);
                    long unpublishedObservationId = inserter.createNode(unpublishedObservationProperties);
                    //---authors person association-----
                    for (long personId : authorsPersonNodesIds) {
                        inserter.createRelationship(unpublishedObservationId, personId, unpublishedObservationAuthorRel, null);
                    }


                }
            }
        }


    }


}
