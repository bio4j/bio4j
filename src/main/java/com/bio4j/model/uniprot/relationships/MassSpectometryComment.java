
package com.bio4j.model.uniprot.relationships.comment;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface MassSpectometryComment extends BasicComment{
    
    public String getBegin();
    public String getEnd();
    public String getMass();
    public String getMethod();

    public void setBegin(String value);
    public void setEnd(String value);
    public void setMass(String value);
    public void setMethod(String value);
    
}
