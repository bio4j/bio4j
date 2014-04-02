package bio4j.model

trait AnyEdge {
  
  edge =>

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType

  // the raw underlying type representing this Edge
  type Rep

  import shapeless.record._

  /*
    Tags a Rep with this edge type
  */
  def ->>(v: Rep): FieldType[edge.type, Rep] = field[edge.type](v)

  // read a property from this representation
  abstract case class ReadProperty[P <: AnyProperty](val p: P) {

    def apply(edgeRep: FieldType[edge.type, Rep]): p.Rep
  }


  // this should go somewhere else
  case class PropertyOps(val edgeRep: FieldType[edge.type, Rep]) {

    import AnyEdgeTypeHasProperty.PropertyOf

    def get[P <: AnyProperty]
    (p: P)
    (implicit 
      witness: PropertyOf[edge.EdgeType]#is[P],
      retrieve: ReadProperty[P]
    ) = retrieve(edgeRep)
  }
  
  implicit def propertyOps[P <: AnyProperty]
    (edgeRep: FieldType[edge.type, Rep]): edge.PropertyOps = PropertyOps(edgeRep)
}