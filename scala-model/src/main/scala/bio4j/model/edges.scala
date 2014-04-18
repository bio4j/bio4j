package bio4j.model

trait AnyEdge extends Tagged {
  
  edge =>

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType

  /* Read a property from this representation */
  abstract case class ReadProperty[P <: AnyProperty](val p: P) {
    def apply(edgeRep: TaggedRep): p.Rep
  }

  // TODO: this should go somewhere else
  implicit class PropertyOps(val edgeRep: TaggedRep) {

    import AnyEdgeTypeHasProperty.PropertyOf

    def get[P <: AnyProperty: PropertyOf[edge.EdgeType]#is](p: P)
      (implicit 
        retrieve: ReadProperty[P]
      ) = retrieve(edgeRep)
  }
}

abstract class Edge[ET <: AnyEdgeType](val edgeType: ET)
  extends AnyEdge { type EdgeType = ET }
