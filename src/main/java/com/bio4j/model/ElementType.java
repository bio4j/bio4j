package com.bio4j.model;

public interface ElementType <
  N extends Element<N,T>,
  T extends Enum<T> & ElementType<N,T>
> {

  /*
    this is a strong hit for you to implement this as a singleton
  */
  public T value();
}