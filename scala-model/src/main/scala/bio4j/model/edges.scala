package bio4j.model

trait AnyEdge {
  
  edge =>

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType

  /* The raw underlying type representing this Edge */
  type Rep

  /* Tags `Rep` with this edge type */
  import shapeless.record._

  type TaggedRep = FieldType[edge.type, Rep]
  def ->>(e: Rep): FieldType[edge.type, Rep] = field[edge.type](e)

  /* Read a property from this representation */
  abstract case class ReadProperty[P <: AnyProperty](val p: P) {
    def apply(edgeRep: TaggedRep): p.Rep
  }

  // TODO: this should go somewhere else
  case class PropertyOps(val edgeRep: TaggedRep) {

    import AnyEdgeTypeHasProperty.PropertyOf

    def get[P <: AnyProperty](p: P)
      (implicit 
        witness: PropertyOf[edge.EdgeType]#is[P],
        retrieve: ReadProperty[P]
      ) = retrieve(edgeRep)
  }
  
  implicit def propertyOps(edgeRep: TaggedRep): edge.PropertyOps = PropertyOps(edgeRep)
}

abstract class Edge[ET <: AnyEdgeType](val edgeType: ET)
  extends AnyEdge { type EdgeType = ET }
