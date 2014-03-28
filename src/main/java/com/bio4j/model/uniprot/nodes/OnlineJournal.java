
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface OnlineJournal extends Node<OnlineJournal, OnlineJournal.type> {
    
  enum type implements NodeType<OnlineJournal, OnlineJournal.type> {

    onlineJournal;
    public type value() { return onlineJournal; }
  }
      
    //----GETTERS---
    public String getName();
    public List<OnlineArticle> getOnlineArticles();
    
    //----SETTERS---
    public void setName(String value);
    
}
