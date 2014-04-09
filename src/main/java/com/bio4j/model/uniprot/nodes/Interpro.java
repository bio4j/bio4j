package com.bio4j.model.uniprot.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Interpro extends Node<Interpro, Interpro.Type> {
    
  enum Type implements NodeType<Interpro, Interpro.Type> {

    interpro;
    public Type value() { return interpro; }
  }
  
    //----GETTERS---
    public String getId();
    public String getName();
    
    public List<Protein> getAssociatedProteins();

    //----SETTERS----
    public void setId(String value);
    public void setName(String value);    
    
    
}
