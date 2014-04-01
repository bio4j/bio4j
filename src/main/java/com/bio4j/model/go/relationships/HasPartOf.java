package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface HasPartOf extends Relationship <
  GoTerm, GoTerm.type,
  HasPartOf, HasPartOf.type,
  GoTerm, GoTerm.type
> {

  public static type TYPE = type.hasPartOf;
  public static enum type implements RelationshipType <
    GoTerm, GoTerm.type,
    HasPartOf, HasPartOf.type,
    GoTerm, GoTerm.type
  > {
    hasPartOf;
    public type value() { return hasPartOf; }
    public arity arity() { return arity.manyToMany; }
  }

  public GoTerm source();
  public GoTerm target();
}