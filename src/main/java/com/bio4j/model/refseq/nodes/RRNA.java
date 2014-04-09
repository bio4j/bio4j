package com.bio4j.model.refseq.nodes;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface RRNA extends RNA<RRNA, RRNA.Type> { 

  public static Type TYPE = Type.rRNA;
  public static enum Type implements RNAType<RRNA, RRNA.Type> {

    rRNA;
    public Type value() { return rRNA; }
  }   
}
