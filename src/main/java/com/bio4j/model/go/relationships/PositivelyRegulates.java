package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface PositivelyRegulates extends Relationship <

  GoTerm, GoTerm.type,
  PositivelyRegulates,  PositivelyRegulates.type,
  GoTerm, GoTerm.type

> 
{

  public GoTerm source();
  public GoTerm target();

  public static type TYPE = type.positivelyRegulates;
  public static enum type implements RelationshipType <

    GoTerm, GoTerm.type,
    PositivelyRegulates,  PositivelyRegulates.type,
    GoTerm, GoTerm.type

  > 
  {
    positivelyRegulates;

    public arity arity() { return arity.manyToMany; }
    
    public type value() { return positivelyRegulates; }    
    public GoTerm.type sourceType() { return GoTerm.TYPE; }
    public GoTerm.type targetType() { return GoTerm.TYPE; }
  }

  
}