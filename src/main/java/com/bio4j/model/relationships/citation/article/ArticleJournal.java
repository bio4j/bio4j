
package com.bio4j.model.relationships.citation.article;

import com.bio4j.model.nodes.citation.Article;
import com.bio4j.model.nodes.citation.Journal;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ArticleJournal extends Edge {
    
    //--------GETTERS----------
    public String getDate();
    public String getVolume();
    public String getFirst();
    public String getLast();
    public Article getArticle();
    public Journal getJournal();

    //--------SETTERS------------
    public void setDate(String value);
    public void setVolume(String value);
    public void setFirst(String value);
    public void setLast(String value);
    
}
