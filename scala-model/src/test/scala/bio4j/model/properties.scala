package bio4j.model.test

object propertyTypes {

  import bio4j.model.PropertyType
  
  case object since extends PropertyType[Int]
  case object validUntil extends PropertyType[Int]
  case object isPublic extends PropertyType[Boolean]
}