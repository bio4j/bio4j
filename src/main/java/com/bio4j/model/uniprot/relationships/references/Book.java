package com.bio4j.model.uniprot.relationships.references;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

//properties
import com.bio4j.model.properties.Date;
import com.bio4j.model.properties.Name;


import com.bio4j.model.uniprot.nodes.references.Reference;
import com.bio4j.model.uniprot.nodes.references.Books;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Book extends Relationship<
	Reference, Reference.Type,
	Book, Book.Type, 
	Books, Books.Type
>,

	// properties
	Name<Book, Book.Type>, // TODO Title?
	Date<Book, Book.Type> // TODO this is already in `CitedIn` ??

{

	public static Type TYPE = Type.book;

	public static enum Type implements RelationshipType<

	Reference, Reference.Type, Book, Book.Type, Books, Books.Type> {
		book;

		// there is only one Articles node => many to one.
		public Arity arity() {
			return Arity.manyToOne;
		}

		public Reference.Type sourceType() {
			return Reference.TYPE;
		}

		public Books.Type targetType() {
			return Books.TYPE;
		}

		public Type value() {
			return book;
		}
	}
}
