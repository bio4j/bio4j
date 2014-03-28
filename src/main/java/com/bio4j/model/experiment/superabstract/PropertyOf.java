package com.bio4j.model.experiment.superabstract;

public interface PropertyOf< 
  N extends Node<N,T>,
  T extends Enum<T> & NodeType<N,T>,
  V
> {

  public String name();
}