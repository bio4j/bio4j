package com.bio4j.model.uniprot.relationships.onarticle;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.OnlineArticle;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface OnlineArticleProteinCitation extends Relationship <
    OnlineArticle, OnlineArticle.Type,
    OnlineArticleProteinCitation, OnlineArticleProteinCitation.Type,
    Protein, Protein.Type
> {

    public static Type TYPE = Type.onlineArticleProteinCitation;
    public static enum Type implements RelationshipType <
        OnlineArticle, OnlineArticle.Type,
        OnlineArticleProteinCitation, OnlineArticleProteinCitation.Type,
        Protein, Protein.Type
    > {
        onlineArticleProteinCitation;
        public Type value() { return onlineArticleProteinCitation; }
        public Arity arity() { return Arity.manyToMany; }
        public OnlineArticle.Type sourceType() { return OnlineArticle.TYPE; }
        public Protein.Type targetType() { return Protein.TYPE; }
    }

}
