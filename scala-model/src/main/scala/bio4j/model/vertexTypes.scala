package bio4j.model

/*
  Declares a Vertex type. They are essentially classified by its label, a `String`.
*/
trait AnyVertexType {  val label: String  }
/*
  A convenience class for declaring types as case objects; just do

  ``` scala
  case object User extends NodeType
  ```
*/
class VertexType  extends AnyVertexType { val label = this.toString }
class LiteralType extends AnyVertexType { val label = this.toString }

object AnyVertexType {

  implicit def vertexTypeOps[E <: AnyVertexType](vertexType: E): VertexTypeOps[E] = VertexTypeOps(vertexType) 
}

/*
  
*/
case class VertexTypeOps[E <: AnyVertexType](val vertexType: E) {

  def has[P <: AnyPropertyType](propertyType: P): (E VertexTypeHas P) = VertexTypeHas(vertexType, propertyType)
}
