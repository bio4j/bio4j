package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.properties.Name;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Country extends Node<Country, Country.Type>,
	Name<Country, Country.Type>{
  
	  public static Type TYPE = Type.country;   
	  public static enum Type implements NodeType<Country, Country.Type> {
	
	    country;
	    public Type value() { return country; }
	    public static Type INSTANCE = country;
	  }
     
}
