package com.bio4j.model.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Institute extends Node<Institute, Institute.type> {
    
  enum type implements NodeType<Institute, Institute.type> {

    institute;
    public type value() { return institute; }
  }

    //----GETTERS---
    public String getName();

    //----SETTERS----
    public void setName(String value);  
}
