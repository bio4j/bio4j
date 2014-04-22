
```scala
package bio4j.model

trait AnyEdge extends Denotation[AnyEdgeType] { edge =>

  // NOTE: if I remove this from here type inference fails. Most likely a bug
  type Tpe <: AnyEdgeType
```

Get source/target from this representation

```scala
  abstract class GetSource[S <: AnyVertex.ofType[Tpe#SourceType]](val source: S) {
    def apply(edgeRep: edge.TaggedRep): source.TaggedRep
  }
  abstract class GetTarget[T <: AnyVertex.ofType[Tpe#TargetType]](val target: T) {
    def apply(edgeRep: edge.TaggedRep): target.TaggedRep
  }

  implicit def edgeOps(edgeRep: edge.TaggedRep) = EdgeOps(edgeRep)
  case class   EdgeOps(edgeRep: edge.TaggedRep) {

    def source[S <: AnyVertex.ofType[Tpe#SourceType]](implicit getter: GetSource[S]) = getter(edgeRep)

    def target[T <: AnyVertex.ofType[Tpe#TargetType]](implicit getter: GetTarget[T]) = getter(edgeRep)

  }

}

class Edge[ET <: AnyEdgeType](val tpe: ET) 
  extends AnyEdge { type Tpe = ET }

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

[main/scala/bio4j/model/Denotation.scala]: Denotation.scala.md
[main/scala/bio4j/model/Edge.scala]: Edge.scala.md
[main/scala/bio4j/model/EdgeType.scala]: EdgeType.scala.md
[main/scala/bio4j/model/Property.scala]: Property.scala.md
[main/scala/bio4j/model/Vertex.scala]: Vertex.scala.md
[main/scala/bio4j/model/VertexType.scala]: VertexType.scala.md
[test/scala/bio4j/model/edges.scala]: ../../../../test/scala/bio4j/model/edges.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: ../../../../test/scala/bio4j/model/edgeTypes.scala.md
[test/scala/bio4j/model/properties.scala]: ../../../../test/scala/bio4j/model/properties.scala.md
[test/scala/bio4j/model/titan/godsImplementation.scala]: ../../../../test/scala/bio4j/model/titan/godsImplementation.scala.md
[test/scala/bio4j/model/titan/GodsSchema.scala]: ../../../../test/scala/bio4j/model/titan/GodsSchema.scala.md
[test/scala/bio4j/model/titan/TEdge.scala]: ../../../../test/scala/bio4j/model/titan/TEdge.scala.md
[test/scala/bio4j/model/titan/TitanGodsTest.scala]: ../../../../test/scala/bio4j/model/titan/TitanGodsTest.scala.md
[test/scala/bio4j/model/titan/TVertex.scala]: ../../../../test/scala/bio4j/model/titan/TVertex.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: ../../../../test/scala/bio4j/model/vertexTypes.scala.md
[test/scala/bio4j/model/vertices.scala]: ../../../../test/scala/bio4j/model/vertices.scala.md