package com.bio4j.model.go.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface GoNamespace <

  NS extends GoNamespace<NS,NST>,
  NST extends Enum<NST> & GoNamespace.Type<NS,NST>

> extends Node<NS, NST> {

  public interface MolecularFunction extends GoNamespace<MolecularFunction, MolecularFunction.Type> {

    public default Type type() { return TYPE; }
    public static Type TYPE = Type.molecularFunction;
    
    public static enum Type implements GoNamespace.Type<MolecularFunction, MolecularFunction.Type> {

      molecularFunction;
      public Type value() { return molecularFunction; }
    }
  }

  public interface CellularComponent extends GoNamespace<CellularComponent, CellularComponent.Type> {

    public default Type type() { return TYPE; }
    public static Type TYPE = Type.cellularComponent;
    
    public static enum Type implements GoNamespace.Type<CellularComponent, CellularComponent.Type> {
      
      cellularComponent;
      public Type value() { return cellularComponent; }
    }
  }
  // TODO
  // public interface BiologicalProcess extends GoNamespace<BiologicalProcess, BiologicalProcess.Type> {}

  public interface Type <
    NS extends GoNamespace<NS,NST>,
    NST extends Enum<NST> & GoNamespace.Type<NS,NST>
  > extends NodeType<NS,NST> {}
}