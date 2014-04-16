package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.uniprot.relationships.references.Article;

/**
 *  This Node has just one instance per graph. Relationships of type `Article` to this node blahblahblah
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Articles extends Node<Articles, Articles.Type> {
  
  public List<? extends Article> article_in();
  public List<? extends Reference> article_inNodes();

  public static Type TYPE = Type.articles;

  public static enum Type implements NodeType<Articles, Articles.Type> {
    
    articles;
    
    public Type value() { return articles; }
  }
}