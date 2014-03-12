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
  implicit val withSince = Has(memberOf, since)
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

  case object usersOwnOrgs extends Rel(user, owns, org)
  implicit val usersOwnOrgsArity = usersOwnOrgs oneToMany

  case object orgsOwnOrgs extends Rel(org, owns, org)
  implicit val orgsOwnOrgsArity = orgsOwnOrgs manyToOne


}