
package com.bio4j.model.nodes.refseq.rna;


/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface TmRNA extends RNA<TmRNA, TmRNA.type> { 

  enum type implements RNAType<TmRNA, TmRNA.type> {

    tmRNA;
    public type value() { return tmRNA; }
  }   
}