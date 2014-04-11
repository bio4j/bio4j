package com.bio4j.model;

/*
  A Property. Just a marker.

  @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
*/
public interface Property <
  N extends Element<N,T>, 
  T extends Enum<T> & ElementType<N,T>
> 
{}