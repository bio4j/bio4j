package com.bio4j.model.go.relationships;

import com.bio4j.model.go.nodes.GoTerm;
import com.ohnosequences.typedGraphs.Relationship;
import com.bio4j.model.go.GoGraph.*;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface PositivelyRegulates <
  S extends GoTerm<S,ST>, ST extends TermType<S,ST>,
  R extends PositivelyRegulates<S,ST,R,RT,T,TT>, RT extends PositivelyRegulates.Type<S,ST,R,RT,T,TT>,
  T extends GoTerm<T,TT>, TT extends TermType<T,TT>
>
  extends Relationship<S,ST,R,RT,T,TT>
{}
