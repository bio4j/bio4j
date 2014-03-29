package com.bio4j.model.relationships.go;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface PositivelyRegulatesGo extends Relationship <
  GoTerm, GoTerm.type,
  PositivelyRegulatesGo,  PositivelyRegulatesGo.type,
  GoTerm, GoTerm.type
> {

  public static type TYPE = type.positivelyRegulatesGo;
  public static enum type implements RelationshipType <
    GoTerm, GoTerm.type,
    PositivelyRegulatesGo,  PositivelyRegulatesGo.type,
    GoTerm, GoTerm.type
  > {
    positivelyRegulatesGo;
    public type value() { return positivelyRegulatesGo; }
  }

  public GoTerm source();
  public GoTerm target();
}