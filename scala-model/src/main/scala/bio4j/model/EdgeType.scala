package bio4j.model

/*
  Witnesses of a sourceType/type adscription to an edge type.
*/
trait AnyEdgeType {

  val label: String

  // TODO add an applicative/monad requirement here
  type In[X]
  type Out[X]

  type SourceType <: AnyVertexType
  val sourceType: SourceType

  type TargetType <: AnyVertexType
  val targetType: TargetType

}

trait EdgeType[
  S <: AnyVertexType, 
  T <: AnyVertexType
] extends AnyEdgeType {

  type SourceType = S
  type TargetType = T

  /* for this thing to work, you should extend this class by a _case_ class/object */
  val label = this.toString 
}

// TODO move to scalaz Id etc
case class Id[T](val t: T)

object AnyEdgeType {
  implicit def edgeTypeOps[ET <: AnyEdgeType](et: ET) = EdgeTypeOps(et)
}

/* Arities */
sealed trait AnyManyToMany[S <: AnyVertexType, T <: AnyVertexType] extends EdgeType[S,T] {
    type In[X] = List[X]
    type Out[X] = List[X]
}
class ManyToMany[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends AnyManyToMany[S, T]

sealed trait AnyOneToMany[S <: AnyVertexType, T <: AnyVertexType] extends EdgeType[S, T] {
    type In[X] = Option[X]
    type Out[X] = List[X]
  }
class OneToMany[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends AnyOneToMany[S, T]

sealed trait AnyManyToOne[S <: AnyVertexType, T <: AnyVertexType] extends EdgeType[S, T] {
    type In[X] = List[X]
    type Out[X] = Option[X]
  }
class ManyToOne[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends AnyManyToOne[S, T]

sealed trait AnyOneToOne[S <: AnyVertexType, T <: AnyVertexType] extends EdgeType[S, T] {
    type In[X] = Option[X]
    type Out[X] = Option[X]
  }
class OneToOne[S <: AnyVertexType, T <: AnyVertexType](val sourceType: S, val targetType: T) 
  extends AnyOneToOne[S, T]


/* Arrows "DSL" */

// NOTE what's the point of all this once we have included arities in edge types?
sealed trait AnyArityVertex { 
  type VType <: AnyVertexType
  val  vType: VType
}
sealed trait ArityVertex[VT <: AnyVertexType] extends AnyArityVertex { type VType = VT }
case class  one[V <: AnyVertexType](vType: V) extends ArityVertex[V] {
  def --(label: String) = OneToSmth(vType, label)
}
case class many[V <: AnyVertexType](vType: V) extends ArityVertex[V] {
  def --(label: String) = ManyToSmth(vType, label)
}

case class OneToSmth[ST <: AnyVertexType](st: ST, l: String) {
  def -->[T <: AnyArityVertex](t: T) = t match {
    case one(tt)  => new OneToOne(st,tt)  { override val label = l }
    case many(tt) => new OneToMany(st,tt) { override val label = l }
  }
}
case class ManyToSmth[ST <: AnyVertexType](st: ST, l: String) {
  def -->[TT <: AnyVertexType](t: one[TT]) = t match {
    case one(tt)  => new ManyToOne(st,tt)  { override val label = l }
  }
  def -->[TT <: AnyVertexType](t: many[TT]) = t match {
    case many(tt) => new ManyToMany(st,tt) { override val label = l }
  }
}

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
