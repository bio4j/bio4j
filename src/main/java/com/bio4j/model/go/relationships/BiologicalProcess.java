package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;

import com.bio4j.model.go.nodes.Term;
import com.bio4j.model.go.nodes.SubOntologies;
import com.bio4j.model.go.GoGraph.*;
/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface BiologicalProcess <
  S extends Term<S,ST>, ST extends TermType<S,ST>,
  R extends BiologicalProcess<S,ST,R,RT,T,TT>, RT extends BiologicalProcessType<S,ST,R,RT,T,TT>,
  T extends SubOntologies<T,TT>, TT extends SubOntologiesType<T,TT>
>
  extends Relationship<S,ST,R,RT,T,TT>
{}
