package com.bio4j.model.uniprot.relationships.references;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// properties

import com.bio4j.model.properties.Date;
import com.bio4j.model.properties.Title;
import com.bio4j.model.uniprot.nodes.references.Reference;
import com.bio4j.model.uniprot.nodes.references.Submissions;

/**
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Submission extends Relationship<

	Reference, Reference.Type, 
	Submission, Submission.Type, 
	Submissions, Submissions.Type

>,
	// properties
	Title<Submission, Submission.Type>, 
	Date<Submission, Submission.Type>
	{

	public static Type TYPE = Type.submission;

	public static enum Type implements RelationshipType<

	Reference, Reference.Type, Submission, Submission.Type, Submissions, Submissions.Type> {
		submission;

		// there is only one Submissions node => many to one.
		public Arity arity() {
			return Arity.manyToOne;
		}

		public Reference.Type sourceType() {
			return Reference.TYPE;
		}

		public Submissions.Type targetType() {
			return Submissions.TYPE;
		}

		public Type value() {
			return submission;
		}
	}
}
