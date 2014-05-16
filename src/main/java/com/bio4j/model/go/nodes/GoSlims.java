package com.bio4j.model.go.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.bio4j.model.go.relationships.goSlims.*;


/**
 * Relationships into this singleton node represent GO Slims.
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface GoSlims extends Node<GoSlims,GoSlims.Type> {

  // plantSlim
  // incoming
  // public List<? extends PlantSlim> plantSlim_in();
  // public List<? extends Term> plantSlim_inNodes();
  // TODO same for the other GoSlims

  public default Type type() { return TYPE; }

  public static Type TYPE = Type.goSlims;

  public static enum Type implements NodeType<GoSlims,GoSlims.Type> {
    
    goSlims;

    public Type value() { return goSlims; }
  }
}
