
```scala
package bio4j.model

import shapeless.record._

trait AnyRel {

  rel =>

  type RelType <: AnyRelType
  val relType: RelType

  // node types
  type MySourceType = relType.SourceType
  val sourceType: MySourceType = relType.sourceType

  type MyTargetType = relType.TargetType
  val targetType: MyTargetType = relType.targetType

  type Rep
  def ->>(r: Rep): FieldType[rel.type, Rep] = field[rel.type](r)
}

case class Rel[RT <: AnyRelType](val relType: RT) extends AnyRel {

  rel =>

  type RelType = RT
}

trait AnySource {

  type Rel <: AnyRel
  val rel: Rel

  type Source <: AnyVertex { type VertexType <: Rel#MySourceType }
  val source: Source

  def apply(relRep: FieldType[rel.type, rel.Rep]): 
    FieldType[source.type, source.Rep]
}

abstract class Source[
  R <: AnyRel,
  S <: AnyVertex { type VertexType <: R#MySourceType }
  ](val rel: R, val source: S) extends AnySource {

    type Rel = R
    type Source = S
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