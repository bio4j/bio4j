package com.bio4j.model.isoforms.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.Name;
import com.bio4j.model.properties.Id;
import com.bio4j.model.properties.Sequence;
import com.bio4j.model.properties.Note;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
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
  public static enum Type implements NodeType<Isoform, Isoform.Type> {
    isoform;
    public Type value() { return isoform; }
  }
}
