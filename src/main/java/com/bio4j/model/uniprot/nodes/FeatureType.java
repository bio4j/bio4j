package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

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
	
	public static enum Type implements NodeType<FeatureType, FeatureType.Type> {
	
	    featureType;
	    public Type value() { return featureType; }
	}
}
