package com.bio4j.model.relationships.citation.book;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.nodes.citation.Publisher;
import com.bio4j.model.nodes.citation.Book;

/**
 *
 * @author Alexey Alekhin <aalekhin@ohnosequences.com>
 */
public interface BookPublisher extends Relationship <
  Book, Book.type,
  BookPublisher, BookPublisher.type,
  Publisher, Publisher.type
> {

  enum type implements RelationshipType <
    Book, Book.type,
    BookPublisher, BookPublisher.type,
    Publisher, Publisher.type
  > {
    BookPublisher;
    public type value() { return BookPublisher; }
  }

}
