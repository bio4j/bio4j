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
  def -->[T <: AnyArityVertex](t: T) = new RelType[S,T](s,t)
}

/*
  Witnesses of a sourceType/type adscription to an edge type are called rels. It's not that I love this name, but...
*/
trait AnyRelType {

  type InArity <: AnyArityVertex
  val inArity: InArity

  type SourceType = InArity#VType
  val sourceType: SourceType = inArity.vType

  val label: String

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
  Y <: AnyArityVertex
](val inArity: X, val outArity: Y) extends AnyRelType {

  type InArity = X
  type OutArity = Y

  // for this thing to work, you should extends this class by a _case_ class/object
  val label = this.toString 
}

object AnyRelType {
  // type SourceOf[RT <: AnyRelType] = { 
  //   type is[VT <: AnyVertexType] = AnyRelType { type SourceType = VT }
  // }
  // type TargetOf[RT <: AnyRelType] = { 
  //   type is[VT <: AnyVertexType] = AnyRelType { type TargetType = VT }
  // }

  implicit def relTypeOps[R <: AnyRelType](r: R) = RelTypeOps(r)
}

case class RelTypeOps[R <: AnyRelType](val relType: R) {

  def has[P <: AnyProperty](property: P) = RelTypeHasProperty(relType, property)

}
