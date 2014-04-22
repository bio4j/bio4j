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

  case object alternativeTitan extends TitanImpl with AnyTVertex {

    import bio4j.model.AnyEdge

    implicit val _name: GetProperty[name.type] = unsafeGetProperty(name)
    implicit val _age:  GetProperty[age.type] = unsafeGetProperty(age)

    implicit def fatherIn[
      E <: Singleton with AnyEdge { type Tpe = TitanFather.type }
    ](e: E): RetrieveInEdge[E] = unsafeRetrieveManyInEdge[E](e)
  }

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
