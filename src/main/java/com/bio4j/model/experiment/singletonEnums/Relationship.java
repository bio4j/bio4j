package com.bio4j.model.experiment.singletonEnums;

public interface Relationship<T extends Enum<T> & RelationshipType<T>> {
  
  public T getType();
}