package bio4j.model

/*
  `AnyVertex` defines a denotation of the corresponding `VertexType`.

  Instances are modeled as instances of other type tagged with the singleton type of a `Vertex`. For example, an instance of a vertex of type `User` when stored/represented by a `Neo4jNode` is going to be something of type `FieldType[user.type, Neo4jNode]`  where `user.type <: AnyVertex { type VertexType = User.type; type Rep = Neo4jNode }`.
  
  They are designed to be compatible with shapeless records (maybe, we'll see).
*/
trait AnyVertex {
  
  vertex =>

  type VertexType <: AnyVertexType
  val vertexType: VertexType

  // the raw underlying type representing this vertex
  type Rep

  import shapeless.record._

  /*
    Tags a Rep with this vertex type
  */
  def ->>(v: Rep): FieldType[vertex.type, Rep] = field[vertex.type](v)

  // read a property from this representation
  abstract case class ReadProperty[P <: AnyProperty](val p: P) {

    def apply(vRep: FieldType[vertex.type, Rep]): p.Rep
  }

  import AnyVertexTypeHasProperty.PropertyOf

  // this should go somewhere else
  case class PropertyOps(val vRep: FieldType[vertex.type, Rep]) {

    def get[P <: AnyProperty]
    (p: P)
    (implicit 
      witness: PropertyOf[vertex.VertexType]#is[P],
      retrieve: ReadProperty[P]
    ) = retrieve(vRep)
  }
  
  implicit def propertyOps[P <: AnyProperty]
    (vRep: FieldType[vertex.type, Rep]): vertex.PropertyOps = PropertyOps(vRep)
}

object AnyVertex {

  import shapeless.record.KeyTag  

  type FieldOf[Rep, V <: AnyVertex] = { type is[X] = Rep with KeyTag[V, Rep] }

  type VertexType[VT <: AnyVertexType] = AnyVertex { type VertexType = VT }
}

abstract class Vertex[VT <: AnyVertexType](val vertexType: VT) extends AnyVertex {

  type VertexType = VT
}