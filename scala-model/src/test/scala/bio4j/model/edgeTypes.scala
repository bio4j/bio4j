package bio4j.model.test

import bio4j.model._
import vertexTypes._
import properties._

object edgeTypes {

  case object MemberOf  extends ManyToMany  ( User, "memberOf", Org )

  implicit val memberOfHasValidUntil  = MemberOf has validUntil
  implicit val memberOfHasSince       = MemberOf has since

  case object   Owns      extends ManyToOne   ( User, "owns",     Org )

  implicit val  ownsHasSince           = Owns has since
  implicit val  ownsHasValidUntil      = Owns has validUntil

}
