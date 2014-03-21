package com.bio4j.model.experiment.superabstract;

/*
  A relationship. 

  - `S` the source Node
  - `ST` the source Node type
  - `R` the relationship
  - `RT` the relationship type
  - `T` the target Node
  - `TT` the target Node type
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