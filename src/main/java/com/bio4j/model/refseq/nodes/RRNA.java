package com.bio4j.model.refseq.nodes;

import java.util.List;

import com.bio4j.model.refseq.relationships.HasRRNA;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface RRNA extends RNA<RRNA, RRNA.Type> { 

  // hasRRNA
  // in
  public List<? extends HasRRNA> hasRRNA_in();
  public List<? extends GenomeElement> hasRRNA_inNodes();

  public static Type TYPE = Type.rRNA;
  public static enum Type implements RNAType<RRNA, RRNA.Type> {

    rRNA;
    public Type value() { return rRNA; }
  }   
}
