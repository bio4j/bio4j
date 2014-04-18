
```scala
package bio4j.model.test

import bio4j.model._

import edgeTypes._
import vertexTypes._

object relTypes {
  // specify a source and target
  case object UsersMembersOfOrgs extends RelType(many(User), MemberOf, many(Org))

  // explicit witness
  case object UserOwnOrgs extends RelType(one(User), Owns, many(Org))
  // another one
  case object OrgsOwnOrgs extends RelType(many(Org), Owns, one(Org))

  // pretty cool DSL
  implicit val thisIsSoCoolItScaresMe = many(User) -- MemberOf --> one(Org)
  implicit val manymanymany = many(Org) -- Owns --> many(Org)

}

```


------

### Index

+ src
  + test
    + scala
      + bio4j
        + model
          + [properties.scala][test/scala/bio4j/model/properties.scala]
          + [edges.scala][test/scala/bio4j/model/edges.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]
          + [rels.scala][test/scala/bio4j/model/rels.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [relTypes.scala][test/scala/bio4j/model/relTypes.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
  + main
    + scala
      + bio4j
        + model
          + [properties.scala][main/scala/bio4j/model/properties.scala]
          + [reps.scala][main/scala/bio4j/model/reps.scala]
          + [edges.scala][main/scala/bio4j/model/edges.scala]
          + [vertices.scala][main/scala/bio4j/model/vertices.scala]
          + [relationships.scala][main/scala/bio4j/model/relationships.scala]
          + [relationshipTypes.scala][main/scala/bio4j/model/relationshipTypes.scala]
          + [vertexTypes.scala][main/scala/bio4j/model/vertexTypes.scala]
          + [edgeTypes.scala][main/scala/bio4j/model/edgeTypes.scala]

[test/scala/bio4j/model/properties.scala]: properties.scala.md
[test/scala/bio4j/model/edges.scala]: edges.scala.md
[test/scala/bio4j/model/vertices.scala]: vertices.scala.md
[test/scala/bio4j/model/rels.scala]: rels.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: vertexTypes.scala.md
[test/scala/bio4j/model/relTypes.scala]: relTypes.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: edgeTypes.scala.md
[main/scala/bio4j/model/properties.scala]: ../../../../main/scala/bio4j/model/properties.scala.md
[main/scala/bio4j/model/reps.scala]: ../../../../main/scala/bio4j/model/reps.scala.md
[main/scala/bio4j/model/edges.scala]: ../../../../main/scala/bio4j/model/edges.scala.md
[main/scala/bio4j/model/vertices.scala]: ../../../../main/scala/bio4j/model/vertices.scala.md
[main/scala/bio4j/model/relationships.scala]: ../../../../main/scala/bio4j/model/relationships.scala.md
[main/scala/bio4j/model/relationshipTypes.scala]: ../../../../main/scala/bio4j/model/relationshipTypes.scala.md
[main/scala/bio4j/model/vertexTypes.scala]: ../../../../main/scala/bio4j/model/vertexTypes.scala.md
[main/scala/bio4j/model/edgeTypes.scala]: ../../../../main/scala/bio4j/model/edgeTypes.scala.md