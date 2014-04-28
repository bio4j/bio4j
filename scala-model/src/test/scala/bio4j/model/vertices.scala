package bio4j.model.test

import vertexTypes._
import properties._
import bio4j.model._
import shapeless.record.FieldType

object vertices {

  /* A representation of the `User` vertex type; note how it is external to `User`, and it doesn't mention the corresponding type at all */
  case class UserImpl(
    val id: String,
    val name: String,
    val since: Int
  )
  
  case object user extends Vertex(User) { self =>
    /* Now users can be created with `user ->> UserImpl(...)`  */
    type Rep = UserImpl

    /* Provide implicits here (or elsewhere) for all (or some) properties */
    implicit object readId extends GetProperty(id) {
      def apply(rep: user.TaggedRep): id.Rep = (rep: user.Rep).id
    }
    implicit object readSince extends GetProperty(since) {
      def apply(rep: self.TaggedRep): since.Rep = (rep: self.Rep).since
    }
  }

  case object org extends Vertex(Org) { self =>
    /*
      We are lazy, so we will use the same representation for orgs
      even though we care only about the `name` property
    */
    type Rep = UserImpl

    implicit object readName extends GetProperty(name) {
      def apply(rep: self.TaggedRep) = rep.name
    }
  }

}

class VertexSuite extends org.scalatest.FunSuite {

  import vertices._  

  test("retrieve vertex properties") {

    import vertices.user._
    import vertexTypes.User._
    val u = user ->> UserImpl(id = "1ad3a34df", name = "Robustiano Satrústegui", since = 2349965)

    val u_id = u.get(id)
    /* 
      Too bad, no witness for `since`, so `u.get(since)` won't compile.
      But we can add it here!
    */
    implicit val userSince = User has since
    val u_since = u.get(since)
    val u_since_again = u get since

    /* 
      We can also add a retriever for the `name` property externally:
    */
    implicit object readUserName extends GetProperty(name) {
      def apply(rep: user.TaggedRep) = rep.name
    }

    assert((u get id) === "1ad3a34df")
    assert((u get name) === "Robustiano Satrústegui")
    assert((u get since) === 2349965)


    /* 
      Again, we are using user's representation for org, 
      even though it has only the `name` property
    */
    import vertices.org._
    import vertexTypes.Org._
    val o = org ->> UserImpl(id = "NYSE:ORCL", name = "Oracle Inc.", since = 1977)

    assert((o get name) === "Oracle Inc.")

    /*
      Now we realized, that we can do more things with this 
      representation of org so we just implement it in place:
    */
    implicit val orgFounded = Org has since
    implicit object readOrgSince extends org.GetProperty(since) {
      def apply(rep: org.TaggedRep) = rep.since
    }
    assert((o get since) === 1977)

  }
}
