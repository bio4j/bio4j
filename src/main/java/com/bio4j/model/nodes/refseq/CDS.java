
package com.bio4j.model.nodes.refseq;

import com.bio4j.model.Node;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface CDS extends Node {
    
    //---------GETTERS------------
    public String getPositions();
    public String getNote();
    public GenomeElement getGenomeElement();

    //---------SETTERS--------------
    public void setPositions(String value);
    public void setNote(String value);

    
}
