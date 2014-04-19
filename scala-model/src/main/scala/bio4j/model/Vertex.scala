package bio4j.model

/*
  `AnyVertex` defines a denotation of the corresponding `VertexType`.

  Instances are modeled as instances of other type tagged with the singleton type of a `Vertex`. For example, an instance of a vertex of type `User` when stored/represented by a `Neo4jNode` is going to be something of type `FieldType[user.type, Neo4jNode]`  where `user.type <: AnyVertex { type VertexType = User.type; type Rep = Neo4jNode }`.
  
  They are designed to be compatible with shapeless records (maybe, we'll see).
*/
trait AnyVertex extends Tagged {
  
  vertex =>

  type VertexType <: AnyVertexType
  val vertexType: VertexType

  /* Read a property from this representation */
  abstract case class ReadProperty[P <: AnyProperty](val p: P) {

    def apply(vRep: TaggedRep): p.Rep
  }

  // TODO this should go somewhere else
  implicit class PropertyOps(val vRep: TaggedRep) {

    // TODO this name is awful
    import AnyVertexTypeHasProperty.PropertyOf

    def get[P <: AnyProperty: PropertyOf[vertex.VertexType]#is](p: P)
      (implicit 
        retrieve: ReadProperty[P]
      ) = retrieve(vRep)
  }
  
  ///////////////////////////////////////////

  // abstract case class RetrieveEdge[R <: AnyEdge](val r: R) {
  //   def apply(vRep: TaggedRep): r.Rep
  // }

  // implicit class EdgeOps(val vRep: TaggedRep) {
  //   def out[
  //     E <: AnyEdgeType, 
  //     T <: AnyVertexType,
  //     RT <: EdgeType[ArityVertex[VertexType], E, ArityVertex[T]],
  //     R <: Edge[RT]
  //   ](r: R)(implicit retrieve: RetrieveEdge[R]) = retrieve(vRep)
  // }
}

object AnyVertex {

  type ofType[VT <: AnyVertexType] = AnyVertex { type VertexType = VT }
}

abstract class Vertex[VT <: AnyVertexType](val vertexType: VT) extends AnyVertex {

  type VertexType = VT
}
