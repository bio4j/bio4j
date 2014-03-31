package bio4j.model.test

import vertexTypes._
import propertyTypes._
import bio4j.model._
import shapeless.record.FieldType

object vertices {
  
  // now users are user ->> "hey Joe" 
  case object user extends Vertex(User) { type Rep = String;

    // provide implicits here for all properties
    // or elsewhere
    implicit object readId extends ReadProperty(id) {

      // constant ids :)
      // TODO: why String methods doesn't work directly here?
      def apply(vRep: FieldType[user.type, id.Rep]): String = (vRep:String)+" id: "+ "1234"
    }
  }
}

class UserSuite extends org.scalatest.FunSuite {

  import vertices._  

  test("retrieve properties") {

    import vertexTypes.User._
    import user._
    val x = user ->> "hey Joe"
    val x_id: id.Rep = x.get(id)
    // too bad, no witness for since
    // val x_since = x.get(since)
    // add it here!
    implicit val Iknowit = User has since
    // but no way of retrieving it!
    // hahaha
    // val x_since = x.get(since)

    println (x_id)

  }
}