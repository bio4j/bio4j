/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.bioinfo.bio4j;

import com.era7.bioinfo.bio4jmodel.nodes.GoTermNode;
import com.era7.bioinfo.bio4jmodel.nodes.ProteinNode;
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

}
