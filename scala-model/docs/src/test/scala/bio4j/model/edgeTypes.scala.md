
```scala
package bio4j.model.test

object edgeTypes {
  
  import bio4j.model._
  import propertyTypes._
  import vertexTypes._

  case object MemberOf extends EdgeType {

    this has isPublic
  }
  
  // add some more props externally  
  // directly
  implicit val withSince = EdgeTypeHasProperty(MemberOf, since)
  // through ops
  implicit val withValidUntil = MemberOf has validUntil

  // specify a source and target
  case object usersMembersOfOrgs extends RelType(User, MemberOf, Org)
  // and now its arity
  implicit val arity = usersMembersOfOrgs manyToMany

  case object Owns extends EdgeType {

    implicit val x = this has since
    implicit val y = this has validUntil
  }

  // explicit witness
  case object usersOwnOrgs extends RelType(User, Owns, Org)
  // its arity
  implicit val usersOwnOrgsArity = usersOwnOrgs oneToMany
  // another one
  case object orgsOwnOrgs extends RelType(Org, Owns, Org)
  implicit val orgsOwnOrgsArity = orgsOwnOrgs manyToOne

  import DeclareRelTypes._
  // pretty cool DSL
  implicit val thisIsSoCoolItScaresMe = User -- MemberOf --> Org manyToOne

}
```


------

### Index

+ src
  + test
    + scala
      + bio4j
        + model
          + [propertyTypes.scala][test/scala/bio4j/model/propertyTypes.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]
          + [relationships.scala][test/scala/bio4j/model/relationships.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
  + main
    + scala
      + bio4j
        + model
          + [properties.scala][main/scala/bio4j/model/properties.scala]
          + [edges.scala][main/scala/bio4j/model/edges.scala]
          + [vertices.scala][main/scala/bio4j/model/vertices.scala]
          + [relationships.scala][main/scala/bio4j/model/relationships.scala]
          + [relationshipTypes.scala][main/scala/bio4j/model/relationshipTypes.scala]
          + [vertexTypes.scala][main/scala/bio4j/model/vertexTypes.scala]
          + [edgeTypes.scala][main/scala/bio4j/model/edgeTypes.scala]

[test/scala/bio4j/model/propertyTypes.scala]: propertyTypes.scala.md
[test/scala/bio4j/model/vertices.scala]: vertices.scala.md
[test/scala/bio4j/model/relationships.scala]: relationships.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: vertexTypes.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: edgeTypes.scala.md
[main/scala/bio4j/model/properties.scala]: ../../../../main/scala/bio4j/model/properties.scala.md
[main/scala/bio4j/model/edges.scala]: ../../../../main/scala/bio4j/model/edges.scala.md
[main/scala/bio4j/model/vertices.scala]: ../../../../main/scala/bio4j/model/vertices.scala.md
[main/scala/bio4j/model/relationships.scala]: ../../../../main/scala/bio4j/model/relationships.scala.md
[main/scala/bio4j/model/relationshipTypes.scala]: ../../../../main/scala/bio4j/model/relationshipTypes.scala.md
[main/scala/bio4j/model/vertexTypes.scala]: ../../../../main/scala/bio4j/model/vertexTypes.scala.md
[main/scala/bio4j/model/edgeTypes.scala]: ../../../../main/scala/bio4j/model/edgeTypes.scala.md