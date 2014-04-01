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
  GoTerm, GoTerm.type,
  IsA,  IsA.type,
  GoTerm, GoTerm.type
> {

  public static type TYPE = type.isA;
  public static enum type implements RelationshipType <
    GoTerm, GoTerm.type,
    IsA,  IsA.type,
    GoTerm, GoTerm.type
  > {
    isA;
    public type value() { return isA; }
    public arity arity() { return arity.manyToMany; }
  }

  public GoTerm source();
  public GoTerm target();
}