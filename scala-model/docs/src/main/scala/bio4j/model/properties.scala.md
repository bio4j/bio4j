
```scala
package bio4j.model
```


Properties


```scala
import shapeless.FieldOf
```


this has a label!


```scala
trait AnyProperty extends LiteralType {

  // should this go somewhere else?
  type Rep
}
class Property[V]() extends AnyProperty with FieldOf[V] {

  type Rep = V
}
```


witness for an Edge of type E having a property of type P


```scala
trait AnyEdgeTypeHasProperty {

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType

  type Property <: AnyProperty
  val property: Property
}

case class EdgeTypeHasProperty 
[
  E <: AnyEdgeType,
  P <: AnyProperty
](
  val edgeType: E,
  val property: P
) 
extends AnyEdgeTypeHasProperty {

  type EdgeType = E
  type Property = P
}

object AnyEdgeTypeHasProperty {

  type PropertyOf[V <: AnyEdgeType] = { 
    type is[P <: AnyProperty] = AnyEdgeTypeHasProperty { type EdgeType = V; type Property = P }
  }
}
```


witness for a vertex type declaring a property of the given type


```scala
trait AnyVertexTypeHasProperty {

  type VertexType <: AnyVertexType
  val vertexType: VertexType

  type Property <: AnyProperty
  val property: Property
}

case class VertexTypeHasProperty 
[
  V <: AnyVertexType,
  P <: AnyProperty
](
  val vertexType: V,
  val property: P
) 
extends AnyVertexTypeHasProperty {

  type VertexType = V
  type Property = P
}

object AnyVertexTypeHasProperty {

  type PropertyOf[V <: AnyVertexType] = { 
    type is[P <: AnyProperty] = AnyVertexTypeHasProperty { type VertexType = V; type Property = P }
  }
}
```


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