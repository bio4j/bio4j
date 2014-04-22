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
  case object titanFather  extends TEdge(god,     TitanFather,  titan)
  case object godFather    extends TEdge(demigod, GodFather,    god)
  case object humanMother  extends TEdge(demigod, HumanMother,  human)
  case object brother      extends TEdge(god,     Brother,      god)
  case object pet          extends TEdge(god,     Pet,          monster)
  case object battled      extends TEdge(demigod, Battled,      monster)
  case object godLives     extends TEdge(god,     GodLives,     location)
  case object monsterLives extends TEdge(monster, MonsterLives, location)

}
