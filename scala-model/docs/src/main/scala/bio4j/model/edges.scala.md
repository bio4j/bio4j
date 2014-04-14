
```scala
package bio4j.model

trait AnyEdge {
  
  edge =>

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType

  // the raw underlying type representing this Edge
  type Rep

  import shapeless.record._
```

Tags a Rep with this edge type

```scala
  def ->>(v: Rep): FieldType[edge.type, Rep] = field[edge.type](v)

  // read a property from this representation
  abstract case class ReadProperty[P <: AnyProperty](val p: P) {

    def apply(edgeRep: FieldType[edge.type, Rep]): p.Rep
  }


  // this should go somewhere else
  case class PropertyOps(val edgeRep: FieldType[edge.type, Rep]) {

    import AnyEdgeTypeHasProperty.PropertyOf

    def get[P <: AnyProperty]
    (p: P)
    (implicit 
      witness: PropertyOf[edge.EdgeType]#is[P],
      retrieve: ReadProperty[P]
    ) = retrieve(edgeRep)
  }
  
  implicit def propertyOps[P <: AnyProperty]
    (edgeRep: FieldType[edge.type, Rep]): edge.PropertyOps = PropertyOps(edgeRep)
}
```


------

### Index

+ src
  + test
    + scala
      + bio4j
        + model
          + [propertyTypes.scala][test/scala/bio4j/model/propertyTypes.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]
          + [relationships.scala][test/scala/bio4j/model/relationships.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
  + main
    + scala
      + bio4j
        + model
          + [properties.scala][main/scala/bio4j/model/properties.scala]
          + [edges.scala][main/scala/bio4j/model/edges.scala]
          + [vertices.scala][main/scala/bio4j/model/vertices.scala]
          + [relationships.scala][main/scala/bio4j/model/relationships.scala]
          + [relationshipTypes.scala][main/scala/bio4j/model/relationshipTypes.scala]
          + [vertexTypes.scala][main/scala/bio4j/model/vertexTypes.scala]
          + [edgeTypes.scala][main/scala/bio4j/model/edgeTypes.scala]

[test/scala/bio4j/model/propertyTypes.scala]: ../../../../test/scala/bio4j/model/propertyTypes.scala.md
[test/scala/bio4j/model/vertices.scala]: ../../../../test/scala/bio4j/model/vertices.scala.md
[test/scala/bio4j/model/relationships.scala]: ../../../../test/scala/bio4j/model/relationships.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: ../../../../test/scala/bio4j/model/vertexTypes.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: ../../../../test/scala/bio4j/model/edgeTypes.scala.md
[main/scala/bio4j/model/properties.scala]: properties.scala.md
[main/scala/bio4j/model/edges.scala]: edges.scala.md
[main/scala/bio4j/model/vertices.scala]: vertices.scala.md
[main/scala/bio4j/model/relationships.scala]: relationships.scala.md
[main/scala/bio4j/model/relationshipTypes.scala]: relationshipTypes.scala.md
[main/scala/bio4j/model/vertexTypes.scala]: vertexTypes.scala.md
[main/scala/bio4j/model/edgeTypes.scala]: edgeTypes.scala.md