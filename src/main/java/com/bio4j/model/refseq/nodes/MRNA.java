package com.bio4j.model.refseq.nodes;


/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface MRNA extends RNA<MRNA, MRNA.Type> { 

  public static Type TYPE = Type.mRNA;
  public static enum Type implements RNAType<MRNA, MRNA.Type> {

    mRNA;
    public Type value() { return mRNA; }
  }   
}
