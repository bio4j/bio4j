
```scala
package bio4j.model
```


Witnesses of a sourceType/type adscription to an edge type.


```scala
trait AnyEdgeType {

  val label: String

  // TODO add an applicative/monad requirement here
  type In[+X]
  type Out[+X]

  type SourceType <: AnyVertexType
  val sourceType: SourceType

  type TargetType <: AnyVertexType
  val targetType: TargetType

}

// TODO move to scalaz Id etc
case class Id[T](val t: T)

object AnyEdgeType {
  implicit def edgeTypeOps[ET <: AnyEdgeType](et: ET) = EdgeTypeOps(et)
}

trait EdgeFrom[S <: AnyVertexType] extends AnyEdgeType { type SourceType = S }
trait   EdgeTo[T <: AnyVertexType] extends AnyEdgeType { type TargetType = T }

// trait EdgeType[S <: AnyVertexType, T <: AnyVertexType] extends EdgeFrom[S] with EdgeTo[T]

```

Arities

```scala
trait SmthToMany extends AnyEdgeType { type Out[+X] = List[X] }
trait  SmthToOne extends AnyEdgeType { type Out[+X] = Option[X] }
trait ManyToSmth extends AnyEdgeType { type  In[+X] = List[X] }
trait  OneToSmth extends AnyEdgeType { type  In[+X] = Option[X] }

class ManyToMany[S <: AnyVertexType, T <: AnyVertexType]
  (val sourceType: S, val label: String, val targetType: T) 
    extends EdgeFrom[S] with EdgeTo[T] with ManyToSmth with SmthToMany

class OneToMany[S <: AnyVertexType, T <: AnyVertexType]
  (val sourceType: S, val label: String, val targetType: T) 
    extends EdgeFrom[S] with EdgeTo[T] with OneToSmth with SmthToMany

class ManyToOne[S <: AnyVertexType, T <: AnyVertexType]
  (val sourceType: S, val label: String, val targetType: T) 
    extends EdgeFrom[S] with EdgeTo[T] with ManyToSmth with SmthToOne

class OneToOne[S <: AnyVertexType, T <: AnyVertexType]
  (val sourceType: S, val label: String, val targetType: T) 
    extends EdgeFrom[S] with EdgeTo[T] with OneToSmth with SmthToOne
```

Properties

```scala
case class EdgeTypeOps[ET <: AnyEdgeType](val edgeType: ET) {
  def has[P <: AnyProperty](property: P) = EdgeTypeHasProperty(edgeType, property)
}
```

Witness for an Edge of type E having a property of type P

```scala
case class EdgeTypeHasProperty [
  E <: AnyEdgeType,
  P <: AnyProperty
](val smth: E,
  val property: P) extends SmthHasProperty {
  type Smth = E
  type Property = P
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