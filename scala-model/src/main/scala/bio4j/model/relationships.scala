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