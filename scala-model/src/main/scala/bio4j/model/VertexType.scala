package bio4j.model

/*
  Declares a Vertex type. They are essentially classified by its label, a `String`.
*/
trait AnyVertexType {  val label: String  }
/*
  A convenience class for declaring types as case objects; just do

  ``` scala
  case object User extends VertexType
  ```
*/
class VertexType  extends AnyVertexType { val label = this.toString }
class LiteralType extends AnyVertexType { val label = this.toString }

object AnyVertexType {

  implicit class VertexTypeOps[E <: AnyVertexType](val vertexType: E) {

    def has[P <: AnyProperty](property: P): (E VertexTypeHasProperty P) = VertexTypeHasProperty(vertexType, property)
  }
}

/* Witness for an Vertex of type V having a property of type P */
case class VertexTypeHasProperty[
  V <: AnyVertexType,
  P <: AnyProperty
](val smth: V,
  val property: P) extends SmthHasProperty {
  type Smth = V
  type Property = P
}
