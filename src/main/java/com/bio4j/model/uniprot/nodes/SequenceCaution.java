package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.Term.Type;
import com.bio4j.model.properties.Name;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface SequenceCaution extends Node<SequenceCaution, SequenceCaution.Type>,

  // properties
  Name<SequenceCaution, SequenceCaution.Type>
{
    
  public static Type TYPE = Type.sequenceCaution;
  public default Type type() { return TYPE; }
  public static enum Type implements NodeType<SequenceCaution, SequenceCaution.Type> {

    sequenceCaution;
    public Type value() { return sequenceCaution; }
  }
}
