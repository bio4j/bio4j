
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.properties.Date;
import com.bio4j.model.properties.Title;
import com.bio4j.model.uniprot.relationships.submission.SubmissionDb;
import com.bio4j.model.uniprot.nodes.Consortium;
import com.bio4j.model.uniprot.nodes.Person;

import java.util.List;

import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Submission extends Node<Submission, Submission.Type>,
  
  // properties
	Title<Submission, Submission.Type>,
	Date<Submission, Submission.Type>
{
	
	public static Type TYPE = Type.submission;    
	public static enum Type implements NodeType<Submission, Submission.Type> {
	
	    submission;
	    public Type value() { return submission; }
	}
	
	  // submissionDB
    // outgoing
    public SubmissionDb submissionDb_out(); 
    public DB submissionDb_outNodes();
      
    public DB getDB();    
    public List<Consortium> getConsortiumAuthors();
    public List<Person> getPersonAuthors();
    public List<Protein> getProteinCitations();

    

}
