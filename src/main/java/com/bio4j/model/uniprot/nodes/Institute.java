package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Institute extends Node<Institute, Institute.Type> {
    
  public static Type TYPE = Type.institute;  
  public static enum Type implements NodeType<Institute, Institute.Type> {

    institute;
    public Type value() { return institute; }
  }

    //----GETTERS---
    public String getName();

    //----SETTERS----
    public void setName(String value);  
}
