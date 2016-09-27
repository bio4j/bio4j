/*
  # UniRef

  [UniRef](http://www.uniprot.org/help/uniref) clusters protein sequences from UniProt and UniParc based (among other criteria) on sequence similarity. There are three different partitions: UniRef100, UniRef90 and UniRef50, where the numbered suffix is the "identity" threshold used for the clustering procedure.

  1. **UniRef100** clusters contain identical sequences *and* substrings from one of the organisms of the cluster, depending on the order in which the sequences are processed.
  2. UniRef90 clusters are *derived* from UniRef100 representatives, but their corresponding representatives are not; UniRef90 representatives are chosen between each cluster member through different criteria.
  3. UniRef50 clusters are **derived* from UniRef90 representatives, but their corresponding representatives are not; UniRef50 representatives are chosen between each cluster member through different criteria.

  The average and maximum cluster size for UniRef50 (the one with the biggest clusters) are approximately `10` and `10^6`.

  If a cluster (of any paritition) contains a UniProt protein the its representative will be a UniProt protein. Note that the selection of representatives and the update procedures are somewhat arbitrary; quoting from the [UniRef clusters: a comprehensive and scalable alternative for improving sequence similarity searches](https://bioinformatics.oxfordjournals.org/content/31/6/926.full):

  > Using a full update procedure, the clusters are computed ab initio at the end of year and are updated for the remaining year using an incremental procedure that favors clustering of new sequences under existing clusters. The representatives of clusters are selected based on the level of curation (reviewed versus unreviewed), protein name (e.g. names do not contain hypothetical or putative preferred), source organism (e.g. proteins from model organisms preferred) and length of protein. The UniRef identifiers are derived from the cluster ‘representatives’ identifiers and are preserved for approximately 98% of the clusters between releases.

  ## UniRef and UniProt

  In Bio4j we only include clusters for which the representative is a UniProt protein.

  ## References

  - [UniRef100 FTP Readme](ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref100/README)
  - [UniRef90 FTP Readme](ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref90/README)
  - [UniRef50 FTP Readme](ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref50/README)
  - [UniRef clusters: a comprehensive and scalable alternative for improving sequence similarity searches](https://bioinformatics.oxfordjournals.org/content/31/6/926.full)
  - [UniRef help](http://www.uniprot.org/help/uniref)
*/
package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

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
