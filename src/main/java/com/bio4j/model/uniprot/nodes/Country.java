package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Country extends Node<Country, Country.type> {
    
  enum type implements NodeType<Country, Country.type> {

    country;
    public type value() { return country; }
  }
 
    //----GETTERS---
    public String getName();

    //----SETTERS----
    public void setName(String value); 
    
}
