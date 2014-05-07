package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.uniprot.relationships.references.Submission;


/**
 *  This Node has just one instance per graph. Relationships of type `Submission` to this node blahblahblah
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Submissions extends Node<Submissions, Submissions.Type> {
  
  public List<? extends Submission> submission_in();
  public List<? extends Reference> submission_inNodes();

  public static Type TYPE = Type.submissions;

  public static enum Type implements NodeType<Submissions, Submissions.Type> {
    
	  submissions;
    
    public Type value() { return submissions; }
  }
}
