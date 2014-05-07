package com.bio4j.model.uniprot.relationships.references;

import java.util.List;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

// properties
import com.bio4j.model.properties.Name;

import com.bio4j.model.uniprot.nodes.references.Persons;
import com.bio4j.model.uniprot.nodes.references.Author;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Person extends Relationship <

  Author, Author.Type, // TODO why Author? it can be anything actually. We should leave the source free here
  Person, Person.Type,
  Persons, Persons.Type

>,
  
  // properties
  Name<Person, Person.Type>
{
  
  public static Type TYPE = Type.person;
  
  public static enum Type implements RelationshipType <
    
    Author, Author.Type,
    Person, Person.Type,
    Persons, Persons.Type
  >
  {

    person;

    // there is only one Persons node => many to one.
    public Arity arity() { return Arity.manyToOne; }

    public Author.Type sourceType() { return Author.TYPE; }
    public Persons.Type targetType() { return Persons.TYPE; }

    public Type value() { return person; }
  }
}
