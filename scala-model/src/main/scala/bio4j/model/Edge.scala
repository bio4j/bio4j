package bio4j.model

trait AnyEdge extends Denotation[AnyEdgeType] { edge =>

  // NOTE: if I remove this from here type inference fails in tests. Most likely a bug
  type Tpe <: AnyEdgeType

  /* Source-Edge-Target types. Looks like we need to add them here to guide type inference; tests fail otherwise  */
  type SourceType = tpe.SourceType
  type TargetType = tpe.TargetType

  // type Out[X] = tpe.Out[X]
  // type In[X] = tpe.In[X]

  /* Get source/target from this representation */
  abstract class GetSource[S <: AnyVertex.ofType[SourceType]](val source: S) {
    def apply(edgeRep: edge.TaggedRep): source.TaggedRep
  }
  abstract class GetTarget[T <: AnyVertex.ofType[TargetType]](val target: T) {
    def apply(edgeRep: edge.TaggedRep): target.TaggedRep
  }

  implicit def edgeOps(edgeRep: edge.TaggedRep) = EdgeOps(edgeRep)
  case class   EdgeOps(edgeRep: edge.TaggedRep) {

    def source[S <: AnyVertex.ofType[SourceType]](implicit getter: GetSource[S]) = getter(edgeRep)

    def target[T <: AnyVertex.ofType[TargetType]](implicit getter: GetTarget[T]) = getter(edgeRep)
  }
}

class Edge[ET <: AnyEdgeType](val tpe: ET) 
  extends AnyEdge { type Tpe = ET }

object AnyEdge {
  type withSourceType[VT <: AnyVertexType] = AnyEdge { type SourceType = VT }
  type withTargetType[VT <: AnyVertexType] = AnyEdge { type TargetType = VT }
}
