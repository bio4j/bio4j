package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;

import java.util.List;

import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.Term.Type;
import com.bio4j.model.properties.Name;
import com.bio4j.model.uniprot.relationships.BookPublisher;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Publisher extends Node<Publisher, Publisher.Type>,

	// properties
	Name<Publisher, Publisher.Type> {

	public static Type TYPE = Type.publisher;
	public default Type type() { return TYPE; }

	public static enum Type implements NodeType<Publisher, Publisher.Type> {

		publisher;
		public Type value() {
			return publisher;
		}
	}

	// bookPublisher
    // ingoing
    public List<BookPublisher> bookPublisher_in(); 
    public List<Book> bookPublisher_inNodes();
}
