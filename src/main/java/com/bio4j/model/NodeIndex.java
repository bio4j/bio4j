package com.bio4j.model;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.PropertyType;

public interface NodeIndex <
  N extends Node<N,T>, 
  T extends Enum<T> & NodeType<N,T>, 
  P extends Enum<P> & PropertyType<P,V>,
  V
> {}
