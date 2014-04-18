
```scala
package bio4j.model

trait AnyEdge {
  
  edge =>

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType
```

The raw underlying type representing this Edge

```scala
  type Rep
```

Tags `Rep` with this edge type

```scala
  import shapeless.record._

  type TaggedRep = FieldType[edge.type, Rep]
  def ->>(e: Rep): FieldType[edge.type, Rep] = field[edge.type](e)
```

Read a property from this representation

```scala
  abstract case class ReadProperty[P <: AnyProperty](val p: P) {
    def apply(edgeRep: TaggedRep): p.Rep
  }

  // TODO: this should go somewhere else
  case class PropertyOps(val edgeRep: TaggedRep) {

    import AnyEdgeTypeHasProperty.PropertyOf

    def get[P <: AnyProperty](p: P)
      (implicit 
        witness: PropertyOf[edge.EdgeType]#is[P],
        retrieve: ReadProperty[P]
      ) = retrieve(edgeRep)
  }
  
  implicit def propertyOps(edgeRep: TaggedRep): edge.PropertyOps = PropertyOps(edgeRep)
}

abstract class Edge[ET <: AnyEdgeType](val edgeType: ET)
  extends AnyEdge { type EdgeType = ET }

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