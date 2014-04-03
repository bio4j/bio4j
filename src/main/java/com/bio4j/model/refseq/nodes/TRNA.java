package com.bio4j.model.refseq.nodes;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface TRNA extends RNA<TRNA, TRNA.type> { 

  enum type implements RNAType<TRNA, TRNA.type> {

    tRNA;
    public type value() { return tRNA; }
  }   
}
