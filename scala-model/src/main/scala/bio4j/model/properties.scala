package bio4j.model

/*
  Properties
*/
import shapeless.FieldOf

/*
  This has a label!
*/
trait AnyProperty extends LiteralType {

  // should this go somewhere else?
  type Rep
}
class Property[V]() extends AnyProperty with FieldOf[V] {

  type Rep = V
}

/* Witness for an Rel of type R having a property of type P */
trait AnyRelTypeHasProperty {
  type RelType <: AnyRelType
  val edgeType: RelType

  type Property <: AnyProperty
  val property: Property
}

case class RelTypeHasProperty [
  R <: AnyRelType,
  P <: AnyProperty
](val edgeType: R,
  val property: P) extends AnyRelTypeHasProperty {
  type RelType = R
  type Property = P
}

object AnyRelTypeHasProperty {
  type PropertyOf[R <: AnyRelType] = { 
    type is[P <: AnyProperty] = AnyRelTypeHasProperty { type RelType = R; type Property = P }
  }
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

case class VertexTypeHasProperty[
  V <: AnyVertexType,
  P <: AnyProperty
](val vertexType: V,
  val property: P) extends AnyVertexTypeHasProperty {
  type VertexType = V
  type Property = P
}

object AnyVertexTypeHasProperty {
  type PropertyOf[V <: AnyVertexType] = { 
    type is[P <: AnyProperty] = AnyVertexTypeHasProperty { type VertexType = V; type Property = P }
  }
}
