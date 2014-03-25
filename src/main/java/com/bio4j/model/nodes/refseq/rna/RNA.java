
package com.bio4j.model.nodes.refseq.rna;

import com.bio4j.model.Node;
import com.bio4j.model.nodes.refseq.GenomeElement;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface RNA <
  R extends RNA<R,T>,
  T extends Enum<T> & RNAType<R,T>
> extends Node<R,T> {
    
  // interface type <
  //   R extends RNA,
  //   RT extends Enum<RT> & NodeType<R,RT>
  // > extends NodeType<R, RNA.type> {}
   
    
    //-----------GETTERS------------
    public String getPositions();
    public String getNote();
    public GenomeElement getGenomeElement();

    //-----------SETTERS-------------
    public void setPositions(String value);
    public void setNote(String value);
}
