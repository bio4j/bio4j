package bio4j.model

import shapeless.record._

trait AnyEdge extends Tagged { self =>

  type Tpe <: AnyEdgeType
  val tpe: Tpe

  /* Source-Edge-Target types */
  type SourceType = tpe.SourceType
  val sourceType: SourceType = tpe.sourceType

  type TargetType = tpe.TargetType
  val targetType: TargetType = tpe.targetType

  /* Get source-edge-target from this representation */
  abstract case class GetSource[V <: AnyVertex.ofType[SourceType]](val v: V) {
    def apply(rep: TaggedRep): v.TaggedRep
  }
  abstract case class GetTarget[V <: AnyVertex.ofType[TargetType]](val v: V) {
    def apply(rep: TaggedRep): v.TaggedRep
  }

  implicit class EdgeOps(val rep: TaggedRep) {
    import AnyEdgeType._
    def source[V <: AnyVertex.ofType[SourceType]](implicit getter: GetSource[V]) = getter(rep)
    def target[V <: AnyVertex.ofType[TargetType]](implicit getter: GetTarget[V]) = getter(rep)
  }

  abstract case class GetProperty[P <: AnyProperty](val p: P) {
    def apply(rep: self.TaggedRep): p.Rep
  }

  case class PropertyOps(rep: self.TaggedRep) {
    import SmthHasProperty._
    def get[P <: AnyProperty: PropertyOf[self.Tpe]#is](p: P)
      (implicit getter: GetProperty[P]) = getter(rep)
  }

  implicit def getProperty(rep: self.TaggedRep) = PropertyOps(rep)

}

case class Edge[ET <: AnyEdgeType](val tpe: ET) extends AnyEdge { self =>
  type Tpe = ET
}

object AnyEdge {
  type withSourceType[VT <: AnyVertexType] = AnyEdge { type SourceType = VT }
  type withTargetType[VT <: AnyVertexType] = AnyEdge { type TargetType = VT }
}
