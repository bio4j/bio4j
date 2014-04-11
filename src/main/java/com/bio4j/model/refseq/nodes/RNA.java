package com.bio4j.model.refseq.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.refseq.nodes.GenomeElement;

import com.bio4j.model.properties.Note;
import com.bio4j.model.properties.Positions;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
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