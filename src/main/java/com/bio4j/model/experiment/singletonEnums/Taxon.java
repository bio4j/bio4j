package com.bio4j.model.experiment.singletonEnums;

public interface Taxon extends Node<Taxon.type> {

  enum type implements NodeType<type> { 
    taxon;
    public type value() { return taxon; } 
  }

  // now getType() can only return taxon
}