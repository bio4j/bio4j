
package com.bio4j.model.relationships.citation.onarticle;

import com.bio4j.model.nodes.citation.OnlineArticle;
import com.bio4j.model.nodes.citation.OnlineJournal;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface OnlineArticleJournal extends Relationship {
    
    //---------GETTERS------------
    public String getLocator();
    public OnlineArticle getOnlineArticle();    
    public OnlineJournal getOnlineJournal();

    //---------SETTERS-----------
    public void setLocator(String value);   
    
}
