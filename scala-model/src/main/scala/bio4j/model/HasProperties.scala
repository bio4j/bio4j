package bio4j.model

trait HasProperties { self: AnyDenotation =>

  /* Read a property from this representation */
  import SmthHasProperty._

  trait AnyGetProperty {
    type Property <: AnyProperty
    val p: Property

    def apply(rep: self.TaggedRep): p.Rep
  }

  abstract class GetProperty[P <: AnyProperty](val p: P) 
      extends AnyGetProperty { type Property = P }

  implicit def propertyOps(rep: self.TaggedRep): PropertyOps = PropertyOps(rep)
  case class   PropertyOps(rep: self.TaggedRep) {

    def get[P <: AnyProperty: PropertyOf[self.Tpe]#is](p: P)
      (implicit mkGetter: P => GetProperty[P]): P#Rep = mkGetter(p).apply(rep)

  }

  /* If have just an independent getter for a particular property: */
  implicit def idGetter[P <: AnyProperty: PropertyOf[self.Tpe]#is](p: P)
      (implicit getter: GetProperty[P]) = getter

}