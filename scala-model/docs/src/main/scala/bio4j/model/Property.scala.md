
```scala
package bio4j.model
```


Properties


```scala
// LiteralType has a label!
// TODO move to AnyDenotation
trait AnyProperty extends LiteralType {
  // NOTE: should this go somewhere else?
  type Rep
}
class Property[V]() extends AnyProperty with shapeless.FieldOf[V] { type Rep = V }

object AnyProperty {

  import SmthHasProperty._

  type VertexTag = AnyDenotationTag { type Denotation <: AnyVertex }
```

Right associative property getter for vertices

```scala
  implicit class PropertyOps[P <: AnyProperty](val p: P) {

    def %:[VT <: VertexTag](vr: VT)
      (implicit
        ev: PropertyOf[vr.DenotedType]#is[P],
        mkReader: VT => ReadFrom[VT]
      ): p.Rep = mkReader(vr).apply(p)

  }
```

For using `%:` you have to provide an implicit val of `ReadFrom`

```scala
  abstract class ReadFrom[VT <: VertexTag](val vt: VT) {
    // NOTE: can't add `PropertyOf[vt.DenotedType]#is` requirement here
    def apply[P <: AnyProperty](p: P): p.Rep
  }

}
```

Evidence that an arbitrary type `Smth` has property `Property`

```scala
trait SmthHasProperty {
  type Smth
  val smth: Smth

  type Property <: AnyProperty
  val property: Property
}

object SmthHasProperty {
  type PropertyOf[S] = { 
    type is[P <: AnyProperty] = SmthHasProperty { type Smth = S; type Property = P }
  }
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