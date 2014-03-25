package com.bio4j.model.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface FeatureType extends Node<FeatureType, FeatureType.type> {
    
  enum type implements NodeType<FeatureType, FeatureType.type> {

    featureType;
    public type value() { return featureType; }
  }
 
    //----GETTERS---
    public String getName();

    //----SETTERS----
    public void setName(String value); 
}
