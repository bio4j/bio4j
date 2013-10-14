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

package com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.BasicVertex;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.rna.*;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinGenomeElementRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.refseq.*;
import com.era7.bioinfo.bio4j.model.nodes.Protein;
import com.era7.bioinfo.bio4j.model.nodes.refseq.CDS;
import com.era7.bioinfo.bio4j.model.nodes.refseq.Gene;
import com.era7.bioinfo.bio4j.model.nodes.refseq.GenomeElement;
import com.era7.bioinfo.bio4j.model.nodes.refseq.rna.MRNA;
import com.era7.bioinfo.bio4j.model.nodes.refseq.rna.MiscRNA;
import com.era7.bioinfo.bio4j.model.nodes.refseq.rna.NcRNA;
import com.era7.bioinfo.bio4j.model.nodes.refseq.rna.RRNA;
import com.era7.bioinfo.bio4j.model.nodes.refseq.rna.TRNA;
import com.era7.bioinfo.bio4j.model.nodes.refseq.rna.TmRNA;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Organisms genome elements
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GenomeElementNode extends BasicVertex implements GenomeElement{

    public static final String NODE_TYPE = GenomeElementNode.class.getCanonicalName();

    public static final String VERSION_PROPERTY = "genome_element_version";
    public static final String COMMENT_PROPERTY = "genome_element_comment";
    public static final String DEFINITION_PROPERTY = "genome_element_definition";


    public GenomeElementNode(Vertex v){
        super(v);
    }


    @Override
    public String getVersion(){  return String.valueOf(vertex.getProperty(VERSION_PROPERTY));}
    @Override
    public String getComment(){    return String.valueOf(vertex.getProperty(COMMENT_PROPERTY));}
    @Override
    public String getDefinition(){  return String.valueOf(vertex.getProperty(DEFINITION_PROPERTY));}


    @Override
    public void setVersion(String value){    vertex.setProperty(VERSION_PROPERTY, value);}
    @Override
    public void setComment(String value){  vertex.setProperty(COMMENT_PROPERTY, value);}
    @Override
    public void setDefinition(String value){  vertex.setProperty(DEFINITION_PROPERTY, value);}

    
    @Override
    public List<Protein> getAssociatedProteins(){
        List<Protein> proteins = new LinkedList<Protein>();
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, ProteinGenomeElementRel.NAME).iterator();
        while(iterator.hasNext()){
            ProteinNode protein = new ProteinNode(iterator.next());
            proteins.add(protein);                        
        }
        return proteins;  
    }
    
    
    /**
     * 
     * @return 
     */
    @Override
    public List<CDS> getCDS(){
        List<CDS> list = new ArrayList<CDS>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, GenomeElementCDSRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new CDSNode(iterator.next()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    @Override
    public List<Gene> getGenes(){
        List<Gene> list = new LinkedList<Gene>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, GenomeElementGeneRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new GeneNode(iterator.next()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    @Override
    public List<MRNA> getMRnas(){
        List<MRNA> list = new LinkedList<MRNA>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, GenomeElementMRnaRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new MRNANode(iterator.next()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    @Override
    public List<MiscRNA> getMiscRnas(){
        List<MiscRNA> list = new LinkedList<MiscRNA>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, GenomeElementMiscRnaRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new MiscRNANode(iterator.next()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    @Override
    public List<NcRNA> getNcRnas(){
        List<NcRNA> list = new LinkedList<NcRNA>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, GenomeElementNcRnaRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new NcRNANode(iterator.next()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    @Override
    public List<RRNA> getRRnas(){
        List<RRNA> list = new LinkedList<RRNA>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, GenomeElementRRnaRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new RRNANode(iterator.next()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    @Override
    public List<TRNA> getTRnas(){
        List<TRNA> list = new LinkedList<TRNA>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, GenomeElementTRnaRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new TRNANode(iterator.next()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    @Override
    public List<TmRNA> getTmRnas(){
        List<TmRNA> list = new LinkedList<TmRNA>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, GenomeElementTmRnaRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new TmRNANode(iterator.next()));
        }
        return list;
    }
    
    
    @Override
    public String toString(){
        return "version: " + getVersion() + "\n" +
                "comment: " + getComment() + "\n" + 
                "definition: " + getDefinition() + "\n";
    }

}
