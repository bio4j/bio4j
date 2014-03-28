package com.bio4j.model.relationships.citation.onarticle;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.nodes.citation.OnlineArticle;
import com.bio4j.model.nodes.Protein;

/**
 *
 * @author Alexey Alekhin <aalekhin@ohnosequences.com>
 */
public interface OnlineArticleProteinCitation extends Relationship <
    OnlineArticle, OnlineArticle.type,
    OnlineArticleProteinCitation, OnlineArticleProteinCitation.type,
    Protein, Protein.type
> {

    enum type implements RelationshipType <
        OnlineArticle, OnlineArticle.type,
        OnlineArticleProteinCitation, OnlineArticleProteinCitation.type,
        Protein, Protein.type
    > {
        OnlineArticleProteinCitation;
        public type value() { return OnlineArticleProteinCitation; }
    }

}
