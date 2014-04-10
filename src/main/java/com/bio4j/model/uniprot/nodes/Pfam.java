package com.bio4j.model.uniprot.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

public interface Pfam extends Node<Pfam, Pfam.Type> {
    
  enum Type implements NodeType<Pfam, Pfam.Type> {

    pfam;
    public Type value() { return pfam; }
  }
    //----GETTERS---
    public String getId();
    public String getName();
    
    public List<Protein> getAssociatedProteins();

    //----SETTERS----
    public void setId(String value);
    public void setName(String value);  
    
}
