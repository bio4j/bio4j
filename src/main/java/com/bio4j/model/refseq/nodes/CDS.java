package com.bio4j.model.refseq.nodes;

import java.util.List;

// relationships
import com.bio4j.model.refseq.relationships.HasCDS;
// properties

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface CDS extends GenomicFeature<CDS, CDS.Type> {
  
  // hasCDS
  // in
  public List<? extends HasCDS> hasCDS_in();
  public List<? extends GenomeElement> hasCDS_inNodes();

  public static Type TYPE = Type.cds;
  public default Type type() { return TYPE; }
  public static enum Type implements GenomicFeatureType<CDS, CDS.Type> {
    cds;
    public Type value() { return cds; }
  }
}
