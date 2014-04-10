package com.bio4j.model;

public interface NodeType <
  N extends Node<N,T>,
  T extends Enum<T> & NodeType<N,T>
> extends ElementType<N,T> {

  /*
    this is a strong hit for you to implement this as a singleton
  */
  public T value();
}