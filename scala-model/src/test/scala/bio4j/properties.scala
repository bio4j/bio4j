package bio4j.model.test

import bio4j.model._

object TestGetPropertiesFromNodes {

  import shapeless.record._
  
  case object name extends Property[String]
  case object age extends Property[Int]
  case object email extends Property[String]

  case object User extends Node {

    import NodeSchemas._

    implicit val hasAge = this has age manyToOne
    implicit val hasName = this has name manyToOne
  }

  case object UserWithEmail {

    import NodeSchemas._
    // externally defined
    implicit val userHasEmail = User has email manyToMany
  }

}