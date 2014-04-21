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

    def apply(rep: vertex.TaggedRep): e.tpe.Out[e.TaggedRep]
  }

  abstract class RetrieveManyOutEdge[E <: Singleton with AnyEdge { type Tpe <: SmthToMany }](val e: E) 
      extends AnyRetrieveOutEdge { type Edge = E }

  abstract class RetrieveOneOutEdge[E <: Singleton with AnyEdge { type Tpe <: SmthToOne }](val e: E) 
      extends AnyRetrieveOutEdge { type Edge = E }

  // TODO: same as for Out
  // abstract case class RetrieveInEdge[E <: AnyEdge](val e: E) {
  //   def apply(rep: vertex.TaggedRep): e.tpe.In[e.TaggedRep]
  // }

  implicit def vertexOps(rep: vertex.TaggedRep) = VertexOps(rep)
  case class   VertexOps(rep: vertex.TaggedRep) {

    def out[E <: Singleton with AnyEdge { type Tpe <: EdgeFrom[vertex.Tpe] with SmthToMany }]
      (e: E)(implicit mkRetriever: E => RetrieveManyOutEdge[E]): E#Tpe#Out[E#TaggedRep] = {

        val uh = mkRetriever(e)

        val hey = uh(rep)
        hey
      }

    def out[E <: Singleton with AnyEdge { type Tpe <: EdgeFrom[vertex.Tpe] with SmthToOne }]
      (e: E)(implicit mkRetriever: E => RetrieveOneOutEdge[E]): E#Tpe#Out[E#TaggedRep] = {

        val uh = mkRetriever(e)

        val hey = uh(rep)
        hey
      }

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
