package com.bio4j.model.experiment.superabstract;

public interface Taxon extends Node<Taxon, Taxon.type> {
    
  enum type implements NodeType<Taxon, Taxon.type> {

    taxon;
    public type value() { return taxon; }
  }
      
  public static enum name implements PropertyOf<Taxon, Taxon.type, String> {
    INSTANCE;
  }

  // get(Taxon.name)
  // they could
  @Override
  public <V, P extends PropertyOf<Taxon, Taxon.type, V>> V get(P property);

  // not needed, but it's here just so that you can see how this can only return `taxon`
  @Override
  public type getType();

  // public Property<Taxon, String> name();

  //----GETTERS---
  // public String getName();
  
  //----SETTERS----
  // public void setName(String value);  
}
