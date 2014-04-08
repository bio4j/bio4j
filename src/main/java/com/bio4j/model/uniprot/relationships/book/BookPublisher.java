package com.bio4j.model.relationships.citation.book;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Publisher;
import com.bio4j.model.uniprot.nodes.Book;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
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
    bookPublisher;
    public type value() { return bookPublisher; }
    public arity arity() { return arity.manyToOne; }
    public Book.type sourceType() { return Book.TYPE; }
    public Publisher.type targetType() { return Publisher.TYPE; }

  }

}
