package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.RelTypes;

import com.bio4j.model.go.nodes.GoTermNamespace;
import com.bio4j.model.go.nodes.GoRoot;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface CellularComponent extends GoSubOntology<CellularComponent, CellularComponent.Type> {

  public static Type TYPE = Type.cellularComponent;

  @Override public default Type type() { return TYPE; }

  public static enum Type implements GoSubOntology.Type<CellularComponent, CellularComponent.Type> {
    
    cellularComponent;

    @Override public Type value() { return cellularComponent; }
  }
}