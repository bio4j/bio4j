package com.bio4j.model;

/*
  A typed relationship with typed source and target. 

  - `S` the source Node
  - `ST` the source Node type
  - `R` the relationship
  - `RT` the relationship type
  - `T` the target Node
  - `TT` the target Node type

  @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
*/
public interface Relationship <
  S extends Node<S,ST>,
  ST extends Enum<ST> & NodeType<S,ST>,
  R extends Relationship<S,ST,R,RT,T,TT>, 
  RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
  T extends Node<T,TT>,
  TT extends Enum<TT> & NodeType<T,TT>
> {

  /*
    Its type
  */
  public RT getType();

  /*
    get the source
  */
  public S getSource();
  /*
    get the target
  */
  public T getTarget();
}
