package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.uniprot.nodes.Article;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
// articleCitesProtein
public interface ArticleProteinCitation extends Relationship <
  Article, Article.Type,
  ArticleProteinCitation, ArticleProteinCitation.Type,
  Protein, Protein.Type
> {

  public Article source();
  public Protein target();

  public static enum Type implements RelationshipType <
      Article, Article.Type,
      ArticleProteinCitation, ArticleProteinCitation.Type,
      Protein, Protein.Type
  > {
      articleProteinCitation;
      public Type value() { return articleProteinCitation; }
      public Arity arity() { return Arity.manyToMany; }
      public Article.Type sourceType() { return Article.TYPE; }
      public Protein.Type targetType() { return Protein.TYPE; }

  }
    
  

}
