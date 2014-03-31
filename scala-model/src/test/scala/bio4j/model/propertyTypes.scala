package bio4j.model.test

object propertyTypes {

  import bio4j.model.Property
  
  case object since extends Property[Int]
  case object validUntil extends Property[Int]
  case object name extends Property[String]
  case object isPublic extends Property[Boolean]
  case object id extends Property[String]
}