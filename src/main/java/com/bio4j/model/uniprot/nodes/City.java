package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.properties.Name;
import com.bio4j.model.uniprot.nodes.Book;
import com.bio4j.model.uniprot.relationships.book.BookCity;

import java.util.List;

import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface City extends Node<City, City.Type>,
	Name<City, City.Type>{
  
	  public static Type TYPE = Type.city;
	  
	  public static enum Type implements NodeType<City, City.Type> {
	    city;
	    public Type value() { return city; }
	  }

	// bookCity
	// ingoing
	public List<BookCity> bookCity_in(); 
	public List<Book> bookCity_inNodes();

}