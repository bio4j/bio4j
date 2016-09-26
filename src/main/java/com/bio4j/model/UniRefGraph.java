package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;
import java.util.Set;

public final class UniRefGraph<V,E> extends LinkGraph<UniRefGraph<V,E>,V,E> {

  public UniRefGraph(UniProtGraph<V,E> uniProtGraph) {

    super(uniProtGraph.raw());
    this.uniProtGraph = uniProtGraph;
  }

  public final UniProtGraph<V,E> uniProtGraph;

  @Override public final UniRefGraph<V,E> self() { return this; }

  public final class UniRef100Cluster extends Vertex<UniRef100Cluster> {

    private UniRef100Cluster(V vertex) { super(vertex, uniRef100Cluster); }
    @Override public final UniRef100Cluster self() { return this; }
  }

  public final UniRef100ClusterType uniRef100Cluster = new UniRef100ClusterType();
  public final class UniRef100ClusterType extends VertexType<UniRef100Cluster> {

    @Override public final UniRef100Cluster fromRaw(V vertex) { return new UniRef100Cluster(vertex); }

    public final ID id = new ID();
    public final class ID extends Property<String> implements FromAtMostOne, ToOne {
      private ID() { super(String.class); }
      public final Index index = new Index();
      public final class Index extends UniqueIndex<ID, String> {
        private Index() { super(ID.this); }
      }
    }
  }

  public final class UniRef100Representative extends LinkEdge<
    UniRefGraph<V,E>, UniRef100Cluster,
    UniRef100Representative,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  {
    private UniRef100Representative(E edge) { super(edge, uniRef100Representative); }
    @Override public final UniRef100Representative self() { return this; }
  }

  public final UniRef100RepresentativeType uniRef100Representative = new UniRef100RepresentativeType();
  public final class UniRef100RepresentativeType extends LinkEdgeType<
    UniRefGraph<V,E>, UniRef100Cluster,
    UniRef100Representative,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  implements FromAny, ToOne {

    private UniRef100RepresentativeType() { super(uniRef100Cluster, uniProtGraph.protein); }
    @Override public final UniRef100Representative fromRaw(E edge) { return new UniRef100Representative(edge); }
  }
}
