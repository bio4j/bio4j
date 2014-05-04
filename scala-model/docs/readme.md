
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

## arities and the like

The default types for the relationship actions are

``` scala
val targets: Option[List[TargetType]] = x.target(rel)
val sources: Option[List[SourceType]] = y.source(rel)
```

There are **4** different things here, with two dual couples:

1. **single-valued** given `x`, there is at most one `r` with source `x`
2. **injective** given `x`, there is at most one `r` with target `x`

<!-- <br/> -->

1. **everywhere-defined** given `x`, there is at least one `r` with source `x`
2. **surjective** given `x`, there is at least one `r` with target `x`


In terms of the types above, we have

- **single-valued** and **everywhere-defined** affect the **target** type
    + **single-valued** means no `List`,
    + **everywhere-defined** means no `Option`
- **injective** and **surjective** affect the **source** type
    + **injective** means no `List`,
    + **surjective** means no `Option`

``` scala
// imaginary trait
trait AnyRel {

  type SourceType
  type TargetType
}

trait AnySourceArity {

  type Rel
  val rel: Rel
  type SourceResult
}
trait DefaultSourceArity extends AnySourceArity {

  type Rel
  val rel: Rel
  type SourceResult = Option[List[rel.SourceType]]
}

trait SingleValued                  extends AnySourceArity { type SourceResult = Option[rel.SourceType] }
trait EverywhereDefined             extends AnySourceArity { type SourceResult = List[rel.SourceType] }
trait SingleValuedEverywhereDefined extends AnySourceArity { type SourceResult = rel.SourceType }
```

Examples can help here. 

### single-valued, injective

This means that

``` scala
// x fatherOf y
// single-valued
val father Option[Human] = someone source fatherOf
// injective: the dual rel
val children: Option[Human] = other target fatherOf
```

### everywhere defined, surjective

``` scala
// todo
```

## vertex properties as edges

The idea is to

1. use the edge label as the key for the property
2. move the value to the target node (literal in this case)

It is key (hahaha) to keep in mind that all this is about describing the types, not how or where they are stored. So, no problem with taking a vertex to be a (essentially named) literal.

> should "normal" vertices and literals be treated differently?

## vertex operations

The most basic thing that we want is to get edges (of a fixed label) with source `v`. For that, we have

- the vertex type
- the edge type (including the label)

We should return the type derived from the relationship target type (and its arity).