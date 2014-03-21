package com.bio4j.model.experiment.superabstract;

/*
  A node.
*/
public interface Node <
  N extends Node<N,T>, 
  T extends Enum<T> & NodeType<N,T>
> {

  /*
    Its type
  */
  public T getType();
}