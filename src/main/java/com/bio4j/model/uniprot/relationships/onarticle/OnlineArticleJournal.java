package com.bio4j.model.uniprot.relationships.onarticle;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.properties.Locator;
import com.bio4j.model.uniprot.nodes.OnlineArticle;
import com.bio4j.model.uniprot.nodes.OnlineJournal;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface OnlineArticleJournal extends Relationship <
    OnlineArticle, OnlineArticle.Type,
    OnlineArticleJournal, OnlineArticleJournal.Type,
    OnlineJournal, OnlineJournal.Type
    >,
    Locator<OnlineArticleJournal,OnlineArticleJournal.Type>{
    
    public static enum Type implements RelationshipType <
        OnlineArticle, OnlineArticle.Type,
        OnlineArticleJournal, OnlineArticleJournal.Type,
        OnlineJournal, OnlineJournal.Type
    > {
        OnlineArticleJournal;
        public Type value() { return OnlineArticleJournal; }
        public Arity arity() { return Arity.manyToOne; }
        public OnlineArticle.Type sourceType() { return OnlineArticle.TYPE; }
        public OnlineJournal.Type targetType() { return OnlineJournal.TYPE; }
    }

    public String locator();

}
