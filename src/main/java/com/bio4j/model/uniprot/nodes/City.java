package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;

import com.bio4j.model.uniprot.nodes.Book;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface City extends Node<City, City.type> {
    
  enum type implements NodeType<City, City.type> {

    city;
    public type value() { return city; }
  }

    //----GETTERS---
    public String getName();
    public List<Book> getBooks();

    //----SETTERS----
    public void setName(String value);        
}