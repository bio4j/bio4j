package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Book;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface BookEditor extends Relationship <
  Book, Book.Type,
  BookEditor, BookEditor.Type,
  Person, Person.Type
> {

  public static enum Type implements RelationshipType <
    Book, Book.Type,
    BookEditor, BookEditor.Type,
    Person, Person.Type
  > {
    BookEditor;
    public Type value() { return BookEditor; }
    public Arity arity() { return Arity.manyToMany; }
    public Book.Type sourceType() { return Book.TYPE; }
    public Person.Type targetType() { return Person.TYPE; }

  }
  
  public Book source();
  public Person target();

}
