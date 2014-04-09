package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface PartOf extends Relationship <
  GoTerm, GoTerm.Type,
  PartOf,  PartOf.Type,
  GoTerm, GoTerm.Type
> {

  public static Type TYPE = Type.partOfGo;
  public static enum Type implements RelationshipType <
    GoTerm, GoTerm.Type,
    PartOf,  PartOf.Type,
    GoTerm, GoTerm.Type
  > {
    partOfGo;
    public Type value() { return partOfGo; }
    public arity arity() { return arity.manyToMany; }
    public GoTerm.Type sourceType() { return GoTerm.TYPE; }
    public GoTerm.Type targetType() { return GoTerm.TYPE; }
  }

  public GoTerm source();
  public GoTerm target();
}