package com.bio4j.model.nodes.citation;
import com.bio4j.model.Node;
import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.Protein;

import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 **/
public interface UnpublishedObservation extends Node {
    
    //------GETTERS-----
    public String getDate();
    public Person getAuthor();
    public List<Protein> getProteinCitations();
    
    //------SETTERS--------
    public void setDate(String value);
  
    
}
