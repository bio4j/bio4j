package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Dataset extends Node<Dataset, Dataset.Type> {
    
  enum Type implements NodeType<Dataset, Dataset.Type> {

    dataset;
    public Type value() { return dataset; }
  }

    
    //----GETTERS---
    public String getName();

    //----SETTERS----
    public void setName(String value); 
}