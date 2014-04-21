
```scala
package bio4j.model
```


Witnesses of a sourceType/type adscription to an edge type.


```scala
trait AnyEdgeType {

  val label: String

  // TODO add an applicative/monad requirement here
  type In[X]
  type Out[X]

  type SourceType <: AnyVertexType
  val sourceType: SourceType

  type TargetType <: AnyVertexType
  val targetType: TargetType

}

trait EdgeType[
  S <: AnyVertexType, 
  T <: AnyVertexType
] extends AnyEdgeType {

  type SourceType = S
  type TargetType = T
```

for this thing to work, you should extend this class by a _case_ class/object

```scala
  val label = this.toString 
}

// TODO move to scalaz Id etc
case class Id[T](val t: T)

object AnyEdgeType {
  implicit def edgeTypeOps[ET <: AnyEdgeType](et: ET) = EdgeTypeOps(et)
}
```

Arities

```scala
sealed trait AnyManyToMany[S <: AnyVertexType, T <: AnyVertexType] extends EdgeType[S,T] {

    type In[X] = List[X]
    type Out[X] = List[X]
}
class ManyToMany[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends AnyManyToMany[S, T] {}

class OneToMany[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends EdgeType[S, T] {

    type In[X] = Option[X]
    type Out[X] = List[X]
  }

class ManyToOne[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends EdgeType[S, T] {

    type In[X] = List[X]
    type Out[X] = Option[X]
  }

class OneToOne[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends EdgeType[S, T] {

    type In[X] = Option[X]
    type Out[X] = Option[X]
  }
```

Arrows "DSL"

```scala
// NOTE what's the point of all this once we have included arities in edge types?
sealed trait AnyArityVertex { 
  type VType <: AnyVertexType
  val  vType: VType
}
sealed trait ArityVertex[VT <: AnyVertexType] extends AnyArityVertex { type VType = VT }
case class  one[V <: AnyVertexType](vType: V) extends ArityVertex[V] {
  def --(label: String) = OneToSmth(vType, label)
}
case class many[V <: AnyVertexType](vType: V) extends ArityVertex[V] {
  def --(label: String) = ManyToSmth(vType, label)
}

case class OneToSmth[ST <: AnyVertexType](st: ST, l: String) {
  def -->[T <: AnyArityVertex](t: T) = t match {
    case one(tt)  => new OneToOne(st,tt)  { override val label = l }
    case many(tt) => new OneToMany(st,tt) { override val label = l }
  }
}
case class ManyToSmth[ST <: AnyVertexType](st: ST, l: String) {
  def -->[TT <: AnyVertexType](t: one[TT]) = t match {
    case one(tt)  => new ManyToOne(st,tt)  { override val label = l }
  }
  def -->[TT <: AnyVertexType](t: many[TT]) = t match {
    case many(tt) => new ManyToMany(st,tt) { override val label = l }
  }
}
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
  + test
    + scala
      + bio4j
        + model
          + [properties.scala][test/scala/bio4j/model/properties.scala]
          + [edges.scala][test/scala/bio4j/model/edges.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]
          + titan
            + [TitanGodsTest.scala][test/scala/bio4j/model/titan/TitanGodsTest.scala]
            + [TEdge.scala][test/scala/bio4j/model/titan/TEdge.scala]
            + [TVertex.scala][test/scala/bio4j/model/titan/TVertex.scala]
            + [godsImplementation.scala][test/scala/bio4j/model/titan/godsImplementation.scala]
            + [godsSchema.scala][test/scala/bio4j/model/titan/godsSchema.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
  + main
    + scala
      + bio4j
        + model
          + [Denotation.scala][main/scala/bio4j/model/Denotation.scala]
          + [EdgeType.scala][main/scala/bio4j/model/EdgeType.scala]
          + [VertexType.scala][main/scala/bio4j/model/VertexType.scala]
          + [Vertex.scala][main/scala/bio4j/model/Vertex.scala]
          + [Edge.scala][main/scala/bio4j/model/Edge.scala]
          + [Property.scala][main/scala/bio4j/model/Property.scala]

[test/scala/bio4j/model/properties.scala]: ../../../../test/scala/bio4j/model/properties.scala.md
[test/scala/bio4j/model/edges.scala]: ../../../../test/scala/bio4j/model/edges.scala.md
[test/scala/bio4j/model/vertices.scala]: ../../../../test/scala/bio4j/model/vertices.scala.md
[test/scala/bio4j/model/titan/TitanGodsTest.scala]: ../../../../test/scala/bio4j/model/titan/TitanGodsTest.scala.md
[test/scala/bio4j/model/titan/TEdge.scala]: ../../../../test/scala/bio4j/model/titan/TEdge.scala.md
[test/scala/bio4j/model/titan/TVertex.scala]: ../../../../test/scala/bio4j/model/titan/TVertex.scala.md
[test/scala/bio4j/model/titan/godsImplementation.scala]: ../../../../test/scala/bio4j/model/titan/godsImplementation.scala.md
[test/scala/bio4j/model/titan/godsSchema.scala]: ../../../../test/scala/bio4j/model/titan/godsSchema.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: ../../../../test/scala/bio4j/model/vertexTypes.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: ../../../../test/scala/bio4j/model/edgeTypes.scala.md
[main/scala/bio4j/model/Denotation.scala]: Denotation.scala.md
[main/scala/bio4j/model/EdgeType.scala]: EdgeType.scala.md
[main/scala/bio4j/model/VertexType.scala]: VertexType.scala.md
[main/scala/bio4j/model/Vertex.scala]: Vertex.scala.md
[main/scala/bio4j/model/Edge.scala]: Edge.scala.md
[main/scala/bio4j/model/Property.scala]: Property.scala.md