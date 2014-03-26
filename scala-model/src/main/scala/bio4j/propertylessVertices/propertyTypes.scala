package bio4j.graphs

/*
  Properties
*/
import shapeless.FieldOf

trait AnyPropertyType extends LiteralType {

  type Rep
}
class PropertyType[V]() extends AnyPropertyType with FieldOf[V] {

  type Rep = V
}

/*
  witness for an Edge of type E having a property of type P
*/
trait AnyEdgeTypeHas {

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType

  type PropertyType <: AnyPropertyType
  val propertyType: PropertyType
}

case class EdgeTypeHas[
  E <: AnyEdgeType,
  P <: AnyPropertyType
](val edgeType: E, val propertyType: P) extends AnyEdgeTypeHas {

  type EdgeType = E
  type PropertyType = P
}

/*
  witness for a vertex type declaring a property of the given type
*/
trait AnyVertexTypeHas {

  type VertexType <: AnyVertexType
  val vertexType: VertexType

  type PropertyType <: AnyPropertyType
  val propertyType: PropertyType
}

case class VertexTypeHas[
  V <: AnyVertexType,
  P <: AnyPropertyType
](val vertexType: V, val propertyType: P) extends AnyVertexTypeHas {

  type VertexType = V
  type PropertyType = P
}