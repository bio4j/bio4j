package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.bio4j.model.go.nodes.Term;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface IsA <
  S extends Term<S,ST>, ST extends Term.Type<S,ST>,
  R extends IsA<S,ST,R,RT,T,TT>, RT extends IsA.Type<S,ST,R,RT,T,TT>,
  T extends Term<T,TT>, TT extends Term.Type<T,TT>
>
  extends Relationship<S,ST,R,RT,T,TT>
{

  public static interface Type <
    S extends Term<S,ST>, ST extends Term.Type<S,ST>,
    R extends IsA<S,ST,R,RT,T,TT>, RT extends IsA.Type<S,ST,R,RT,T,TT>,
    T extends Term<T,TT>, TT extends Term.Type<T,TT>
  >
    extends Relationship.Type<S,ST,R,RT,T,TT>
  {}
}
