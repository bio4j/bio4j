package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class UniRefGraph<V,E> extends TypedGraph<UniRefGraph<V,E>,V,E> {

  public UniRefGraph(UniProtGraph<V,E> uniProtGraph) {

    super(uniProtGraph.raw());
    this.uniProtGraph = uniProtGraph;
  }

  public final UniProtGraph<V,E> uniProtGraph;

  @Override public final UniRefGraph<V,E> self() { return this; }
}
