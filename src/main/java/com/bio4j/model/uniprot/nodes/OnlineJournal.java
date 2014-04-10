
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface OnlineJournal extends Node<OnlineJournal, OnlineJournal.Type> {
   
  public static Type TYPE = Type.onlineJournal; 
  public static enum Type implements NodeType<OnlineJournal, OnlineJournal.Type> {

    onlineJournal;
    public Type value() { return onlineJournal; }
  }
      
    //----GETTERS---
    public String getName();
    public List<OnlineArticle> getOnlineArticles();
    
    //----SETTERS---
    public void setName(String value);
    
}
