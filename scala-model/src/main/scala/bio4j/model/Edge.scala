package bio4j.model

import shapeless.record._

trait AnyEdge extends Tagged { self =>

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType

  /* Source-Edge-Target types */
  type SourceType = edgeType.SourceType
  val sourceType: SourceType = edgeType.sourceType

  type TargetType = edgeType.TargetType
  val targetType: TargetType = edgeType.targetType

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


  /* Read a property from this representation */
  abstract case class ReadProperty[P <: AnyProperty](val p: P) {
    def apply(rep: TaggedRep): p.Rep
  }

  implicit class PropertyOps(val rep: TaggedRep) {
    import AnyEdgeTypeHasProperty.PropertyOf
    def get[P <: AnyProperty: PropertyOf[self.EdgeType]#is](p: P)
      (implicit 
        retrieve: ReadProperty[P]
      ) = retrieve(rep)
  }
}

case class Edge[ET <: AnyEdgeType](val edgeType: ET) extends AnyEdge { self =>
  type EdgeType = ET
}
