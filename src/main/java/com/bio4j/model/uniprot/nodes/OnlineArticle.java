
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.uniprot.nodes.Consortium;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;
import java.util.List;
import com.bio4j.model.NodeType;

import com.bio4j.model.properties.Title;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface OnlineArticle extends Node<OnlineArticle, OnlineArticle.Type>,
  
  // properties
  Title<OnlineArticle, OnlineArticle.Type>
{
  
  public static Type TYPE = Type.onlineArticle;  
  public static enum Type implements NodeType<OnlineArticle, OnlineArticle.Type> {

    onlineArticle;
    public Type value() { return onlineArticle; }
  }  

  // TODO move to rels
  public OnlineJournal getOnlineJournal();
  public List<Consortium> getConsortiumAuthors();
  public List<Person> getPersonAuthors();
  public List<Protein> getProteinCitations();
}
