
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.uniprot.nodes.City;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Book extends Node<Book, Book.Type> {
   
  public static Type TYPE = Type.INSTANCE;
  public static enum Type implements NodeType<Book, Book.Type> {

    book;
    public Type value() { return book; }
    public static Type INSTANCE = book;
  }   
    //----GETTERS---
    public String getName();
    public String getDate();

    
    public List<Protein> getProteinCitations();
    public Publisher getPublisher();
    public City getCity();
    public List<Person> getAuthors();
    public List<Person> getEditors();

    //----SETTERS----
    public void setName(String value);
    public void setDate(String value);
    
}
