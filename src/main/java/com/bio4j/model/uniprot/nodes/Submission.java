
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.uniprot.nodes.Consortium;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Submission extends Node<Submission, Submission.Type> {
    
  public static enum Type implements NodeType<Submission, Submission.Type> {

    submission;
    public Type value() { return submission; }
  }
      
    //--------GETTERS-------------
    public String getTitle();
    public String getDate();
    public DB getDB();    
    public List<Consortium> getConsortiumAuthors();
    public List<Person> getPersonAuthors();
    public List<Protein> getProteinCitations();

    //--------SETTERS---------
    public void setTitle(String value);
    public void setDate(String value);
    

}
