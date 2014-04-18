
```scala
package bio4j.model

import shapeless.record._

trait AnyRel {

  rel =>

  type RelType <: AnyRelType
  val relType: RelType
```

Source-Edge-Target types

```scala
  type SourceType = relType.SourceType
  val sourceType: SourceType = relType.sourceType

  type EdgeType = relType.EdgeType
  val edgeType: EdgeType = relType.edgeType

  type TargetType = relType.TargetType
  val targetType: TargetType = relType.targetType
```

The raw underlying type representing this Rel

```scala
  type Rep
```

Tags `Rep` with this rel type

```scala
  type TaggedRep = FieldType[rel.type, Rep]
  def ->>(r: Rep): FieldType[rel.type, Rep] = field[rel.type](r)
```

Get source-edge-target from this representation

```scala
  abstract case class GetSource[V <: Vertex[SourceType]](val v: V) {
    def apply(relRep: TaggedRep): v.TaggedRep
  }
  abstract case class GetEdge[E <: Edge[EdgeType]](val e: E) {
    def apply(relRep: TaggedRep): e.TaggedRep
  }
  abstract case class GetTarget[V <: Vertex[TargetType]](val v: V) {
    def apply(relRep: TaggedRep): v.TaggedRep
  }

  implicit class RelOps(val relRep: TaggedRep) {
    import AnyRelType._

    def getSource[V <: Vertex[SourceType]](implicit getter: GetSource[V]) = getter(relRep)

    def getEdge[E <: Edge[EdgeType]](implicit getter: GetEdge[E]) = getter(relRep)

    def getTarget[V <: Vertex[TargetType]](implicit getter: GetTarget[V]) = getter(relRep)
  }
}

case class Rel[RT <: AnyRelType](val relType: RT) extends AnyRel {

  rel =>

  type RelType = RT
}
```

TODO: do something with it:

trait AnySource {

  type Rel <: AnyRel
  val rel: Rel

  type Source <: AnyVertex { type VertexType <: Rel#SourceType }
  val source: Source

  def apply(relRep: FieldType[rel.type, rel.Rep]): 
 FieldType[source.type, source.Rep]
}

abstract class Source[
  R <: AnyRel,
  S <: AnyVertex { type VertexType <: R#SourceType }
  ](val rel: R, val source: S) extends AnySource {

 type Rel = R
 type Source = S
  }


------

### Index

+ src
  + main
    + scala
      + bio4j
        + model
          + [edges.scala][main/scala/bio4j/model/edges.scala]
          + [edgeTypes.scala][main/scala/bio4j/model/edgeTypes.scala]
          + [properties.scala][main/scala/bio4j/model/properties.scala]
          + [relationships.scala][main/scala/bio4j/model/relationships.scala]
          + [relationshipTypes.scala][main/scala/bio4j/model/relationshipTypes.scala]
          + [vertexTypes.scala][main/scala/bio4j/model/vertexTypes.scala]
          + [vertices.scala][main/scala/bio4j/model/vertices.scala]
  + test
    + scala
      + bio4j
        + model
          + [edges.scala][test/scala/bio4j/model/edges.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
          + [properties.scala][test/scala/bio4j/model/properties.scala]
          + [rels.scala][test/scala/bio4j/model/rels.scala]
          + [relTypes.scala][test/scala/bio4j/model/relTypes.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]

[main/scala/bio4j/model/edges.scala]: edges.scala.md
[main/scala/bio4j/model/edgeTypes.scala]: edgeTypes.scala.md
[main/scala/bio4j/model/properties.scala]: properties.scala.md
[main/scala/bio4j/model/relationships.scala]: relationships.scala.md
[main/scala/bio4j/model/relationshipTypes.scala]: relationshipTypes.scala.md
[main/scala/bio4j/model/vertexTypes.scala]: vertexTypes.scala.md
[main/scala/bio4j/model/vertices.scala]: vertices.scala.md
[test/scala/bio4j/model/edges.scala]: ../../../../test/scala/bio4j/model/edges.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: ../../../../test/scala/bio4j/model/edgeTypes.scala.md
[test/scala/bio4j/model/properties.scala]: ../../../../test/scala/bio4j/model/properties.scala.md
[test/scala/bio4j/model/rels.scala]: ../../../../test/scala/bio4j/model/rels.scala.md
[test/scala/bio4j/model/relTypes.scala]: ../../../../test/scala/bio4j/model/relTypes.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: ../../../../test/scala/bio4j/model/vertexTypes.scala.md
[test/scala/bio4j/model/vertices.scala]: ../../../../test/scala/bio4j/model/vertices.scala.md