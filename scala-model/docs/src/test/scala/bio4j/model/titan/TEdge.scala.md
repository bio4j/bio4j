
```scala
package bio4j.model.test.titan

import bio4j.model._

trait AnyTEdge extends AnyEdge { tedge =>

  type Rep = com.thinkaurelius.titan.core.TitanEdge

  type Source <: AnyVertex.ofType[Tpe#SourceType]
  val source: Source

  type Target <: AnyVertex.ofType[Tpe#TargetType]
  val target: Target
```

Getting a property from any TitanEdge

```scala
  import SmthHasProperty._
  implicit def unsafeGetProperty[P <: AnyProperty: PropertyOf[this.Tpe]#is](p: P) = 
    new GetProperty[P](p) {
      def apply(rep: tedge.TaggedRep): p.Rep = rep.getProperty[p.Rep](p.label)
    }

  import com.tinkerpop.blueprints.Direction
```

Getting source vertex

```scala
  implicit object sourceGetter extends GetSource[Source](source) {
    def apply(rep: tedge.TaggedRep): source.TaggedRep = 
      source ->> rep.getVertex(Direction.OUT).asInstanceOf[source.Rep]
  }
```

Getting target vertex

```scala
  implicit object targetGetter extends GetTarget[Target](target) {
    def apply(rep: tedge.TaggedRep): target.TaggedRep = 
      target ->> rep.getVertex(Direction.IN).asInstanceOf[target.Rep]
  }

}

class TEdge[
    ET <: AnyEdgeType, 
    S <: AnyVertex.ofType[ET#SourceType], 
    T <: AnyVertex.ofType[ET#TargetType]
  ](val source: S, val tpe: ET, val target: T) extends AnyTEdge { 
    type Source = S
    type Tpe = ET 
    type Target = T
  }

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