package com.bio4j.model.relationships.citation.onarticle;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.OnlineArticle;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface OnlineArticleAuthor extends Relationship <
  OnlineArticle, OnlineArticle.type,
  OnlineArticleAuthor, OnlineArticleAuthor.type,
  Person, Person.type
> {

  enum type implements RelationshipType <
    OnlineArticle, OnlineArticle.type,
    OnlineArticleAuthor, OnlineArticleAuthor.type,
    Person, Person.type
  > {
    OnlineArticleAuthor;
    public type value() { return OnlineArticleAuthor; }
  }

}
