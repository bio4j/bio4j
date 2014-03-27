package bio4j.model

/*
  Given a vertex type, their instances are modeled as instances of other type tagged with the corresponding vertex type.

  They are designed to be compatible with shapeless records; in the end, an instance of a vertex of type `User` when stored/represented by a `Neo4jNode` is something of type `FieldType[User, Neo4jNode]`.
*/
trait AnyVertexOf {
  
  type VertexType <: AnyVertexType
  val vertexType: VertexType
  type Rep

  import shapeless.record._
  def ->>(v: Rep): FieldType[VertexType, Rep] = field[VertexType](v)
}

class VertexOf[VT <: AnyVertexType](val vertexType: VT) extends AnyVertexOf {
  
  type VertexType = VT
}

trait AnyNeo4jVertexOf extends AnyVertexOf {

  // bounded by Neo4j stuff
  type Rep
}

/*
  ### operations

  I need to think about this
*/