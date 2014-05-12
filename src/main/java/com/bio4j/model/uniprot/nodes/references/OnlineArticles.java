package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.GoTerm.Type;
import com.bio4j.model.uniprot.nodes.OnlineArticle;


/**
 *  This Node has just one instance per graph. Relationships of type `OnlineArticle` to this node blahblahblah
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface OnlineArticles extends Node<OnlineArticles, OnlineArticles.Type> {
  
  public List<? extends OnlineArticle> onlineArticle_in();
  public List<? extends Reference> onlineArticle_inNodes();

  public static Type TYPE = Type.onlineArticles;
  public default Type type() { return TYPE; }

  public static enum Type implements NodeType<OnlineArticles, OnlineArticles.Type> {
    
	  onlineArticles;
    
    public Type value() { return onlineArticles; }
  }
}
