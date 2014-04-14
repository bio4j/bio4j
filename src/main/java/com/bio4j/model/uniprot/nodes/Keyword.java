package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.properties.Name;
import com.bio4j.model.properties.Id;


/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Keyword extends Node<Keyword, Keyword.Type>,
  
  // properties
  Id<Keyword, Keyword.Type>,
  Name<Keyword, Keyword.Type>
{
    
  public static Type TYPE = Type.keyword;
  public static enum Type implements NodeType<Keyword, Keyword.Type> {

    keyword;
    public Type value() { return keyword; }
  }
}
