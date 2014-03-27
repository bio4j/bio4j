package bio4j.simple

trait AnyVertex {  val label: String  }
trait AnyVertexOps {

  type Vertex <: AnyVertex
}

trait AnyVertexModule {
  self =>
  type Vertex <: AnyVertex
  val vertex: Vertex

  type VertexOps <: AnyVertexOps { type Vertex = self.Vertex }

  implicit def vertexOps[V <: Vertex](v: V): VertexOps
}