package com.bio4j.model.experiment.superabstract;

public class StupidTaxon implements Taxon {

  @Override
  public <V, P extends PropertyOf<Taxon, Taxon.type, V>> V get(P property) {

    if (property.equals(Taxon.name.INSTANCE)) {
      return (V) "stupid taxon";
    } else {
      throw new IllegalArgumentException();
    }
  }

  // not needed, but it's here just so that you can see how this can only return `taxon`
  @Override
  public type getType() {  return type.taxon; }

  // public Property<Taxon, String> name();

  //----GETTERS---
  // public String getName();
  
  //----SETTERS----
  // public void setName(String value);  
}