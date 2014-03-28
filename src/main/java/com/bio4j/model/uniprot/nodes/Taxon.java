package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Taxon extends Node<Taxon, Taxon.type> {
    
  enum type implements NodeType<Taxon, Taxon.type> {

    taxon;
    public type value() { return taxon; }
  }

  public String name();
}