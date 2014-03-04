
package com.bio4j.model.nodes.citation;

import com.bio4j.model.Vertex;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface OnlineJournal extends Vertex {
    
    //----GETTERS---
    public String getName();
    public List<OnlineArticle> getOnlineArticles();
    
    //----SETTERS---
    public void setName(String value);
    
}
