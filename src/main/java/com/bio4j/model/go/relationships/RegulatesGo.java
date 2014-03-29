package com.bio4j.model.relationships.go;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface RegulatesGo extends Relationship <
  GoTerm, GoTerm.type,
  RegulatesGo,  RegulatesGo.type,
  GoTerm, GoTerm.type
> {

  public static type TYPE = type.regulatesGo;
  public static enum type implements RelationshipType <
    GoTerm, GoTerm.type,
    RegulatesGo,  RegulatesGo.type,
    GoTerm, GoTerm.type
  > {
    regulatesGo;
    public type value() { return regulatesGo; }
    public arity arity() { return arity.manyToMany; }
  }

  public GoTerm source();
  public GoTerm target();
}