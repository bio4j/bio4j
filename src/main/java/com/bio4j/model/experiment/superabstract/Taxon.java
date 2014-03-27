package com.bio4j.model.experiment.superabstract;

public interface Taxon extends Node<Taxon, Taxon.type> {
    
  enum type implements NodeType<Taxon, Taxon.type> {

    taxon;
    public type value() { return taxon; }
  }

  // not needed, but it's here just so that you can see how this can only return `taxon`
  public type getType();

  public Property<String> name();

  //----GETTERS---
  // public String getName();
  
  //----SETTERS----
  // public void setName(String value);  
}
