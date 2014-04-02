package bio4j.model.test

import vertexTypes._
import propertyTypes._
import bio4j.model._
import shapeless.record.FieldType

object vertices {

  // stupid implementation
    case class UserImpl(
      val id: String,
      val name: String,
      val since: Int
    )
  
  // now users are user ->> "hey Joe" 
  case object user extends Vertex(User) { type Rep = UserImpl;

    
    // provide implicits here for all properties
    // or elsewhere
    implicit object readId extends ReadProperty(id) {

      @Override
      def apply(vRep: FieldType[user.type, UserImpl]): String = (vRep:UserImpl).id
    }

    implicit object readSince extends ReadProperty(since) {

      @Override
      def apply(vRep: FieldType[user.type, user.Rep]): since.Rep = (vRep:UserImpl).since
    }
  }
}

class UserSuite extends org.scalatest.FunSuite {

  import vertices._  

  test("retrieve properties") {

    import vertexTypes.User._
    import user._
    val x = user ->> UserImpl(
                                id = "1ad3a34df",
                                name = "Robustiano Satrústegui",
                                since = 2349965
                              )

    val x_id = x.get(id)
    // too bad, no witness for since
    // val x_since = x.get(since)
    // add it here!
    // implicit val Iknowit = User has since
    // but no way of retrieving it!
    // hahaha
    // val x_since = x.get(since)
    val x_since = x.get(since)

    println (user +" get "+ id +": "+ x_id)
    println (user +" get "+ since +": "+ x_since.toString)

  }
}