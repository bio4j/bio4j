package bio4j.graphs

// a vertex rep

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