package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;

import com.bio4j.model.go.nodes.Term;
import com.bio4j.model.go.nodes.SubOntologies;
import com.bio4j.model.go.GoGraph.*;

public interface CellularComponent <
  S extends Term<S,ST>, ST extends TermType<S,ST>,
  R extends CellularComponent<S,ST,R,RT,T,TT>, RT extends CellularComponentType<S,ST,R,RT,T,TT>,
  T extends SubOntologies<T,TT>, TT extends SubOntologiesType<T,TT>
>
  extends Relationship<S,ST,R,RT,T,TT>
{}