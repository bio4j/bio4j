package bio4j.graphs

/*
  A labeled Vertex
*/
trait AnyVertexType {  val label: String  }
class VertexType  extends AnyVertexType { val label = this.toString }
class LiteralType extends AnyVertexType { val label = this.toString }