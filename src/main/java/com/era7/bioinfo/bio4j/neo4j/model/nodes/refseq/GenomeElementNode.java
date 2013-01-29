/*
 * Copyright (C) 2010-2011  "Bio4j"
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

package com.era7.bioinfo.bio4j.neo4j.model.nodes.refseq;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.refseq.rna.*;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinGenomeElementRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.refseq.*;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * Organisms genome elements
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GenomeElementNode extends BasicEntity{

    public static final String GENOME_ELEMENT_VERSION_INDEX = "genome_element_version_index";
    public static final String NODE_TYPE = GenomeElementNode.class.getCanonicalName();

    public static final String VERSION_PROPERTY = "genome_element_version";
    public static final String COMMENT_PROPERTY = "genome_element_comment";
    public static final String DEFINITION_PROPERTY = "genome_element_definition";


    public GenomeElementNode(Node n){
        super(n);
    }


    public String getVersion(){  return String.valueOf(node.getProperty(VERSION_PROPERTY));}
    public String getComment(){    return String.valueOf(node.getProperty(COMMENT_PROPERTY));}
    public String getDefinition(){  return String.valueOf(node.getProperty(DEFINITION_PROPERTY));}


    public void setVersion(String value){    node.setProperty(VERSION_PROPERTY, value);}
    public void setComment(String value){  node.setProperty(COMMENT_PROPERTY, value);}
    public void setDefinition(String value){  node.setProperty(DEFINITION_PROPERTY, value);}

    
    public List<ProteinNode> getAssociatedProteins(){
        List<ProteinNode> proteins = new LinkedList<ProteinNode>();
        
        Iterator<Relationship> iterator = node.getRelationships(new ProteinGenomeElementRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            ProteinNode protein = new ProteinNode(iterator.next().getStartNode());
            proteins.add(protein);                        
        }
        return proteins;  
    }
    
    
    /**
     * 
     * @return 
     */
    public List<CDSNode> getCDS(){
        List<CDSNode> list = new ArrayList<CDSNode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new GenomeElementCDSRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new CDSNode(iterator.next().getEndNode()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    public List<GeneNode> getGenes(){
        List<GeneNode> list = new ArrayList<GeneNode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new GenomeElementGeneRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new GeneNode(iterator.next().getEndNode()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    public List<MRNANode> getMRnas(){
        List<MRNANode> list = new ArrayList<MRNANode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new GenomeElementMRnaRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new MRNANode(iterator.next().getEndNode()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    public List<MiscRNANode> getMiscRnas(){
        List<MiscRNANode> list = new ArrayList<MiscRNANode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new GenomeElementMiscRnaRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new MiscRNANode(iterator.next().getEndNode()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    public List<NcRNANode> getNcRnas(){
        List<NcRNANode> list = new ArrayList<NcRNANode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new GenomeElementNcRnaRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new NcRNANode(iterator.next().getEndNode()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    public List<RRNANode> getRRnas(){
        List<RRNANode> list = new ArrayList<RRNANode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new GenomeElementRRnaRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new RRNANode(iterator.next().getEndNode()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    public List<TRNANode> getTRnas(){
        List<TRNANode> list = new ArrayList<TRNANode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new GenomeElementTRnaRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new TRNANode(iterator.next().getEndNode()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    public List<TmRNANode> getTmRnas(){
        List<TmRNANode> list = new ArrayList<TmRNANode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new GenomeElementTmRnaRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new TmRNANode(iterator.next().getEndNode()));
        }
        return list;
    }
    

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GenomeElementNode){
            GenomeElementNode other = (GenomeElementNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }
    
    @Override
    public String toString(){
        return "version: " + getVersion() + "\n" +
                "comment: " + getComment() + "\n" + 
                "definition: " + getDefinition() + "\n";
    }

}
