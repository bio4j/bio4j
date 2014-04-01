package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface NegativelyRegulates extends Relationship <
  GoTerm, GoTerm.type,
  NegativelyRegulates, NegativelyRegulates.type,
  GoTerm, GoTerm.type
> {

  public static type TYPE = type.negativelyRegulates;
  public static enum type implements RelationshipType <
    GoTerm, GoTerm.type,
    NegativelyRegulates, NegativelyRegulates.type,
    GoTerm, GoTerm.type
  > {
    negativelyRegulates;
    public type value() { return negativelyRegulates; }
    public arity arity() { return arity.manyToMany; }
  }

  public GoTerm source();
  public GoTerm target();
}