package bio4j.model

import shapeless.record._

trait AnyRel extends Tagged {

  rel =>

  type RelType <: AnyRelType
  val relType: RelType

  /* Source-Edge-Target types */
  type SourceType = relType.SourceType
  val sourceType: SourceType = relType.sourceType

  type TargetType = relType.TargetType
  val targetType: TargetType = relType.targetType

  /* Get source-edge-target from this representation */
  abstract case class GetSource[V <: AnyVertex.ofType[SourceType]](val v: V) {
    def apply(relRep: TaggedRep): v.TaggedRep
  }
  abstract case class GetTarget[V <: AnyVertex.ofType[TargetType]](val v: V) {
    def apply(relRep: TaggedRep): v.TaggedRep
  }

  implicit class RelOps(val relRep: TaggedRep) {
    import AnyRelType._
    def source[V <: AnyVertex.ofType[SourceType]](implicit getter: GetSource[V]) = getter(relRep)
    def target[V <: AnyVertex.ofType[TargetType]](implicit getter: GetTarget[V]) = getter(relRep)
  }


  /* Read a property from this representation */
  abstract case class ReadProperty[P <: AnyProperty](val p: P) {
    def apply(rep: TaggedRep): p.Rep
  }

  implicit class PropertyOps(val relRep: TaggedRep) {
    import AnyRelTypeHasProperty.PropertyOf
    def get[P <: AnyProperty: PropertyOf[rel.RelType]#is](p: P)
      (implicit 
        retrieve: ReadProperty[P]
      ) = retrieve(relRep)
  }
}

case class Rel[RT <: AnyRelType](val relType: RT) extends AnyRel {

  rel =>

  type RelType = RT
}


/* TODO: do something with it:

trait AnySource {

  type Rel <: AnyRel
  val rel: Rel

  type Source <: AnyVertex { type VertexType <: Rel#SourceType }
  val source: Source

  def apply(relRep: FieldType[rel.type, rel.Rep]): 
    FieldType[source.type, source.Rep]
}

abstract class Source[
  R <: AnyRel,
  S <: AnyVertex { type VertexType <: R#SourceType }
  ](val rel: R, val source: S) extends AnySource {

    type Rel = R
    type Source = S
  }
*/
