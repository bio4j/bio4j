package bio4j.model

/*
  Witnesses of a sourceType/type adscription to an edge type.
*/
trait AnyEdgeType {

  val label: String

  // TODO add an applicative/monad requirement here
  type In[+X]
  type Out[+X]

  type SourceType <: AnyVertexType
  val sourceType: SourceType

  type TargetType <: AnyVertexType
  val targetType: TargetType

}

// TODO move to scalaz Id etc
case class Id[T](val t: T)

object AnyEdgeType {
  implicit def edgeTypeOps[ET <: AnyEdgeType](et: ET) = EdgeTypeOps(et)
}

trait EdgeFrom[S <: AnyVertexType] extends AnyEdgeType { type SourceType = S }
trait   EdgeTo[T <: AnyVertexType] extends AnyEdgeType { type TargetType = T }

trait EdgeType[S <: AnyVertexType, T <: AnyVertexType] extends EdgeFrom[S] with EdgeTo[T]

/* Arities */
trait SmthToMany extends AnyEdgeType { type Out[+X] = List[X] }
trait  SmthToOne extends AnyEdgeType { type Out[+X] = Option[X] }
trait ManyToSmth extends AnyEdgeType { type  In[+X] = List[X] }
trait  OneToSmth extends AnyEdgeType { type  In[+X] = Option[X] }

class ManyToMany[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends EdgeFrom[S] with EdgeTo[T] with ManyToSmth with SmthToMany { val label = this.toString }

class OneToMany[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends EdgeFrom[S] with EdgeTo[T] with OneToSmth with SmthToMany { val label = this.toString }

class ManyToOne[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends EdgeFrom[S] with EdgeTo[T] with ManyToSmth with SmthToOne { val label = this.toString }

class OneToOne[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends EdgeFrom[S] with EdgeTo[T] with OneToSmth with SmthToOne { val label = this.toString }


/* Properties */

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
