
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
public interface OnlineArticle extends Node<OnlineArticle, OnlineArticle.Type> {
  
  public static OnlineArticle.Type TYPE = Type.onlineArticle;  
  public static enum Type implements NodeType<OnlineArticle, OnlineArticle.Type> {

    onlineArticle;
    public Type value() { return onlineArticle; }
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
