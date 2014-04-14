package com.bio4j.model.uniprot.relationships.book;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.uniprot.nodes.City;
import com.bio4j.model.uniprot.nodes.Book;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface BookCity extends Relationship <
  Book, Book.Type,
  BookCity, BookCity.Type,
  City, City.Type
> {
    

  public static enum Type implements RelationshipType <
    Book, Book.Type,
    BookCity, BookCity.Type,
    City, City.Type
  > {
    BookCity;
    public Type value() { return BookCity; }
    public Arity arity() { return Arity.manyToOne; }
    public Book.Type sourceType() { return Book.TYPE; }
    public City.Type targetType() { return City.TYPE; }

  }
  
  public Book source();
  public City target();

}
