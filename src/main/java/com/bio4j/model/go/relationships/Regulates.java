package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.RelTypes;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface Regulates extends Relationship <
  GoTerm, GoTerm.Type,
  Regulates,  Regulates.Type,
  GoTerm, GoTerm.Type
> {

  @Override public GoTerm source();
  @Override public GoTerm target();

  public static Type TYPE = Type.regulates; 

  @Override public default Type type() { return TYPE; }

  public static enum Type implements RelTypes.ManyToMany <
    GoTerm, GoTerm.Type,
    Regulates,  Regulates.Type,
    GoTerm, GoTerm.Type
  >
  {    
    regulates;

    public Type value() { return regulates; }
    public GoTerm.Type sourceType() { return GoTerm.TYPE; }
    public GoTerm.Type targetType() { return GoTerm.TYPE; }
  }
}