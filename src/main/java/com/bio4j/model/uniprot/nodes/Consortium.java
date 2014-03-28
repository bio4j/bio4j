package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Consortium extends Node<Consortium, Consortium.type> {
    
  enum type implements NodeType<Consortium, Consortium.type> {

    consortium;
    public type value() { return consortium; }
  }
   
    //----GETTERS---
    public String getName();

    //----SETTERS----
    public void setName(String value); 
}
