package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface MolecularFunction extends SubOntology<MolecularFunction, MolecularFunction.Type> {

  public static Type TYPE = Type.molecularFunction;

  @Override public default Type type() { return TYPE; }

  public static enum Type implements SubOntology.Type<MolecularFunction, MolecularFunction.Type> {
    
    molecularFunction;

    @Override public Type value() { return molecularFunction; }
  }
}
