package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface HasPartOf extends Relationship <
  GoTerm, GoTerm.Type,
  HasPartOf, HasPartOf.Type,
  GoTerm, GoTerm.Type
> {

  public GoTerm source();
  public GoTerm target();

  public static Type TYPE = Type.hasPartOf;
  public static enum Type implements RelationshipType <
    GoTerm, GoTerm.Type,
    HasPartOf, HasPartOf.Type,
    GoTerm, GoTerm.Type
  > {
    hasPartOf;

    public Arity arity() { return Arity.manyToMany; } // TODO review arity

    public Type value() { return hasPartOf; }
    public GoTerm.Type sourceType() { return GoTerm.TYPE; }
    public GoTerm.Type targetType() { return GoTerm.TYPE; }
  }
}
