package bio4j.model.test.titan

import GodsSchema._

object GodsImplementation {

  /*
    ### Vertices
  */
  case object titan    extends TVertex(Titan)
  case object god      extends TVertex(God)
  case object demigod  extends TVertex(Demigod)
  case object human    extends TVertex(Human)
  case object monster  extends TVertex(Monster)
  case object location extends TVertex(Location)

  /*
    ### Edges
  */
  case object titanFather  extends TEdge(TitanFather)
  case object godFather    extends TEdge(GodFather)
  case object humanMother  extends TEdge(HumanMother)
  case object godBrother   extends TEdge(GodBrother)
  case object pet          extends TEdge(Pet)
  case object battled      extends TEdge(Battled) 
  case object godLives     extends TEdge(GodLives) 
  case object monsterLives extends TEdge(MonsterLives) 
}
