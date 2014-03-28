package com.bio4j.model.relationships.citation.onarticle;

import com.bio4j.model.Property;
import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.nodes.citation.OnlineArticle;
import com.bio4j.model.nodes.citation.OnlineJournal;

/**
 *
 * @author Alexey Alekhin <aalekhin@ohnosequences.com>
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
    }

    public Property<String> locator();

}
