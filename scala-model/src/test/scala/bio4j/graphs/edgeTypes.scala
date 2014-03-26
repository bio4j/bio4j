package bio4j.graphs.test

object edgeTypes {
  
  import bio4j.graphs._
  import propertyTypes._
  import vertexTypes._

  case object memberOf extends EdgeType {

    this has isPublic
  }
  
  // add some more props externally  
  // directly
  implicit val withSince = EdgeTypeHas(memberOf, since)
  // through ops
  implicit val withValidUntil = memberOf has validUntil

  // specify a source and target
  case object usersMembersOfOrgs extends Rel(user, memberOf, org)
  // and now its arity
  implicit val arity = usersMembersOfOrgs manyToMany

  case object owns extends EdgeType {

    implicit val x = this has since
    implicit val y = this has validUntil
  }

  // explicit witness
  case object usersOwnOrgs extends Rel(user, owns, org)
  // its arity
  implicit val usersOwnOrgsArity = usersOwnOrgs oneToMany
  // another one
  case object orgsOwnOrgs extends Rel(org, owns, org)
  implicit val orgsOwnOrgsArity = orgsOwnOrgs manyToOne

  import DeclareRels._
  // pretty cool DSL
  implicit val thisIsSoCoolItScaresMe = user -- memberOf --> org manyToOne

}