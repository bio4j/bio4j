package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface BiologicalProcess extends SubOntology<BiologicalProcess, BiologicalProcess.Type> {

  public static Type TYPE = Type.biologicalProcess;

  @Override public default Type type() { return TYPE; }

  public static enum Type implements SubOntology.Type<BiologicalProcess, BiologicalProcess.Type> {
    
    biologicalProcess;

    @Override public Type value() { return biologicalProcess; }
  }
}
