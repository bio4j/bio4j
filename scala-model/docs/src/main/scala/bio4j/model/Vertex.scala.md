
```scala
package bio4j.model
```


`AnyVertex` defines a denotation of the corresponding `VertexType`.

Instances are modeled as instances of other type tagged with the singleton type of a `Vertex`. For example, an instance of a self of type `User` when stored/represented by a `Neo4jNode` is going to be something of type `FieldType[user.type, Neo4jNode]`  where `user.type <: AnyVertex { type VertexType = User.type; type Rep = Neo4jNode }`.

They are designed to be compatible with shapeless records (maybe, we'll see).


```scala
trait AnyVertex extends Tagged { self =>

  type Tpe <: AnyVertexType
  val tpe: Tpe
```

Read a property from this representation

```scala
  abstract case class GetProperty[P <: AnyProperty](val p: P) {
    def apply(rep: self.TaggedRep): p.Rep
  }

  case class PropertyOps(rep: self.TaggedRep) {
    import SmthHasProperty._
    def get[P <: AnyProperty: PropertyOf[self.Tpe]#is](p: P)
      (implicit getter: GetProperty[P]) = getter(rep)
  }

  implicit def getProperty(rep: self.TaggedRep) = PropertyOps(rep)


  abstract case class RetrieveEdge[E <: AnyEdge](val r: E) {
    def apply(vRep: TaggedRep): r.tpe.Out[r.Rep]
  }

  implicit class EdgeOps(val vRep: TaggedRep) {
    def out[E <: AnyEdge.withSourceType[self.Tpe]]
      (e: E)(implicit retrieve: RetrieveEdge[E]) = retrieve(vRep)
  }

}

object AnyVertex {
  type ofType[VT <: AnyVertexType] = AnyVertex { type Tpe = VT }
}

abstract class Vertex[VT <: AnyVertexType](val tpe: VT) extends AnyVertex {

  type Tpe = VT
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