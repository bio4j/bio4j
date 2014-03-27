package bio4j.model.test

import bio4j.model._
import shapeless.syntax.singleton._
import shapeless._ 
import shapeless.record._

object vertices {
  
  import vertexTypes._
  import propertyTypes._
  import shapeless.record.FieldType

  // represent a user as a String (why not?)
  case object User extends VertexOf(user) { type Rep = String }
  // now ints
  case object IntUser extends VertexOf(user) { type Rep = Int }

  val uh = User ->> "Alexey Alekhin"
  val uhoh = IntUser ->> 22342

  case object readName extends VertexProperty(User, isPublic) {

    def apply(vertex: String): Boolean = {

      vertex match {
        case "Alexey Alekhin" => true
        case _ => false
      }
      
    }
  }

  

  case class readByLabel[PT <: AnyPropertyType](val propertyType: PT) {

    // just take the node as a Neo4j Node and read the property using its `String` representation
  }

  /*
    How should we provide the the get instances? rels are specified statically and they have a source; we should just require that

    - `VertexOf` has the right type
    
    So the signature for the getRel typeclass should just declare a VertexOf instance and the given rel spec.
  */
}