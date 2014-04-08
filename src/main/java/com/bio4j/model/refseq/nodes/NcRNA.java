package com.bio4j.model.refseq.nodes;


/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface NcRNA extends RNA<NcRNA, NcRNA.type> { 

  public static type TYPE = type.ncRNA; 
  public static enum type implements RNAType<NcRNA, NcRNA.type> {

    ncRNA;
    public type value() { return ncRNA; }
  }   
}
