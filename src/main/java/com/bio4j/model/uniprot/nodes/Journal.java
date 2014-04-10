
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Journal extends Node<Journal, Journal.Type> {
  
  public static Type TYPE = Type.INSTANCE;
  public static enum Type implements NodeType<Journal, Journal.Type> {

    journal;
    public Type value() { return journal; }
    public static Type INSTANCE = journal;
  }    
    //----GETTERS---
    public String getName();
    public List<Article> getArticles();
    
    //----SETTERS----
    public void setName(String value);
    
}
