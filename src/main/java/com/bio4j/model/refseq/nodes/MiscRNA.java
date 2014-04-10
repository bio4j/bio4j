package com.bio4j.model.refseq.nodes;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface MiscRNA extends RNA<MiscRNA, MiscRNA.Type> { 

  public static Type TYPE = Type.miscRNA;
  enum Type implements RNAType<MiscRNA, MiscRNA.Type> {

    miscRNA;
    public Type value() { return miscRNA; }
  }   
}