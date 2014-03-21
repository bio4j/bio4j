package com.bio4j.model.experiment.superabstract;

public interface NodeType <
  N extends Node<N,T>,
  T extends Enum<T> & NodeType<N,T>
> {

  /*
    this is a strong hit for you to implement this as a singleton
  */
  public T value();
}