package com.bio4j.model.isoforms.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.name;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface AlternativeProduct extends Node<AlternativeProduct, AlternativeProduct.type>,
  // properties
  name 
{    

  public static type TYPE = type.alternativeProduct;
  public static enum type implements NodeType<AlternativeProduct, AlternativeProduct.type> {
    alternativeProduct;
    public type value() { return alternativeProduct; }
  }

  public List<Isoform> getIsoforms();
}
