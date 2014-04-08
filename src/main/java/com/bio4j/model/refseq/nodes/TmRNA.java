package com.bio4j.model.refseq.nodes;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface TmRNA extends RNA<TmRNA, TmRNA.type> { 

  public static type TYPE = type.tmRNA;
  public static enum type implements RNAType<TmRNA, TmRNA.type> {

    tmRNA;
    public type value() { return tmRNA; }
  }   
}