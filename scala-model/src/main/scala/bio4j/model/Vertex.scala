package bio4j.model

/*
  `AnyVertex` defines a denotation of the corresponding `VertexType`.

  Instances are modeled as instances of other type tagged with the singleton type of a `Vertex`. For example, an instance of a self of type `User` when stored/represented by a `Neo4jNode` is going to be something of type `FieldType[user.type, Neo4jNode]`  where `user.type <: AnyVertex { type VertexType = User.type; type Rep = Neo4jNode }`.
  
  They are designed to be compatible with shapeless records (maybe, we'll see).
*/
trait AnyVertex extends Tagged { self =>

  type Tpe <: AnyVertexType
  val tpe: Tpe
  
  ///////////////////////////////////////////

  // abstract case class RetrieveEdge[R <: AnyEdge](val r: R) {
  //   def apply(vRep: TaggedRep): r.Rep
  // }

  // implicit class EdgeOps(val vRep: TaggedRep) {
  //   def out[
  //     E <: AnyEdgeType, 
  //     T <: AnyVertexType,
  //     RT <: EdgeType[ArityVertex[Tpe], E, ArityVertex[T]],
  //     R <: Edge[RT]
  //   ](r: R)(implicit retrieve: RetrieveEdge[R]) = retrieve(vRep)
  // }

  /* Read a property from this representation */
  abstract case class GetProperty[P <: AnyProperty](val p: P) {
    def apply(rep: self.TaggedRep): p.Rep
  }

  case class PropertyOps(rep: self.TaggedRep) {
    import SmthHasProperty._
    def get[P <: AnyProperty: PropertyOf[self.Tpe]#is](p: P)
      (implicit getter: GetProperty[P]) = getter(rep)
  }

  implicit def getProperty(rep: self.TaggedRep) = PropertyOps(rep)
}

object AnyVertex {

  type ofType[VT <: AnyVertexType] = AnyVertex { type Tpe = VT }
}

abstract class Vertex[VT <: AnyVertexType](val tpe: VT) extends AnyVertex {

  type Tpe = VT
}
