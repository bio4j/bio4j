package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;


public interface CellularComponent <
  S extends Term<S,ST>, ST extends Term.Type<S,ST>,
  R extends CellularComponent<S,ST,R,RT,T,TT>, RT extends CellularComponent.Type<S,ST,R,RT,T,TT>,
  T extends SubOntologies<T,TT>, TT extends SubOntologies.Type<T,TT>
>
  extends Relationship<S,ST,R,RT,T,TT>
{

  interface Type <
    S extends Term<S,ST>, ST extends Term.Type<S,ST>,
    R extends CellularComponent<S,ST,R,RT,T,TT>, RT extends CellularComponent.Type<S,ST,R,RT,T,TT>,
    T extends SubOntologies<T,TT>, TT extends SubOntologies.Type<T,TT>
  >
    extends Relationship.Type<S,ST,R,RT,T,TT>
  {}
}