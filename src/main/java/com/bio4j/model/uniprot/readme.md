# property-less vertices

After a lot of thought and data modeling experiments, we are moving to a model where only edges can have properties (or data in general).

Let's try to explain why. For that, we will start reflecting on for _what_ do we need properties. There are essentially two different uses of properties

1. to store data local to some entity
2. to link entities with each other

1. is of course not so well-defined, but anyway I think it is more or less clear at an intuitive level. 2. is maybe not so obvious, but it is pretty widespread. In a lot of situations where you think an index is needed, this is caused by properties used as links. 

### why only edges

1. dependent types
2. 

## standard translation techniques

Using a naive json-like syntax

``` json
Article {

  id: 234234,
  author: ["Robustiano Hernández Mesa", "Espinete", "Gayofa"]
}
```

The id would stay the same, but it shouldn't be exposed at any point, or be made part of the data model. What about authors? easy: you should

1. create an edge typed `Author`
2. with target `author` (yes, naming sucks)
3. whatever that author is, this is encoded as an edge out of that `author` node; in this case, a `Person`. But it could also be a consortium

In general,

``` java
publication --[Book]--> book --[Author]--> author --[Person]--> Persons
// vs
publication --[Author]--> author --[Person]--> Persons
publication --[Book]--> Books
```

I think it amounts to whether all `Publications` should have authors or not. The other thing is what to do with edges out of a publication that are only there _if_ we have a `Book` edge, for example (like `Editor`). The two options

``` java
publication --[Book]--> book --[Editor]--> editor
// vs
publication --[Book]--> book
publication --[Editor]--> editor
```

The first one is more fine-grained. I like it more.

``` scala
// first option
val x: Publication
val e: Option[Editor] = x out Book map {_ out Editor}

// second option
val y: Publication
// get if book
val b = y out Book
val e = b map {_ => y out Editor}
// get it directly if present
val e2: Option[Editor] = y out Editor
```

Now, where do generic `Publication` data (if any) should go? from the point of view of composition, the more natural approach would be to split everything so that the composed type would be

``` java
x --[Publication]--> publication --[Book]--> book
```

Then `Publication` could include things such as a date or a title. Another example could be authors:

``` java
x --[Publication]--> publication --[Author]--> author --[Person]--> person
```

## queries

Queries need to look OK. Given a node `x` we can

``` scala
val e = x out Buh 
```

### query operators

I think that we are working with the free wscc category on a graph or something like that. This means that a query (a path, traversal, whatever) is essentially a morphism. The idea would be to declare a base `Morphism` trait, and do a shapeless-style implementation of all cases. We would have

- `Edge` for a simple edge
- `Compose(F,G)` where `F` and `G` should match
- `Par(F,G)` for the product-like par
- `Or(F,G)` for ...

Maybe it is possible to do this in Scala.
