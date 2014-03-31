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
  case object usersMembersOfOrgs extends RelType(User, MemberOf, Org)
  // and now its arity
  implicit val arity = usersMembersOfOrgs manyToMany

  case object Owns extends EdgeType {

    implicit val x = this has since
    implicit val y = this has validUntil
  }

  // explicit witness
  case object usersOwnOrgs extends RelType(User, Owns, Org)
  // its arity
  implicit val usersOwnOrgsArity = usersOwnOrgs oneToMany
  // another one
  case object orgsOwnOrgs extends RelType(Org, Owns, Org)
  implicit val orgsOwnOrgsArity = orgsOwnOrgs manyToOne

  import DeclareRelTypes._
  // pretty cool DSL
  implicit val thisIsSoCoolItScaresMe = User -- MemberOf --> Org manyToOne

}