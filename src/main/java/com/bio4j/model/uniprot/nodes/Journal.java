package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import java.util.List;
import com.bio4j.model.NodeType;

import com.bio4j.model.properties.Name;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Journal extends Node<Journal, Journal.Type>,
  
  // properties
  Name<Journal, Journal.Type>
{
  
  public static Type TYPE = Type.journal;
  public static enum Type implements NodeType<Journal, Journal.Type> {

    journal;
    public Type value() { return journal; }
  }    

  // TODO rels  
  public List<Article> getArticles();    
}
