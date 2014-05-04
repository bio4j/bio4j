
The basic idea is to treat literals and nodes in the same way. This means that nodes are different just based in

- their type, and
- the intrinsic graph structure

Given this, give `n,m : User` their properties are considered as edges (named as the property); their scope should probably be restricted to the corresponding node type, `User` in this case. This gives you a nice understanding of indexes at the node level: 
they are just a way of traversing these edges in the other direction.

Now, what's the difference between this and a "normal" so to say Edge? There should be none, as far as I understand. The only one is that they can have properties.

It is nice that this matches with one the options suggested for Tinkerpop 3: 

- https://groups.google.com/d/msg/gremlin-users/KqBv3jlj6xM/Fz7QLltUnxwJ

All this means that the fields and relationships that a node has are

- local to that type
- declared externally (if needed)
- accessed through typeclasses

A natural way of doing this for literals is as key-values (records). But, why not take it a bit further and use the same mechanism for Nodes? An index then should give you a field of the given type `FieldType[N,V]` where `V` is the node representation.

### restrictions

When you declare a property, you can

- restrict the source type and its arity
- restrict the target type and its arity

Note that at this point one does not need to choose between properties and edges. Essentially, a property is just and edge which

1. has as source a non-primitive type, as target a primitive one
2. for a given source it is unique

_I need to remind myself about the view of properties as a special kind of edges_

This restrictions could be encoded statically. Let's review it from a semantics point of view.

#### arities

All this are _local_ predicates, and they should be understood as can we say _for all_ vertices.

Given `x`, and a relationship like `x -[p]-> y` we can have either

1. none
2. at most one
3. always one
4. sometimes more than one
5. always more than one

All this is something that we should be able to specify at the point where we link `p` as something starting from `x`.

``` scala
// the type of ys?
val ys = x >-- p --> y
```

In principle we should

1. fail statically
2. `Option[Y]`
3. `Y`
4. `Either[Y, List[Y]]` _is this useful/valuable?_
5. `List[Y]`

If there's no arity recorded/declared, probably `Option[List[Y]]` is a good default.

This type should be derived from

1. there is a property out of `x.type`
2. the value type of that property is `Y`
3. there's an arity spec for it


