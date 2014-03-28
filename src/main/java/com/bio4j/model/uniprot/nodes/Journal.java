
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Journal extends Node<Journal, Journal.type> {
    
  enum type implements NodeType<Journal, Journal.type> {

    journal;
    public type value() { return journal; }
  }    
    //----GETTERS---
    public String getName();
    public List<Article> getArticles();
    
    //----SETTERS----
    public void setName(String value);
    
}
