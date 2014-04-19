package bio4j.model

/*
  Arities for rels. The point of this is that it lets you specify the right output type for when you get the outgoing edges of a given type from a vertex.
*/
sealed trait AnyArityVertex { 
  type VType <: AnyVertexType
  val  vType: VType
  def --(label: String) = SourceAndEdgeType(this)
}
sealed trait ArityVertex[VT <: AnyVertexType] extends AnyArityVertex { type VType = VT }
case class  one[V <: AnyVertexType](vType: V) extends ArityVertex[V]
case class many[V <: AnyVertexType](vType: V) extends ArityVertex[V]

case class SourceAndEdgeType[S <: AnyArityVertex](s: S) {
  def -->[T <: AnyArityVertex](t: T) = new EdgeType[S,T](s,t)
}

/*
  Witnesses of a sourceType/type adscription to an edge type are called rels. It's not that I love this name, but...
*/
trait AnyEdgeType {

  val label: String

  type InArity <: AnyArityVertex
  val inArity: InArity

  type SourceType = InArity#VType
  val sourceType: SourceType = inArity.vType

  type OutArity <: AnyArityVertex
  val outArity: OutArity

  type TargetType = OutArity#VType
  val targetType: TargetType = outArity.vType

}

class EdgeType[
  X <: AnyArityVertex, 
  Y <: AnyArityVertex
](val inArity: X, val outArity: Y) extends AnyEdgeType {

  type InArity = X
  type OutArity = Y

  // for this thing to work, you should extends this class by a _case_ class/object
  val label = this.toString 
}

object AnyEdgeType {
  // type SourceOf[RT <: AnyEdgeType] = { 
  //   type is[VT <: AnyVertexType] = AnyEdgeType { type SourceType = VT }
  // }
  // type TargetOf[RT <: AnyEdgeType] = { 
  //   type is[VT <: AnyVertexType] = AnyEdgeType { type TargetType = VT }
  // }

  implicit def edgeTypeOps[ET <: AnyEdgeType](et: ET) = EdgeTypeOps(et)
}

case class EdgeTypeOps[ET <: AnyEdgeType](val edgeType: ET) {

  def has[P <: AnyProperty](property: P) = EdgeTypeHasProperty(edgeType, property)

}
