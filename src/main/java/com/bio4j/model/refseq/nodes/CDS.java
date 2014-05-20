package com.bio4j.model.refseq.nodes;

import com.bio4j.model.refseq.nodes.GenomeElement.version;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

public interface CDS<N extends CDS<N, NT>, NT extends CDS.Type<N, NT>> extends
		Node<N, NT> {

	// CDS type
	public static interface Type<N extends CDS<N, NT>, NT extends CDS.Type<N, NT>>
			extends Node.Type<N, NT> {
	}
	
	// properties
	  
	public static interface id <
	    N extends CDS<N,NT>,
	    NT extends CDS.Type<N,NT>,
	    P extends id<N,NT,P>
	 > 
	    extends Property<N,NT,P,String> 
	 {
	   @Override public default String name() { return "id"; } 
	   @Override public default Class<String> valueClass() { return String.class; }
	 }
	
}
