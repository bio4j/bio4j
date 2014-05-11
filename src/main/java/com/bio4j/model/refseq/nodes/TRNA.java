package com.bio4j.model.refseq.nodes;

import java.util.List;

import com.bio4j.model.go.nodes.GoTerm.Type;
import com.bio4j.model.refseq.relationships.HasTRNA;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface TRNA extends RNA<TRNA, TRNA.Type> { 

  // hasTRNA
  // in
  public List<? extends HasTRNA> hasTRNA_in();
  public List<? extends GenomeElement> hasTRNA_inNodes();

  public static Type TYPE = Type.tRNA; 
  public default Type type() { return TYPE; }
  enum Type implements RNAType<TRNA, TRNA.Type> {

    tRNA;
    public Type value() { return tRNA; }
  }   
}
