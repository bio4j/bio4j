
package com.bio4j.model.uniprot.relationships.patent;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.RelationshipType.Arity;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Patent;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface PatentAuthor extends Relationship <
	Patent, Patent.Type,
	PatentAuthor, PatentAuthor.Type,
	Person, Person.Type
	> {
	
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

}
