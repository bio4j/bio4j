package com.bio4j.model.relationships.citation.book;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.citation.Book;

/**
 *
 * @author Alexey Alekhin <aalekhin@ohnosequences.com>
 */
public interface BookEditor extends Relationship <
  Book, Book.type,
  BookEditor, BookEditor.type,
  Person, Person.type
> {

  enum type implements RelationshipType <
    Book, Book.type,
    BookEditor, BookEditor.type,
    Person, Person.type
  > {
    BookEditor;
    public type value() { return BookEditor; }
  }

}
