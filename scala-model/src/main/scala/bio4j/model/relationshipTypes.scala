package bio4j.model

/*
  Arities for rels. The point of this is that it lets you specify the right output type for when you get the outgoing edges of a given type from a vertex.
*/
sealed trait AnyAritVertex { 
  type VType <: AnyVertexType
  val  vType: VType
  def --[E <: AnyEdgeType](e: E) = SourceAndEdgeType(this, e)
}
sealed trait AritVertex[VT <: AnyVertexType] extends AnyAritVertex { type VType = VT }
case class  one[V <: AnyVertexType](vType: V) extends AritVertex[V]
case class many[V <: AnyVertexType](vType: V) extends AritVertex[V]

case class SourceAndEdgeType[S <: AnyAritVertex, E <: AnyEdgeType](s: S, e: E) {
  def -->[T <: AnyAritVertex](t: T) = new RelType[S,E,T](s, e, t)
}

/*
  Witnesses of a sourceType/type adscription to an edge type are called rels. It's not that I love this name, but...
*/
trait AnyRelType {

  type InArity <: AnyAritVertex
  val inArity: InArity

  type SourceType = InArity#VType
  val sourceType: SourceType = inArity.vType

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType

  type OutArity <: AnyAritVertex
  val outArity: OutArity

  type TargetType = OutArity#VType
  val targetType: TargetType = outArity.vType

}

// object AnyRelType {
  // implicit def relTypeOps[R <: AnyRelType](rel: R): RelOps[R] = RelOps(rel)
// }

class RelType[
  X <: AnyAritVertex, 
  E <: AnyEdgeType, 
  Y <: AnyAritVertex
](val inArity: X, val edgeType: E, val outArity: Y) extends AnyRelType {

  type InArity = X
  type EdgeType = E
  type OutArity = Y

  // if needed add here implicits for witnessing that this rel goes from X to Y
}
