package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.bio4j.model.go.nodes.Term.Type;
import com.bio4j.model.properties.Date;
import com.bio4j.model.properties.Title;
import com.bio4j.model.uniprot.relationships.SubmissionDb;
import com.bio4j.model.uniprot.relationships.SubmissionProteinCitation;
import com.bio4j.model.uniprot.nodes.Consortium;
import com.bio4j.model.uniprot.nodes.Person;

import java.util.List;

import com.ohnosequences.typedGraphs.NodeType;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Submission extends Node<Submission, Submission.Type>,

	// properties
	Title<Submission, Submission.Type>, Date<Submission, Submission.Type> {

	public static Type TYPE = Type.submission;
	public default Type type() { return TYPE; }

	public static enum Type implements NodeType<Submission, Submission.Type> {

		submission;
		public Type value() {
			return submission;
		}
	}

	// submissionDB
	// outgoing
	public SubmissionDb submissionDb_out();
	public DB submissionDb_outNodes();

	// submissionProteinCitation
	// outgoing
	public List<SubmissionProteinCitation> submissionProteinCitation_out();
	public List<Protein> submissionProteinCitation_outNodes();
	
	public List<Consortium> getConsortiumAuthors();

	public List<Person> getPersonAuthors();

}
