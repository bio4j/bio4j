package com.bio4j.model.isoforms.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.bio4j.model.go.nodes.GoTerm.Type;
// properties
import com.bio4j.model.properties.Name;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface AlternativeProduct extends Node<AlternativeProduct, AlternativeProduct.Type>,

  // properties
  Name<AlternativeProduct, AlternativeProduct.Type>
{    

  // TODO what is this
  public List<Isoform> getIsoforms();
  
  public default Type type() { return TYPE; }

  public static Type TYPE = Type.alternativeProduct;
  public static enum Type implements NodeType<AlternativeProduct, AlternativeProduct.Type> {
    alternativeProduct;
    public Type value() { return alternativeProduct; }
  }
}
