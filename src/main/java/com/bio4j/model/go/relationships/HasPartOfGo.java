package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface HasPartOfGo extends Relationship <
  GoTerm, GoTerm.type,
  HasPartOfGo, HasPartOfGo.type,
  GoTerm, GoTerm.type
> {

  public static enum type implements RelationshipType <
    GoTerm, GoTerm.type,
    HasPartOfGo, HasPartOfGo.type,
    GoTerm, GoTerm.type
  > {
    hasPartOfGo;
    public type value() { return hasPartOfGo; }
  }

  public GoTerm source();
  public GoTerm target();
}