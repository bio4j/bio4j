package com.bio4j.model.nodes;

import java.util.List;
import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface AlternativeProduct extends Node<AlternativeProduct, AlternativeProduct.type> {
    
  enum type implements NodeType<AlternativeProduct, AlternativeProduct.type> {

    alternativeProduct;
    public type value() { return alternativeProduct; }
  }

    //----GETTERS---
    public String getName();

    //----SETTERS----
    public void setName(String value);  
    
    public List<Isoform> getIsoforms();
}
