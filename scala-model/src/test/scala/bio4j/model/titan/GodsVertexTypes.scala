package bio4j.model.test.titan

import bio4j.model.VertexType
import GodsProperties._

object GodsVertexTypes {
  
  case object Titan extends VertexType {
    implicit val Titan_name = this has name
    implicit val Titan_age  = this has age
  }

  case object God extends VertexType {
    implicit val God_name = this has name
    implicit val God_age  = this has age
  }

  case object Demigod extends VertexType
  implicit val Demigod_name = Demigod has name
  implicit val Demigod_age  = Demigod has age

  case object Human extends VertexType {
    implicit val Human_name = this has name
    implicit val Human_age  = this has age
  }

  case object Monster extends VertexType {
    implicit val Monster_name = this has name
  }

  case object Location extends VertexType {
    implicit val Location_name = this has name
  }

}
