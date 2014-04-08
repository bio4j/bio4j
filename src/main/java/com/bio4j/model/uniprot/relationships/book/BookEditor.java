package com.bio4j.model.relationships.citation.book;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Book;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
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
    public arity arity() { return arity.manyToMany; }
    public Book.type sourceType() { return Book.TYPE; }
    public Person.type targetType() { return Person.TYPE; }

  }

}
