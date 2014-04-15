package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.OnlineArticle;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface OnlineArticleAuthor extends Relationship <
	  OnlineArticle, OnlineArticle.Type,
	  OnlineArticleAuthor, OnlineArticleAuthor.Type,
	  Person, Person.Type
	  > {
	
	  public static Type TYPE = Type.onlineArticleAuthor;
	  public static enum Type implements RelationshipType <
	    OnlineArticle, OnlineArticle.Type,
	    OnlineArticleAuthor, OnlineArticleAuthor.Type,
	    Person, Person.Type
	    > {
	
		    onlineArticleAuthor;
		    public Type value() { return onlineArticleAuthor; }
		    public Arity arity() { return Arity.manyToMany; }
		    public OnlineArticle.Type sourceType() { return OnlineArticle.TYPE; }
		    public Person.Type targetType() { return Person.TYPE; }
	  }
	  
	  public OnlineArticle source();
	  public Person target();

}