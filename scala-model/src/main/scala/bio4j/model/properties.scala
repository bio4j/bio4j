package bio4j.model

/*
  Properties
*/
import shapeless.FieldOf

/*
  this has a label!
*/
trait AnyProperty extends LiteralType {

  // should this go somewhere else?
  type Rep
}
class Property[V]() extends AnyProperty with FieldOf[V] {

  type Rep = V
}

/*
  witness for an Edge of type E having a property of type P
*/
trait AnyEdgeTypeHasProperty {

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType

  type Property <: AnyProperty
  val property: Property
}

case class EdgeTypeHasProperty 
[
  E <: AnyEdgeType,
  P <: AnyProperty
](
  val edgeType: E,
  val property: P
) 
extends AnyEdgeTypeHasProperty {

  type EdgeType = E
  type Property = P
}

/*
  witness for a vertex type declaring a property of the given type
*/
trait AnyVertexTypeHasProperty {

  type VertexType <: AnyVertexType
  val vertexType: VertexType

  type Property <: AnyProperty
  val property: Property
}

case class VertexTypeHasProperty 
[
  V <: AnyVertexType,
  P <: AnyProperty
](
  val vertexType: V,
  val property: P
) 
extends AnyVertexTypeHasProperty {

  type VertexType = V
  type Property = P
}

object AnyVertexTypeHasProperty {

  type PropertyOf[V <: AnyVertexType] = { 
    type is[P <: AnyProperty] = AnyVertexTypeHasProperty { type VertexType = V; type Property = P }
  }
}