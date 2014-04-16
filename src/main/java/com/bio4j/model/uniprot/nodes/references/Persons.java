package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.uniprot.relationships.references.Person;

/**
 *  This Node has just one instance per graph. Relationships of type `Person` to this node blahblahblah
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Persons extends Node<Persons, Persons.Type> {
  
  // all person authors in the world
  public List<? extends Person> person_in();
  public List<? extends Author> person_inNodes();

  public static Type TYPE = Type.persons;

  public static enum Type implements NodeType<Persons, Persons.Type> {
    
    persons;
    
    public Type value() { return persons; }
  }
}