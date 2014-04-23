package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.uniprot.nodes.OnlineArticle;


/**
 *  This Node has just one instance per graph. Relationships of type `OnlineArticle` to this node blahblahblah
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface OnlineArticles extends Node<OnlineArticles, OnlineArticles.Type> {
  
  public List<? extends OnlineArticle> onlineArticle_in();
  public List<? extends Reference> onlineArticle_inNodes();

  public static Type TYPE = Type.onlineArticles;

  public static enum Type implements NodeType<OnlineArticles, OnlineArticles.Type> {
    
	  onlineArticles;
    
    public Type value() { return onlineArticles; }
  }
}