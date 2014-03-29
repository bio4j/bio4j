package com.bio4j.model.refseq.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.properties.positions;
import com.bio4j.model.properties.note;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface CDS extends Node<CDS, CDS.type>,
  // properties
  positions,
  note
{
  
  public static enum type implements NodeType<CDS, CDS.type> {
    cds;
    public type value() { return cds; }
  }

  public GenomeElement getGenomeElement();
}