package bio4j.model.test.titan

import bio4j.model.Property

object GodsProperties {
  
  case object name extends Property[String]
  case object age extends Property[Int]

  // instead of this property we will create REAL types (because we are cool)
  // case object `type` extends Property[String]

  case object time extends Property[Int]
  case object reason extends Property[String]

  // This is something advanced:
  import com.thinkaurelius.titan.core.attribute.Geoshape
  case object place extends Property[Geoshape]

}
