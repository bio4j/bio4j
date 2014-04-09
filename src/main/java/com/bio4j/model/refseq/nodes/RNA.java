package com.bio4j.model.refseq.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.refseq.nodes.GenomeElement;

import com.bio4j.model.properties.note;
import com.bio4j.model.properties.positions;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface RNA <
  R extends RNA<R,T>,
  T extends Enum<T> & RNAType<R,T>
> extends Node<R,T>,
  // properties
  note,
  positions
{
    
  // interface type <
  //   R extends RNA,
  //   RT extends Enum<RT> & NodeType<R,RT>
  // > extends NodeType<R, RNA.type> {}
  
  // TODO migrate to rels 
  public GenomeElement getGenomeElement();
}
