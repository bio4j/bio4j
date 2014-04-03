package com.bio4j.model.refseq.nodes;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface MiscRNA extends RNA<MiscRNA, MiscRNA.type> { 

  public static type TYPE = type.miscRNA;
  enum type implements RNAType<MiscRNA, MiscRNA.type> {

    miscRNA;
    public type value() { return miscRNA; }
  }   
}