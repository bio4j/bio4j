package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.GoTerm.Type;
import com.bio4j.model.uniprot.relationships.references.Article;

/**
 *  This Node has just one instance. A `Reference` node is an article if it has a relationship of type `Article` pointing to the unique `Articles` node.
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface Articles extends Node<Articles, Articles.Type> {
  
  public List<? extends Article> article_in();
  public List<? extends Reference> article_inNodes();

  public static Type TYPE = Type.articles;
  public default Type type() { return TYPE; }

  public static enum Type implements NodeType<Articles, Articles.Type> {
    
    articles;
    
    public Type value() { return articles; }
  }
}
