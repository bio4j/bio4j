package bio4j.model

/*
  Arities for rels. The point of this is that it lets you specify the right output type for when you get the outgoing edges of a given type from a vertex.
*/
sealed trait AnyArityVertex { 
  type VType <: AnyVertexType
  val  vType: VType
  def --[E <: AnyEdgeType](e: E) = SourceAndEdgeType(this, e)
}
sealed trait ArityVertex[VT <: AnyVertexType] extends AnyArityVertex { type VType = VT }
case class  one[V <: AnyVertexType](vType: V) extends ArityVertex[V]
case class many[V <: AnyVertexType](vType: V) extends ArityVertex[V]

case class SourceAndEdgeType[S <: AnyArityVertex, E <: AnyEdgeType](s: S, e: E) {
  def -->[T <: AnyArityVertex](t: T) = new RelType[S,E,T](s, e, t)
}

/*
  Witnesses of a sourceType/type adscription to an edge type are called rels. It's not that I love this name, but...
*/
trait AnyRelType {

  type InArity <: AnyArityVertex
  val inArity: InArity

  type SourceType = InArity#VType
  val sourceType: SourceType = inArity.vType

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType

  type OutArity <: AnyArityVertex
  val outArity: OutArity

  type TargetType = OutArity#VType
  val targetType: TargetType = outArity.vType

}

// object AnyRelType {
  // implicit def relTypeOps[R <: AnyRelType](rel: R): RelOps[R] = RelOps(rel)
// }

class RelType[
  X <: AnyArityVertex, 
  E <: AnyEdgeType, 
  Y <: AnyArityVertex
](val inArity: X, val edgeType: E, val outArity: Y) extends AnyRelType {

  type InArity = X
  type EdgeType = E
  type OutArity = Y

  // implicit val s = SourceOf[this.type]#is[this.SourceType]
}

object AnyRelType {
  type SourceOf[RT <: AnyRelType] = { 
    type is[VT <: AnyVertexType] = AnyRelType { type SourceType = VT }
  }
  type EdgeOf[RT <: AnyRelType] = { 
    type is[ET <: AnyEdgeType] = AnyRelType { type EdgeType = ET }
  }
  type TargetOf[RT <: AnyRelType] = { 
    type is[VT <: AnyVertexType] = AnyRelType { type TargetType = VT }
  }
}
