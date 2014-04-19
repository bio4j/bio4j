
```scala
package bio4j.model
```


Witnesses of a sourceType/type adscription to an edge type are called rels. It's not that I love this name, but...


```scala
trait AnyEdgeType {

  val label: String

  type In[_]
  type Out[_]

  type SourceType <: AnyVertexType
  val sourceType: SourceType

  type TargetType <: AnyVertexType
  val targetType: TargetType

}

abstract class EdgeType[
  I[_], O[_],
  S <: AnyVertexType, 
  T <: AnyVertexType
] extends AnyEdgeType {

  type In[X] = I[X]
  type Out[X] = O[X]

  type SourceType = S
  type TargetType = T

  // for this thing to work, you should extend this class by a _case_ class/object
  val label = this.toString 
}

case class Id[T](val t: T)

object AnyEdgeType {
  implicit def edgeTypeOps[ET <: AnyEdgeType](et: ET) = EdgeTypeOps(et)
  implicit def elimId[T](idt: Id[T]) = idt.t
}
```

Arities

```scala
class ManyToMany[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends EdgeType[List, List, S, T]

class OneToMany[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends EdgeType[Option, List, S, T]

class ManyToOne[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends EdgeType[List, Option, S, T]

class OneToOne[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends EdgeType[Option, Option, S, T]
```

Arrows "DSL"

```scala
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
  + main
    + scala
      + bio4j
        + model
          + [Edge.scala][main/scala/bio4j/model/Edge.scala]
          + [EdgeType.scala][main/scala/bio4j/model/EdgeType.scala]
          + [Property.scala][main/scala/bio4j/model/Property.scala]
          + [Tagged.scala][main/scala/bio4j/model/Tagged.scala]
          + [Vertex.scala][main/scala/bio4j/model/Vertex.scala]
          + [VertexType.scala][main/scala/bio4j/model/VertexType.scala]
  + test
    + scala
      + bio4j
        + model
          + [edges.scala][test/scala/bio4j/model/edges.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
          + [properties.scala][test/scala/bio4j/model/properties.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]

[main/scala/bio4j/model/Edge.scala]: Edge.scala.md
[main/scala/bio4j/model/EdgeType.scala]: EdgeType.scala.md
[main/scala/bio4j/model/Property.scala]: Property.scala.md
[main/scala/bio4j/model/Tagged.scala]: Tagged.scala.md
[main/scala/bio4j/model/Vertex.scala]: Vertex.scala.md
[main/scala/bio4j/model/VertexType.scala]: VertexType.scala.md
[test/scala/bio4j/model/edges.scala]: ../../../../test/scala/bio4j/model/edges.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: ../../../../test/scala/bio4j/model/edgeTypes.scala.md
[test/scala/bio4j/model/properties.scala]: ../../../../test/scala/bio4j/model/properties.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: ../../../../test/scala/bio4j/model/vertexTypes.scala.md
[test/scala/bio4j/model/vertices.scala]: ../../../../test/scala/bio4j/model/vertices.scala.md