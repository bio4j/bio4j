package com.bio4j.model.relationships.citation.onarticle;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.OnlineArticle;
import com.bio4j.model.uniprot.nodes.OnlineJournal;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface OnlineArticleJournal extends Relationship <
    OnlineArticle, OnlineArticle.type,
    OnlineArticleJournal, OnlineArticleJournal.type,
    OnlineJournal, OnlineJournal.type
> {
    
    enum type implements RelationshipType <
        OnlineArticle, OnlineArticle.type,
        OnlineArticleJournal, OnlineArticleJournal.type,
        OnlineJournal, OnlineJournal.type
    > {
        OnlineArticleJournal;
        public type value() { return OnlineArticleJournal; }
        public arity arity() { return arity.manyToOne; }
    }

    public String locator();

}
