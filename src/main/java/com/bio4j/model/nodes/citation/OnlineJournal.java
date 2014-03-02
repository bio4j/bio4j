
package com.bio4j.model.nodes.citation;

import com.bio4j.model.Node;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface OnlineJournal extends Node {
    
    //----GETTERS---
    public String getName();
    public List<OnlineArticle> getOnlineArticles();
    
    //----SETTERS---
    public void setName(String value);
    
}
