package bio4j.graphs

/*
  Declares an Edge type. They are essentially classified by its label, a `String`.
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