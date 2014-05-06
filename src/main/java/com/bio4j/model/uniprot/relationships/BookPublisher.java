package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.uniprot.nodes.Publisher;
import com.bio4j.model.uniprot.nodes.Book;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface BookPublisher extends Relationship <
  Book, Book.Type,
  BookPublisher, BookPublisher.Type,
  Publisher, Publisher.Type
> {

  public static enum Type implements RelationshipType <
    Book, Book.Type,
    BookPublisher, BookPublisher.Type,
    Publisher, Publisher.Type
  > {
    bookPublisher;
    public Type value() { return bookPublisher; }
    public Arity arity() { return Arity.manyToOne; }
    public Book.Type sourceType() { return Book.TYPE; }
    public Publisher.Type targetType() { return Publisher.TYPE; }

  }
  
  public Book source();
  public Publisher target();

}
