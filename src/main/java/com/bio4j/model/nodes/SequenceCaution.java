package com.bio4j.model.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface SequenceCaution extends Node<SequenceCaution, SequenceCaution.type> {
    
  enum type implements NodeType<SequenceCaution, SequenceCaution.type> {

    sequenceCaution;
    public type value() { return sequenceCaution; }
  }
    
    //----GETTERS---
    public String getName();
    
    //----SETTERS----
    public void setName(String value);    
    
    
}
