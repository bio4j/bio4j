
package com.bio4j.model.uniprot.relationships.thesis;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.RelationshipType.Arity;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Thesis;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ThesisAuthor extends Relationship <
	Thesis, Thesis.Type,
	ThesisAuthor, ThesisAuthor.Type,
	Person, Person.Type
	> {
	
	public static Type TYPE = Type.thesisAuthor;
	enum Type implements RelationshipType <
		Thesis, Thesis.Type,
		ThesisAuthor, ThesisAuthor.Type,
		Person, Person.Type
		> {
		thesisAuthor;
		  public Type value() { return thesisAuthor; }
		  public Arity arity() { return Arity.manyToMany; }
		  public Thesis.Type sourceType() { return Thesis.TYPE; }
		  public Person.Type targetType() { return Person.TYPE; }
	
	}
	
	public Thesis source();
	public Person target();

}
