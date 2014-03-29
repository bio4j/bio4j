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
    Book, Book.type,
    BookProteinCitation, BookProteinCitation.type,
    Protein, Protein.type
> {

    enum type implements RelationshipType <
        Book, Book.type,
        BookProteinCitation, BookProteinCitation.type,
        Protein, Protein.type
    > {
        BookProteinCitation;
        public type value() { return BookProteinCitation; }
        public arity arity() { return arity.manyToMany; }
    }

    public String title();
    public String volume();
    public String first();
    public String last();

}
