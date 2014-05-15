package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.bio4j.model.uniprot.relationships.ProteinReactome;

import java.util.List;

import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.Term.Type;
import com.bio4j.model.properties.Id;
import com.bio4j.model.properties.PathwayName;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface ReactomeTerm extends Node<ReactomeTerm, ReactomeTerm.Type>,

	// properties
	Id<ReactomeTerm, ReactomeTerm.Type>,
	PathwayName<ReactomeTerm, ReactomeTerm.Type> {

	public static Type TYPE = Type.reactomeTerm;
	public default Type type() { return TYPE; }

	public static enum Type implements
			NodeType<ReactomeTerm, ReactomeTerm.Type> {

		reactomeTerm;
		public Type value() {
			return reactomeTerm;
		}
	}

	// proteinReactome
    // ingoing
    public List<ProteinReactome> proteinReactome_in(); 
    public List<Protein> proteinReactome_inNodes();
}
