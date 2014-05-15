package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;

import java.util.List;

import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.Term.Type;
import com.bio4j.model.properties.Name;
import com.bio4j.model.uniprot.nodes.references.Reference;
import com.bio4j.model.uniprot.relationships.ArticleJournal;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Journal extends Node<Journal, Journal.Type>,

	// properties
	Name<Journal, Journal.Type> {

	public static Type TYPE = Type.journal;
	public default Type type() { return TYPE; }

	public static enum Type implements NodeType<Journal, Journal.Type> {

		journal;
		public Type value() {
			return journal;
		}
	}

	// articleJournal
	// outgoing
	public List<ArticleJournal> articleJournal_out();
	public List<Reference> articleJournal_outNodes();
}
