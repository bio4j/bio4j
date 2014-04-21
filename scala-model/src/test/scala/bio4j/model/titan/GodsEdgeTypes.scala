package bio4j.model.test.titan

import bio4j.model._
import GodsProperties._
import GodsVertexTypes._

object GodsEdgeTypes {
  
  /* Family relationships */

  // gods can have a titan father
  case object TitanFather extends ManyToOne(God, Titan)

  // demigods can have a god father
  case object GodFather extends ManyToOne(Demigod, God)

  // demigods can have a human mother
  case object HumanMother extends ManyToOne(Demigod, Human)

  // gods can be brothers with gods
  case object GodBrother extends ManyToMany(God, God)

  // TODO: other combinations are also possible, but are just not needed for the example

  /* Other relationships */

  // a god can have moster pets
  case object Pet extends OneToMany(God, Monster)

  // demigods battle with monsters
  case object Battled extends ManyToMany(Demigod, Monster) {
    implicit val _time  = this has time
    implicit val _place = this has place
  }

  // gods live in some location
  case object GodLives extends ManyToOne(God, Location) {
    implicit val _reason = this has reason
  }

  // monsters live in some location (without any reason)
  case object MonsterLives extends ManyToOne(Monster, Location) 

}
