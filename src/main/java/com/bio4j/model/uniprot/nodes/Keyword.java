package com.bio4j.model.uniprot.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.properties.Name;
import com.bio4j.model.properties.Id;
import com.bio4j.model.uniprot.relationships.ProteinKeyword;

/**
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
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
