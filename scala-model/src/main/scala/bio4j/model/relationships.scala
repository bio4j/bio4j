package bio4j.model

import shapeless.record._

trait AnyRel {

  rel =>

  type RelType <: AnyRelType
  val relType: RelType

  /* Node types */
  type SourceType = relType.SourceType
  val sourceType: SourceType = relType.sourceType

  type TargetType = relType.TargetType
  val targetType: TargetType = relType.targetType

  /* The raw underlying type representing this Edge */
  type Rep

  /* Tags `Rep` with this rel type */
  type RelRep = FieldType[rel.type, Rep]
  def ->>(r: Rep): FieldType[rel.type, Rep] = field[rel.type](r)
}

case class Rel[RT <: AnyRelType](val relType: RT) extends AnyRel {

  rel =>

  type RelType = RT
}

trait AnySource {

  type Rel <: AnyRel
  val rel: Rel

  type Source <: AnyVertex { type VertexType <: Rel#SourceType }
  val source: Source

  def apply(relRep: FieldType[rel.type, rel.Rep]): 
    FieldType[source.type, source.Rep]
}

abstract class Source[
  R <: AnyRel,
  S <: AnyVertex { type VertexType <: R#SourceType }
  ](val rel: R, val source: S) extends AnySource {

    type Rel = R
    type Source = S
  }
