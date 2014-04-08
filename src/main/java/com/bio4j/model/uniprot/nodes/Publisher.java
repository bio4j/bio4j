
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Publisher extends Node<Publisher, Publisher.type> {
  
  public static type TYPE = type.INSTANCE;
  public static enum type implements NodeType<Publisher, Publisher.type> {

    publisher;
    public type value() { return publisher; }
    public static type INSTANCE = publisher;
  }
          
    //----GETTERS---
    public String getName();
    public List<Book> getBooks();

    //----SETTERS---
    public void setName(String value);
}
