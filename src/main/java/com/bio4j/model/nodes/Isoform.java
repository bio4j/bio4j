package com.bio4j.model.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Isoform extends Node<Isoform, Isoform.type> {
    
  enum type implements NodeType<Isoform, Isoform.type> {

    isoform;
    public type value() { return isoform; }
  }
 
    //----GETTERS---
    public String getId();
    public String getNote();
    public String getName();
    public String getSequence();

    //----SETTERS----
    public void setId(String value);
    public void setNote(String value);
    public void setName(String value);
    public void setSequence(String value);  
}
