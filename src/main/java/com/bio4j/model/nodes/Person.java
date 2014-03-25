package com.bio4j.model.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Person extends Node<Person, Person.type> {
    
  enum type implements NodeType<Person, Person.type> {

    person;
    public type value() { return person; }
  }

    //----GETTERS---
    public String getName();
    
    //----SETTERS----
    public void setName(String value);    
    
}
