package com.bio4j.model.go.nodes;

import java.util.List;


import com.ohnosequences.typedGraphs.Node;

// import com.bio4j.model.go.relationships.goSlims.*;


/**
 * Relationships into this singleton node represent GO Slims.
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface GoSlims <
  N extends GoSlims<N,NT>,
  NT extends GoSlims.Type<N,NT>
> 
  extends Node<N,NT>
{

  public static interface Type <
    N extends GoSlims<N,NT>,
    NT extends GoSlims.Type<N,NT>
  > 
    extends Node.Type<N,NT>
  {}

  // plantSlim
  // incoming
  // public List<? extends PlantSlim> plantSlim_in();
  // public List<? extends Term> plantSlim_inNodes();
  // TODO same for the other GoSlims
}
