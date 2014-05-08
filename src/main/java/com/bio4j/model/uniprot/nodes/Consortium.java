package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.GoTerm.Type;
import com.bio4j.model.properties.Name;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Consortium extends Node<Consortium, Consortium.Type>,
  
  // properties
  Name<Consortium, Consortium.Type>
{
	
	public static Type TYPE = Type.consortium;
	
	public default Type type() { return TYPE; }
	
	public static enum Type implements NodeType<Consortium, Consortium.Type> {

	   consortium;
	   public Type value() { return consortium; }
	}
   
}
