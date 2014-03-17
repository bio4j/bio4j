package com.bio4j.model.util;

/*
  get a Node of type N by passing a value of type V
*/
public interface NodeIndex< N extends com.bio4j.model.Node, V > {

  N get(V using);
}