package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.uniprot.relationships.ProteinReactome;

import java.util.List;

import com.bio4j.model.NodeType;
import com.bio4j.model.properties.Id;
import com.bio4j.model.properties.PathwayName;

/**
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ReactomeTerm extends Node<ReactomeTerm, ReactomeTerm.Type>,

	// properties
	Id<ReactomeTerm, ReactomeTerm.Type>,
	PathwayName<ReactomeTerm, ReactomeTerm.Type> {

	public static Type TYPE = Type.reactomeTerm;

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