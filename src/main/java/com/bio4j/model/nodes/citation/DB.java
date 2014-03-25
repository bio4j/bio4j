
package com.bio4j.model.nodes.citation;

import com.bio4j.model.Node;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface DB extends Node<DB, DB.type> {
    
  enum type implements NodeType<DB, DB.type> {

    db;
    public type value() { return db; }
  }    
    //----GETTERS---
    public String getName();
    public List<Submission> getAssociatedSubmissions();
    
    //----SETTERS----
    public void setName(String value);
    
}
