package com.bio4j.model.relationships.citation.article;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.nodes.citation.Article;
import com.bio4j.model.nodes.Protein;

/**
 *
 * @author Alexey Alekhin <aalekhin@ohnosequences.com>
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
        ArticleProteinCitation;
        public type value() { return ArticleProteinCitation; }
    }

}
