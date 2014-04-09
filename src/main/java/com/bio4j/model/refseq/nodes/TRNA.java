package com.bio4j.model.refseq.nodes;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface TRNA extends RNA<TRNA, TRNA.Type> { 

  public static Type TYPE = Type.tRNA; 
  enum Type implements RNAType<TRNA, TRNA.Type> {

    tRNA;
    public Type value() { return tRNA; }
  }   
}
