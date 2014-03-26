package bio4j.graphs

/*
  Declares an Edge type. They are essentially classified by its label, a `String`.

  Its source and target types are not bound here, as that would lead to an explosion of equally named but different objects. The goal is to be able to express any source and target type configurations through witnesses.
*/
trait AnyEdgeType { val label: String }
/*
  A convenience class for declaring types as case objects; just do

  ``` scala
  case object Follows extends EdgeType
  ```
*/
class EdgeType extends AnyEdgeType { val label = this.toString }

object AnyEdgeType {

  implicit def edgeTypeOps[E <: AnyEdgeType](edgeType: E): EdgeTypeOps[E] = EdgeTypeOps(edgeType) 
}

case class EdgeTypeOps[E <: AnyEdgeType](val edgeType: E) {

  def has[P <: AnyPropertyType](propertyType: P): (E EdgeTypeHas P) = EdgeTypeHas(edgeType, propertyType)
}

/*
  Witnesses of a source/type adscription to an edge type are called rels. It's not that I love this name, but...
*/
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

class Rel[
  X <: AnyVertexType, 
  E <: AnyEdgeType, 
  Y <: AnyVertexType
](val source: X, val edge: E, val target: Y) extends AnyRel {

  type Source = X
  type Edge = E
  type Target = Y
  // if needed add here implicits for witnessing that this rel goes from X to Y
}

/*
  ### creating rels

  We need to start with one vertex type, then an edge type and then another vertex type. This involves 
*/

object DeclareRels {

  case class SourceOps[S <: AnyVertexType](source: S) {

    def --[E <: AnyEdgeType](edge: E) = SourceAndEdge(source, edge)
  }
  case class SourceAndEdge[S <: AnyVertexType, E <: AnyEdgeType](source: S, edge: E) {

    def -->[T <: AnyVertexType](target: T): Rel[S,E,T] = new Rel[S,E,T](source, edge, target)
  }

  implicit def toSourceOps[S <: AnyVertexType](source: S): SourceOps[S] = SourceOps(source)
}


/*
  Arities for rels. The point of this is that it lets you specify the right output type for when you get the outgoing edges of a given type from a vertex. 
*/
sealed trait AnyArity {

  type Rel <: AnyRel
  val rel: Rel
}

// TODO think about the output types
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
