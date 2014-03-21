package com.bio4j.model.experiment.singletonEnums;

public interface Node<T extends Enum<T> & NodeType<T>> {
  
  public T getType();
}