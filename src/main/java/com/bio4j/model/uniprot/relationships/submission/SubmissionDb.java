
package com.bio4j.model.uniprot.relationships.submission;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.RelationshipType.Arity;
import com.bio4j.model.properties.Date;
import com.bio4j.model.uniprot.nodes.DB;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.Submission;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface SubmissionDb extends Relationship <
	Submission, Submission.Type,
	SubmissionDb, SubmissionDb.Type,
	DB, DB.Type
	>,
	Date<SubmissionDb, SubmissionDb.Type>{
	
	enum Type implements RelationshipType <
		Submission, Submission.Type,
		SubmissionDb, SubmissionDb.Type,
		DB, DB.Type
	> {
		bookAuthor;
		  public Type value() { return bookAuthor; }
		  public Arity arity() { return Arity.manyToMany; }
		  public Submission.Type sourceType() { return Submission.TYPE; }
		  public DB.Type targetType() { return DB.TYPE; }
	
	}
    

}
