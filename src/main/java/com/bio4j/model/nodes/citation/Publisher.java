
package com.bio4j.model.nodes.citation;

import com.bio4j.model.Node;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Publisher extends Node<Publisher, Publisher.type> {
    
  enum type implements NodeType<Publisher, Publisher.type> {

    publisher;
    public type value() { return publisher; }
  }
          
    //----GETTERS---
    public String getName();
    public List<Book> getBooks();

    //----SETTERS---
    public void setName(String value);
}
