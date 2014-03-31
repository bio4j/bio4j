package bio4j.model.test

object vertexTypes {
  
  import bio4j.model.VertexType

  import bio4j.model._
  import propertyTypes._

  // just labels and witnesses for properties
  case object User extends VertexType {

    implicit val _id     = this has id
    implicit val _since  = this has since
    implicit val _name   = this has name
  }

  case object Org extends VertexType  
}