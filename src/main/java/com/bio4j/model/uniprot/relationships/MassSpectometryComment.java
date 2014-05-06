
package com.bio4j.model.uniprot.relationships;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
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
