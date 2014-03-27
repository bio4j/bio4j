// package bio4j.model.test

// import bio4j.model._

// case object Protein extends AnyVertex { val label = "protein" }
// object ProteinOps extends AnyVertexOps {

//   type Vertex = Protein.type
//   def doSomething() = "lalala"
// }

// case object ProteinModule extends AnyVertexModule {
  
//   type Vertex = Protein.type
//   val vertex = Protein
//   type VertexOps = ProteinOps.type

//   implicit def vertexOps[V <: Vertex](v: V): VertexOps = ProteinOps
// }