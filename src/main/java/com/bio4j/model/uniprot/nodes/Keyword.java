package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Keyword extends Node<Keyword, Keyword.type> {
    
  enum type implements NodeType<Keyword, Keyword.type> {

    keyword;
    public type value() { return keyword; }
  }
    
    //----GETTERS---
    public String getId();
    public String getName();

    //----SETTERS----
    public void setId(String value);
    public void setName(String value);
    
}
