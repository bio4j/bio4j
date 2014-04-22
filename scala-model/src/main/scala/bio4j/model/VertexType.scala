package bio4j.model

/*
  Declares a Vertex type. They are essentially classified by its label, a `String`.
*/
trait AnyVertexType {  val label: String  }
/*
  A convenience class for declaring types as case objects; just do

  ``` scala
  case object User extends LiteralType
  ```
*/
class LiteralType extends AnyVertexType { val label = this.toString }

class VertexType(val label: String) extends AnyVertexType

object AnyVertexType {
  implicit def vertexTypeOps[VT <: AnyVertexType](et: VT) = VertexTypeOps(et)
}

case class VertexTypeOps[VT <: AnyVertexType](val vt: VT) {
  def has[P <: AnyProperty](p: P) = HasProperty[VT, P](vt, p)
}
