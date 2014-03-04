
package com.bio4j.model.nodes.refseq;

import com.bio4j.model.Vertex;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Gene extends Vertex {
    
    //---------GETTERS------------
    public String getPositions();
    public String getNote();
    public GenomeElement getGenomeElement();


    //---------SETTERS-------
    public void setPositions(String value);
    public void setNote(String value);

}
