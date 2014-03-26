package bio4j.graphs

import shapeless.record.FieldType

/*
  ## Accessing properties

  The basic static requirement for accessing a property is for it to be declared for that vertex/edge type; that means having a witness of type `AnyVertexTypeHas`. Now, given that, you need to be able to produce something given

  - a representation of the given vertex declaration
  - the property type itself

*/

trait AnyVertexProperty {

  type VertexOf <: AnyVertexOf
  val vertexOf: VertexOf

  type PropertyType <: AnyPropertyType
  val propertyType: PropertyType

  def apply(vertex: vertexOf.Rep): propertyType.Rep
}

abstract class VertexProperty[
  VO <: AnyVertexOf,
  PT <: AnyPropertyType
](
  val vertexOf: VO,
  val propertyType: PT
) extends AnyVertexProperty {

  type VertexOf = VO
  type PropertyType = PT
}

