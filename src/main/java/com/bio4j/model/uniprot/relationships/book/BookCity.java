package com.bio4j.model.relationships.citation.book;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.City;
import com.bio4j.model.uniprot.nodes.Book;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface BookCity extends Relationship <
  Book, Book.type,
  BookCity, BookCity.type,
  City, City.type
> {
    
  enum type implements RelationshipType <
    Book, Book.type,
    BookCity, BookCity.type,
    City, City.type
  > {
    BookCity;
    public type value() { return BookCity; }
    public arity arity() { return arity.manyToOne; }
  }

}
