package com.bio4j.model.uniprot.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Submission;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface SubmissionAuthor extends Relationship <
	Submission, Submission.Type,
	SubmissionAuthor, SubmissionAuthor.Type,
	Person, Person.Type
	> {
    
	public static Type TYPE = Type.submissionAuthor;
	enum Type implements RelationshipType <
	  Submission, Submission.Type,
	  SubmissionAuthor, SubmissionAuthor.Type,
	  Person, Person.Type
	> {
		submissionAuthor;
	  public Type value() { return submissionAuthor; }
	  public Arity arity() { return Arity.manyToMany; }
	  public Submission.Type sourceType() { return Submission.TYPE; }
	  public Person.Type targetType() { return Person.TYPE; }
	
	}
	
	public Submission source();
	public Person target();
    
}
