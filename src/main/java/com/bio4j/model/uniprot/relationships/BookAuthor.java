package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Book;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
// bookAuthor
public interface BookAuthor extends Relationship <
  Book, Book.Type,
  BookAuthor, BookAuthor.Type,
  Person, Person.Type
> {

  public static enum Type implements RelationshipType <
    Book, Book.Type,
    BookAuthor, BookAuthor.Type,
    Person, Person.Type
  > {
    bookAuthor;
    public Type value() { return bookAuthor; }
    public Arity arity() { return Arity.manyToMany; }
    public Book.Type sourceType() { return Book.TYPE; }
    public Person.Type targetType() { return Person.TYPE; }

  }
  
  public Book source();
  public Person target();

}
