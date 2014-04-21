package bio4j.model.test.titan

import bio4j.model._
import GodsEdgeTypes._

object GodsEdges {

  case object titanFather  extends TEdge(TitanFather)
  case object godFather    extends TEdge(GodFather)
  case object humanMother  extends TEdge(HumanMother)
  case object godBrother   extends TEdge(GodBrother)
  case object pet          extends TEdge(Pet)
  case object battled      extends TEdge(Battled) 
  case object godLives     extends TEdge(GodLives) 
  case object monsterLives extends TEdge(MonsterLives) 

}
