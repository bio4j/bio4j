
package com.bio4j.model.refseq.nodes.rna;


/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface MiscRNA extends RNA<MiscRNA, MiscRNA.type> { 

  enum type implements RNAType<MiscRNA, MiscRNA.type> {

    miscRNA;
    public type value() { return miscRNA; }
  }   
}