package bio4j.model

/*
  Given a vertex type, their instances are modeled as instances of other type tagged with the corresponding vertex type.
  
  They are designed to be compatible with shapeless records; in the end, an instance of a vertex of type `User` when stored/represented by a `Neo4jNode` is something of type `FieldType[User, Neo4jNode]`.
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

  // case class VertexOps(v: vertex.Rep with KeyTag[vertex.vertexType.type, vertex.Rep]) {

  //   def get[P <: AnyPropertyType]
  //     (property: P)
  //     (implicit vertexProperty: VertexProperty[vertex.vertexType.type, P]): property.Rep = 
  //       vertexProperty(v)
  // }
}

object AnyVertex {

  import shapeless.record.KeyTag  

  type FieldOf[Rep, V <: AnyVertex] = { type is[X] = Rep with KeyTag[V, Rep] }
}

class Vertex[VT <: AnyVertexType](val vertexType: VT) extends AnyVertex {
  
  vertex =>
  type VertexType = VT

  import shapeless.record.FieldType

  // read a property from this representation
  abstract case class ReadProperty[P <: AnyPropertyType](val p: P) {

    def apply(vRep: FieldType[vertex.type, Rep]): p.Rep
  }

  import AnyVertexTypeHas.PropertyOf

  // this should go somewhere else
  case class PropertyOps(val vRep: FieldType[vertex.type, Rep]) {

    def get[P <: AnyPropertyType]
    (p: P)
    (implicit 
      witness: PropertyOf[vertex.VertexType]#is[P],
      retrieve: ReadProperty[P]
    ) = retrieve(vRep)
  }
  
  implicit def propertyOps[P <: AnyPropertyType]
    (vRep: FieldType[vertex.type, Rep]): vertex.PropertyOps = PropertyOps(vRep)
}

//////////////////////////////////////////////////////////////////////////////////////

trait AnyNeo4jVertex extends AnyVertex {

  // bounded by Neo4j stuff in this case
  type Rep
}

/*
  ### operations

  I need to think about this
*/