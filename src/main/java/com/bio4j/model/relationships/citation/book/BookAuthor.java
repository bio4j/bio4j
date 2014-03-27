package com.bio4j.model.relationships.citation.book;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.citation.Book;

/**
 *
 * @author Alexey Alekhin <aalekhin@ohnosequences.com>
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
    BookAuthor;
    public type value() { return BookAuthor; }
  }

}
