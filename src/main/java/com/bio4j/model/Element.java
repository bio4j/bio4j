package com.bio4j.model;

/*
  A typed Element. Base class for both Nodes and Relationships

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
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
