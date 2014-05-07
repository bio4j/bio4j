package com.bio4j.model.uniprot.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Patent;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface PatentAuthor extends Relationship <
	Patent, Patent.Type,
	PatentAuthor, PatentAuthor.Type,
	Person, Person.Type
	> {
	
	public static Type TYPE = Type.patentAuthor;
	enum Type implements RelationshipType <
	  Patent, Patent.Type,
	  PatentAuthor, PatentAuthor.Type,
	  Person, Person.Type
	> {
	  patentAuthor;
	  public Type value() { return patentAuthor; }
	  public Arity arity() { return Arity.manyToMany; }
	  public Patent.Type sourceType() { return Patent.TYPE; }
	  public Person.Type targetType() { return Person.TYPE; }
	
	}
	
	public Patent source();
	public Person target();

}
