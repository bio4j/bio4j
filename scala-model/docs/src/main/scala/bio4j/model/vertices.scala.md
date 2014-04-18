
```scala
package bio4j.model
```


`AnyVertex` defines a denotation of the corresponding `VertexType`.

Instances are modeled as instances of other type tagged with the singleton type of a `Vertex`. For example, an instance of a vertex of type `User` when stored/represented by a `Neo4jNode` is going to be something of type `FieldType[user.type, Neo4jNode]`  where `user.type <: AnyVertex { type VertexType = User.type; type Rep = Neo4jNode }`.

They are designed to be compatible with shapeless records (maybe, we'll see).


```scala
trait AnyVertex {
  
  vertex =>

  type VertexType <: AnyVertexType
  val vertexType: VertexType
```

The raw underlying type representing this vertex

```scala
  type Rep
```

Tags `Rep` with this vertex type

```scala
  import shapeless.record._

  type TaggedRep = FieldType[vertex.type, Rep]
  def ->>(v: Rep): FieldType[vertex.type, Rep] = field[vertex.type](v)
```

Read a property from this representation

```scala
  abstract case class ReadProperty[P <: AnyProperty](val p: P) {

    def apply(vRep: TaggedRep): p.Rep
  }

  import AnyVertexTypeHasProperty.PropertyOf

  // TODO: this should go somewhere else
  case class PropertyOps(val vRep: TaggedRep) {

    def get[P <: AnyProperty](p: P)
      (implicit 
        witness: PropertyOf[vertex.VertexType]#is[P],
        retrieve: ReadProperty[P]
      ) = retrieve(vRep)
  }
  
  implicit def propertyOps(vRep: TaggedRep): vertex.PropertyOps = PropertyOps(vRep)

  ///////////////////////////////////////////

  abstract case class RetrieveRel[R <: AnyRel](val r: R) {
    def apply(vRep: TaggedRep): r.Rep
  }

  implicit class RelOps(val vRep: TaggedRep) {
    def out[
      E <: AnyEdgeType, 
      T <: AnyVertexType,
      RT <: RelType[AritVertex[VertexType], E, AritVertex[T]],
      R <: Rel[RT]
    ](r: R)(implicit retrieve: RetrieveRel[R]) = retrieve(vRep)
  }
}

abstract class Vertex[VT <: AnyVertexType](val vertexType: VT) extends AnyVertex {

  type VertexType = VT
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