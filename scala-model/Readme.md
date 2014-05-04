# a Scala based model

## why?

- using Bio4j from Scala code
- abstract over storage
- lots of other reasons that I don't have time to mention now

## how

### nice but hard

Everything through typeclasses, separate types from values, use records. It is _maybe_ possible, but looks pretty hard.

There are two levels here

1. graph schemas
2. graph instances

We use singleton types for schemas, and instances are to be thought of as _models_. Due to the limitations of the Scala language, some collection of types being a model is kept implicit (hahaha).

#### graph schemas

A bit of terminology

- edge, vertex types
- rel type = witness for edge having specific source and target types

#### graph instances

#### operations

``` scala
val x = user ->> store.node(id = "34sdad5as9")
// get properties
val x_id x get id
val x_name = x get name
// relationships
val x_blockRels = x out blocks
val x_blocksWhen = x_blocRels map { b => (b get date) }
// get the nodes
val followedBy ?-- follows --> x
val followers = x <-- follows --?
```

- getting properties for vertices and edges could be the same in principle; but, as source and target are specified indepedently, it could be possible to defer specification of properties for edges so that it depends on a source/target specification.
- getting the edges of a given type out of a vertex could contain edges with different...

**THE ABOVE IS WRONG**. We should only retrieve things based in a rel instance for the corresponding types. this way the arity, source/target types etc are all known and client code can easily provide implementations for it.

- client code implements how to retrieve something given a known `Rel` + arity type: source, target, return type if needed, etc
- given the right `Rel` witness in scope the typeclass above gets resolved.
- `Rel` instances should already have the corresponding `Vertex` instances of their types


-----

### simple

This is what we are going to do as a first step:

- create an abstract typeclass per vertex/edge type
- bound the wrapped type and use it as a guide for typeclass resolution
- the return type is typeclass-specific
- create abstract providers for typeclasses using abstract modules
- a Bio4j module just groups them: they are there, inside

``` scala
trait GeneModule {

  trait Gene extends Vertex
  abstract class GeneOps[V <: Gene](val v: V) {

    // note that this type is local to the typeclass
    type E <: Encodes
    def encodes: E

    // if needed this could be specialized per operation
    // by requiring a method-specific typeclass
    // etc etc 
  }

  implicit def geneOps[G <: Gene](v: G): GeneOps[G] = GeneOps(v)
}

trait Encodes extends Edge
```

I don't see any signifcant problems with this approach, and it would allow for all the things that we need

1. store/access different modules in different ways, express modules at the abstract level
2. get precise typing for everything

