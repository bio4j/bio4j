package com.bio4j.model;

/*
  A typed Element. Base class for both Nodes and Relationships

  @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
*/
public interface Element <
  N extends Element<N,T>, 
  T extends Enum<T> & ElementType<N,T>
> {

  /*
    Its type
  */
  public T getType();
}