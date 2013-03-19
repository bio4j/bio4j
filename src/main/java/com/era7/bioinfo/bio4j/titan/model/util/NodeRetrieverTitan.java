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
package com.era7.bioinfo.bio4j.titan.model.util;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.*;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.citation.*;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ncbi.NCBITaxonNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.reactome.ReactomeTermNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductInitiationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductPromoterRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductRibosomalFrameshiftingRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductSplicingRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.*;
import com.era7.bioinfo.bio4j.model.nodes.AlternativeProduct;
import com.era7.bioinfo.bio4j.model.nodes.City;
import com.era7.bioinfo.bio4j.model.nodes.CommentType;
import com.era7.bioinfo.bio4j.model.nodes.Consortium;
import com.era7.bioinfo.bio4j.model.nodes.Country;
import com.era7.bioinfo.bio4j.model.nodes.Dataset;
import com.era7.bioinfo.bio4j.model.nodes.Enzyme;
import com.era7.bioinfo.bio4j.model.nodes.FeatureType;
import com.era7.bioinfo.bio4j.model.nodes.GoTerm;
import com.era7.bioinfo.bio4j.model.nodes.Institute;
import com.era7.bioinfo.bio4j.model.nodes.Interpro;
import com.era7.bioinfo.bio4j.model.nodes.Isoform;
import com.era7.bioinfo.bio4j.model.nodes.Keyword;
import com.era7.bioinfo.bio4j.model.nodes.Organism;
import com.era7.bioinfo.bio4j.model.nodes.Person;
import com.era7.bioinfo.bio4j.model.nodes.Pfam;
import com.era7.bioinfo.bio4j.model.nodes.Protein;
import com.era7.bioinfo.bio4j.model.nodes.SequenceCaution;
import com.era7.bioinfo.bio4j.model.nodes.SubcellularLocation;
import com.era7.bioinfo.bio4j.model.nodes.Taxon;
import com.era7.bioinfo.bio4j.model.nodes.citation.Article;
import com.era7.bioinfo.bio4j.model.nodes.citation.Book;
import com.era7.bioinfo.bio4j.model.nodes.citation.DB;
import com.era7.bioinfo.bio4j.model.nodes.citation.Journal;
import com.era7.bioinfo.bio4j.model.nodes.citation.OnlineArticle;
import com.era7.bioinfo.bio4j.model.nodes.citation.OnlineJournal;
import com.era7.bioinfo.bio4j.model.nodes.citation.Patent;
import com.era7.bioinfo.bio4j.model.nodes.citation.Publisher;
import com.era7.bioinfo.bio4j.model.nodes.citation.Submission;
import com.era7.bioinfo.bio4j.model.nodes.citation.Thesis;
import com.era7.bioinfo.bio4j.model.nodes.ncbi.NCBITaxon;
import com.era7.bioinfo.bio4j.model.nodes.reactome.ReactomeTerm;
import com.era7.bioinfo.bio4j.model.nodes.refseq.GenomeElement;
import com.era7.bioinfo.bio4j.model.util.NodeRetriever;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class NodeRetrieverTitan implements NodeRetriever{

    protected Bio4jManager manager;

    public NodeRetrieverTitan(Bio4jManager bio4jManager) {
        manager = bio4jManager;
    }

    //-------------------------------------------------------------------
    //--------------------------ENZYME-----------------------------------
    @Override
    public Enzyme getEnzymeById(String id) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(EnzymeNode.ID_PROPERTY, id).iterator();
        if (iterator.hasNext()) {
            return new EnzymeNode(iterator.next());
        } else {
            return null;
        }
    }

    //-------------------------------------------------------------------
    //--------------------------DATASETS-----------------------------------
    @Override
    public Dataset getDatasetByName(String name) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, name).iterator();
        if (iterator.hasNext()) {
            return new DatasetNode(iterator.next());
        } else {
            return null;
        }
    }

    @Override
    public Dataset getSwissProtDataset() {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, DatasetNode.SWISS_PROT_DATASET_NAME).iterator();
        if (iterator.hasNext()) {
            return new DatasetNode(iterator.next());
        } else {
            return null;
        }
    }

    @Override
    public Dataset getTremblDataset() {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, DatasetNode.TREMBL_DATASET_NAME).iterator();
        if (iterator.hasNext()) {
            return new DatasetNode(iterator.next());
        } else {
            return null;
        }
    }

    //-------------------------------------------------------------------
    //--------------------------REFSEQ-----------------------------------    
    @Override
    public GenomeElement getGenomeElementByVersion(String version) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(GenomeElementNode.VERSION_PROPERTY, version).iterator();
        if (iterator.hasNext()) {
            return new GenomeElementNode(iterator.next());
        } else {
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
    @Override
    public GoTerm getGoTermById(String goId) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(GoTermNode.ID_PROPERTY, goId).iterator();
        if (iterator.hasNext()) {
            return new GoTermNode(iterator.next());
        } else {
            return null;
        }
    }
    @Override
    public GoTerm getMolecularFunctionGoTerm(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(GoTermNode.ID_PROPERTY, GoTermNode.MOLECULAR_FUNCTION_GO_ID).iterator();
        if(iterator.hasNext()){
            return new GoTermNode(iterator.next());
        }else{
            return null;
        }
    }
    @Override
    public GoTerm getBiologicalProcessGoTerm(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(GoTermNode.ID_PROPERTY, GoTermNode.BIOLOGICAL_PROCESS_GO_ID).iterator();
        if(iterator.hasNext()){
            return new GoTermNode(iterator.next());
        }else{
            return null;
        }
    }
    @Override
    public GoTerm getCellularComponentGoTerm(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(GoTermNode.ID_PROPERTY, GoTermNode.CELLULAR_COMPONENT_GO_ID).iterator();
        if(iterator.hasNext()){
            return new GoTermNode(iterator.next());
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
    @Override
    public Protein getProteinByAccession(String proteinAccession) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.ACCESSION_PROPERTY, proteinAccession).iterator();
        if (iterator.hasNext()) {
            return new ProteinNode(iterator.next());
        } else {
            //we check if we can find it as a an alternative accession
            iterator = manager.getGraph().getVertices(ProteinNode.ALTERNATIVE_ACCESSIONS_PROPERTY, proteinAccession).iterator();
            if (iterator.hasNext()) {
                return new ProteinNode(iterator.next());
            } else {
                return null;
            }
        }
    }
    /**
     * 
     * @param ensemblPlantsRef 
     * @return ProteinNode with the Ensembl Plants reference provided
     */
    @Override
    public List<Protein> getProteinByEnsemblPlantsRef(String ensemblPlantsRef){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.ENSEMBL_PLANTS_REFERENCES_PROPERTY, ensemblPlantsRef).iterator();        
        List<Protein> list = new LinkedList<Protein>();  
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }        
        return list;         
    }    
    /**
     * 
     * @param proteinFullName
     * @return List of proteins (if any) which match the full name provided
     */
    @Override
    public List<Protein> getProteinByFullName(String proteinFullName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.FULL_NAME_PROPERTY, proteinFullName).iterator();        
        List<Protein> list = new LinkedList<Protein>();        
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }        
        return list;        
    }
    /**
     * 
     * @param proteinGeneName
     * @return List of proteins (if any) which match the gene name provided
     */
    @Override
    public List<Protein> getProteinByGeneNames(String proteinGeneName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.GENE_NAMES_PROPERTY, proteinGeneName).iterator();        
        List<Protein> list = new LinkedList<Protein>();        
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }        
        return list;        
    }
    @Override
    public List<Protein> getProteinByPIRReference(String id){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.PIR_REFERENCES_PROPERTY, id).iterator();        
        List<Protein> list = new LinkedList<Protein>();        
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }        
        return list;        
    }
    @Override
    public List<Protein> getProteinByKEGGReference(String id){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.KEGG_REFERENCES_PROPERTY, id).iterator();        
        List<Protein> list = new LinkedList<Protein>();        
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
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
    @Override
    public Keyword getKeywordById(String keywordId){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(KeywordNode.ID_PROPERTY, keywordId).iterator();        
        if(iterator.hasNext()){
            return new KeywordNode(iterator.next());
        }else{
            return null;
        }        
    }    
    /**
     * 
     * @param keywordName 
     * @return KeywordNode with the id provided
     */
    @Override
    public Keyword getKeywordByName(String keywordName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(KeywordNode.NAME_PROPERTY, keywordName).iterator();        
        if(iterator.hasNext()){
            return new KeywordNode(iterator.next());
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
    @Override
    public Interpro getInterproById(String interproId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(InterproNode.ID_PROPERTY, interproId).iterator();        
        if(iterator.hasNext()){
            return new InterproNode(iterator.next());
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
    @Override
    public Pfam getPfamById(String pfamId){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(PfamNode.ID_PROPERTY, pfamId).iterator();        
        if(iterator.hasNext()){
            return new PfamNode(iterator.next());
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
    @Override
    public Organism getOrganismByScientificName(String scientificName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(OrganismNode.SCIENTIFIC_NAME_PROPERTY, scientificName).iterator();        
        if(iterator.hasNext()){
            return new OrganismNode(iterator.next());
        }else{
            return null;
        }        
    }
    /**
     * 
     * @param ncbiTaxonomyId 
     * @return OrganismNode with the scientific name provided
     */
    @Override
    public Organism getOrganismByNCBITaxonomyId(String ncbiTaxonomyId){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, ncbiTaxonomyId).iterator();        
        if(iterator.hasNext()){
            return new OrganismNode(iterator.next());
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
    @Override
    public Taxon getTaxonByName(String taxonName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(TaxonNode.NAME_PROPERTY, taxonName).iterator();        
        if(iterator.hasNext()){
            return new TaxonNode(iterator.next());
        }else{
            return null;
        }   
    }
    /**
     * 
     * @param taxId
     * @return NCBITaxonNode with the tax id provided
     */
    @Override
    public NCBITaxon getNCBITaxonByTaxId(String taxId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(NCBITaxonNode.TAX_ID_PROPERTY, taxId).iterator();        
        if(iterator.hasNext()){
            return new NCBITaxonNode(iterator.next());
        }else{
            return null;
        }   
    }
    /**
     * 
     * @param giId
     * @return NCBITaxonNode with the tax id provided
     */
    @Override
    public NCBITaxon getNCBITaxonByGiId(String giId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(NCBITaxonNode.GI_IDS_PROPERTY, giId).iterator();        
        if(iterator.hasNext()){
            return new NCBITaxonNode(iterator.next());
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
    @Override
    public Isoform getIsoformById(String isoformId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(IsoformNode.ID_PROPERTY, isoformId).iterator();        
        if(iterator.hasNext()){
            return new IsoformNode(iterator.next());
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
    @Override
    public List<Person> getPersonByName(String personName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(PersonNode.NAME_PROPERTY, personName).iterator();        
        List<Person> list = new LinkedList<Person>();        
        while(iterator.hasNext()){
            list.add(new PersonNode(iterator.next()));
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
    @Override
    public Consortium getConsortiumByName(String consortiumName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ConsortiumNode.NAME_PROPERTY, consortiumName).iterator();        
        if(iterator.hasNext()){
            return new ConsortiumNode(iterator.next());
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
    @Override
    public Institute getInstituteByName(String instituteName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(InstituteNode.NAME_PROPERTY, instituteName).iterator();        
        if(iterator.hasNext()){
            return new InstituteNode(iterator.next());
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
    @Override
    public Country getCountryNodeByName(String countryName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(CountryNode.NAME_PROPERTY, countryName).iterator();        
        if(iterator.hasNext()){
            return new CountryNode(iterator.next());
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
    @Override
    public City getCityNodeByName(String cityName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(CityNode.NAME_PROPERTY, cityName).iterator();        
        if(iterator.hasNext()){
            return new CityNode(iterator.next());
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
    @Override
    public List<Thesis> getThesisByTitle(String thesisTitle){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ThesisNode.TITLE_PROPERTY, thesisTitle).iterator();        
        List<Thesis> list = new LinkedList<Thesis>();        
        while(iterator.hasNext()){
            list.add(new ThesisNode(iterator.next()));
        }        
        return list; 
    }
    //-------------------------------------------------------------------
    //--------------------PATENTS--------------------------------
    /**
     * 
     * @param patentNumber
     * @return PatentNode with the number provided
     */
    @Override
    public Patent getPatentByNumber(String patentNumber){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(PatentNode.NUMBER_PROPERTY, patentNumber).iterator();        
        if(iterator.hasNext()){
            return new PatentNode(iterator.next());
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
    @Override
    public List<Book> getBooksByName(String bookName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(BookNode.NAME_PROPERTY, bookName).iterator();        
        List<Book> list = new LinkedList<Book>();        
        while(iterator.hasNext()){
            list.add(new BookNode(iterator.next()));
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
    @Override
    public Publisher getPublisherByName(String publisherName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(PublisherNode.NAME_PROPERTY, publisherName).iterator();        
        if(iterator.hasNext()){
            return new PublisherNode(iterator.next());
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
    @Override
    public List<OnlineArticle> getOnlineArticlesByTitle(String onlineArticleTitle){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(OnlineArticleNode.TITLE_PROPERTY, onlineArticleTitle).iterator();        
        List<OnlineArticle> list = new LinkedList<OnlineArticle>();        
        while(iterator.hasNext()){
            list.add(new OnlineArticleNode(iterator.next()));
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
    @Override
    public OnlineJournal getOnlineJournalByName(String onlineJournalName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(OnlineJournalNode.NAME_PROPERTY, onlineJournalName).iterator();        
        if(iterator.hasNext()){
            return new OnlineJournalNode(iterator.next());
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
    @Override
    public List<Article> getArticlesByTitle(String articleTitle){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ArticleNode.TITLE_PROPERTY, articleTitle).iterator();        
        List<Article> list = new LinkedList<Article>();        
        while(iterator.hasNext()){
            list.add(new ArticleNode(iterator.next()));
        }        
        return list; 
    }
    /**
     * 
     * @param articleMedlineId
     * @return ArticleNode with the medline id provided
     */
    @Override
    public Article getArticleByMedlineId(String articleMedlineId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ArticleNode.MEDLINE_ID_PROPERTY, articleMedlineId).iterator();        
        if(iterator.hasNext()){
            return new ArticleNode(iterator.next());
        }else{
            return null;
        } 
    }
    /**
     * 
     * @param articleDoiId
     * @return ArticleNode with the DOI id provided
     */
    @Override
    public ArticleNode getArticleByDoiId(String articleDoiId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ArticleNode.DOI_ID_PROPERTY, articleDoiId).iterator();        
        if(iterator.hasNext()){
            return new ArticleNode(iterator.next());
        }else{
            return null;
        } 
    }
     /**
     * 
     * @param articlePubmedId
     * @return ArticleNode with the Pubmed id provided
     */
    @Override
    public Article getArticleByPubmedId(String articlePubmedId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ArticleNode.PUBMED_ID_PROPERTY, articlePubmedId).iterator();        
        if(iterator.hasNext()){
            return new ArticleNode(iterator.next());
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
    @Override
    public Journal getJournalByName(String journalName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(JournalNode.NAME_PROPERTY, journalName).iterator();        
        if(iterator.hasNext()){
            return new JournalNode(iterator.next());
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
    @Override
    public ReactomeTerm getReactomeTermById(String reactomeTermId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ReactomeTermNode.ID_PROPERTY, reactomeTermId).iterator();
        if(iterator.hasNext()){
            return new ReactomeTermNode(iterator.next());
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
    @Override
    public FeatureType getFeatureTypeByName(String featureTypeName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(FeatureTypeNode.NAME_PROPERTY, featureTypeName).iterator();
        if(iterator.hasNext()){
            return new FeatureTypeNode(iterator.next());
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
    @Override
    public CommentType getCommentTypeByName(String commentTypeName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(CommentTypeNode.NAME_PROPERTY, commentTypeName).iterator();
        if(iterator.hasNext()){
            return new CommentTypeNode(iterator.next());
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
    @Override
    public SubcellularLocation getSubcellularLocationByName(String subcellularLocationName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SubcellularLocationNode.NAME_PROPERTY, subcellularLocationName).iterator();
        if(iterator.hasNext()){
            return new SubcellularLocationNode(iterator.next());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------ALTERNATIVE PRODUCTS--------------------------------
    
    @Override
    public AlternativeProduct getAlternativeProductInitiationNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(AlternativeProductNode.NAME_PROPERTY, AlternativeProductInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new AlternativeProductNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public AlternativeProduct getAlternativeProductPromoterNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(AlternativeProductNode.NAME_PROPERTY, AlternativeProductPromoterRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new AlternativeProductNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public AlternativeProduct getAlternativeProductRibosomalFrameshiftingNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(AlternativeProductNode.NAME_PROPERTY, AlternativeProductRibosomalFrameshiftingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new AlternativeProductNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public AlternativeProduct getAlternativeProductSplicingNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(AlternativeProductNode.NAME_PROPERTY, AlternativeProductSplicingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new AlternativeProductNode(iterator.next());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------SEQ-CAUTION--------------------------------
    @Override
    public SequenceCaution getSequenceCautionErroneousGeneModelPredictionNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousGeneModelPredictionRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new SequenceCautionNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public SequenceCaution getSequenceCautionErroneousInitiationNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new SequenceCautionNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public SequenceCaution getSequenceCautionErroneousTranslationNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousTranslationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new SequenceCautionNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public SequenceCaution getSequenceCautionErroneousTerminationNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousTerminationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new SequenceCautionNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public SequenceCaution getSequenceCautionFrameshiftNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SequenceCautionNode.NAME_PROPERTY, ProteinFrameshiftRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new SequenceCautionNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public SequenceCaution getSequenceCautionMiscellaneousDiscrepancyNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SequenceCautionNode.NAME_PROPERTY, ProteinMiscellaneousDiscrepancyRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new SequenceCautionNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------SUBMISSION--------------------------------
    /**
     * 
     * @param submissionTitle
     * @return 
     */
    @Override
    public Submission getSubmissionByTitle(String submissionTitle){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SubmissionNode.TITLE_PROPERTY, submissionTitle).iterator();        
        SubmissionNode submissionNode = null;        
        if(iterator.hasNext()){
            submissionNode = new SubmissionNode(iterator.next());
        }        
        return submissionNode; 
    }
    //-------------------------------------------------------------------
    //--------------------DB--------------------------------
    /**
     * 
     */
    @Override
    public DB getDBByName(String dbName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DBNode.NAME_PROPERTY, dbName).iterator();        
        DBNode dbNode = null;        
        if(iterator.hasNext()){
            dbNode = new DBNode(iterator.next());
        }        
        return dbNode; 
    }
    
}
