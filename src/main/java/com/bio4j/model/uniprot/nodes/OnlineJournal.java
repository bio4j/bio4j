
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import java.util.List;
import com.bio4j.model.NodeType;

import com.bio4j.model.properties.Name;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface OnlineJournal extends Node<OnlineJournal, OnlineJournal.Type>,

  Name<OnlineJournal, OnlineJournal.Type>
{
   
  public static Type TYPE = Type.onlineJournal; 
  public static enum Type implements NodeType<OnlineJournal, OnlineJournal.Type> {

    onlineJournal;
    public Type value() { return onlineJournal; }
  }
      
  public List<OnlineArticle> getOnlineArticles();
}
