package bio4j.model.test

import bio4j.model._

import edgeTypes._
import vertexTypes._

object relTypes {
  // specify a source and target
  case object UsersMembersOfOrgs extends RelType(many(User), MemberOf, many(Org))

  // explicit witness
  case object UserOwnOrgs extends RelType(one(User), Owns, many(Org))
  // another one
  case object OrgsOwnOrgs extends RelType(many(Org), Owns, one(Org))

  // pretty cool DSL
  implicit val thisIsSoCoolItScaresMe = many(User) -- MemberOf --> one(Org)
  implicit val manymanymany = many(Org) -- Owns --> many(Org)

}
