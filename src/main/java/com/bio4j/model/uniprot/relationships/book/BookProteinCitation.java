package com.bio4j.model.relationships.citation.book;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.Book;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface BookProteinCitation extends Relationship <
    Book, Book.Type,
    BookProteinCitation, BookProteinCitation.Type,
    Protein, Protein.Type
> {

    public static enum Type implements RelationshipType <
        Book, Book.Type,
        BookProteinCitation, BookProteinCitation.Type,
        Protein, Protein.Type
    > {
        BookProteinCitation;
        public Type value() { return BookProteinCitation; }
        public Arity arity() { return Arity.manyToMany; }
        public Book.Type sourceType() { return Book.TYPE; }
        public Protein.Type targetType() { return Protein.TYPE; }

    }

    public String title();
    public String volume();
    public String first();
    public String last();

}
