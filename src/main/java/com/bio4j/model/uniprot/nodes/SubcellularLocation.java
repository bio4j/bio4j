package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface SubcellularLocation extends Node<SubcellularLocation, SubcellularLocation.Type> {
  
  public static Type TYPE = Type.subcellularLocation;  
  public static enum Type implements NodeType<SubcellularLocation, SubcellularLocation.Type> {

    subcellularLocation;
    public Type value() { return subcellularLocation; }
  }
    
}
