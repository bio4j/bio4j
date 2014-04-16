package bio4j.model

/*
  Arities for rels. The point of this is that it lets you specify the right output type for when you get the outgoing edges of a given type from a vertex.
*/
sealed trait AnyAritVertex { 
  type VType <: AnyVertexType
  val  vType: VType
  def --[E <: AnyEdgeType](e: E) = SourceAndEdgeType(this, e)
}
case class  one[V <: AnyVertexType](vType: V) extends AnyAritVertex { type VType = V }
case class many[V <: AnyVertexType](vType: V) extends AnyAritVertex { type VType = V }

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

/*
  ### creating rels

  We need to start with one vertex type, then an edge type and then another vertex type. This involves 
*/

/*
object DeclareRelTypes {

  /* You can write `many(user) -- memberOf --> one(org)` */

  implicit class oneWithEdge[V <: AnyVertexType](v: One[V]) {
    def --[E <: AnyEdgeType](edgeType: E) = oneToSmth(v, edgeType)
  }

  case class oneToSmth[S <: AnyVertexType, E <: AnyEdgeType](sourceType: S, edgeType: E) {
    def -->[T <: AnyVertexType](targetType: one[T]) = 
      OneToOne (new RelType[S,E,T](sourceType, edgeType, targetType.v))
    def -->[T <: AnyVertexType](targetType: many[T]) = 
      OneToMany (new RelType[S,E,T](sourceType, edgeType, targetType.v))
  }


  case class many[V <: AnyVertexType](v: V) {
    def --[E <: AnyEdgeType](edgeType: E) = manyToSmth(v, edgeType)
  }

  case class manyToSmth[S <: AnyVertexType, E <: AnyEdgeType](sourceType: S, edgeType: E) {
    def -->[T <: AnyVertexType](targetType: one[T]) = 
      ManyToOne (new RelType[S,E,T](sourceType, edgeType, targetType.v))
    def -->[T <: AnyVertexType](targetType: many[T]) = 
      ManyToMany (new RelType[S,E,T](sourceType, edgeType, targetType.v))
  }

  implicit def toSourceOps[S <: AnyVertexType](sourceType: S): Many[S] = Many(sourceType)
}
*/
