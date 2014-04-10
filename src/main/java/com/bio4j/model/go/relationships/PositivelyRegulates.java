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

  GoTerm, GoTerm.Type,
  PositivelyRegulates,  PositivelyRegulates.Type,
  GoTerm, GoTerm.Type

> 
{

  public GoTerm source();
  public GoTerm target();

  public static Type TYPE = Type.positivelyRegulates;
  public static enum Type implements RelationshipType <

    GoTerm, GoTerm.Type,
    PositivelyRegulates,  PositivelyRegulates.Type,
    GoTerm, GoTerm.Type

  > 
  {
    positivelyRegulates;

    public Arity arity() { return Arity.manyToMany; }
    
    public Type value() { return positivelyRegulates; }    
    public GoTerm.Type sourceType() { return GoTerm.TYPE; }
    public GoTerm.Type targetType() { return GoTerm.TYPE; }
  }

  
}