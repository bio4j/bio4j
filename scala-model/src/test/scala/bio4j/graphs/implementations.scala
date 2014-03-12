package bio4j.graphs.test

import bio4j.graphs._

object vertices {
  
  import vertexTypes._

  // represent a user as a String (why not?)
  case object User extends VertexOf(user) { type Rep = String }
  // now ints
  case object IntUser extends VertexOf(user) { type Rep = Int }

  val uh = User ->> "Alexey Alekhin"
  val uhoh = IntUser ->> 22342

  /*
    How should we provide the the get instances? rels are specified statically and they have a source; we should just require that

    - `VertexOf` has the right type
    
    So the signature for the getRel typeclass should just declare a VertexOf instance and the given rel spec.
  */
}