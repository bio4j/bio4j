package com.bio4j.model.uniprot.relationships.references;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// properties

import com.bio4j.model.properties.Date;
import com.bio4j.model.uniprot.nodes.references.Reference;
import com.bio4j.model.uniprot.nodes.references.UnpublishedObservations;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface UnpublishedObservation extends Relationship <

	Reference, Reference.Type, 
	UnpublishedObservation, UnpublishedObservation.Type, 
	UnpublishedObservations, UnpublishedObservations.Type

>,
		// properties
	Date<UnpublishedObservation, UnpublishedObservation.Type> 
{

	public static Type TYPE = Type.unpublishedObservation;

	public static enum Type implements RelationshipType <

		Reference, Reference.Type, UnpublishedObservation, 
		UnpublishedObservation.Type, UnpublishedObservations, 
		UnpublishedObservations.Type

	> 
	{
		unpublishedObservation;

		// there is only one UnpublishedObservations node => many to one.
		public Arity arity() {
			
			return Arity.manyToOne;
		}

		public Reference.Type sourceType() {
			return Reference.TYPE;
		}

		public UnpublishedObservations.Type targetType() {
			return UnpublishedObservations.TYPE;
		}

		public Type value() {
			return unpublishedObservation;
		}
	}
}
