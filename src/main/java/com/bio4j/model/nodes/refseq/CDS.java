
package com.bio4j.model.nodes.refseq;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface CDS extends Node<CDS, CDS.type> {
    
  enum type implements NodeType<CDS, CDS.type> {

    cds;
    public type value() { return cds; }
  }
  
    //---------GETTERS------------
    public String getPositions();
    public String getNote();
    public GenomeElement getGenomeElement();

    //---------SETTERS--------------
    public void setPositions(String value);
    public void setNote(String value);

    
}
