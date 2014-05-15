package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;


import com.bio4j.model.go.nodes.Term;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface HasPartOf extends Relationship <
  Term, Term.Type,
  HasPartOf, HasPartOf.Type,
  Term, Term.Type
>
{

  @Override public Term source();
  @Override public Term target();

  public static Type TYPE = Type.hasPartOf;

  @Override public default Type type() { return TYPE; }

  public static enum Type implements RelationshipType.ManyToMany <
    Term, Term.Type,
    HasPartOf, HasPartOf.Type,
    Term, Term.Type
  >
  {
    hasPartOf;

    public Type value() { return hasPartOf; }
    public Term.Type sourceType() { return Term.TYPE; }
    public Term.Type targetType() { return Term.TYPE; }
  }
}
