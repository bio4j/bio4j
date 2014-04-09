package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Consortium extends Node<Consortium, Consortium.Type> {
    
  enum Type implements NodeType<Consortium, Consortium.Type> {

    consortium;
    public Type value() { return consortium; }
  }
   
    //----GETTERS---
    public String getName();

    //----SETTERS----
    public void setName(String value); 
}
