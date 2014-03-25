
package com.bio4j.model.nodes.refseq.rna;


/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface NcRNA extends RNA<NcRNA, NcRNA.type> { 

  enum type implements RNAType<NcRNA, NcRNA.type> {

    ncRNA;
    public type value() { return ncRNA; }
  }   
}
