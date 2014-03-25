
package com.bio4j.model.nodes.refseq.rna;


/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface MRNA extends RNA<MRNA, MRNA.type> { 

  enum type implements RNAType<MRNA, MRNA.type> {

    mRNA;
    public type value() { return mRNA; }
  }   
}
