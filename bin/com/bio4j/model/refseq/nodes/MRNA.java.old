package com.bio4j.model.refseq.nodes;

import java.util.List;

import com.bio4j.model.go.nodes.Term.Type;
import com.bio4j.model.refseq.relationships.HasMRNA;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface MRNA extends RNA<MRNA, MRNA.Type> { 

  // hasMRNA
  // in
  public List<? extends HasMRNA> hasMRNA_in();
  public List<? extends GenomeElement> hasMRNA_inNodes();

  public static Type TYPE = Type.mRNA;
  public default Type type() { return TYPE; }
  public static enum Type implements RNAType<MRNA, MRNA.Type> {

    mRNA;
    public Type value() { return mRNA; }
  }   
}
