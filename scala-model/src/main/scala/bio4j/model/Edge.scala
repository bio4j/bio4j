package bio4j.model

import shapeless.record._

trait AnyEdge extends Tagged { self =>

  type Tpe <: AnyEdgeType
  val  tpe: Tpe

  /* Source-Edge-Target types */
  type SourceType = tpe.SourceType
  val sourceType: SourceType = tpe.sourceType

  type TargetType = tpe.TargetType
  val targetType: TargetType = tpe.targetType

  /* Get source/target from this representation */
  abstract case class GetSource[V <: AnyVertex.ofType[SourceType]](val v: V) {
    def apply(rep: TaggedRep): v.TaggedRep
  }
  abstract case class GetTarget[V <: AnyVertex.ofType[TargetType]](val v: V) {
    def apply(rep: TaggedRep): v.TaggedRep
  }

  implicit def edgeOps(rep: self.TaggedRep) = EdgeOps(rep)
  case class   EdgeOps(rep: self.TaggedRep) {

    def source[V <: AnyVertex.ofType[SourceType]](implicit getter: GetSource[V]) = getter(rep)

    def target[V <: AnyVertex.ofType[TargetType]](implicit getter: GetTarget[V]) = getter(rep)

  }

  /* Read a property from this representation */
  import SmthHasProperty._

  trait AnyGetProperty {
    type Property <: AnyProperty
    val p: Property

    def apply(rep: self.TaggedRep): Property#Rep
  }
  abstract class GetProperty[P <: AnyProperty](val p: P) extends AnyGetProperty {

    type Property = P
  }

  implicit def propertyOps(rep: self.TaggedRep): PropertyOps = PropertyOps(rep)
  case class   PropertyOps(rep: self.TaggedRep) {

    def get[P <: AnyProperty: PropertyOf[self.Tpe]#is](p: P)
      (implicit mkGetter: P => GetProperty[P]): P#Rep = {

        val g: GetProperty[P] = mkGetter(p)
        g(rep)
      }

  }

  /* If have just an independent getter for a particular property: */
  implicit def idGetter[P <: AnyProperty: PropertyOf[self.Tpe]#is](p: P)
      (implicit getter: GetProperty[P]) = getter

}

case class Edge[ET <: AnyEdgeType](val tpe: ET) 
  extends AnyEdge { type Tpe = ET }

object AnyEdge {
  type withSourceType[VT <: AnyVertexType] = AnyEdge { type SourceType = VT }
  type withTargetType[VT <: AnyVertexType] = AnyEdge { type TargetType = VT }
}
