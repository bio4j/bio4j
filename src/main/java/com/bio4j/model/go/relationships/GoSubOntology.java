package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.RelTypes;

import com.bio4j.model.go.nodes.GoNamespace;
import com.bio4j.model.go.nodes.GoRoot;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface GoSubOntology<
  GSO extends Relationship<GoNamespace,GoNamespace.Type, GSO,GSOT, GoRoot, GoRoot.Type>,
  GSOT extends Enum<GSOT> & RelationshipType<GoNamespace,GoNamespace.Type, GSO,GSOT, GoRoot, GoRoot.Type>
> extends Relationship <
  GoNamespace, GoNamespace.Type,
  GSO, GSOT,
  GoRoot, GoRoot.Type
> {

  @Override public GoNamespace source();
  @Override public GoRoot target();

  public interface Type <
    GSO extends Relationship<GoNamespace,GoNamespace.Type, GSO,GSOT, GoRoot,GoRoot.Type>,
    GSOT extends Enum<GSOT> & RelationshipType<GoNamespace,GoNamespace.Type, GSO,GSOT, GoRoot,GoRoot.Type>
  > 
  extends RelTypes.OneToOne <
    GoNamespace, GoNamespace.Type,
    GSO, GSOT,
    GoRoot, GoRoot.Type
  >
  {

    @Override public default GoNamespace.Type sourceType() { return GoNamespace.TYPE; }
    @Override public default GoRoot.Type targetType() { return GoRoot.TYPE; }
  }
}
