package bio4j.model.test

import bio4j.model._

object testEdges {
  

  case object User extends Node
  case object Tweet extends Node
  case object twit extends -->(User, Tweet)
}