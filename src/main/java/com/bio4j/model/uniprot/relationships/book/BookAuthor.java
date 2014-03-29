package com.bio4j.model.relationships.citation.book;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Book;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface BookAuthor extends Relationship <
  Book, Book.type,
  BookAuthor, BookAuthor.type,
  Person, Person.type
> {

  enum type implements RelationshipType <
    Book, Book.type,
    BookAuthor, BookAuthor.type,
    Person, Person.type
  > {
    bookAuthor;
    public type value() { return bookAuthor; }
    public arity arity() { return arity.manyToMany; }
  }

}
