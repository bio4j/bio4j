package com.bio4j.model.refseq.nodes;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface TRNA extends RNA<TRNA, TRNA.type> { 

  public static type TYPE = type.tRNA; 
  enum type implements RNAType<TRNA, TRNA.type> {

    tRNA;
    public type value() { return tRNA; }
  }   
}
