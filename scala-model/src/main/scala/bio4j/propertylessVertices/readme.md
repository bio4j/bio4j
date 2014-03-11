
# property-less vertices

A model where only edges can have properties.

## what are properties

We start with a universe of types `V` that corresponds to the types that we can use as values for properties. For simplicity, keys are assumed to be `String`s; they could come from another universe `K` if needed.

The basic approach could be summarized as

- properties are defined independently of nodes, identified at the type level by a singleton type
- a key-value pair is encoded as the value type `V` tagged with the corresponding singleton type (shapeless records)

``` scala
case object name extends Property[String]
case object age  extends Property[Int]

val nm = name ->> "Alexey"
val ag = age ->> 135
val nm2 = name ->> "Ataúlfo"
val ag2 = age ->> 17
val nm3 = name ->> "Sigerico"

// optional: declare properties from literals
val dnm = "name" ->> "Unsafe"
val dag = "age" ->> 368
```

## declaring properties for edges

Edges are declared as singleton types, where the label comes from the `toString` method called on them (thus make them `case object`s).

``` scala
// the label = knows.label = knows.toString = "knows"
case object knows extends Edge {

  // this way you're forcing everyone to have at least the 'since' property on 'knows'
  implicit val since = this has since
}

// now somewhere else
case object score extends Property[Int]

// and at some other place
object ExtraStuff {

  implicit val knowsScore = knows has score
}
```

## assigning source and target to edges

This is done again through witnesses. Given the vertex types `user` and `org`, and the edge type `memberOf`,

``` scala
object OrgsGraph {

  implicit val umo = user -- memberOf --> org
}

// or use abstract modules
abstract class Rel[
  N <: Vertex: Singleton#is,
  E <: Edge: Singleton#is,
  M <: Vertex: Singleton#is
]
(
  val source: N,
  val edge: E,
  val target: M
) 
{
  implicit val set = source -- edge --> target 
}
case object userMemberOfOrg extends Rel(user, memberOf, org)

// somewhere else
object doSomething {

  import userMemberOfOrg._
  // do something
}
```

I think this is flexible enough, and with abstract modules could work well for what we want.

#### terminology

It is maybe a good idea to use _Relationship_ or _Rel_ for the `Edge` together with the specification of the source and target types.

## vertex properties as edges

The idea is to

1. use the edge label as the key for the property
2. move the value to the target node (literal in this case)

It is key (hahaha) to keep in mind that all this is about describing the types, not how or where they are stored. So, no problem with taking a node to be a (essentially named) literal.

