package com.bio4j.model.relationships.citation.article;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Article;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface ArticleAuthor extends Relationship <
  Article, Article.type,
  ArticleAuthor, ArticleAuthor.type,
  Person, Person.type
> {
    
  enum type implements RelationshipType <
    Article, Article.type,
    ArticleAuthor, ArticleAuthor.type,
    Person, Person.type
  > {
    articleAuthor;
    public type value() { return articleAuthor; }
    public arity arity() { return arity.manyToMany; }
    public Article.type sourceType() { return Article.TYPE; }
    public Person.type targetType() { return Person.TYPE; }

  }
    
}
