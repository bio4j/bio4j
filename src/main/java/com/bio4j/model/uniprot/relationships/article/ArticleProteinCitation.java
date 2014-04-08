package com.bio4j.model.relationships.citation.article;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Article;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface ArticleProteinCitation extends Relationship <
    Article, Article.type,
    ArticleProteinCitation, ArticleProteinCitation.type,
    Protein, Protein.type
> {

    enum type implements RelationshipType <
        Article, Article.type,
        ArticleProteinCitation, ArticleProteinCitation.type,
        Protein, Protein.type
    > {
        articleProteinCitation;
        public type value() { return articleProteinCitation; }
        public arity arity() { return arity.manyToMany; }
        public Article.type sourceType() { return Article.TYPE; }
        public Protein.type targetType() { return Protein.TYPE; }

    }

}
