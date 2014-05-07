package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.bio4j.model.properties.Name;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Taxon extends Node<Taxon, Taxon.Type>,

	// properties
	Name<Taxon, Taxon.Type> {

	public static Type TYPE = Type.taxon;

	public static enum Type implements NodeType<Taxon, Taxon.Type> {

		taxon;
		public Type value() {
			return taxon;
		}
	}
}
