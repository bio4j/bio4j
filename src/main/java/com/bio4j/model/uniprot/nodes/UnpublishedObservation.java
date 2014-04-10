package com.bio4j.model.uniprot.nodes;
import com.bio4j.model.Node;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;

import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface UnpublishedObservation extends Node<UnpublishedObservation, UnpublishedObservation.Type> {
    
  public static enum Type implements NodeType<UnpublishedObservation, UnpublishedObservation.Type> {

    unpublishedObservation;
    public Type value() { return unpublishedObservation; }
  }
  
  //------GETTERS-----
  public String getDate();
  public Person getAuthor();
  public List<Protein> getProteinCitations();
  
  //------SETTERS--------
  public void setDate(String value);   
}
