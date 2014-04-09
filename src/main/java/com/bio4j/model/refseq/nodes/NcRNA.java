package com.bio4j.model.refseq.nodes;


/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface NcRNA extends RNA<NcRNA, NcRNA.Type> { 

  public static Type TYPE = Type.ncRNA; 
  public static enum Type implements RNAType<NcRNA, NcRNA.Type> {

    ncRNA;
    public Type value() { return ncRNA; }
  }   
}
