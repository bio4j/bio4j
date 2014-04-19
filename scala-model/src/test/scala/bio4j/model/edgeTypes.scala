package bio4j.model.test

import bio4j.model._
import vertexTypes._
import properties._

object edgeTypes {
  // specify a source and target
  case object MemberOf extends EdgeType(many(User), many(Org))
  // add some more props externally  
  // directly
  implicit val withSince = EdgeTypeHasProperty(MemberOf, since)
  // through ops
  implicit val withValidUntil = MemberOf has validUntil

  // explicit witness
  case object Owns extends EdgeType(one(User), many(Org)){
    implicit val x = this has since
    implicit val y = this has validUntil
  }
  // another one
  // case object OrgsOwnOrgs extends EdgeType(many(Org), Owns, one(Org))

  // pretty cool DSL
  implicit val thisIsSoCoolItScaresMe = many(User) -- "MemberOf" --> one(Org)
  implicit val manymanymany = many(Org) -- "Owns" --> many(Org)

}
