package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.uniprot.relationships.references.Thesis;

/**
 *  This Node has just one instance per graph. Relationships of type `Thesis` to this node blahblahblah
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Theses extends Node<Theses, Theses.Type> {
  
  public List<? extends Thesis> thesis_in();
  public List<? extends Reference> thesis_inNodes();

  public static Type TYPE = Type.theses;

  public static enum Type implements NodeType<Theses, Theses.Type> {
    
    theses;
    
    public Type value() { return theses; }
  }
}
