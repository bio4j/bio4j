package bio4j.graphs.test

object propertyTypes {

  import bio4j.graphs.PropertyType
  
  case object since extends PropertyType[Int]
  case object validUntil extends PropertyType[Int]
  case object isPublic extends PropertyType[Boolean]
}