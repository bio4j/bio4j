
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import java.util.List;
import com.bio4j.model.NodeType;

import com.bio4j.model.properties.Name;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Publisher extends Node<Publisher, Publisher.Type>, 

  // properties
  Name<Publisher, Publisher.Type>
{
  
  public static Type TYPE = Type.publisher;
  public static enum Type implements NodeType<Publisher, Publisher.Type> {

    publisher;
    public Type value() { return publisher; }
  }

    // TODO rels
    public List<Book> getBooks();
}
