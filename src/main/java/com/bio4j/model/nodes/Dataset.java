package com.bio4j.model.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Dataset extends Node<Dataset, Dataset.type> {
    
  enum type implements NodeType<Dataset, Dataset.type> {

    dataset;
    public type value() { return dataset; }
  }

    
    //----GETTERS---
    public String getName();

    //----SETTERS----
    public void setName(String value); 
}
