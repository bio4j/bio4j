package com.bio4j.model.uniprot.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.properties.Name;
import com.bio4j.model.properties.Id;
import com.bio4j.model.uniprot.relationships.ProteinKeyword;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Keyword extends Node<Keyword, Keyword.Type>,

	// properties
	Id<Keyword, Keyword.Type>, Name<Keyword, Keyword.Type> {

	public static Type TYPE = Type.keyword;

	public static enum Type implements NodeType<Keyword, Keyword.Type> {
		keyword;
		public Type value() {
			return keyword;
		}
	}
	
	// proteinKeyword
    // ingoing
    public List<ProteinKeyword> proteinKeyword_in(); 
    public List<Protein> proteinKeyword_inNodes();
}
