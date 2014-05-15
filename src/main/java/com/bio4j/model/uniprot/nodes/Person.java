package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.Term.Type;
import com.bio4j.model.properties.Name;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Person extends Node<Person, Person.Type>,

	Name<Person, Person.Type> {

	public static enum Type implements NodeType<Person, Person.Type> {

		person;
		public Type value() {
			return person;
		}
	}

	public static Person.Type TYPE = Type.person;
	public default Type type() { return TYPE; }

}
