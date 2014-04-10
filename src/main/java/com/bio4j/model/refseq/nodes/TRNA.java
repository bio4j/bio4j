package com.bio4j.model.refseq.nodes;

import java.util.List;

import com.bio4j.model.refseq.relationships.HasTRNA;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface TRNA extends RNA<TRNA, TRNA.Type> { 

  // hasTRNA
  // in
  public List<? extends HasTRNA> hasTRNA_in();
  public List<? extends GenomeElement> hasTRNA_inNodes();

  public static Type TYPE = Type.tRNA; 
  enum Type implements RNAType<TRNA, TRNA.Type> {

    tRNA;
    public Type value() { return tRNA; }
  }   
}
