package bio4j.model

/*
  Witnesses of a sourceType/type adscription to an edge type are called rels. It's not that I love this name, but...
*/
trait AnyRelType {

  type SourceType <: AnyVertexType
  val sourceType: SourceType

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType

  type TargetType <: AnyVertexType
  val targetType: TargetType
}
object AnyRelType {
  
  implicit def relTypeOps[R <: AnyRelType](rel: R): RelOps[R] = RelOps(rel)
}

class RelType[
  X <: AnyVertexType, 
  E <: AnyEdgeType, 
  Y <: AnyVertexType
](val sourceType: X, val edgeType: E, val targetType: Y) extends AnyRelType {

  type SourceType = X
  type EdgeType = E
  type TargetType = Y
  // if needed add here implicits for witnessing that this rel goes from X to Y
}

/*
  ### creating rels

  We need to start with one vertex type, then an edge type and then another vertex type. This involves 
*/

object DeclareRelTypes {

  case class SourceTypeOps[S <: AnyVertexType](sourceType: S) {

    def --[E <: AnyEdgeType](edgeType: E) = SourceAndEdgeType(sourceType, edgeType)
  }

  case class SourceAndEdgeType[S <: AnyVertexType, E <: AnyEdgeType](sourceType: S, edgeType: E) {

    def -->[T <: AnyVertexType](targetType: T): RelType[S,E,T] = new RelType[S,E,T](sourceType, edgeType, targetType)
  }

  implicit def toSourceOps[S <: AnyVertexType](sourceType: S): SourceTypeOps[S] = SourceTypeOps(sourceType)
}


/*
  Arities for rels. The point of this is that it lets you specify the right output type for when you get the outgoing edges of a given type from a vertex.
*/
// TODO move to RelType
sealed trait AnyArity {

  type RelType <: AnyRelType
  val relType: RelType
}

// TODO think about the output types
case class ManyToMany[R <: AnyRelType](val relType: R) extends AnyArity { type RelType = R }
case class ManyToOne[R <: AnyRelType](val relType: R)  extends AnyArity { type RelType = R }
case class OneToMany[R <: AnyRelType](val relType: R)  extends AnyArity { type RelType = R }
case class OneToOne[R <: AnyRelType](val relType: R)   extends AnyArity { type RelType = R }

case class RelOps[R <: AnyRelType](relType: R) {

  def manyToMany:  ManyToMany[R] = ManyToMany(relType)
  def manyToOne:    ManyToOne[R] = ManyToOne(relType)
  def oneToMany:    OneToMany[R] = OneToMany(relType)
  def oneToOne:      OneToOne[R] = OneToOne(relType)
}