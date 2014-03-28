package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Person extends Node<Person, Person.type> {
    
  public static enum type implements NodeType<Person, Person.type> {

    person;
    public type value() { return person; }
  }
  // good?
  public static Person.type TYPE = type.person;

  public String name();
}
