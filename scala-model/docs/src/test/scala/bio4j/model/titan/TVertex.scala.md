
```scala
package bio4j.model.test.titan

import bio4j.model._

trait AnyTVertex extends AnyVertex { tvertex =>

  type Rep = com.thinkaurelius.titan.core.TitanVertex
```

Reading any property from a TitanVertex

```scala
  import AnyProperty._
  implicit def readFromTitanVertex(vr: TaggedRep) = 
    new ReadFrom[TaggedRep](vr) {
      def apply[P <: AnyProperty](p: P): p.Rep = vr.getProperty[p.Rep](p.label)
    }
```

Getting a property from any TitanVertex

```scala
  import SmthHasProperty._
  implicit def unsafeGetProperty[P <: AnyProperty: PropertyOf[this.Tpe]#is](p: P) = 
    new GetProperty[P](p) {
      def apply(rep: TaggedRep): p.Rep = rep.getProperty[p.Rep](p.label)
    }

  // TODO: provide ReadFrom for %:

```

Retrieving edges

```scala
  import com.tinkerpop.blueprints.Direction
  import scala.collection.JavaConversions._

  // TODO: when we get all edges with the given label, they can come from vertices with the wrong type

```

OUT

```scala
  implicit def unsafeRetrieveOneOutEdge[
    E <: Singleton with AnyEdge { type Tpe <: EdgeFrom[tvertex.Tpe] with SmthToOne }
  ](e: E): RetrieveOutEdge[E] = new RetrieveOutEdge[E](e) {

      def apply(rep: tvertex.TaggedRep): e.tpe.Out[e.TaggedRep] = {
        val it = rep.getEdges(Direction.OUT, e.tpe.label).asInstanceOf[java.lang.Iterable[e.TaggedRep]]
        it.headOption: Option[e.TaggedRep]
      }
    }

  implicit def unsafeRetrieveManyOutEdge[
    E <: Singleton with AnyEdge { type Tpe <: EdgeFrom[tvertex.Tpe] with SmthToMany }
  ](e: E): RetrieveOutEdge[E] = new RetrieveOutEdge[E](e) {

      def apply(rep: tvertex.TaggedRep): e.tpe.Out[e.TaggedRep] = {
        val it = rep.getEdges(Direction.OUT, e.tpe.label).asInstanceOf[java.lang.Iterable[e.TaggedRep]]
        it.toList: List[e.TaggedRep]
      }
    }
```

IN

```scala
  implicit def unsafeRetrieveOneInEdge[
    E <: Singleton with AnyEdge { type Tpe <: EdgeTo[tvertex.Tpe] with OneToSmth }
  ](e: E): RetrieveInEdge[E] = new RetrieveInEdge[E](e) {

      def apply(rep: tvertex.TaggedRep): e.tpe.In[e.TaggedRep] = {
        val it = rep.getEdges(Direction.IN, e.tpe.label).asInstanceOf[java.lang.Iterable[e.TaggedRep]]
        it.headOption: Option[e.TaggedRep]
      }
    }

  implicit def unsafeRetrieveManyInEdge[
    E <: Singleton with AnyEdge { type Tpe <: EdgeTo[tvertex.Tpe] with ManyToSmth }
  ](e: E): RetrieveInEdge[E] = new RetrieveInEdge[E](e) {

      def apply(rep: tvertex.TaggedRep): e.tpe.In[e.TaggedRep] = {
        val it = rep.getEdges(Direction.IN, e.tpe.label).asInstanceOf[java.lang.Iterable[e.TaggedRep]]
        it.toList: List[e.TaggedRep]
      }
    }

}

class TVertex[VT <: AnyVertexType](val tpe: VT) 
  extends AnyTVertex { type Tpe = VT }

```


------

### Index

+ src
  + main
    + scala
      + bio4j
        + model
          + [Denotation.scala][main/scala/bio4j/model/Denotation.scala]
          + [Edge.scala][main/scala/bio4j/model/Edge.scala]
          + [EdgeType.scala][main/scala/bio4j/model/EdgeType.scala]
          + [Property.scala][main/scala/bio4j/model/Property.scala]
          + [Vertex.scala][main/scala/bio4j/model/Vertex.scala]
          + [VertexType.scala][main/scala/bio4j/model/VertexType.scala]
  + test
    + scala
      + bio4j
        + model
          + [edges.scala][test/scala/bio4j/model/edges.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
          + [properties.scala][test/scala/bio4j/model/properties.scala]
          + titan
            + [godsImplementation.scala][test/scala/bio4j/model/titan/godsImplementation.scala]
            + [GodsSchema.scala][test/scala/bio4j/model/titan/GodsSchema.scala]
            + [TEdge.scala][test/scala/bio4j/model/titan/TEdge.scala]
            + [TitanGodsTest.scala][test/scala/bio4j/model/titan/TitanGodsTest.scala]
            + [TVertex.scala][test/scala/bio4j/model/titan/TVertex.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]

[main/scala/bio4j/model/Denotation.scala]: ../../../../../main/scala/bio4j/model/Denotation.scala.md
[main/scala/bio4j/model/Edge.scala]: ../../../../../main/scala/bio4j/model/Edge.scala.md
[main/scala/bio4j/model/EdgeType.scala]: ../../../../../main/scala/bio4j/model/EdgeType.scala.md
[main/scala/bio4j/model/Property.scala]: ../../../../../main/scala/bio4j/model/Property.scala.md
[main/scala/bio4j/model/Vertex.scala]: ../../../../../main/scala/bio4j/model/Vertex.scala.md
[main/scala/bio4j/model/VertexType.scala]: ../../../../../main/scala/bio4j/model/VertexType.scala.md
[test/scala/bio4j/model/edges.scala]: ../edges.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: ../edgeTypes.scala.md
[test/scala/bio4j/model/properties.scala]: ../properties.scala.md
[test/scala/bio4j/model/titan/godsImplementation.scala]: godsImplementation.scala.md
[test/scala/bio4j/model/titan/GodsSchema.scala]: GodsSchema.scala.md
[test/scala/bio4j/model/titan/TEdge.scala]: TEdge.scala.md
[test/scala/bio4j/model/titan/TitanGodsTest.scala]: TitanGodsTest.scala.md
[test/scala/bio4j/model/titan/TVertex.scala]: TVertex.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: ../vertexTypes.scala.md
[test/scala/bio4j/model/vertices.scala]: ../vertices.scala.md