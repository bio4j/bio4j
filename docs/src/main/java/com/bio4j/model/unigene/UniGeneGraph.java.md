
```java
package com.bio4j.model.unigene;

import com.bio4j.angulillos.*;
import com.bio4j.model.unigene.vertices.*;

public abstract class UniGeneGraph <
  I extends UntypedGraph<RV,RVT,RE,RET>,
  RV,RVT,
  RE,RET
>
implements
  TypedGraph <
    UniGeneGraph<I,RV,RVT,RE,RET>,
    I,RV,RVT,RE,RET
  >
{

  protected I raw = null;
  public UniGeneGraph(I graph) { raw = graph; }

  public final I raw() { return raw; }

  public abstract UniGeneClusterType UniGeneCluster();

  public final class UniGeneClusterType
    extends
      UniGeneVertexType <
        UniGeneCluster<I,RV,RVT,RE,RET>,
        UniGeneGraph<I,RV,RVT,RE,RET>.UniGeneClusterType
      >
  {

    public UniGeneClusterType(RVT raw) { super(raw); }

    @Override
    public UniGeneClusterType value() { return graph().UniGeneCluster(); }

    @Override
    public UniGeneCluster<I,RV,RVT,RE,RET> from(RV vertex) { return new UniGeneCluster<I,RV,RVT,RE,RET>(vertex, this); }

  }






  public abstract static class UniGeneVertex<
    V extends UniGeneVertex<V, VT, I,RV,RVT,RE,RET>,
    VT extends UniGeneGraph<I,RV,RVT,RE,RET>.UniGeneVertexType<V, VT>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET
    >
    implements
    TypedVertex<V, VT, UniGeneGraph<I,RV,RVT,RE,RET>, I,RV,RVT,RE,RET> {

  private RV vertex;
  private VT type;

  protected UniGeneVertex(RV vertex, VT type) {

    this.vertex = vertex;
    this.type = type;
  }

  @Override
  public UniGeneGraph<I,RV,RVT,RE,RET> graph() {
    return type().graph();
  }

  @Override
  public RV raw() {
    return this.vertex;
  }

  @Override
  public VT type() {
    return type;
  }
  }

  abstract class UniGeneVertexType<
    V extends UniGeneVertex<V, VT, I,RV,RVT,RE,RET>,
    VT extends UniGeneGraph<I,RV,RVT,RE,RET>.UniGeneVertexType<V, VT>
    >
    implements
    TypedVertex.Type<V, VT, UniGeneGraph<I,RV,RVT,RE,RET>, I,RV,RVT,RE,RET> {

  private RVT raw;

  protected UniGeneVertexType(RVT raw) {
    this.raw = raw;
  }

  @Override
  public final RVT raw() {
    return raw;
  }

  @Override
  public final UniGeneGraph<I,RV,RVT,RE,RET> graph() {
    return UniGeneGraph.this;
  }
  }

}

```




[main/java/com/bio4j/model/enzymedb/ENZYMEGraph.java]: ../enzymedb/ENZYMEGraph.java.md
[main/java/com/bio4j/model/uniprot_uniref/UniProtUniRefGraph.java]: ../uniprot_uniref/UniProtUniRefGraph.java.md
[main/java/com/bio4j/model/uniref/UniRefGraph.java]: ../uniref/UniRefGraph.java.md
[main/java/com/bio4j/model/go/GOGraph.java]: ../go/GOGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]: ../uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]: ../ncbiTaxonomy/NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/uniprot_go/UniProtGoGraph.java]: ../uniprot_go/UniProtGoGraph.java.md
[main/java/com/bio4j/model/unigene/UniGeneGraph.java]: UniGeneGraph.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/UniProtEnzymeDBGraph.java]: ../uniprot_enzymedb/UniProtEnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot/UniProtGraph.java]: ../uniprot/UniProtGraph.java.md