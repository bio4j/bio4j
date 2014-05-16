package com.bio4j.model.go.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.bio4j.model.go.relationships.MolecularFunction;
import com.bio4j.model.go.relationships.BiologicalProcess;
import com.bio4j.model.go.relationships.CellularComponent;

/**
 * Rels into this singleton node represent subontologies.
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface SubOntologies extends Node<SubOntologies, SubOntologies.Type> {

  // MolecularFunction
  // incoming
  public List<? extends MolecularFunction> molecularFunction_in();
  public List<? extends Term> term_inNodes();
  
  // BiologicalProcess
  // incoming
  public List<? extends BiologicalProcess> biologicalProcess_in(); 
  public List<? extends Term> biologicalProcess_inNodes();

  // CellularComponent
  // incoming
  public List<? extends CellularComponent> cellularComponent_in(); 
  public List<? extends Term> cellularComponent_inNodes();


  // type

  public default Type type() { return TYPE; }

  public static Type TYPE = Type.subOntologies;

  public static enum Type implements NodeType<SubOntologies, SubOntologies.Type> {
    
    subOntologies;

    public Type value() { return subOntologies; }
  }
}
