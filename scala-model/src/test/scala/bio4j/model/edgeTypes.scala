package bio4j.model.test

object edgeTypes {
  
  import bio4j.model._
  import propertyTypes._
  import vertexTypes._

  case object MemberOf extends EdgeType {

    this has isPublic
  }
  
  // add some more props externally  
  // directly
  implicit val withSince = EdgeTypeHasProperty(MemberOf, since)
  // through ops
  implicit val withValidUntil = MemberOf has validUntil

  // specify a source and target
  case object usersMembersOfOrgs extends RelType(many(User), MemberOf, many(Org))

  case object Owns extends EdgeType {

    implicit val x = this has since
    implicit val y = this has validUntil
  }

  // explicit witness
  case object usersOwnOrgs extends RelType(one(User), Owns, many(Org))
  // another one
  case object orgsOwnOrgs extends RelType(many(Org), Owns, one(Org))

  // pretty cool DSL
  implicit val thisIsSoCoolItScaresMe = many(User) -- MemberOf --> one(Org)
  implicit val manymanymany = many(Org) -- Owns --> many(Org)

}
