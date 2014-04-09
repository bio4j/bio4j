package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface Regulates extends Relationship <
  GoTerm, GoTerm.Type,
  Regulates,  Regulates.Type,
  GoTerm, GoTerm.Type
> {

  public GoTerm source();
  public GoTerm target();

  public static Type TYPE = Type.regulates; 
  public static enum Type implements RelationshipType <
    GoTerm, GoTerm.Type,
    Regulates,  Regulates.Type,
    GoTerm, GoTerm.Type
  > {
    regulates;
    public Type value() { return regulates; }
    public arity arity() { return arity.manyToMany; }
    public GoTerm.Type sourceType() { return GoTerm.TYPE; }
    public GoTerm.Type targetType() { return GoTerm.TYPE; }

  }

  
}