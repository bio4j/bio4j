
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
public interface OnlineArticle extends Node<OnlineArticle, OnlineArticle.type> {
  
  public static OnlineArticle.type TYPE = type.onlineArticle;  
  public static enum type implements NodeType<OnlineArticle, OnlineArticle.type> {

    onlineArticle;
    public type value() { return onlineArticle; }
  }  
    //----GETTERS---
    public String getTitle();
    public OnlineJournal getOnlineJournal();
    public List<Consortium> getConsortiumAuthors();
    public List<Person> getPersonAuthors();
    public List<Protein> getProteinCitations();
    
    //----SETTERS---
    public void setTitle(String value);
    
    
}
