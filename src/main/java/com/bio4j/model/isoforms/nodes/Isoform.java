package com.bio4j.model.isoforms.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.bio4j.model.go.nodes.Term.Type;
// properties
import com.bio4j.model.properties.Name;
import com.bio4j.model.properties.Id;
import com.bio4j.model.properties.Sequence;
import com.bio4j.model.properties.Note;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Isoform extends Node<Isoform, Isoform.Type>,

  // properties
  Id<Isoform, Isoform.Type>,
  Name<Isoform, Isoform.Type>,
  Sequence<Isoform, Isoform.Type>,
  Note<Isoform, Isoform.Type>
  
{ 
  
	
  // TODO most likely rel methods missing here
  public static Type TYPE = Type.isoform;  
  public default Type type() { return TYPE; }
  public static enum Type implements NodeType<Isoform, Isoform.Type> {
    isoform;
    public Type value() { return isoform; }
  }
}
