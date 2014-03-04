
package com.bio4j.model.relationships.citation.article;

import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.citation.Article;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ArticleProteinCitation extends Edge {
    
    //------GETTERS-----
    public Article getArticle();
    
    //-------SETTERS------
    public Protein getProtein();
    
}
