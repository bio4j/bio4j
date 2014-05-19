package com.bio4j.model.refseq.nodes;


/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface RNA <
  R extends RNA<R,T>,
  T extends Enum<T> & RNAType<R,T>
> extends GenomicFeature<R,T> {
    
  // interface type <
  //   R extends RNA,
  //   RT extends Enum<RT> & NodeType<R,RT>
  // > extends NodeType<R, RNA.Type> {}
  
  // TODO migrate to rels 
  public GenomeElement getGenomeElement();
}
