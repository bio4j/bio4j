package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface IsAGo extends Relationship <
  GoTerm, GoTerm.type,
  IsAGo,  IsAGo.type,
  GoTerm, GoTerm.type
> {

  public static type TYPE = type.isAGo;
  public static enum type implements RelationshipType <
    GoTerm, GoTerm.type,
    IsAGo,  IsAGo.type,
    GoTerm, GoTerm.type
  > {
    isAGo;
    public type value() { return isAGo; }
  }

  public GoTerm source();
  public GoTerm target();
}