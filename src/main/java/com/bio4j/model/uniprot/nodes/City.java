package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;

import com.bio4j.model.uniprot.nodes.Book;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface City extends Node<City, City.Type> {
  
  public static Type TYPE = Type.INSTANCE;
  
  public static enum Type implements NodeType<City, City.Type> {
    city;
    public Type value() { return city; }
    public static Type INSTANCE = city;
  }

    //----GETTERS---
    public String getName();
    public List<Book> getBooks();

    //----SETTERS----
    public void setName(String value);    
}