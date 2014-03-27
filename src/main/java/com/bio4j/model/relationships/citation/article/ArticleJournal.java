package com.bio4j.model.relationships.citation.article;

import com.bio4j.model.Property;
import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.nodes.citation.Article;
import com.bio4j.model.nodes.citation.Journal;

/**
 *
 * @author Alexey Alekhin <aalekhin@ohnosequences.com>
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
        ArticleJournal;
        public type value() { return ArticleJournal; }
    }

    public Property<String> date();
    public Property<String> volume();
    public Property<String> first();
    public Property<String> last();

}
