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
    ArticleAuthor;
    public type value() { return ArticleAuthor; }
  }
    
}
