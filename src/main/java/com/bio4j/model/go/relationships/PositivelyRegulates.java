package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface PositivelyRegulates extends Relationship <
  GoTerm, GoTerm.type,
  PositivelyRegulates,  PositivelyRegulates.type,
  GoTerm, GoTerm.type
> {

  public static type TYPE = type.positivelyRegulates;
  public static enum type implements RelationshipType <
    GoTerm, GoTerm.type,
    PositivelyRegulates,  PositivelyRegulates.type,
    GoTerm, GoTerm.type
  > {
    positivelyRegulates;
    public type value() { return positivelyRegulates; }
    public arity arity() { return arity.manyToMany; }
  }

  public GoTerm source();
  public GoTerm target();
}