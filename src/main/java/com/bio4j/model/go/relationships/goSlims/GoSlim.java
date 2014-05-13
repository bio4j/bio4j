package com.bio4j.model.go.relationships.goSlims;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.RelTypes;

import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.go.nodes.GoSlims;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface GoSlim <
  GSO extends Relationship<GoTerm,GoTerm.Type, GSO,GSOT, GoSlims, GoSlims.Type>,
  GSOT extends Enum<GSOT> & RelationshipType<GoTerm,GoTerm.Type, GSO,GSOT, GoSlims, GoSlims.Type>
> extends Relationship <
  GoTerm, GoTerm.Type,
  GSO, GSOT,
  GoSlims, GoSlims.Type
> {

  @Override public GoTerm source();
  @Override public GoSlims target();

  public interface Type <
    GSO extends Relationship<GoTerm,GoTerm.Type, GSO,GSOT, GoSlims,GoSlims.Type>,
    GSOT extends Enum<GSOT> & RelationshipType<GoTerm,GoTerm.Type, GSO,GSOT, GoSlims,GoSlims.Type>
  > 
  extends RelTypes.ManyToOne <
    GoTerm, GoTerm.Type,
    GSO, GSOT,
    GoSlims, GoSlims.Type
  >
  {

    @Override public default GoTerm.Type sourceType() { return GoTerm.TYPE; }
    @Override public default GoSlims.Type targetType() { return GoSlims.TYPE; }
  }
}