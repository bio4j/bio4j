package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface FeatureType extends Node<FeatureType, FeatureType.Type> {
    
  enum Type implements NodeType<FeatureType, FeatureType.Type> {

    featureType;
    public Type value() { return featureType; }
  }
 
    //----GETTERS---
    public String getName();

    //----SETTERS----
    public void setName(String value); 
}
