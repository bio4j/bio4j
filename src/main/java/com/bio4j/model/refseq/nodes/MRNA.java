package com.bio4j.model.refseq.nodes;

import java.util.List;
import com.bio4j.model.refseq.relationships.HasMRNA;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface MRNA extends RNA<MRNA, MRNA.Type> { 

  // hasMRNA
  // in
  public List<? extends HasMRNA> hasMRNA_in();
  public List<? extends GenomeElement> hasMRNA_inNodes();

  public static Type TYPE = Type.mRNA;
  public static enum Type implements RNAType<MRNA, MRNA.Type> {

    mRNA;
    public Type value() { return mRNA; }
  }   
}
