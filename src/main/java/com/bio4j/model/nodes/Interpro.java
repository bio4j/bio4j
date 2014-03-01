package com.bio4j.model.nodes;

import java.util.List;

import com.bio4j.model.Node;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Interpro extends Node{
    
    //----GETTERS---
    public String getId();
    public String getName();
    
    public List<Protein> getAssociatedProteins();

    //----SETTERS----
    public void setId(String value);
    public void setName(String value);    
    
    
}
