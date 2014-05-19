package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;

import com.bio4j.model.go.nodes.Term;
import com.bio4j.model.go.nodes.SubOntologies;


/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface MolecularFunction <
  S extends Term<S,ST>, ST extends Term.Type<S,ST>,
  R extends MolecularFunction<S,ST,R,RT,T,TT>, RT extends MolecularFunction.Type<S,ST,R,RT,T,TT>,
  T extends SubOntologies<T,TT>, TT extends SubOntologies.Type<T,TT>
>
  extends Relationship<S,ST,R,RT,T,TT>
{

  interface Type <
    S extends Term<S,ST>, ST extends Term.Type<S,ST>,
    R extends MolecularFunction<S,ST,R,RT,T,TT>, RT extends MolecularFunction.Type<S,ST,R,RT,T,TT>,
    T extends SubOntologies<T,TT>, TT extends SubOntologies.Type<T,TT>
  >
    extends Relationship.Type.ManyToOne<S,ST,R,RT,T,TT>
  {}
}