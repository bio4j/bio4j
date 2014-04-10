package com.bio4j.model.isoforms.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.Name;

/**
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface AlternativeProduct extends Node<AlternativeProduct, AlternativeProduct.Type>,

  // properties
  Name<AlternativeProduct, AlternativeProduct.Type>
  
{    

  public static Type TYPE = Type.alternativeProduct;
  public static enum Type implements NodeType<AlternativeProduct, AlternativeProduct.Type> {
    alternativeProduct;
    public Type value() { return alternativeProduct; }
  }

  public List<Isoform> getIsoforms();
}
