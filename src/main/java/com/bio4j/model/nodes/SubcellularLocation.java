package com.bio4j.model.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface SubcellularLocation extends Node<SubcellularLocation, SubcellularLocation.type> {
    
  enum type implements NodeType<SubcellularLocation, SubcellularLocation.type> {

    subcellularLocation;
    public type value() { return subcellularLocation; }
  }
    
}
