package com.bio4j.model.relationships.citation.article;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Article;
import com.bio4j.model.uniprot.nodes.Journal;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface ArticleJournal extends Relationship <
    Article, Article.type,
    ArticleJournal, ArticleJournal.type,
    Journal, Journal.type
> {
    
    enum type implements RelationshipType <
        Article, Article.type,
        ArticleJournal, ArticleJournal.type,
        Journal, Journal.type
    > {
        articleJournal;
        public type value() { return articleJournal; }
        public arity arity() { return arity.manyToOne; }
    }

    public String date();
    public String volume();
    public String first();
    public String last(); 

}
