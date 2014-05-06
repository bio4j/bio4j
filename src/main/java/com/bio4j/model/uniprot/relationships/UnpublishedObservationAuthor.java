package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.RelationshipType.Arity;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.UnpublishedObservation;
import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface UnpublishedObservationAuthor extends Relationship <
	UnpublishedObservation, UnpublishedObservation.Type,
	UnpublishedObservationAuthor, UnpublishedObservationAuthor.Type,
	Person, Person.Type
	> {
	
	public static Type TYPE = Type.unpublishedObservationAuthor;
	enum Type implements RelationshipType <
		UnpublishedObservation, UnpublishedObservation.Type,
		UnpublishedObservationAuthor, UnpublishedObservationAuthor.Type,
		Person, Person.Type
		> {
		unpublishedObservationAuthor;
		  public Type value() { return unpublishedObservationAuthor; }
		  public Arity arity() { return Arity.manyToMany; }
		  public UnpublishedObservation.Type sourceType() { return UnpublishedObservation.TYPE; }
		  public Person.Type targetType() { return Person.TYPE; }
	
	}
	
	public UnpublishedObservation source();
	public Person target();

}
