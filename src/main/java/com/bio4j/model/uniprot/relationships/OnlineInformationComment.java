
package com.bio4j.model.uniprot.relationships.comment;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface OnlineInformationComment extends BasicComment{
    
    
    public String getName();
    public String getLink();

    
    public void setName(String value);
    public void setLink(String value);
    
}
