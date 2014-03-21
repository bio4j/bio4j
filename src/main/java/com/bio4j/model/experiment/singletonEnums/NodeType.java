package com.bio4j.model.experiment.singletonEnums;

public interface NodeType<T extends Enum<T> & NodeType<T>> {

  public T value();
}