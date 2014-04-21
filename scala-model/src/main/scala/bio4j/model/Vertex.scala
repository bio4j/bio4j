package bio4j.model

/*
  `AnyVertex` defines a denotation of the corresponding `VertexType`.

  Instances are modeled as instances of other type tagged with the singleton type of a `Vertex`. For example, an instance of a self of type `User` when stored/represented by a `Neo4jNode` is going to be something of type `FieldType[user.type, Neo4jNode]`  where `user.type <: AnyVertex { type VertexType = User.type; type Rep = Neo4jNode }`.
  
  They are designed to be compatible with shapeless records (maybe, we'll see).
*/

trait AnyVertex extends Denotation[AnyVertexType] { self =>

  /* Getters for incoming/outgoing edges */
  abstract case class RetrieveOutEdge[E <: AnyEdge](val e: E) {
    def apply(rep: TaggedRep): e.tpe.Out[e.Rep]
  }
  abstract case class RetrieveInEdge[E <: AnyEdge](val e: E) {
    def apply(rep: TaggedRep): e.tpe.In[e.Rep]
  }

  implicit def vertexOps(rep: TaggedRep) = VertexOps(rep)
  case class   VertexOps(rep: TaggedRep) {

    def out[E <: AnyEdge.withSourceType[self.Tpe]]
      (e: E)(implicit retrieve: RetrieveOutEdge[E]) = retrieve(rep)

    def in[E <: AnyEdge.withTargetType[self.Tpe]]
      (e: E)(implicit retrieve: RetrieveInEdge[E]) = retrieve(rep)

  }

}

abstract class Vertex[VT <: AnyVertexType](val tpe: VT) 
  extends AnyVertex { type Tpe = VT }

object AnyVertex {
  type ofType[VT <: AnyVertexType] = AnyVertex { type Tpe = VT }
}
