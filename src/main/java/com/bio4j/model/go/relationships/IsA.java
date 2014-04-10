package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface IsA extends Relationship <
  GoTerm, GoTerm.Type,
  IsA, IsA.Type,
  GoTerm, GoTerm.Type
> {

  public static Type TYPE = Type.isA;
  public static enum Type implements RelationshipType <
    GoTerm, GoTerm.Type,
    IsA,  IsA.Type,
    GoTerm, GoTerm.Type
  > {
    isA;
    public Type value() { return isA; }
    public Arity arity() { return Arity.manyToMany; }
    public GoTerm.Type sourceType() { return GoTerm.TYPE; }
    public GoTerm.Type targetType() { return GoTerm.TYPE; }
  }

  public GoTerm source();
  public GoTerm target();
}