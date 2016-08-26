package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class UniProtGOGraph<V,E> extends TypedGraph<UniProtGOGraph<V,E>,V,E> {

  public UniProtGOGraph(UniProtGraph<V,E> uniProtGraph, GOGraph<V,E> goGraph) {

    super(uniProtGraph.raw());
    this.uniProtGraph = uniProtGraph;
    this.goGraph = goGraph;
  }

  public final UniProtGraph<V,E> uniProtGraph;
  public final GOGraph<V,E> goGraph;

  @Override public final UniProtGOGraph<V,E> self() { return this; }

  // TODO add edge here
}
