package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Taxon extends Node<Taxon, Taxon.Type> {
  
  public static Type TYPE = Type.taxon;  
  public static enum Type implements NodeType<Taxon, Taxon.Type> {

    taxon;
    public Type value() { return taxon; }
  }

  public String name();
}