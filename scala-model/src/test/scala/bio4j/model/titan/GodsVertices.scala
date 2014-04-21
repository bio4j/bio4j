package bio4j.model.test.titan

import bio4j.model._
import GodsProperties._
import GodsVertexTypes._

object GodsVertices {

  case object titan    extends TVertex(Titan)
  case object god      extends TVertex(God)
  case object demigod  extends TVertex(Demigod)
  case object human    extends TVertex(Human)
  case object monster  extends TVertex(Monster)
  case object location extends TVertex(Location)

}
