
package com.bio4j.model.relationships.citation.onarticle;

import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.citation.OnlineArticle;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface OnlineArticleProteinCitation extends Edge {
    
    //---------GETTERS------------
    public OnlineArticle getOnlineArticle();    
    public Protein getProtein();
    
}
