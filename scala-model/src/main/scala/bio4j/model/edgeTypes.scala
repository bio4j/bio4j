package bio4j.model

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

  def has[P <: AnyProperty](property: P): (E EdgeTypeHasProperty P) = EdgeTypeHasProperty(edgeType, property)
}
