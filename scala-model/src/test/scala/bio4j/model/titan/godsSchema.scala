package bio4j.model.test.titan

import bio4j.model._

/* 
  ## The Graph of the Gods Schema

  See [Titan tutorial](https://github.com/thinkaurelius/titan/wiki/Getting-Started) and
  [the code](https://github.com/thinkaurelius/titan/blob/master/titan-core/src/main/java/com/thinkaurelius/titan/example/GraphOfTheGodsFactory.java) for the reference.
*/

object GodsSchema {

  /*
    ### Properties
  */
  case object name extends Property[String]
  case object age extends Property[Int]
  // instead of this property we will create REAL types (because we are cool)
  // case object `type` extends Property[String]
  case object time extends Property[Int]
  case object reason extends Property[String]
  // This is something advanced:
  import com.thinkaurelius.titan.core.attribute.Geoshape
  case object place extends Property[Geoshape]

  /*
    ### Vertices
  */
  case object Titan extends AnyVertexType { val label = "titan" }
  implicit val Titan_name = Titan has name
  implicit val Titan_age  = Titan has age

  case object God extends AnyVertexType { val label = "god" }
  implicit val God_name = God has name
  implicit val God_age  = God has age

  case object Demigod extends AnyVertexType { val label = "demigod" }
  implicit val Demigod_name = Demigod has name
  implicit val Demigod_age  = Demigod has age

  case object Human extends AnyVertexType { val label = "human" }
  implicit val Human_name = Human has name
  implicit val Human_age  = Human has age

  case object Monster extends AnyVertexType { val label = "monster" }
  implicit val Monster_name = Monster has name
  
  case object Location extends AnyVertexType { val label = "location" }
  implicit val Location_name = Location has name

  /*
    ### Edges
  */

  /* 
    #### Family relationships 
  */

  /* Gods can have a Titan father */
  case object TitanFather extends ManyToOne(God, Titan) { override val label = "father" }

  /* Demigods can have a God father */
  case object GodFather extends ManyToOne(Demigod, God) { override val label = "father" }

  /* 
    Note, that GodFather and TitanFather have the same label, but the two things are needed, 
    because of different source/target types
  */

  /* Demigods can have a Human mother */
  case object HumanMother extends ManyToOne(Demigod, Human) { override val label = "mother" }

  /* Gods can be brothers with Gods */
  case object GodBrother extends ManyToMany(God, God) { override val label = "brother" }

  // TODO: other combinations are also possible, but are just not needed for the example


  /* 
    #### Other relationships 
  */

  /* a God can have moster pets */
  case object Pet extends OneToMany(God, Monster) { override val label = "pet" }

  /* Demigods battle with Monsters */
  case object Battled extends ManyToMany(Demigod, Monster) { override val label = "battled" }
  implicit val Battled_time  = Battled has time
  implicit val Battled_place = Battled has place
 

  /* Gods live in some Location and they have a reason for that */
  case object GodLives extends ManyToOne(God, Location) { override val label = "lives" }
  implicit val GodLives_reason = GodLives has reason

  /* Monsters live in some Location (without any reason) */
  case object MonsterLives extends ManyToOne(Monster, Location) { override val label = "lives" }

}
