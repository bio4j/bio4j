package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.bio4j.model.uniprot.relationships.references.Patent;

/**
 *  This Node has just one instance. A `Reference` node is an article if it has a relationship of type `Patent` pointing to the unique `Patents` node.
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface Patents extends Node<Patents, Patents.Type> {
  
  public List<? extends Patent> article_in();
  public List<? extends Reference> article_inNodes();

  public static Type TYPE = Type.articles;

  public static enum Type implements NodeType<Patents, Patents.Type> {
    
    articles;
    
    public Type value() { return articles; }
  }
}
