package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface SequenceCaution extends Node<SequenceCaution, SequenceCaution.Type> {
    
  public static enum Type implements NodeType<SequenceCaution, SequenceCaution.Type> {

    sequenceCaution;
    public Type value() { return sequenceCaution; }
  }
    
    //----GETTERS---
    public String getName();
    
    //----SETTERS----
    public void setName(String value);    
    
    
}
