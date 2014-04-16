package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.uniprot.relationships.references.Consortium;

/**
 *  This Node has just one instance per graph. Relationships of type `Consortium` to this node blahblahblah
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Consortiums extends Node<Consortiums, Consortiums.Type> {
  
  public List<? extends Consortium> consortium_in();
  public List<? extends Author> consortium_inNodes();

  public static Type TYPE = Type.consortiums;

  public static enum Type implements NodeType<Consortiums, Consortiums.Type> {
    
    consortiums;
    
    public Type value() { return consortiums; }
  }
}