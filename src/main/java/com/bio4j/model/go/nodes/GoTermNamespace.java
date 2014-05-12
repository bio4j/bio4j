package com.bio4j.model.go.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;


import com.bio4j.model.go.relationships.Term;
import com.bio4j.model.go.relationships.MolecularFunction;
import com.bio4j.model.go.relationships.BiologicalProcess;
import com.bio4j.model.go.relationships.CellularComponent;

/**
 * Each of these nodes represent a GO namespace/subOntology. Which one is determined by which of the out rels is present.
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface GoTermNamespace extends Node<GoTermNamespace,GoTermNamespace.Type> {

  // Term
  // incoming
  public List<Term> term_in();
  public List<GoTerm> term_inNodes();

  // MolecularEvolution
  // outgoing
  public MolecularFunction molecularFunction_out(); 
  public GoRoot molecularFunction_outNodes();
  // MolecularEvolution
  // outgoing
  public BiologicalProcess biologicalProcess_out(); 
  public GoRoot biologicalProcess_outNodes();

  // MolecularEvolution
  // outgoing
  public CellularComponent cellularComponent_out(); 
  public GoRoot cellularComponent_outNodes();

  public default Type type() { return TYPE; }

  public static Type TYPE = Type.goTermNamespace;

  public static enum Type implements NodeType<GoTermNamespace, GoTermNamespace.Type> {
    
    goTermNamespace;

    public Type value() { return goTermNamespace; }
  }
}
