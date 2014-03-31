package bio4j.model

import shapeless.record.FieldType

/*
  ## Accessing properties

  The basic static requirement for accessing a property is for it to be declared for that vertex/edge type; that means having a witness of type `AnyVertexTypeHas`. Now, given that, you need to be able to produce something given

  - a representation of the given vertex declaration
  - the property type itself

*/
trait AnyVertexProperty {

  type Vertex <: AnyVertex
  val vertex: Vertex

  type PropertyType <: AnyPropertyType
  val propertyType: PropertyType

  import shapeless.record.KeyTag
  def apply(v: vertex.Rep with KeyTag[vertex.type, vertex.Rep]): propertyType.Rep
}

abstract class VertexProperty [
  V <: AnyVertex,
  PT <: AnyPropertyType
](
  val vertex: V,
  val propertyType: PT
) extends AnyVertexProperty {

  type Vertex = V
  type PropertyType = PT
}

/*
  ### the ops game

  I need to

  1. get a VertexOps class providing a getProperty method
  2. that method requires an implicit VertexProperty instance of the given type
  3. the return type is determined by the Property itself, and the value produced by the apply method of the aforementioned VertexProperty
*/
