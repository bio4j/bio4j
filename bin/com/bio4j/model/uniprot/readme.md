# property-less vertices

After a lot of thought and data modeling experiments, we are moving to a model where only edges can have properties (or data in general).

Let's try to explain why. For that, we will start reflecting on for _what_ do we need properties. There are essentially two different uses of properties

1. to store data local to some entity
2. to link entities with each other

1. is of course not so well-defined, but anyway I think it is more or less clear at an intuitive level. 2. is maybe not so obvious, but it is pretty widespread. In a lot of situations where you think an index is needed, this is caused by properties used as links.

### why only edges

1. **dependent types** edges introduce different contexts depending on...
2. clear and simple categorical model (cats enriched in a monoidal category of records?)

### then, what about node types?

With our approach, they serve essentially as contexts. The same node at the data level can be the subject of different contexts, and its function is to determine *which* edges can have this node as source or target. Let's see an example.

``` java
publication --[Author]--> author --[Person]--> Persons
```

From the `publication` context we can take `Author`. Once we know it, we can get `Person`, which would be there _if_ the author is a person. What about those authors which are persons

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

Then `Publication` could include things such as a date or a title.

Another example could be authors:

``` java
x --[Publication]--> publication --[Author]--> author --[Person]--> person
```



## queries

Queries need to look OK. Given a node `x` 


## example model

``` scala

// vertex types are more like contexts than types
object Protein      extends VertexType
object Publication  extends VertexType

object Author       extends VertexType
object Person       extends VertexType
object Consortium   extends VertexType

// "types" of publications
object Article      extends VertexType
object Book         extends VertexType
object Editor       extends VertexType
object Thesis       extends VertexType

object Institution  extends VertexType // ??
object Journal      extends VertexType
    
// TODO introduce an abstract CitedLike rel with the corresponding properties
object cited extends Rel(Protein, "cited", Publication) {

  implicit val _date  = this has date
  implicit val _title = this has title
}

object author extends Rel(Publication, "author", Author) {

  // TODO something else?
  implicit val _position = this has position
}

object person extends Rel(Author, "person", Person) {

  implicit val _name = this has name
}
object consortium extends Rel(Author, "consortium", Consortium) {

  implicit val _name = this has name
}

object editor extends Rel(Book, "editor", Editor)

object article extends Rel(Publication, "article", Article) {}
object book extends Rel(publication, "book", Book) {}
object thesis extends Rel(publication, "thesis", Thesis) {}

```

## Titan impl

### local indexes

You need to specify at the same time

1. a property which will be used 
