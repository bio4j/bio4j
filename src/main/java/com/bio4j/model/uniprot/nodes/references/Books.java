package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.uniprot.nodes.Book;

/**
 *  This Node has just one instance per graph. Relationships of type `Book` to this node blahblahblah
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Books extends Node<Books, Books.Type> {
  
  public List<? extends Book> book_in();
  public List<? extends Reference> book_inNodes();

  public static Type TYPE = Type.books;

  public static enum Type implements NodeType<Books, Books.Type> {
    
	  books;
    
    public Type value() { return books; }
  }
}