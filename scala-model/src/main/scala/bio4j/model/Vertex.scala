package bio4j.model

/*
  `AnyVertex` defines a denotation of the corresponding `VertexType`.

  Instances are modeled as instances of other type tagged with the singleton type of a `Vertex`. For example, an instance of a self of type `User` when stored/represented by a `Neo4jNode` is going to be something of type `FieldType[user.type, Neo4jNode]`  where `user.type <: AnyVertex { type VertexType = User.type; type Rep = Neo4jNode }`.
  
  They are designed to be compatible with shapeless records (maybe, we'll see).
*/

trait AnyVertex extends Denotation[AnyVertexType] { vertex =>

  /* Getters for incoming/outgoing edges */
  trait AnyRetrieveOutEdge {
    type Edge <: AnyEdge
    val e: Edge

    def apply(rep: vertex.TaggedRep): e.Out[e.TaggedRep]
  }

  abstract class RetrieveOutEdge[E <: AnyEdge](val e: E) 
      extends AnyRetrieveOutEdge { type Edge = E }

  abstract class RetrieveManyOutEdge[E <: AnyEdge { type Tpe <: SmthToMany }](e: E) 
      extends RetrieveOutEdge(e)

  abstract class RetrieveOneOutEdge[E <: AnyEdge { type Tpe <: SmthToOne }](e: E) 
      extends RetrieveOutEdge(e)

  // TODO: same as for Out
  // abstract case class RetrieveInEdge[E <: AnyEdge](val e: E) {
  //   def apply(rep: vertex.TaggedRep): e.In[e.Rep]
  // }

  implicit def vertexOps(rep: vertex.TaggedRep) = VertexOps(rep)
  case class   VertexOps(rep: vertex.TaggedRep) {

    def out[E <: AnyEdge { type Tpe <: EdgeFrom[vertex.Tpe] }]
      (e: E)(implicit mkRetriever: E => RetrieveOutEdge[E]): E#Out[E#TaggedRep] = 
        mkRetriever(e).apply(rep)

    // TODO: same as for Out
    // def in[E <: AnyEdge.withTargetType[vertex.Tpe]]
    //   (e: E)(implicit retrieve: RetrieveInEdge[E]) = retrieve(rep)

  }

}

abstract class Vertex[VT <: AnyVertexType](val tpe: VT) 
  extends AnyVertex { type Tpe = VT }

object AnyVertex {
  type ofType[VT <: AnyVertexType] = AnyVertex { type Tpe = VT }
}
