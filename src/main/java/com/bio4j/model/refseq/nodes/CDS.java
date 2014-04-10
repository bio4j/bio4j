package com.bio4j.model.refseq.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.Positions;
import com.bio4j.model.properties.Note;

// relationships

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface CDS extends Node<CDS, CDS.type>,

  // properties
  Positions,
  Note

{
  
  // cdss
  // incoming
  // TODO

  public static type TYPE = type.cds;
  public static enum type implements NodeType<CDS, CDS.type> {
    cds;
    public type value() { return cds; }
  }
}