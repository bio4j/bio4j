package com.bio4j.model.relationships.citation.onarticle;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.citation.OnlineArticle;

/**
 *
 * @author Alexey Alekhin <aalekhin@ohnosequences.com>
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
