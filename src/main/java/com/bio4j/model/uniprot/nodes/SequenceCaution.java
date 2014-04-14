package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.properties.Name;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface SequenceCaution extends Node<SequenceCaution, SequenceCaution.Type>,

  // properties
  Name<SequenceCaution, SequenceCaution.Type>
{
    
  public static Type TYPE = Type.sequenceCaution;
  public static enum Type implements NodeType<SequenceCaution, SequenceCaution.Type> {

    sequenceCaution;
    public Type value() { return sequenceCaution; }
  }
}
