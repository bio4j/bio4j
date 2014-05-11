package com.bio4j.model.go.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;


import com.bio4j.model.go.relationships.Term;
import com.bio4j.model.go.relationships.MolecularFunction;
import com.bio4j.model.go.relationships.BiologicalProcess;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface GoRoot extends Node<GoRoot,GoRoot.Type> {

  // MolecularFunction
  // incoming
  public MolecularFunction molecularFunction_in();
  public GoTermNamespace molecularFunction_inNodes();

  // same for the other namespaces

  public default Type type() { return TYPE; }

  public static Type TYPE = Type.goRoot;

  public static enum Type implements NodeType<GoRoot,GoRoot.Type> {
    
    goRoot;

    public Type value() { return goRoot; }
  }
}
