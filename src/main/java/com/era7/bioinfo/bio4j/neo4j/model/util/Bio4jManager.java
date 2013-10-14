/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.neo4j.model.util;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.*;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.citation.*;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ncbi.NCBITaxonNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.reactome.ReactomeTermNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.SubcellularLocationParentRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.go.IsAGoRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import com.era7.bioinfo.bioinfoneo4j.Neo4jManager;
import java.util.HashMap;
import java.util.Map;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class Bio4jManager extends Neo4jManager {

    private static boolean alreadyCreated = false;
    private static String PROVIDER_ST = "provider";
    private static String EXACT_ST = "exact";
    private static String FULL_TEXT_ST = "fulltext";
    private static String LUCENE_ST = "lucene";
    private static String TYPE_ST = "type";
     
    
    //----------------main indices names------------------------------
    public static final String NODE_TYPE_INDEX_NAME = "node_type_index";
    public static final String MAIN_NODES_INDEX_NAME = "main_nodes_index";
    
    //----------------main nodes index values--------------------
    public static final String ALTERNATIVE_PRODUCT_INITIATION = "alternative_product_initiation";
    public static final String ALTERNATIVE_PRODUCT_PROMOTER = "alternative_product_promoter";
    public static final String ALTERNATIVE_PRODUCT_RIBOSOMAL_FRAMESHIFTING = "alternative_product_ribosomal_frameshifting";
    public static final String ALTERNATIVE_PRODUCT_SPLICING = "alternative_product_splicing";
    public static final String SEQUENCE_CAUTION_ERRONEOUS_INITIATION = "sequence_caution_erroneous_initiation";
    public static final String SEQUENCE_CAUTION_ERRONEOUS_TRANSLATION = "sequence_caution_erroneous_translation";
    public static final String SEQUENCE_CAUTION_FRAMESHIFT = "sequence_caution_framshift";
    public static final String SEQUENCE_CAUTION_ERRONEOUS_TERMINATION = "sequence_caution_erroneous_termination";
    public static final String SEQUENCE_CAUTION_MISCELLANEOUS_DISCREPANCY = "sequence_caution_miscellaneous_discrepancy";
    public static final String SEQUENCE_CAUTION_ERRONEOUS_GENE_MODEL_PREDICTION = "sequence_caution_erroneous_gene_model_prediction";
    //------------------------------------------------------------
    
    //-----------------node indexes-----------------------
    private Index<Node> mainNodesIndex = null;
    private Index<Node> enzymeIdIndex = null;
    private Index<Node> nodeTypeIndex = null;
    private Index<Node> datasetNameIndex = null;
    private Index<Node> goTermIdIndex = null;
    private Index<Node> proteinAccessionIndex = null;
    private Index<Node> proteinFullNameFullTextIndex = null;
    private Index<Node> proteinGeneNamesFullTextIndex = null;
    private Index<Node> proteinEnsemblPlantsIndex = null;
    private Index<Node> keywordIdIndex = null;
    private Index<Node> keywordNameIndex = null;
    private Index<Node> interproIdIndex = null;
    private Index<Node> pfamIdIndex = null;
    private Index<Node> organismScientificNameIndex = null;
    private Index<Node> organismNcbiTaxonomyIdIndex = null;
    private Index<Node> taxonNameIndex = null;
    private Index<Node> featureTypeNameIndex = null;
    private Index<Node> commentTypeNameIndex = null;
    private Index<Node> isoformIdIndex = null;
    private Index<Node> personNameFullTextIndex = null;
    private Index<Node> consortiumNameIndex = null;
    private Index<Node> instituteNameIndex = null;
    private Index<Node> countryNameIndex = null;
    private Index<Node> cityNameIndex = null;
    private Index<Node> submissionTitleIndex = null;
    private Index<Node> thesisTitleFullTextIndex = null;
    private Index<Node> patentNumberIndex = null;
    private Index<Node> bookNameFullTextIndex = null;
    private Index<Node> publisherNameIndex = null;
    private Index<Node> onlineArticleTitleFullTextIndex = null;
    private Index<Node> onlineJournalNameIndex = null;
    private Index<Node> articleTitleFullTextIndex = null;
    private Index<Node> articleMedlineIdIndex = null;
    private Index<Node> articleDoiIdIndex = null;
    private Index<Node> articlePubmedIdIndex = null;
    private Index<Node> journalNameIndex = null;
    private Index<Node> genomeElementVersionIndex = null;
    private Index<Node> ncbiTaxonIdIndex = null;
    private Index<Node> ncbiTaxonGiIdIndex = null;
    private Index<Node> reactomeTermIdIndex = null;
    private Index<Node> subcellularLocationNameIndex = null;
    //----special indexes----
    //private Index<Node> 
    //------------relationship indexes---------------
    private RelationshipIndex isAGorelIndex = null;
    private RelationshipIndex subcellularLocationParentRelIndex = null;

    /**
     * Constructor
     * @param dbFolder
     */
    public Bio4jManager(String dbFolder) {
        super(dbFolder, firstTimeCalled(), false, null);       

        initializeIndexes(getIndexProps(), getIndexFullTextProps());
        
        System.out.println("graphservice hashcode: " + graphService.hashCode());

    }
    
    /**
     * Constructor
     * @param dbFolder
     */
    public Bio4jManager(String dbFolder, Map<String,String> configFile, boolean readOnlyMode) {
        super(dbFolder, firstTimeCalled(), readOnlyMode, configFile);       

        initializeIndexes(getIndexProps(), getIndexFullTextProps());
        
        System.out.println("graphservice hashcode: " + graphService.hashCode());

    }
    
    /**
     * Constructor
     * @param dbFolder
     */
    public Bio4jManager(String dbFolder, boolean createUnderlyingService, boolean readOnlyMode) {
        
        super(dbFolder, createUnderlyingService, readOnlyMode, null);       

        initializeIndexes(getIndexProps(), getIndexFullTextProps());
        
        System.out.println("graphservice hashcode: " + graphService.hashCode());

    }
    
    /**
     * Creates a new node
     * @param nodeType Type of the new node
     * @return 
     */
    public Node createNode(String nodeType){
        Node node = createNode();
        node.setProperty(BasicEntity.NODE_TYPE_PROPERTY, nodeType);
        return node;
    }
    
    private Map<String, String> getIndexProps(){
        
        Map<String, String> indexProps = new HashMap<>();        
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        
        return indexProps;
    }
    
    private Map<String, String> getIndexFullTextProps(){
        
        Map<String, String> indexFullTextProps = new HashMap<>();
        indexFullTextProps.put(PROVIDER_ST, LUCENE_ST);
        indexFullTextProps.put(TYPE_ST, FULL_TEXT_ST);
        
        return indexFullTextProps;
    }

    private void initializeIndexes(Map<String, String> indexProps, Map<String, String> indexFullTextProps) {
        //----------node indexes-----------
        nodeTypeIndex = graphService.index().forNodes(NODE_TYPE_INDEX_NAME, indexProps);
        enzymeIdIndex = graphService.index().forNodes(EnzymeNode.ENZYME_ID_INDEX, indexProps);
        datasetNameIndex = graphService.index().forNodes(DatasetNode.DATASET_NAME_INDEX, indexProps);
        goTermIdIndex = graphService.index().forNodes(GoTermNode.GO_TERM_ID_INDEX, indexProps);
        proteinAccessionIndex = graphService.index().forNodes(ProteinNode.PROTEIN_ACCESSION_INDEX, indexProps);
        proteinFullNameFullTextIndex = graphService.index().forNodes(ProteinNode.PROTEIN_FULL_NAME_FULL_TEXT_INDEX, indexFullTextProps);
        proteinGeneNamesFullTextIndex = graphService.index().forNodes(ProteinNode.PROTEIN_GENE_NAMES_FULL_TEXT_INDEX, indexFullTextProps);
        proteinEnsemblPlantsIndex = graphService.index().forNodes(ProteinNode.PROTEIN_ENSEMBL_PLANTS_INDEX, indexProps);
        keywordIdIndex = graphService.index().forNodes(KeywordNode.KEYWORD_ID_INDEX, indexProps);
        keywordNameIndex = graphService.index().forNodes(KeywordNode.KEYWORD_NAME_INDEX, indexProps);
        interproIdIndex = graphService.index().forNodes(InterproNode.INTERPRO_ID_INDEX, indexProps);
        pfamIdIndex = graphService.index().forNodes(PfamNode.PFAM_ID_INDEX, indexProps);
        organismScientificNameIndex = graphService.index().forNodes(OrganismNode.ORGANISM_SCIENTIFIC_NAME_INDEX, indexProps);
        organismNcbiTaxonomyIdIndex = graphService.index().forNodes(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, indexProps);
        taxonNameIndex = graphService.index().forNodes(TaxonNode.TAXON_NAME_INDEX, indexProps);
        featureTypeNameIndex = graphService.index().forNodes(FeatureTypeNode.FEATURE_TYPE_NAME_INDEX, indexProps);
        commentTypeNameIndex = graphService.index().forNodes(CommentTypeNode.COMMENT_TYPE_NAME_INDEX, indexProps);
        isoformIdIndex = graphService.index().forNodes(IsoformNode.ISOFORM_ID_INDEX, indexProps);
        personNameFullTextIndex = graphService.index().forNodes(PersonNode.PERSON_NAME_FULL_TEXT_INDEX, indexFullTextProps);
        consortiumNameIndex = graphService.index().forNodes(ConsortiumNode.CONSORTIUM_NAME_INDEX, indexProps);
        instituteNameIndex = graphService.index().forNodes(InstituteNode.INSTITUTE_NAME_INDEX, indexProps);
        countryNameIndex = graphService.index().forNodes(CountryNode.COUNTRY_NAME_INDEX, indexProps);
        cityNameIndex = graphService.index().forNodes(CityNode.CITY_NAME_INDEX, indexProps);
        thesisTitleFullTextIndex = graphService.index().forNodes(ThesisNode.THESIS_TITLE_FULL_TEXT_INDEX, indexFullTextProps);
        submissionTitleIndex = graphService.index().forNodes(SubmissionNode.SUBMISSION_TITLE_INDEX, indexFullTextProps);
        patentNumberIndex = graphService.index().forNodes(PatentNode.PATENT_NUMBER_INDEX, indexProps);
        bookNameFullTextIndex = graphService.index().forNodes(BookNode.BOOK_NAME_FULL_TEXT_INDEX, indexFullTextProps);
        publisherNameIndex = graphService.index().forNodes(PublisherNode.PUBLISHER_NAME_INDEX, indexProps);
        onlineArticleTitleFullTextIndex = graphService.index().forNodes(OnlineArticleNode.ONLINE_ARTICLE_TITLE_FULL_TEXT_INDEX, indexFullTextProps);
        onlineJournalNameIndex = graphService.index().forNodes(OnlineJournalNode.ONLINE_JOURNAL_NAME_INDEX, indexProps);
        articleTitleFullTextIndex = graphService.index().forNodes(ArticleNode.ARTICLE_TITLE_FULL_TEXT_INDEX, indexFullTextProps);
        articleMedlineIdIndex = graphService.index().forNodes(ArticleNode.ARTICLE_MEDLINE_ID_INDEX, indexProps);
        articleDoiIdIndex = graphService.index().forNodes(ArticleNode.ARTICLE_DOI_ID_INDEX, indexProps);
        articlePubmedIdIndex = graphService.index().forNodes(ArticleNode.ARTICLE_PUBMED_ID_INDEX, indexProps);
        journalNameIndex = graphService.index().forNodes(JournalNode.JOURNAL_NAME_INDEX, indexProps);
        genomeElementVersionIndex = graphService.index().forNodes(GenomeElementNode.GENOME_ELEMENT_VERSION_INDEX, indexProps);
        ncbiTaxonIdIndex = graphService.index().forNodes(NCBITaxonNode.NCBI_TAXON_ID_INDEX, indexProps);
        ncbiTaxonGiIdIndex = graphService.index().forNodes(NCBITaxonNode.NCBI_TAXON_GI_ID_INDEX, indexProps);
        reactomeTermIdIndex = graphService.index().forNodes(ReactomeTermNode.REACTOME_TERM_ID_INDEX, indexProps);
        mainNodesIndex = graphService.index().forNodes(MAIN_NODES_INDEX_NAME, indexProps);

        //----------relationship indexes-----
        isAGorelIndex = graphService.index().forRelationships(IsAGoRel.IS_A_REL_INDEX, indexProps);
        subcellularLocationParentRelIndex = graphService.index().forRelationships(SubcellularLocationParentRel.SUBCELLULAR_LOCATION_PARENT_REL_INDEX);

    }

    private static synchronized boolean firstTimeCalled() {
        if (!alreadyCreated) {
            alreadyCreated = true;
            return true;
        } else {
            return false;
        }
    }

    //---------------------------------------------------------------
    //--------------------------INDEXES------------------------------
    //------------------------------------------------------------------
    public Index<Node> getNodeTypeIndex() {
        return nodeTypeIndex;
    }
    
    public Index<Node> getEnzymeIdIndex(){
        return enzymeIdIndex;
    }
    
    public Index<Node> getDatasetNameIndex() {
        return datasetNameIndex;
    }

    public Index<Node> getGoTermIdIndex() {
        return goTermIdIndex;
    }
    
    public Index<Node> getProteinAccessionIndex() {
        return proteinAccessionIndex;
    }

    public Index<Node> getProteinFullNameFullTextIndex() {
        return proteinFullNameFullTextIndex;
    }

    public Index<Node> getProteinGeneNamesFullTextIndex() {
        return proteinGeneNamesFullTextIndex;
    }
    
    public Index<Node> getProteinEnsemblPlantsIndex(){
        return proteinEnsemblPlantsIndex;
    }

    public Index<Node> getKeywordIdIndex() {
        return keywordIdIndex;
    }

    public Index<Node> getKeywordNameIndex() {
        return keywordNameIndex;
    }

    public Index<Node> getInterproIdIndex() {
        return interproIdIndex;
    }
    
    public Index<Node> getPfamIdIndex(){
        return pfamIdIndex;
    }

    public Index<Node> getOrganismScientificNameIndex() {
        return organismScientificNameIndex;
    }

    public Index<Node> getOrganismNcbiTaxonomyIdIndex() {
        return organismNcbiTaxonomyIdIndex;
    }

    public Index<Node> getTaxonNameIndex() {
        return taxonNameIndex;
    }

    public Index<Node> getFeatureTypeNameIndex() {
        return featureTypeNameIndex;
    }

    public Index<Node> getCommentTypeNameIndex() {
        return commentTypeNameIndex;
    }

    public Index<Node> getIsoformIdIndex() {
        return isoformIdIndex;
    }

    public Index<Node> getPersonNameIndex() {
        return personNameFullTextIndex;
    }

    public Index<Node> getConsortiumNameIndex() {
        return consortiumNameIndex;
    }

    public Index<Node> getInstituteNameIndex() {
        return instituteNameIndex;
    }

    public Index<Node> getCountryNameIndex() {
        return countryNameIndex;
    }

    public Index<Node> getCityNameIndex() {
        return cityNameIndex;
    }

    public Index<Node> getThesisFullTextIndex() {
        return thesisTitleFullTextIndex;
    }

    public Index<Node> getSubmissionTitleIndex(){
        return submissionTitleIndex;
    }
    
    public Index<Node> getPatentNumberIndex() {
        return patentNumberIndex;
    }

    public Index<Node> getBookNameFullTextIndex() {
        return bookNameFullTextIndex;
    }

    public Index<Node> getPublisherNameIndex() {
        return publisherNameIndex;
    }

    public Index<Node> getOnlineArticleTitleFullTextIndex() {
        return onlineArticleTitleFullTextIndex;
    }

    public Index<Node> getOnlineJournalNameIndex() {
        return onlineJournalNameIndex;
    }

    public Index<Node> getArticleTitleFullTextIndex() {
        return articleTitleFullTextIndex;
    }

    public Index<Node> getArticleMedLineIdIndex() {
        return articleMedlineIdIndex;
    }

    public Index<Node> getArticleDoiIdIndex() {
        return articleDoiIdIndex;
    }

    public Index<Node> getArticlePubmedIdIndex() {
        return articlePubmedIdIndex;
    }

    public Index<Node> getJournalNameIndex() {
        return journalNameIndex;
    }

    public Index<Node> getGenomeElementVersionIndex() {
        return genomeElementVersionIndex;
    }

    public Index<Node> getNCBITaxonIdIndex() {
        return ncbiTaxonIdIndex;
    }

    public Index<Node> getNCBITaxonGiIdIndex() {
        return ncbiTaxonGiIdIndex;
    }

    public Index<Node> getReactomeTermIdIndex() {
        return reactomeTermIdIndex;
    }
    
    public Index<Node> getSubcellularLocationNameIndex() {
        return subcellularLocationNameIndex;
    }
    
    public Index<Node> getMainNodesIndex(){
        return mainNodesIndex;
    }

    public RelationshipIndex getIsAGoRelIndex() {
        return isAGorelIndex;
    }

    public RelationshipIndex getSubcellularParentRelIndex() {
        return subcellularLocationParentRelIndex;
    }
}
