package bio4j.graphs

trait AnyEdgeType { val label: String }
class EdgeType extends AnyEdgeType { val label = this.toString }

object AnyEdgeType {

  implicit def edgeTypeOps[E <: AnyEdgeType](edge: E): EdgeTypeOps[E] = EdgeTypeOps(edge) 
}

case class EdgeTypeOps[E <: AnyEdgeType](edge: E) {

  def has[P <: AnyPropertyType](property: P): (E Has P) = Has(edge, property)
}
  
trait AnyRel {

  type Source <: AnyVertexType
  val source: Source

  type Edge <: AnyEdgeType
  val edge: Edge

  type Target <: AnyVertexType
  val target: Target
}
object AnyRel {
  
  implicit def relOps[R <: AnyRel](rel: R): RelOps[R] = RelOps(rel)
}

class Rel[X <: AnyVertexType, E <: AnyEdgeType, Y <: AnyVertexType](val source: X, val edge: E, val target: Y) extends AnyRel {
  type Source = X
  type Edge = E
  type Target = Y

  // if needed add here implicits for witnessing that this rel goes from X to Y
}

// TODO think about the output types
sealed trait AnyArity {

  type Rel <: AnyRel
  val rel: Rel
}
case class ManyToMany[R <: AnyRel](rel: R) extends AnyArity { type Rel = R }
case class ManyToOne[R <: AnyRel](rel: R)  extends AnyArity { type Rel = R }
case class OneToMany[R <: AnyRel](rel: R)  extends AnyArity { type Rel = R }
case class OneToOne[R <: AnyRel](rel: R)   extends AnyArity { type Rel = R }

case class RelOps[R <: AnyRel](rel: R) {

  def manyToMany:  ManyToMany[R] = ManyToMany(rel)
  def manyToOne:    ManyToOne[R] = ManyToOne(rel)
  def oneToMany:    OneToMany[R] = OneToMany(rel)
  def oneToOne:      OneToOne[R] = OneToOne(rel)
}
