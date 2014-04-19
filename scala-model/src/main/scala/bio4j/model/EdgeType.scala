package bio4j.model

/*
  Arities for rels. The point of this is that it lets you specify the right output type for when you get the outgoing edges of a given type from a vertex.
*/
// sealed trait AnyArityVertex { 
//   type VType <: AnyVertexType
//   val  vType: VType
//   def --(label: String) = SourceAndEdgeType(this)
// }
// sealed trait ArityVertex[VT <: AnyVertexType] extends AnyArityVertex { type VType = VT }
// case class  one[V <: AnyVertexType](vType: V) extends ArityVertex[V]
// case class many[V <: AnyVertexType](vType: V) extends ArityVertex[V]

// case class SourceAndEdgeType[S <: AnyArityVertex](s: S) {
//   def -->[T <: AnyArityVertex](t: T) = new EdgeType[S,T](s,t)
// }

/*
  Witnesses of a sourceType/type adscription to an edge type are called rels. It's not that I love this name, but...
*/
trait AnyEdgeType {

  val label: String

  type In[_]
  type Out[_]

  type SourceType <: AnyVertexType
  val sourceType: SourceType

  type TargetType <: AnyVertexType
  val targetType: TargetType

}

abstract class EdgeType[
  I[_], O[_],
  S <: AnyVertexType, 
  T <: AnyVertexType
](val sourceType: S, val targetType: T) extends AnyEdgeType {

  // type In = I
  // type Out = O

  type SourceType = S
  type TargetType = T

  // for this thing to work, you should extends this class by a _case_ class/object
  val label = this.toString 
}

object AnyEdgeType {
  implicit def edgeTypeOps[ET <: AnyEdgeType](et: ET) = EdgeTypeOps(et)
}

case class EdgeTypeOps[ET <: AnyEdgeType](val edgeType: ET) {

  def has[P <: AnyProperty](property: P) = EdgeTypeHasProperty(edgeType, property)

}

/* Witness for an Edge of type E having a property of type P */
case class EdgeTypeHasProperty [
  E <: AnyEdgeType,
  P <: AnyProperty
](val smth: E,
  val property: P) extends SmthHasProperty {
  type Smth = E
  type Property = P
}
