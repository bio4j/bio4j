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
public interface CDS extends GenomicFeature<CDS, CDS.Type> {
  
  // cdss
  // incoming
  // TODO

  public static Type TYPE = Type.cds;
  public static enum Type implements GenomicFeatureType<CDS, CDS.Type> {
    cds;
    public Type value() { return cds; }
  }
}