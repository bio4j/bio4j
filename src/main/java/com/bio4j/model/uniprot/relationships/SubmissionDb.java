package com.bio4j.model.uniprot.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.bio4j.model.RelationshipType.Arity;
import com.bio4j.model.properties.Date;
import com.bio4j.model.uniprot.nodes.DB;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.Submission;
import com.bio4j.model.uniprot.relationships.SubmissionAuthor.Type;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface SubmissionDb extends Relationship <
	Submission, Submission.Type,
	SubmissionDb, SubmissionDb.Type,
	DB, DB.Type
	>,
	Date<SubmissionDb, SubmissionDb.Type>{
	
	public static Type TYPE = Type.submissionDb;
	enum Type implements RelationshipType <
		Submission, Submission.Type,
		SubmissionDb, SubmissionDb.Type,
		DB, DB.Type
	> {
		submissionDb;
		  public Type value() { return submissionDb; }
		  public Arity arity() { return Arity.manyToMany; }
		  public Submission.Type sourceType() { return Submission.TYPE; }
		  public DB.Type targetType() { return DB.TYPE; }
	
	}
	
	public Submission source();
	public DB target();
    

}
