
package com.bio4j.model.relationships.citation.article;

import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.citation.Article;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ArticleAuthor {
    
    public Article getArticle();
    public Person getAuthor();
    
}
