package bio4j.model

import shapeless.record._

trait AnyRel {

  rel =>

  type RelType <: AnyRelType
  val relType: RelType

  /* Source-Edge-Target types */
  type SourceType = relType.SourceType
  val sourceType: SourceType = relType.sourceType

  type EdgeType = relType.EdgeType
  val edgeType: EdgeType = relType.edgeType

  type TargetType = relType.TargetType
  val targetType: TargetType = relType.targetType

  /* The raw underlying type representing this Rel */
  type Rep

  /* Tags `Rep` with this rel type */
  type TaggedRep = FieldType[rel.type, Rep]
  def ->>(r: Rep): FieldType[rel.type, Rep] = field[rel.type](r)


  /* Get source-edge-target from this representation */
  abstract case class GetSource[V <: Vertex[SourceType]](val v: V) {
    def apply(relRep: TaggedRep): v.TaggedRep
  }
  abstract case class GetEdge[E <: Edge[EdgeType]](val e: E) {
    def apply(relRep: TaggedRep): e.TaggedRep
  }
  abstract case class GetTarget[V <: Vertex[TargetType]](val v: V) {
    def apply(relRep: TaggedRep): v.TaggedRep
  }

  implicit class RelOps(val relRep: TaggedRep) {
    import AnyRelType._

    def getSource[V <: Vertex[SourceType]](implicit getter: GetSource[V]) = getter(relRep)

    def getEdge[E <: Edge[EdgeType]](implicit getter: GetEdge[E]) = getter(relRep)

    def getTarget[V <: Vertex[TargetType]](implicit getter: GetTarget[V]) = getter(relRep)
  }
}

case class Rel[RT <: AnyRelType](val relType: RT) extends AnyRel {

  rel =>

  type RelType = RT
}


/* TODO: do something with it:

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
*/
