package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Regulates extends Relationship <
  GoTerm, GoTerm.type,
  Regulates,  Regulates.type,
  GoTerm, GoTerm.type
> {

  public GoTerm source();
  public GoTerm target();

  public static type TYPE = type.regulates;
  
  public static enum type implements RelationshipType <
    GoTerm, GoTerm.type,
    Regulates,  Regulates.type,
    GoTerm, GoTerm.type
  > {
    regulates;
    public type value() { return regulates; }
    public arity arity() { return arity.manyToMany; }
  }

  
}