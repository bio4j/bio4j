package com.bio4j.model.experiment.superabstract;

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