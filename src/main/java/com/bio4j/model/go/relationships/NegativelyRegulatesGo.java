package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface NegativelyRegulatesGo extends Relationship <
  GoTerm, GoTerm.type,
  NegativelyRegulatesGo,  NegativelyRegulatesGo.type,
  GoTerm, GoTerm.type
> {

  public static type TYPE = type.negativelyRegulatesGo;
  public static enum type implements RelationshipType <
    GoTerm, GoTerm.type,
    NegativelyRegulatesGo,  NegativelyRegulatesGo.type,
    GoTerm, GoTerm.type
  > {
    negativelyRegulatesGo;
    public type value() { return negativelyRegulatesGo; }
  }

  public GoTerm source();
  public GoTerm target();
}