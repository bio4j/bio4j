package bio4j.model.test

import edgeTypes._
import bio4j.model._

import titan.GodsSchema._

object expressions {


  val goduncle = Compose(Simple(Brother), Simple(GodFather))

  // surprisingly it works
  object buh extends Par(Simple(Brother), Simple(GodFather))

  import shapeless._
  import shapeless.ops._
  // but this not
  // val superBuh = Par(buh, Simple(Pet))
}
