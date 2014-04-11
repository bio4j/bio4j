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
  GoTerm, GoTerm.Type,
  HasPartOf, HasPartOf.Type,
  GoTerm, GoTerm.Type
> {

  public static Type TYPE = Type.hasPartOf;
  public static enum Type implements RelationshipType <
    GoTerm, GoTerm.Type,
    HasPartOf, HasPartOf.Type,
    GoTerm, GoTerm.Type
  > {
    hasPartOf;
    public Type value() { return hasPartOf; }
    public Arity arity() { return Arity.manyToMany; }
    public GoTerm.Type sourceType() { return GoTerm.TYPE; }
    public GoTerm.Type targetType() { return GoTerm.TYPE; }
  }

  public GoTerm source();
  public GoTerm target();
}