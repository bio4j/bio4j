package com.bio4j.model.go.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface MolecularFunction extends GoNamespace<MolecularFunction, MolecularFunction.Type> {

  public default Type type() { return TYPE; }

  public static Type TYPE = Type.molecularFunction;

  public static enum Type implements GoNamespace.Type<MolecularFunction, MolecularFunction.Type> {
    
    molecularFunction;
    
    public Type value() { return molecularFunction; }
  }
}
