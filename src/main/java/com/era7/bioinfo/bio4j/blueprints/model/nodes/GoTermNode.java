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

package com.era7.bioinfo.bio4j.blueprints.model.nodes;

import com.era7.bioinfo.bio4j.blueprints.model.relationships.go.*;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinGoRel;
import com.era7.bioinfo.bio4j.model.nodes.GoTerm;
import com.era7.bioinfo.bio4j.model.nodes.Protein;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Gene ontology term
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GoTermNode extends BasicVertex implements GoTerm{
    
    public static final String MOLECULAR_FUNCTION_GO_ID = "GO:0003674";
    public static final String BIOLOGICAL_PROCESS_GO_ID = "GO:0008150";
    public static final String CELLULAR_COMPONENT_GO_ID = "GO:0005575";

    public static final String NODE_TYPE = GoTermNode.class.getCanonicalName();

    /** GO Term id **/
    public static final String ID_PROPERTY = "go_term_id";
    /** Go Term name **/
    public static final String NAME_PROPERTY = "go_term_name";
    /** Go Term definition **/
    public static final String DEFINITION_PROPERTY = "go_term_definition";
    /** Go Term namespace.<br>
        One of these:<br>
        - Biological process<br>
        - Molecular function<br>
        - Cellular component<br>**/
    public static final String NAMESPACE_PROPERTY = "go_term_namespace";
    /**
     * Boolean value indicating whether the term is obsolete or not
     */
    public static final String OBSOLETE_PROPERTY = "go_term_obsolete";
    public static final String COMMENT_PROPERTY = "go_term_comment";
    public static final String ALTERNATIVE_IDS_PROPERTY = "go_term_alternative_ids";

    public static final String BIOLOGICAL_PROCESS_NAMESPACE = "biological_process";
    public static final String MOLECULAR_FUNCTION_NAMESPACE = "molecular_function";
    public static final String CELLULAR_COMPONENT_NAMESPACE = "cellular_component";


    public GoTermNode(Vertex v){
        super(v);
    }


    @Override
    public String getId(){  return String.valueOf(vertex.getProperty(ID_PROPERTY));}
    @Override
    public String getName(){    return String.valueOf(vertex.getProperty(NAME_PROPERTY));}
    @Override
    public String getDefinition(){  return String.valueOf(vertex.getProperty(DEFINITION_PROPERTY));}
    @Override
    public String getNamespace(){   return String.valueOf(vertex.getProperty(NAMESPACE_PROPERTY));}
    @Override
    public Boolean getObsolete(){    return Boolean.parseBoolean(String.valueOf(vertex.getProperty(OBSOLETE_PROPERTY)));}
    @Override
    public String getComment(){ return String.valueOf(vertex.getProperty(COMMENT_PROPERTY));}
    @Override
    public String[] getAlternativeIds(){    return (String[]) vertex.getProperty(ALTERNATIVE_IDS_PROPERTY);}


    @Override
    public void setId(String value){    vertex.setProperty(ID_PROPERTY, value);}
    @Override
    public void setName(String value){  vertex.setProperty(NAME_PROPERTY, value);}
    @Override
    public void setDefinition(String value){    vertex.setProperty(DEFINITION_PROPERTY, value);}
    @Override
    public void setNamespace(String value){ vertex.setProperty(NAMESPACE_PROPERTY, value);}
    @Override
    public void setObsolete(Boolean value){ vertex.setProperty(OBSOLETE_PROPERTY, String.valueOf(value));}
    @Override
    public void setComment(String value){   vertex.setProperty(COMMENT_PROPERTY, value);}
    @Override
    public void setAlternativeIds(String[] value){  vertex.setProperty(ALTERNATIVE_IDS_PROPERTY, value);}
  
    
    @Override
    public List<Protein> getAssociatedProteins(){
        List<Protein> proteins = new LinkedList<Protein>();
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, ProteinGoRel.NAME).iterator();
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
    public List<GoTerm> getIsAGoNodes(){
        List<GoTerm> list = new LinkedList<GoTerm>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, IsAGoRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new GoTermNode(iterator.next()));
        }
        return list;
    }    
    /**
     * 
     * @return 
     */
    @Override
    public List<GoTerm> getNegativelyRegulatesNodes(){
        List<GoTerm> list = new LinkedList<GoTerm>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, NegativelyRegulatesGoRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new GoTermNode(iterator.next()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    @Override
    public List<GoTerm> getPositivelyRegulatesNodes(){
        List<GoTerm> list = new LinkedList<GoTerm>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, PositivelyRegulatesGoRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new GoTermNode(iterator.next()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    @Override
    public List<GoTerm> getPartOfNodes(){
        List<GoTerm> list = new LinkedList<GoTerm>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, PartOfGoRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new GoTermNode(iterator.next()));
        }
        return list;
    }
    /**
     * 
     * @return 
     */
    @Override
    public List<GoTerm> getHasPartOfNodes(){
        List<GoTerm> list = new LinkedList<GoTerm>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, HasPartOfGoRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new GoTermNode(iterator.next()));
        }
        return list;
    }
    
    @Override
    public String toString(){
        return "id = " + getId() +
                "\nnamespace = " + getNamespace() + 
                "\nname = " + getName() +
                "\ndefinition = " + getDefinition();
    }

}
