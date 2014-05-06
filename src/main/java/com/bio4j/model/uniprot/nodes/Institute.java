package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.properties.Name;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Institute extends Node<Institute, Institute.Type>,
  
  // properties
  Name<Institute, Institute.Type>
{
    
  public static Type TYPE = Type.institute;  
  public static enum Type implements NodeType<Institute, Institute.Type> {

    institute;
    public Type value() { return institute; }
  }
}
