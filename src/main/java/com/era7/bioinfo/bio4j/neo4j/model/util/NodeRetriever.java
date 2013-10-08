/*
 * Copyright (C) 2010-2013  "Bio4j"
 *
 * This file is part of Bio4j
 *
 * Bio4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.era7.bioinfo.bio4j.neo4j.model.util;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.*;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.citation.*;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ncbi.NCBITaxonNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.reactome.ReactomeTermNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.refseq.GenomeElementNode;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.IndexHits;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class NodeRetriever {
    
    protected Bio4jManager manager;
    
    
    public NodeRetriever(Bio4jManager bio4jManager){
        manager = bio4jManager;
    }
        
    //-------------------------------------------------------------------
    //--------------------------ENZYME-----------------------------------
    
    public EnzymeNode getEnzymeById(String id){
        IndexHits<Node> hits = manager.getEnzymeIdIndex().get(EnzymeNode.ENZYME_ID_INDEX, id);
        
        if(hits.hasNext()){
            return new EnzymeNode(hits.getSingle());
        }else{
            return null;
        }
    }
    
    //-------------------------------------------------------------------
    //--------------------------DATASETS-----------------------------------      
    public DatasetNode getDatasetByName(String name){
        IndexHits<Node> hits = manager.getDatasetNameIndex().get(DatasetNode.DATASET_NAME_INDEX, name);        
        if(hits.hasNext()){
            return new DatasetNode(hits.getSingle());
        }else{
            return null;
        }
    }
    public DatasetNode getSwissProtDataset(){
        IndexHits<Node> hits = manager.getDatasetNameIndex().get(DatasetNode.DATASET_NAME_INDEX, DatasetNode.SWISS_PROT_DATASET_NAME);        
        if(hits.hasNext()){
            return new DatasetNode(hits.getSingle());
        }else{
            return null;
        }
    }
    public DatasetNode getTremblDataset(){
        IndexHits<Node> hits = manager.getDatasetNameIndex().get(DatasetNode.DATASET_NAME_INDEX, DatasetNode.TREMBL_DATASET_NAME);        
        if(hits.hasNext()){
            return new DatasetNode(hits.getSingle());
        }else{
            return null;
        }
    }
    
    
    //-------------------------------------------------------------------
    //--------------------------REFSEQ-----------------------------------
    
    
    public GenomeElementNode getGenomeElementByVersion(String version){
        
        IndexHits<Node> hits = manager.getGenomeElementVersionIndex().get(GenomeElementNode.GENOME_ELEMENT_VERSION_INDEX, version);
        
        if(hits.hasNext()){
            return new GenomeElementNode(hits.getSingle());
        }else{
            return null;
        }
    }
    
    
    //-------------------------------------------------------------------
    //--------------------GENE ONTOLOGY--------------------------------
    
    /**
     * 
     * @param goId
     * @return GoTermNode with the id provided
     */
    public GoTermNode getGoTermById(String goId){
        
        IndexHits<Node> hits = manager.getGoTermIdIndex().get(GoTermNode.GO_TERM_ID_INDEX, goId);
        
        if(hits.hasNext()){
            return new GoTermNode(hits.getSingle());
        }else{
            return null;
        }        
    }
    public GoTermNode getMolecularFunctionGoTerm(){
        IndexHits<Node> hits = manager.getGoTermIdIndex().get(GoTermNode.GO_TERM_ID_INDEX, GoTermNode.MOLECULAR_FUNCTION_GO_ID);
        if(hits.hasNext()){
            return new GoTermNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    public GoTermNode getBiologicalProcessGoTerm(){
        IndexHits<Node> hits = manager.getGoTermIdIndex().get(GoTermNode.ID_PROPERTY, GoTermNode.BIOLOGICAL_PROCESS_GO_ID);
        if(hits.hasNext()){
            return new GoTermNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    public GoTermNode getCellularComponentGoTerm(){
        IndexHits<Node> hits = manager.getGoTermIdIndex().get(GoTermNode.ID_PROPERTY, GoTermNode.CELLULAR_COMPONENT_GO_ID);
        if(hits.hasNext()){
            return new GoTermNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------PROTEINS--------------------------------
    
    /**
     * 
     * @param proteinAccession 
     * @return ProteinNode with the accession provided
     */
    public ProteinNode getProteinNodeByAccession(String proteinAccession){
        
        IndexHits<Node> hits = manager.getProteinAccessionIndex().get(ProteinNode.PROTEIN_ACCESSION_INDEX, proteinAccession);
        
        if(hits.hasNext()){
            return new ProteinNode(hits.getSingle());
        }else{
            return null;
        }        
    }
    
    /**
     * 
     * @param ensemblPlantsRef 
     * @return ProteinNode with the Ensembl Plants reference provided
     */
    public ProteinNode getProteinNodeByEnsemblPlantsRef(String ensemblPlantsRef){
        
        IndexHits<Node> hits = manager.getProteinEnsemblPlantsIndex().get(ProteinNode.PROTEIN_ENSEMBL_PLANTS_INDEX, ensemblPlantsRef);
        
        if(hits.hasNext()){
            return new ProteinNode(hits.getSingle());
        }else{
            return null;
        }        
    }
    
    /**
     * 
     * @param proteinFullName
     * @return List of proteins (if any) which match the full name provided
     */
    public List<ProteinNode> getProteinsByFullName(String proteinFullName){
        
        IndexHits<Node> hits = manager.getProteinFullNameFullTextIndex().query(ProteinNode.PROTEIN_FULL_NAME_FULL_TEXT_INDEX, proteinFullName);
        
        List<ProteinNode> list = new ArrayList<ProteinNode>();
        
        while(hits.hasNext()){
            list.add(new ProteinNode(hits.next()));
        }        
        return list;        
    }
    
    /**
     * 
     * @param proteinGeneName
     * @return List of proteins (if any) which match the gene name provided
     */
    public List<ProteinNode> getProteinsByGeneNames(String proteinGeneName){
        
        IndexHits<Node> hits = manager.getProteinGeneNamesFullTextIndex().query(ProteinNode.PROTEIN_GENE_NAMES_FULL_TEXT_INDEX, proteinGeneName);
        
        List<ProteinNode> list = new ArrayList<ProteinNode>();
        
        while(hits.hasNext()){
            list.add(new ProteinNode(hits.next()));
        }        
        return list;        
    }
    
    
    //-------------------------------------------------------------------
    //--------------------KEYWORDS--------------------------------
    
    /**
     * 
     * @param keywordId 
     * @return KeywordNode with the id provided
     */
    public KeywordNode getKeywordById(String keywordId){
        
        IndexHits<Node> hits = manager.getKeywordIdIndex().get(KeywordNode.KEYWORD_ID_INDEX, keywordId);
        
        if(hits.hasNext()){
            return new KeywordNode(hits.getSingle());
        }else{
            return null;
        }        
    }
    
    /**
     * 
     * @param keywordName 
     * @return KeywordNode with the id provided
     */
    public KeywordNode getKeywordByName(String keywordName){
        
        IndexHits<Node> hits = manager.getKeywordNameIndex().get(KeywordNode.KEYWORD_NAME_INDEX, keywordName);
        
        if(hits.hasNext()){
            return new KeywordNode(hits.getSingle());
        }else{
            return null;
        }        
    }
    
    
    //-------------------------------------------------------------------
    //--------------------INTERPRO--------------------------------
    /**
     * 
     * @param interproId 
     * @return InterproNode with the id provided
     */
    public InterproNode getInterproById(String interproId){
        
        IndexHits<Node> hits = manager.getInterproIdIndex().get(InterproNode.INTERPRO_ID_INDEX, interproId);
        
        if(hits.hasNext()){
            return new InterproNode(hits.getSingle());
        }else{
            return null;
        }        
    }
    
    //-------------------------------------------------------------------
    //--------------------PFAM--------------------------------
    /**
     * 
     * @param pfamId 
     * @return PfamNode with the id provided
     */
    public PfamNode getPfamById(String pfamId){
        
        IndexHits<Node> hits = manager.getPfamIdIndex().get(PfamNode.PFAM_ID_INDEX, pfamId);
        
        if(hits.hasNext()){
            return new PfamNode(hits.getSingle());
        }else{
            return null;
        }        
    }
    
    //-------------------------------------------------------------------
    //--------------------ORGANISM--------------------------------
    /**
     * 
     * @param scientificName 
     * @return OrganismNode with the scientific name provided
     */
    public OrganismNode getOrganismByScientificName(String scientificName){
        
        IndexHits<Node> hits = manager.getOrganismScientificNameIndex().get(OrganismNode.ORGANISM_SCIENTIFIC_NAME_INDEX, scientificName);
        
        if(hits.hasNext()){
            return new OrganismNode(hits.getSingle());
        }else{
            return null;
        }        
    }
    /**
     * 
     * @param ncbiTaxonomyId 
     * @return OrganismNode with the scientific name provided
     */
    public OrganismNode getOrganismByNCBITaxonomyId(String ncbiTaxonomyId){
        
        IndexHits<Node> hits = manager.getOrganismNcbiTaxonomyIdIndex().get(OrganismNode.ORGANISM_NCBI_TAXONOMY_ID_INDEX, ncbiTaxonomyId);
        
        if(hits.hasNext()){
            return new OrganismNode(hits.getSingle());
        }else{
            return null;
        }        
    }
    
    //-------------------------------------------------------------------
    //--------------------TAXON--------------------------------
    
    /**
     * 
     * @param taxonName
     * @return TaxonNode with the name provided
     */
    public TaxonNode getTaxonByName(String taxonName){
        IndexHits<Node> hits = manager.getTaxonNameIndex().get(TaxonNode.TAXON_NAME_INDEX, taxonName);        
        if(hits.hasNext()){
            return new TaxonNode(hits.getSingle());
        }else{
            return null;
        }   
    }
    
    /**
     * 
     * @param taxId
     * @return NCBITaxonNode with the tax id provided
     */
    public NCBITaxonNode getNCBITaxonByTaxId(String taxId){
        IndexHits<Node> hits = manager.getNCBITaxonIdIndex().get(NCBITaxonNode.NCBI_TAXON_ID_INDEX, taxId);        
        if(hits.hasNext()){
            return new NCBITaxonNode(hits.getSingle());
        }else{
            return null;
        }   
    }
    /**
     * 
     * @param giId
     * @return NCBITaxonNode with the tax id provided
     */
    public NCBITaxonNode getNCBITaxonByGiId(String giId){
        IndexHits<Node> hits = manager.getNCBITaxonGiIdIndex().get(NCBITaxonNode.NCBI_TAXON_GI_ID_INDEX, giId);        
        if(hits.hasNext()){
            return new NCBITaxonNode(hits.getSingle());
        }else{
            return null;
        }   
    }
    
    //-------------------------------------------------------------------
    //--------------------ISOFORMS--------------------------------
    /**
     * 
     * @param isoformId
     * @return IsoformNode with the id provided
     */
    public IsoformNode getIsoformById(String isoformId){
        IndexHits<Node> hits = manager.getIsoformIdIndex().get(IsoformNode.ISOFORM_ID_INDEX, isoformId);        
        if(hits.hasNext()){
            return new IsoformNode(hits.getSingle());
        }else{
            return null;
        }   
    }
    //-------------------------------------------------------------------
    //--------------------PERSON--------------------------------
    /**
     * 
     * @param personName
     * @return PersonNode list with the name matching the value provided
     */
    public List<PersonNode> getPeopleByName(String personName){
        
        IndexHits<Node> hits = manager.getPersonNameIndex().get(PersonNode.PERSON_NAME_FULL_TEXT_INDEX, personName);
        
        List<PersonNode> list = new ArrayList<PersonNode>();
        
        while(hits.hasNext()){
            list.add(new PersonNode(hits.next()));
        }        
        return list; 
    }
    
    //-------------------------------------------------------------------
    //--------------------CONSORTIUMS--------------------------------
    /**
     * 
     * @param consortiumName
     * @return ConsortiumNode with the name provided
     */
    public ConsortiumNode getConsortiumByName(String consortiumName){
        IndexHits<Node> hits = manager.getConsortiumNameIndex().get(ConsortiumNode.CONSORTIUM_NAME_INDEX, consortiumName);        
        if(hits.hasNext()){
            return new ConsortiumNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------INSTITUTES--------------------------------
    /**
     * 
     * @param instituteName
     * @return InstituteNode with the name provided
     */
    public InstituteNode getInstituteByName(String instituteName){
        IndexHits<Node> hits = manager.getInstituteNameIndex().get(InstituteNode.INSTITUTE_NAME_INDEX, instituteName);        
        if(hits.hasNext()){
            return new InstituteNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------COUNTRIES--------------------------------
    /**
     * 
     * @param countryName
     * @return CountryNode with the name provided
     */
    public CountryNode getCountryNodeByName(String countryName){
        IndexHits<Node> hits = manager.getCountryNameIndex().get(CountryNode.COUNTRY_NAME_INDEX, countryName);        
        if(hits.hasNext()){
            return new CountryNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------CITY--------------------------------
    /**
     * 
     * @param cityName
     * @return CityNode with the name provided
     */
    public CityNode getCityNodeByName(String cityName){
        IndexHits<Node> hits = manager.getCityNameIndex().get(CityNode.CITY_NAME_INDEX, cityName);        
        if(hits.hasNext()){
            return new CityNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------THESIS--------------------------------
    /**
     * 
     * @param thesisTitle
     * @return ThesisNode list with the name matching the value provided
     */
    public List<ThesisNode> getThesisByTitle(String thesisTitle){
        
        IndexHits<Node> hits = manager.getThesisFullTextIndex().get(ThesisNode.THESIS_TITLE_FULL_TEXT_INDEX, thesisTitle);
        
        List<ThesisNode> list = new ArrayList<ThesisNode>();
        
        while(hits.hasNext()){
            list.add(new ThesisNode(hits.next()));
        }        
        return list; 
    }
    
    //-------------------------------------------------------------------
    //--------------------SUBMISSION--------------------------------
    /**
     * 
     * @param submissionTitle
     * @return SubmissionNode with the title matching the value provided
     */
    public SubmissionNode getSubmissionByTitle(String submissionTitle){
        IndexHits<Node> hits = manager.getSubmissionTitleIndex().get(SubmissionNode.SUBMISSION_TITLE_INDEX, submissionTitle);                
        if(hits.hasNext()){
            return new SubmissionNode(hits.next());
        }else{
            return null;
        }
    }
    //-------------------------------------------------------------------
    //--------------------DB--------------------------------
    /**
     * 
     * @param dbName
     * @return DBNode with the name matching the value provided
     */
    public DBNode getDBByName(String dbName){
        IndexHits<Node> hits = manager.getSubmissionTitleIndex().get(DBNode.DB_NAME_INDEX, dbName);                
        if(hits.hasNext()){
            return new DBNode(hits.next());
        }else{
            return null;
        }
    }
    
    //-------------------------------------------------------------------
    //--------------------PATENTS--------------------------------
    /**
     * 
     * @param patentNumber
     * @return PatentNode with the number provided
     */
    public PatentNode getPatentByNumber(String patentNumber){
        IndexHits<Node> hits = manager.getPatentNumberIndex().get(PatentNode.PATENT_NUMBER_INDEX, patentNumber);        
        if(hits.hasNext()){
            return new PatentNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------BOOKS--------------------------------
    /**
     * 
     * @param bookName
     * @return BookNode list with the name matching the value provided
     */
    public List<BookNode> getBooksByName(String bookName){
        
        IndexHits<Node> hits = manager.getBookNameFullTextIndex().get(BookNode.BOOK_NAME_FULL_TEXT_INDEX, bookName);
        
        List<BookNode> list = new ArrayList<BookNode>();
        
        while(hits.hasNext()){
            list.add(new BookNode(hits.next()));
        }        
        return list; 
    }
    
    //-------------------------------------------------------------------
    //--------------------PUBLISHER--------------------------------
    /**
     * 
     * @param publisherName
     * @return PublisherNode with the name provided
     */
    public PublisherNode getPublisherByName(String publisherName){
        IndexHits<Node> hits = manager.getPublisherNameIndex().get(PublisherNode.PUBLISHER_NAME_INDEX, publisherName);        
        if(hits.hasNext()){
            return new PublisherNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------ONLINE ARTICLES--------------------------------
    /**
     * 
     * @param onlineArticleTitle
     * @return OnlineArticleNode list with the title matching the value provided
     */
    public List<OnlineArticleNode> getOnlineArticlesByTitle(String onlineArticleTitle){
        
        IndexHits<Node> hits = manager.getOnlineArticleTitleFullTextIndex().get(OnlineArticleNode.ONLINE_ARTICLE_TITLE_FULL_TEXT_INDEX, onlineArticleTitle);
        
        List<OnlineArticleNode> list = new ArrayList<OnlineArticleNode>();
        
        while(hits.hasNext()){
            list.add(new OnlineArticleNode(hits.next()));
        }        
        return list; 
    }
    
    //-------------------------------------------------------------------
    //--------------------ONLINE JOURNAL--------------------------------
    /**
     * 
     * @param onlineJournalName
     * @return OnlineJournalNode with the name provided
     */
    public OnlineJournalNode getOnlineJournalByName(String onlineJournalName){
        IndexHits<Node> hits = manager.getOnlineJournalNameIndex().get(OnlineJournalNode.ONLINE_JOURNAL_NAME_INDEX, onlineJournalName);        
        if(hits.hasNext()){
            return new OnlineJournalNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------ARTICLES--------------------------------
    /**
     * 
     * @param articleTitle
     * @return ArticleNode list with the title matching the value provided
     */
    public List<ArticleNode> getArticlesByTitle(String articleTitle){
        
        IndexHits<Node> hits = manager.getArticleTitleFullTextIndex().get(ArticleNode.ARTICLE_TITLE_FULL_TEXT_INDEX, articleTitle);
        
        List<ArticleNode> list = new ArrayList<ArticleNode>();
        
        while(hits.hasNext()){
            list.add(new ArticleNode(hits.next()));
        }        
        return list; 
    }
    /**
     * 
     * @param articleMedlineId
     * @return ArticleNode with the medline id provided
     */
    public ArticleNode getArticleByMedlineId(String articleMedlineId){
        IndexHits<Node> hits = manager.getArticleMedLineIdIndex().get(ArticleNode.ARTICLE_MEDLINE_ID_INDEX, articleMedlineId);        
        if(hits.hasNext()){
            return new ArticleNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    /**
     * 
     * @param articleDoiId
     * @return ArticleNode with the DOI id provided
     */
    public ArticleNode getArticleByDoiId(String articleDoiId){
        IndexHits<Node> hits = manager.getArticleDoiIdIndex().get(ArticleNode.ARTICLE_DOI_ID_INDEX, articleDoiId);        
        if(hits.hasNext()){
            return new ArticleNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    /**
     * 
     * @param articlePubmedId
     * @return ArticleNode with the Pubmed id provided
     */
    public ArticleNode getArticleByPubmedId(String articlePubmedId){
        IndexHits<Node> hits = manager.getArticlePubmedIdIndex().get(ArticleNode.ARTICLE_PUBMED_ID_INDEX, articlePubmedId);        
        if(hits.hasNext()){
            return new ArticleNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------JOURNALS--------------------------------
    /**
     * 
     * @param journalName
     * @return JournalNode with the name provided
     */
    public JournalNode getJournalByName(String journalName){
        IndexHits<Node> hits = manager.getJournalNameIndex().get(JournalNode.JOURNAL_NAME_INDEX, journalName);        
        if(hits.hasNext()){
            return new JournalNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------REACTOME--------------------------------
    /**
     * 
     * @param reactomeTermId
     * @return ReactomeTermNode with the id provided
     */
    public ReactomeTermNode getReactomeTermById(String reactomeTermId){
        IndexHits<Node> hits = manager.getReactomeTermIdIndex().get(ReactomeTermNode.REACTOME_TERM_ID_INDEX, reactomeTermId);
        if(hits.hasNext()){
            return new ReactomeTermNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------FEATURE TYPE--------------------------------
    /**
     * 
     * @param featureTypeName
     * @return 
     */
    public FeatureTypeNode getFeatureTypeByName(String featureTypeName){
        IndexHits<Node> hits = manager.getFeatureTypeNameIndex().get(FeatureTypeNode.FEATURE_TYPE_NAME_INDEX, featureTypeName);
        if(hits.hasNext()){
            return new FeatureTypeNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------COMMENT TYPE--------------------------------
    /**
     * 
     * @param commentTypeName
     * @return 
     */
    public CommentTypeNode getCommentTypeByName(String commentTypeName){
        IndexHits<Node> hits = manager.getCommentTypeNameIndex().get(CommentTypeNode.COMMENT_TYPE_NAME_INDEX, commentTypeName);
        if(hits.hasNext()){
            return new CommentTypeNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------SUBCELLULAR LOCATION--------------------------------
    /**
     * 
     * @param subcellularLocationName
     * @return 
     */
    public SubcellularLocationNode getSubcellularLocationByName(String subcellularLocationName){
        IndexHits<Node> hits = manager.getSubcellularLocationNameIndex().get(SubcellularLocationNode.SUBCELLULAR_LOCATION_NAME_INDEX, subcellularLocationName);
        if(hits.hasNext()){
            return new SubcellularLocationNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------ALTERNATIVE PRODUCTS--------------------------------
    
    public AlternativeProductNode getAlternativeProductInitiationNode(){
        IndexHits<Node> hits = manager.getMainNodesIndex().get(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.ALTERNATIVE_PRODUCT_INITIATION);
        if(hits.hasNext()){
            return new AlternativeProductNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    public AlternativeProductNode getAlternativeProductPromoterNode(){
        IndexHits<Node> hits = manager.getMainNodesIndex().get(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.ALTERNATIVE_PRODUCT_PROMOTER);
        if(hits.hasNext()){
            return new AlternativeProductNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    public AlternativeProductNode getAlternativeProductRibosomalFrameshiftingNode(){
        IndexHits<Node> hits = manager.getMainNodesIndex().get(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.ALTERNATIVE_PRODUCT_RIBOSOMAL_FRAMESHIFTING);
        if(hits.hasNext()){
            return new AlternativeProductNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    public AlternativeProductNode getAlternativeProductSplicingNode(){
        IndexHits<Node> hits = manager.getMainNodesIndex().get(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.ALTERNATIVE_PRODUCT_SPLICING);
        if(hits.hasNext()){
            return new AlternativeProductNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------SEQ-CAUTION --------------------------------
    
    public SequenceCautionNode getSequenceCautionErroneousInitiationNode(){
        IndexHits<Node> hits = manager.getMainNodesIndex().get(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.SEQUENCE_CAUTION_ERRONEOUS_INITIATION);
        if(hits.hasNext()){
            return new SequenceCautionNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    public SequenceCautionNode getSequenceCautionErroneousTranslationNode(){
        IndexHits<Node> hits = manager.getMainNodesIndex().get(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.SEQUENCE_CAUTION_ERRONEOUS_TRANSLATION);
        if(hits.hasNext()){
            return new SequenceCautionNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    public SequenceCautionNode getSequenceCautionFrameshiftNode(){
        IndexHits<Node> hits = manager.getMainNodesIndex().get(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.SEQUENCE_CAUTION_FRAMESHIFT);
        if(hits.hasNext()){
            return new SequenceCautionNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    public SequenceCautionNode getSequenceCautionErroneousTerminationNode(){
        IndexHits<Node> hits = manager.getMainNodesIndex().get(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.SEQUENCE_CAUTION_ERRONEOUS_TERMINATION);
        if(hits.hasNext()){
            return new SequenceCautionNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    public SequenceCautionNode getSequenceCautionMiscellaneousDiscrepancyNode(){
        IndexHits<Node> hits = manager.getMainNodesIndex().get(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.SEQUENCE_CAUTION_MISCELLANEOUS_DISCREPANCY);
        if(hits.hasNext()){
            return new SequenceCautionNode(hits.getSingle());
        }else{
            return null;
        } 
    }
    
    public SequenceCautionNode getSequenceCautionErroneousGeneModelPredictionNode(){
        IndexHits<Node> hits = manager.getMainNodesIndex().get(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.SEQUENCE_CAUTION_ERRONEOUS_GENE_MODEL_PREDICTION);
        if(hits.hasNext()){
            return new SequenceCautionNode(hits.getSingle());
        }else{
            return null;
        } 
    }
}
