package com.bio4j.model.refseq.nodes;

import java.util.List;

import com.bio4j.model.go.nodes.GoTerm.Type;
import com.bio4j.model.refseq.relationships.HasTmRNA;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface TmRNA extends RNA<TmRNA, TmRNA.Type> { 

  // hasTmRNA
  // in
  public List<? extends HasTmRNA> hasTmRNA_in();
  public List<? extends GenomeElement> hasTmRNA_inNodes();


  public static Type TYPE = Type.tmRNA;
  public default Type type() { return TYPE; }
  public static enum Type implements RNAType<TmRNA, TmRNA.Type> {

    tmRNA;
    public Type value() { return tmRNA; }
  }   
}
