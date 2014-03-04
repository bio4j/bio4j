
package com.bio4j.model.nodes.refseq.rna;

import com.bio4j.model.Vertex;
import com.bio4j.model.nodes.refseq.GenomeElement;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface RNA extends Vertex {
    
    
    //-----------GETTERS------------
    public String getPositions();
    public String getNote();
    public GenomeElement getGenomeElement();

    //-----------SETTERS-------------
    public void setPositions(String value);
    public void setNote(String value);
}
