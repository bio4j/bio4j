package com.bio4j.model;

/*
  A Property. Just a marker.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Property <
  N extends Element<N,T>, 
  T extends Enum<T> & ElementType<N,T>
> 
{}
