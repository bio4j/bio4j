package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.properties.Date;
import com.bio4j.model.properties.First;
import com.bio4j.model.properties.Last;
import com.bio4j.model.properties.Volume;
import com.bio4j.model.uniprot.nodes.Article;
import com.bio4j.model.uniprot.nodes.Journal;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
// inJournal
public interface ArticleJournal extends Relationship <
    Article, Article.Type,
    ArticleJournal, ArticleJournal.Type,
    Journal, Journal.Type
    >,
    Date<ArticleJournal, ArticleJournal.Type>,
    Volume<ArticleJournal, ArticleJournal.Type>,
    First<ArticleJournal, ArticleJournal.Type>,
    Last<ArticleJournal, ArticleJournal.Type>{
    
    public static Type TYPE = Type.articleJournal;    
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
    
    public Article source();
    public Journal target();

}
