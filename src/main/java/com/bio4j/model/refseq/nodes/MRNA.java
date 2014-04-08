package com.bio4j.model.refseq.nodes;


/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface MRNA extends RNA<MRNA, MRNA.type> { 

  public static type TYPE = type.mRNA;
  public static enum type implements RNAType<MRNA, MRNA.type> {

    mRNA;
    public type value() { return mRNA; }
  }   
}
