package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.Term.Type;
import com.bio4j.model.properties.Name;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface FeatureType extends Node<FeatureType, FeatureType.Type>,
  
  // properties
  Name<FeatureType, FeatureType.Type>
{
    
	public static Type TYPE = Type.featureType; 
	public default Type type() { return TYPE; }
	
	public static enum Type implements NodeType<FeatureType, FeatureType.Type> {
	
	    featureType;
	    public Type value() { return featureType; }
	}
}
