package com.bio4j.model.relationships.citation.book;

import com.bio4j.model.Property;
import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.citation.Book;

/**
 *
 * @author Alexey Alekhin <aalekhin@ohnosequences.com>
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
    }

    public Property<String> title();
    public Property<String> volume();
    public Property<String> first();
    public Property<String> last();

}
