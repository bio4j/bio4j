package com.bio4j.model;

/*
  A typed node.

  @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
*/
public interface Node <
  N extends Node<N,T>, 
  T extends Enum<T> & NodeType<N,T>
> extends Element<N,T> {

  /*
    Its type
  */
  public T getType();
}