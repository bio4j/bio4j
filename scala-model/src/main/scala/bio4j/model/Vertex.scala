package bio4j.model

/*
  `AnyVertex` defines a denotation of the corresponding `VertexType`.

  Instances are modeled as instances of other type tagged with the singleton type of a `Vertex`. For example, an instance of a self of type `User` when stored/represented by a `Neo4jNode` is going to be something of type `FieldType[user.type, Neo4jNode]`  where `user.type <: AnyVertex { type VertexType = User.type; type Rep = Neo4jNode }`.
  
  They are designed to be compatible with shapeless records (maybe, we'll see).
*/
trait AnyVertex extends Tagged { self =>

  type Tpe <: AnyVertexType
  val tpe: Tpe
  
  /* Read a property from this representation */
  abstract class GetProperty[P <: AnyProperty](val p: P) {
    def apply(rep: self.TaggedRep): p.Rep
  }

  case class PropertyOps(rep: self.TaggedRep) {
    import SmthHasProperty._
    def get[P <: AnyProperty: PropertyOf[self.Tpe]#is, GP <: GetProperty[P]](p: P)
      (implicit getter: GP) = getter(rep)
  }

  implicit def propertyOps(rep: self.TaggedRep) = PropertyOps(rep)


  abstract case class RetrieveEdge[E <: AnyEdge](val r: E) {
    def apply(vRep: TaggedRep): r.tpe.Out[r.Rep]
  }

  implicit class EdgeOps(val vRep: TaggedRep) {
    def out[E <: AnyEdge.withSourceType[self.Tpe]]
      (e: E)(implicit retrieve: RetrieveEdge[E]) = retrieve(vRep)

    def in[E <: AnyEdge.withTargetType[self.Tpe]]
      (e: E)(implicit retrieve: RetrieveEdge[E]) = retrieve(vRep)
  }

}

object AnyVertex {
  type ofType[VT <: AnyVertexType] = AnyVertex { type Tpe = VT }
}

abstract class Vertex[VT <: AnyVertexType](val tpe: VT) extends AnyVertex {

  type Tpe = VT
}