package com.bio4j.model.experiment.singletonEnums;

public interface RelationshipType<T extends Enum<T> & RelationshipType<T>> {

  public T value();
}