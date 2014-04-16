package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;

import java.util.List;

import com.bio4j.model.NodeType;
import com.bio4j.model.properties.Name;
import com.bio4j.model.uniprot.relationships.BookPublisher;

/**
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Publisher extends Node<Publisher, Publisher.Type>,

	// properties
	Name<Publisher, Publisher.Type> {

	public static Type TYPE = Type.publisher;

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
