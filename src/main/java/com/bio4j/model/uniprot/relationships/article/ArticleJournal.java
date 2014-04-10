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
    Article, Article.Type,
    ArticleJournal, ArticleJournal.Type,
    Journal, Journal.Type
> {
    
    public static enum Type implements RelationshipType <
        Article, Article.Type,
        ArticleJournal, ArticleJournal.Type,
        Journal, Journal.Type
    > {
        articleJournal;
        public Type value() { return articleJournal; }
        public Arity arity() { return Arity.manyToOne; }
        public Article.Type sourceType() { return Article.TYPE; }
        public Journal.Type targetType() { return Journal.TYPE; }

    }

    public String date();
    public String volume();
    public String first();
    public String last(); 

}
