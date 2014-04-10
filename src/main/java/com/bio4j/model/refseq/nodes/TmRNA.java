package com.bio4j.model.refseq.nodes;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface TmRNA extends RNA<TmRNA, TmRNA.Type> { 

  public static Type TYPE = Type.tmRNA;
  public static enum Type implements RNAType<TmRNA, TmRNA.Type> {

    tmRNA;
    public Type value() { return tmRNA; }
  }   
}