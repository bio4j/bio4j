package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.bio4j.model.go.nodes.Term.Type;
import com.bio4j.model.properties.Date;
import com.bio4j.model.properties.Name;
import com.bio4j.model.uniprot.nodes.City;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;

import java.util.List;

import com.ohnosequences.typedGraphs.NodeType;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Book extends Node<Book, Book.Type>,

// properties
		Name<Book, Book.Type>, Date<Book, Book.Type> {

	public List<Protein> proteinCitations();

	public Publisher getPublisher();

	public City getCity();

	public List<Person> getAuthors();

	public List<Person> getEditors();
	
	public static Type TYPE = Type.book;
	public default Type type() { return TYPE; }

	public static enum Type implements NodeType<Book, Book.Type> {

		book;
		public Type value() {
			return book;
		}
	}

}
