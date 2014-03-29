package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface PartOfGo extends Relationship <
  GoTerm, GoTerm.type,
  PartOfGo,  PartOfGo.type,
  GoTerm, GoTerm.type
> {

  public static type TYPE = type.partOfGo;
  public static enum type implements RelationshipType <
    GoTerm, GoTerm.type,
    PartOfGo,  PartOfGo.type,
    GoTerm, GoTerm.type
  > {
    partOfGo;
    public type value() { return partOfGo; }
  }

  public GoTerm source();
  public GoTerm target();
}