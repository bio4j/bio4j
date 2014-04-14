package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.properties.Name;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Consortium extends Node<Consortium, Consortium.Type>,
	Name<Consortium, Consortium.Type>{
	
	public static Type TYPE = Type.consortium;
	
	public static enum Type implements NodeType<Consortium, Consortium.Type> {

	   consortium;
	   public Type value() { return consortium; }
	}
   
}
