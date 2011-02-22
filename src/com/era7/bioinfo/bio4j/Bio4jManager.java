/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.bioinfo.bio4j;

import com.era7.bioinfo.bio4jmodel.nodes.*;
import com.era7.bioinfo.bio4jmodel.nodes.citation.*;
import com.era7.bioinfo.bioinfoneo4j.Neo4jManager;
import java.util.HashMap;
import java.util.Map;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class Bio4jManager extends Neo4jManager{

    private static boolean alreadyCreated = false;

    private static String PROVIDER_ST = "provider";
    private static String EXACT_ST = "exact";
    private static String FULL_TEXT_ST = "fulltext";
    private static String LUCENE_ST = "lucene";
    private static String TYPE_ST = "type";
    
    public Bio4jManager(String dbFolder){        
        super(dbFolder,firstTimeCalled());
    }

    private static synchronized boolean firstTimeCalled(){
        if(!alreadyCreated){
            alreadyCreated = true;
            return true;
        }else{
            return false;
        }
    }

    public static Bio4jManager getBio4jManager(String dbFolder){
        return new Bio4jManager(dbFolder);
    }

    //---------------------------------------------------------------
    //--------------------------INDEXES------------------------------
    //------------------------------------------------------------------S

    public Index<Node> getGoTermIdIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(GoTermNode.GO_TERM_ID_INDEX, indexProps);
    }

    public Index<Node> getProteinAccessionIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(ProteinNode.PROTEIN_ACCESSION_INDEX, indexProps);
    }

    public Index<Node> getProteinFullNameFullTextIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, FULL_TEXT_ST);
        return this.graphService.index().forNodes(ProteinNode.PROTEIN_FULL_NAME_FULL_TEXT_INDEX, indexProps);
    }

    public Index<Node> getProteinGeneNamesFullTextIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, FULL_TEXT_ST);
        return this.graphService.index().forNodes(ProteinNode.PROTEIN_GENE_NAMES_FULL_TEXT_INDEX, indexProps);
    }

    public Index<Node> getKeywordIdIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(KeywordNode.KEYWORD_ID_INDEX, indexProps);
    }

    public Index<Node> getKeywordNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(KeywordNode.KEYWORD_NAME_INDEX, indexProps);
    }

    public Index<Node> getInterproIdIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(InterproNode.INTERPRO_ID_INDEX, indexProps);
    }

    public Index<Node> getOrganismScientificNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(OrganismNode.ORGANISM_SCIENTIFIC_NAME_INDEX, indexProps);
    }

    public Index<Node> getOrganismNcbiTaxonomyIdIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, indexProps);
    }

    public Index<Node> getTaxonNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(TaxonNode.TAXON_NAME_INDEX, indexProps);
    }

    public Index<Node> getFeatureTypeNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(FeatureTypeNode.FEATURE_TYPE_NAME_INDEX, indexProps);
    }
    public Index<Node> getCommentTypeNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(CommentTypeNode.COMMENT_TYPE_NAME_INDEX, indexProps);
    }
    public Index<Node> getIsoformIdIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(IsoformNode.ISOFORM_ID_INDEX, indexProps);
    }
    public Index<Node> getPersonNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(PersonNode.PERSON_NAME_INDEX, indexProps);
    }
    public Index<Node> getConsortiumNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(ConsortiumNode.CONSORTIUM_NAME_INDEX, indexProps);
    }
    public Index<Node> getInstituteNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(InstituteNode.INSTITUTE_NAME_INDEX, indexProps);
    }
    public Index<Node> getCountryNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(CountryNode.COUNTRY_NAME_INDEX, indexProps);
    }
    public Index<Node> getCityNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(CityNode.CITY_NAME_INDEX, indexProps);
    }
    public Index<Node> getThesisFullTextIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, FULL_TEXT_ST);
        return this.graphService.index().forNodes(ThesisNode.THESIS_TITLE_FULL_TEXT_INDEX, indexProps);
    }
    public Index<Node> getPatentNumberIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(PatentNode.PATENT_NUMBER_INDEX, indexProps);
    }
    public Index<Node> getBookNameFullTextIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, FULL_TEXT_ST);
        return this.graphService.index().forNodes(BookNode.BOOK_NAME_FULL_TEXT_INDEX, indexProps);
    }
    public Index<Node> getPublisherNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(PublisherNode.PUBLISHER_NAME_INDEX, indexProps);
    }
    public Index<Node> getOnlineArticleTitleFullTextIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, FULL_TEXT_ST);
        return this.graphService.index().forNodes(OnlineArticleNode.ONLINE_ARTICLE_TITLE_FULL_TEXT_INDEX, indexProps);
    }
    public Index<Node> getOnlineJournalNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(OnlineJournalNode.ONLINE_JOURNAL_NAME_INDEX, indexProps);
    }
    public Index<Node> getArticleTitleFullTextIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, FULL_TEXT_ST);
        return this.graphService.index().forNodes(ArticleNode.ARTICLE_TITLE_FULL_TEXT_INDEX, indexProps);
    }
    public Index<Node> getArticleMedLineIdIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(ArticleNode.ARTICLE_MEDLINE_ID_INDEX, indexProps);
    }
    public Index<Node> getArticleDoiIdIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(ArticleNode.ARTICLE_DOI_ID_INDEX, indexProps);
    }
    public Index<Node> getArticlePubmedIdIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(ArticleNode.ARTICLE_PUBMED_ID_INDEX, indexProps);
    }
    public Index<Node> getJournalNameIndex(){
        Map<String,String> indexProps = new HashMap<String, String>();
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        return this.graphService.index().forNodes(JournalNode.JOURNAL_NAME_INDEX, indexProps);
    }



}
