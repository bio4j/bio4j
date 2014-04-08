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

  public static type TYPE = type.onlineArticleAuthor;
  public static enum type implements RelationshipType <
    OnlineArticle, OnlineArticle.type,
    OnlineArticleAuthor, OnlineArticleAuthor.type,
    Person, Person.type
  > {

    onlineArticleAuthor;
    public type value() { return onlineArticleAuthor; }
    public arity arity() { return arity.manyToMany; }
    public OnlineArticle.type sourceType() { return OnlineArticle.TYPE; }
    public Person.type targetType() { return Person.TYPE; }
  }

}