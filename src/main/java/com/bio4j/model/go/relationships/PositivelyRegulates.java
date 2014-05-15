package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;


import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface PositivelyRegulates extends Relationship <
  GoTerm, GoTerm.Type,
  PositivelyRegulates, PositivelyRegulates.Type,
  GoTerm, GoTerm.Type
> 
{

  @Override public GoTerm source();
  @Override public GoTerm target();

  public static Type TYPE = Type.positivelyRegulates;

  @Override public default Type type() { return TYPE; }

  public static enum Type implements RelationshipType.ManyToMany <
    GoTerm, GoTerm.Type,
    PositivelyRegulates, PositivelyRegulates.Type,
    GoTerm, GoTerm.Type
  > 
  {
    positivelyRegulates;
    
    public Type value() { return positivelyRegulates; }    
    public GoTerm.Type sourceType() { return GoTerm.TYPE; }
    public GoTerm.Type targetType() { return GoTerm.TYPE; }
  }

  
}
