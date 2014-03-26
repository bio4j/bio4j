package bio4j.graphs

/*
  Properties
*/
import shapeless.FieldOf
trait AnyPropertyType extends LiteralType
class PropertyType[V]() extends AnyPropertyType with FieldOf[V]

/*
  witness of an Edge of type E having a property of type P
*/
trait PropertyOf {

  /*
    Only Edges have properties
  */
  type E <: AnyEdgeType
  type P <: AnyPropertyType
}

object PropertyOf {

  type propertyOf[aN <: AnyEdgeType] = {

    type is[aP <: AnyPropertyType] = PropertyOf { type N = aN; type P = aP }
  }

  type is[aN <: AnyEdgeType, aP <: AnyPropertyType] = PropertyOf { type N = aN; type P = aP }
}

trait AnyHas {

  type Edge <: AnyEdgeType
  val edge: Edge

  type Property <: AnyPropertyType
  val property: Property
}

case class Has[E <: AnyEdgeType, P <: AnyPropertyType](edge: E, property: P) extends AnyHas {

  type Edge = E
  type Property = P
}