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
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.GenomeElementNode;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class NodeRetriever {

    protected Bio4jManager manager;

    public NodeRetriever(Bio4jManager bio4jManager) {
        manager = bio4jManager;
    }

    //-------------------------------------------------------------------
    //--------------------------ENZYME-----------------------------------
    public EnzymeNode getEnzymeById(String id) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(EnzymeNode.ID_PROPERTY, id).iterator();
        if (iterator.hasNext()) {
            return new EnzymeNode(iterator.next());
        } else {
            return null;
        }
    }

    //-------------------------------------------------------------------
    //--------------------------DATASETS-----------------------------------
    public DatasetNode getDatasetByName(String name) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, name).iterator();
        if (iterator.hasNext()) {
            return new DatasetNode(iterator.next());
        } else {
            return null;
        }
    }

    public DatasetNode getSwissProtDataset() {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, DatasetNode.SWISS_PROT_DATASET_NAME).iterator();
        if (iterator.hasNext()) {
            return new DatasetNode(iterator.next());
        } else {
            return null;
        }
    }

    public DatasetNode getTremblDataset() {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, DatasetNode.TREMBL_DATASET_NAME).iterator();
        if (iterator.hasNext()) {
            return new DatasetNode(iterator.next());
        } else {
            return null;
        }
    }

    //-------------------------------------------------------------------
    //--------------------------REFSEQ-----------------------------------    
    public GenomeElementNode getGenomeElementByVersion(String version) {
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
    public GoTermNode getGoTermById(String goId) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(GoTermNode.ID_PROPERTY, goId).iterator();
        if (iterator.hasNext()) {
            return new GoTermNode(iterator.next());
        } else {
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
    public ProteinNode getProteinNodeByAccession(String proteinAccession) {
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
    public ProteinNode getProteinNodeByEnsemblPlantsRef(String ensemblPlantsRef){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.ENSEMBL_PLANTS_REFERENCES_PROPERTY, ensemblPlantsRef).iterator();        
        if(iterator.hasNext()){
            return new ProteinNode(iterator.next());
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
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.FULL_NAME_PROPERTY, proteinFullName).iterator();        
        List<ProteinNode> list = new LinkedList<ProteinNode>();        
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
    public List<ProteinNode> getProteinsByGeneNames(String proteinGeneName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.GENE_NAMES_PROPERTY, proteinGeneName).iterator();        
        List<ProteinNode> list = new LinkedList<ProteinNode>();        
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
    public KeywordNode getKeywordById(String keywordId){        
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
    public KeywordNode getKeywordByName(String keywordName){        
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
    public InterproNode getInterproById(String interproId){
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
    public PfamNode getPfamById(String pfamId){        
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
    public OrganismNode getOrganismByScientificName(String scientificName){        
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
    public OrganismNode getOrganismByNCBITaxonomyId(String ncbiTaxonomyId){        
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
    public TaxonNode getTaxonByName(String taxonName){
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
    public NCBITaxonNode getNCBITaxonByTaxId(String taxId){
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
    public NCBITaxonNode getNCBITaxonByGiId(String giId){
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
    public IsoformNode getIsoformById(String isoformId){
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
    public List<PersonNode> getPeopleByName(String personName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(PersonNode.NAME_PROPERTY, personName).iterator();        
        List<PersonNode> list = new LinkedList<PersonNode>();        
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
    public ConsortiumNode getConsortiumByName(String consortiumName){
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
    public InstituteNode getInstituteByName(String instituteName){
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
    public CountryNode getCountryNodeByName(String countryName){
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
    public CityNode getCityNodeByName(String cityName){
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
     * @param thesisName
     * @return ThesisNode list with the name matching the value provided
     */
    public List<ThesisNode> getThesisByName(String thesisName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ThesisNode.TITLE_PROPERTY, thesisName).iterator();        
        List<ThesisNode> list = new LinkedList<ThesisNode>();        
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
    public PatentNode getPatentByNumber(String patentNumber){
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
    public List<BookNode> getBooksByName(String bookName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(BookNode.NAME_PROPERTY, bookName).iterator();        
        List<BookNode> list = new LinkedList<BookNode>();        
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
    public PublisherNode getPublisherByName(String publisherName){
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
    public List<OnlineArticleNode> getOnlineArticlesByTitle(String onlineArticleTitle){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(OnlineArticleNode.TITLE_PROPERTY, onlineArticleTitle).iterator();        
        List<OnlineArticleNode> list = new LinkedList<OnlineArticleNode>();        
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
    public OnlineJournalNode getOnlineJournalByName(String onlineJournalName){
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
    public List<ArticleNode> getArticlesByTitle(String articleTitle){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ArticleNode.TITLE_PROPERTY, articleTitle).iterator();        
        List<ArticleNode> list = new LinkedList<ArticleNode>();        
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
    public ArticleNode getArticleByMedlineId(String articleMedlineId){
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
    public ArticleNode getArticleByPubmedId(String articlePubmedId){
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
    public JournalNode getJournalByName(String journalName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(JournalNode.NAME_PROPERTY, journalName).iterator();        
        if(iterator.hasNext()){
            return new JournalNode(iterator.next());
        }else{
            return null;
        } 
    }
    
    
}
