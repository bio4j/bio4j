/*
  # UniRef

  [UniRef](http://www.uniprot.org/help/uniref) clusters protein sequences from UniProt and UniParc based (among other criteria) on sequence similarity. There are three different partitions: UniRef100, UniRef90 and UniRef50, where the numbered suffix is the "identity" threshold used for the clustering procedure.

  1. **UniRef100** clusters contain identical sequences *and* substrings from one of the organisms of the cluster, depending on the order in which the sequences are processed.
  2. UniRef90 clusters are *derived* from UniRef100 representatives, but their corresponding representatives are not; UniRef90 representatives are chosen between each cluster member through different criteria.
  3. UniRef50 clusters are **derived* from UniRef90 representatives, but their corresponding representatives are not; UniRef50 representatives are chosen between each cluster member through different criteria.

  The average and maximum cluster size for UniRef50 (the one with the biggest clusters) are approximately `10` and `10^5`.

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

public final class UniRefGraph<V,E> extends TypedGraph<UniRefGraph<V,E>,V,E> {

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

  public final class UniRef90Cluster extends Vertex<UniRef90Cluster> {

    private UniRef90Cluster(V vertex) { super(vertex, uniRef90Cluster); }
    @Override public final UniRef90Cluster self() { return this; }
  }

  public final UniRef90ClusterType uniRef90Cluster = new UniRef90ClusterType();
  public final class UniRef90ClusterType extends VertexType<UniRef90Cluster> {

    @Override public final UniRef90Cluster fromRaw(V vertex) { return new UniRef90Cluster(vertex); }

    public final ID id = new ID();
    public final class ID extends Property<String> implements FromAtMostOne, ToOne {
      private ID() { super(String.class); }
      public final Index index = new Index();
      public final class Index extends UniqueIndex<ID, String> {
        private Index() { super(ID.this); }
      }
    }
  }

  public final class UniRef50Cluster extends Vertex<UniRef50Cluster> {

    private UniRef50Cluster(V vertex) { super(vertex, uniRef50Cluster); }
    @Override public final UniRef50Cluster self() { return this; }
  }

  public final UniRef50ClusterType uniRef50Cluster = new UniRef50ClusterType();
  public final class UniRef50ClusterType extends VertexType<UniRef50Cluster> {

    @Override public final UniRef50Cluster fromRaw(V vertex) { return new UniRef50Cluster(vertex); }

    public final ID id = new ID();
    public final class ID extends Property<String> implements FromAtMostOne, ToOne {
      private ID() { super(String.class); }
      public final Index index = new Index();
      public final class Index extends UniqueIndex<ID, String> {
        private Index() { super(ID.this); }
      }
    }
  }

  /*
    ## Cluster members

    These edges connect clusters with their members. A cluster has at at least one member, and every protein is member of exactly one cluster.
  */
  public final class UniRef100Member extends GenericEdge<
    UniRefGraph<V,E>, UniRef100Cluster,
    UniRef100Member,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  {
    private UniRef100Member(E edge) { super(edge, uniRef100Member); }
    @Override public final UniRef100Member self() { return this; }
  }

  public final UniRef100MemberType uniRef100Member = new UniRef100MemberType();
  public final class UniRef100MemberType extends GenericEdgeType<
    UniRefGraph<V,E>, UniRef100Cluster,
    UniRef100Member,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  implements FromOne, ToAtLeastOne {

    private UniRef100MemberType() { super(uniRef100Cluster, uniProtGraph.protein); }
    @Override public final UniRef100Member fromRaw(E edge) { return new UniRef100Member(edge); }
  }

  public final class UniRef90Member extends GenericEdge<
    UniRefGraph<V,E>, UniRef90Cluster,
    UniRef90Member,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  {
    private UniRef90Member(E edge) { super(edge, uniRef90Member); }
    @Override public final UniRef90Member self() { return this; }
  }

  public final UniRef90MemberType uniRef90Member = new UniRef90MemberType();
  public final class UniRef90MemberType extends GenericEdgeType<
    UniRefGraph<V,E>, UniRef90Cluster,
    UniRef90Member,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  implements FromOne, ToAtLeastOne {

    private UniRef90MemberType() { super(uniRef90Cluster, uniProtGraph.protein); }
    @Override public final UniRef90Member fromRaw(E edge) { return new UniRef90Member(edge); }
  }

  public final class UniRef50Member extends GenericEdge<
    UniRefGraph<V,E>, UniRef50Cluster,
    UniRef50Member,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  {
    private UniRef50Member(E edge) { super(edge, uniRef50Member); }
    @Override public final UniRef50Member self() { return this; }
  }

  public final UniRef50MemberType uniRef50Member = new UniRef50MemberType();
  public final class UniRef50MemberType extends GenericEdgeType<
    UniRefGraph<V,E>, UniRef50Cluster,
    UniRef50Member,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  implements FromOne, ToAtLeastOne {

    private UniRef50MemberType() { super(uniRef50Cluster, uniProtGraph.protein); }
    @Override public final UniRef50Member fromRaw(E edge) { return new UniRef50Member(edge); }
  }


  /*
    ## Cluster seeds

    These edges connect clusters with the protein whose sequence served as their seed. Note that this is *not* in general the same as the cluster representative; what is true though is that seeds for UniRef90 and UniRef50 are always representatives of the immediate more specific partition (UniRef100 and UniRef90 respectively).
  */
  public final class UniRef100Seed extends GenericEdge<
    UniRefGraph<V,E>, UniRef100Cluster,
    UniRef100Seed,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  {
    private UniRef100Seed(E edge) { super(edge, uniRef100Seed); }
    @Override public final UniRef100Seed self() { return this; }
  }

  public final UniRef100SeedType uniRef100Seed = new UniRef100SeedType();
  public final class UniRef100SeedType extends GenericEdgeType<
    UniRefGraph<V,E>, UniRef100Cluster,
    UniRef100Seed,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  implements FromAny, ToOne {

    private UniRef100SeedType() { super(uniRef100Cluster, uniProtGraph.protein); }
    @Override public final UniRef100Seed fromRaw(E edge) { return new UniRef100Seed(edge); }
  }

  public final class UniRef90Seed extends GenericEdge<
    UniRefGraph<V,E>, UniRef90Cluster,
    UniRef90Seed,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  {
    private UniRef90Seed(E edge) { super(edge, uniRef90Seed); }
    @Override public final UniRef90Seed self() { return this; }
  }

  public final UniRef90SeedType uniRef90Seed = new UniRef90SeedType();
  public final class UniRef90SeedType extends GenericEdgeType<
    UniRefGraph<V,E>, UniRef90Cluster,
    UniRef90Seed,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  implements FromAny, ToOne {

    private UniRef90SeedType() { super(uniRef90Cluster, uniProtGraph.protein); }
    @Override public final UniRef90Seed fromRaw(E edge) { return new UniRef90Seed(edge); }
  }

  public final class UniRef50Seed extends GenericEdge<
    UniRefGraph<V,E>, UniRef50Cluster,
    UniRef50Seed,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  {
    private UniRef50Seed(E edge) { super(edge, uniRef50Seed); }
    @Override public final UniRef50Seed self() { return this; }
  }

  public final UniRef50SeedType uniRef50Seed = new UniRef50SeedType();
  public final class UniRef50SeedType extends GenericEdgeType<
    UniRefGraph<V,E>, UniRef50Cluster,
    UniRef50Seed,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  implements FromAny, ToOne {

    private UniRef50SeedType() { super(uniRef50Cluster, uniProtGraph.protein); }
    @Override public final UniRef50Seed fromRaw(E edge) { return new UniRef50Seed(edge); }
  }


  /*
    ## Cluster representatives

    These edges connect each cluster with their representative sequence. Again, note that in general this is *not* the same as the seed used to generate that cluster.
  */
  public final class UniRef100Representative extends GenericEdge<
    UniRefGraph<V,E>, UniRef100Cluster,
    UniRef100Representative,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  {
    private UniRef100Representative(E edge) { super(edge, uniRef100Representative); }
    @Override public final UniRef100Representative self() { return this; }
  }

  public final UniRef100RepresentativeType uniRef100Representative = new UniRef100RepresentativeType();
  public final class UniRef100RepresentativeType extends GenericEdgeType<
    UniRefGraph<V,E>, UniRef100Cluster,
    UniRef100Representative,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  implements FromAny, ToOne {

    private UniRef100RepresentativeType() { super(uniRef100Cluster, uniProtGraph.protein); }
    @Override public final UniRef100Representative fromRaw(E edge) { return new UniRef100Representative(edge); }
  }

  public final class UniRef90Representative extends GenericEdge<
    UniRefGraph<V,E>, UniRef90Cluster,
    UniRef90Representative,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  {
    private UniRef90Representative(E edge) { super(edge, uniRef90Representative); }
    @Override public final UniRef90Representative self() { return this; }
  }

  public final UniRef90RepresentativeType uniRef90Representative = new UniRef90RepresentativeType();
  public final class UniRef90RepresentativeType extends GenericEdgeType<
    UniRefGraph<V,E>, UniRef90Cluster,
    UniRef90Representative,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  implements FromAny, ToOne {

    private UniRef90RepresentativeType() { super(uniRef90Cluster, uniProtGraph.protein); }
    @Override public final UniRef90Representative fromRaw(E edge) { return new UniRef90Representative(edge); }
  }

  public final class UniRef50Representative extends GenericEdge<
    UniRefGraph<V,E>, UniRef50Cluster,
    UniRef50Representative,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  {
    private UniRef50Representative(E edge) { super(edge, uniRef50Representative); }
    @Override public final UniRef50Representative self() { return this; }
  }

  public final UniRef50RepresentativeType uniRef50Representative = new UniRef50RepresentativeType();
  public final class UniRef50RepresentativeType extends GenericEdgeType<
    UniRefGraph<V,E>, UniRef50Cluster,
    UniRef50Representative,
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein
  >
  implements FromAny, ToOne {

    private UniRef50RepresentativeType() { super(uniRef50Cluster, uniProtGraph.protein); }
    @Override public final UniRef50Representative fromRaw(E edge) { return new UniRef50Representative(edge); }
  }
}
