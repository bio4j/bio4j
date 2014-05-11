# Bio4j abstract model changes

## modules

Trying to separate somehow different modules at the type level is virtually impossible in Java. The main difficulty is the lack of any **external** extension mechanism for already defined interfaces/classes. If you add a relationship between two already defined nodes, you need to modify those two nodes in order to be able to call something like `node.out_newRel()`.

 Some degree of encapsulation/independence is needed though; what I propose is

- Identify **modules** with sub**packages** of `com.bio4j.model`

At least this gives a clearer picture for modules just by looking at the code, and helps the user identify the requirements/dependencies for each operation.

## Node and Relationship interfaces

Let's try to explain a bit the point of all this F-bounded polymorphism explosion. The basic idea is that for both nodes and relationships, you need to define them and their type at the same time; their type is basically a namespace for the static stuff related with them.

### explicit source and target types

If we want to have source and target methods with the right type for relationships, we need to be able to refer to the corresponding node types; that's the point of having two nodes as parameters of the `Relationship` interface.

### abstract labels

I think it has a lot of advantages being able to set labels at the level of the abstract model:

- makes easy to have the same names at the storage level for different platforms
- helps in generating Pipes/Gremlin code from the abstract model
- makes type definitions for Titan easier and safer; same for indexes in general

For guaranteeing at the interface level that this is the case, we need this kind of mutual dependendency between nodes/relationships and their types.

#### using labels

Every interface in the abstract model has a corresponding type, nested as as static enum. In client implementing code, you do

``` java
public class TaxonImpl implements Taxon {

  @Override
  public Taxon.Type type() { return Taxon.TYPE; }
  
  // if you want to set the label (a String)
  private static final String label = Taxon.TYPE.name();  
}
```

In the case of neo4j for example, you can (maybe?) pass this enums as rels.

### arities for relationships

Using the relationship types we can encode abstractly and statically the arity of every Relationship. This is now done following the Titan typedefs convention.

### in and out methods for nodes

We need a convention on relationship methods, right now there's nothing connecting a method with the relationship that you should use for implementing it. What I propose is

- `in_<relName>`, `out_<relName>` for getting the _relationships_
- `in_<relName>_nodes`, `out_<relName_nodes>` for getting the _nodes_ connected that way

So that things would be

``` java
Taxon t = ByTaxId.get("tururu lalal");
//get parent
Taxon p = t.out_parent_nodes();
// get children
List<Taxon> c = t.in_parent_nodes();
// is t the root?
Boolean isRoot = p.equals(t);
// should be true
Boolean ofCourse = c.get(0).out_parent_nodes().equals(t);
```

Again, this has a lot of positive implications. Related with this, I'd like to simplify the names of relationship types; source and target are encoded in the type so there's no need for including them as part of the interface name.

## Properties

Defining properties independently makes you have their type defined at the model level, through the corresponding property type. This is a big advantage for client code; in the Titan case we can write a generic method for defining all properties at once, for example. It also helps in maintaining a standard over the types of properties whenever possible, the labels used for their storage, and makes way easier to get/set them abstractly for a particular technology. The way we are doint it now has also the advantages of

- no collisions
- no object wrapping
- visible at the interface definition level

Type erasure forces us to follow a different design here as for Nodes and Rels: we want a Node to implement several property interfaces (meaning that it has those properties). In this case `Property` and `PropertyType` are decoupled, and defining them at the same time becomes a _pattern_ that should be followed.

``` java
public interface name extends Property {

  public String name();

  public static Type TYPE = Type.name;

  public static enum Type implements PropertyType<type, String> {
    name;
    public Type value() { return name; }
    public Class<String> valueClass() { return String.class; }
  }
}
```

Again as with Nodes and Rels

- you access the property type type as `<property_name>.Type`
- a value (the only one) of that type is accessible as the static field `<property_name>.TYPE`






-----

What we want is

- be able to say that the type of a Relationship is one of the specified types
- use it as a label in a safe way in all implementations

First define Bio4jRelationshipType and Bio4jNodeType that would play well with enums (with a `name` method etc)

``` java
package com.bio4j.model;

public interface Bio4jRelationshipType {

  public String name();
}

public interface Bio4jNodeType {

  public String name(); 
}
```

This way we they will be compatible with Neo4j equivalents `RelationshipType` and `Label`. This things are what we are going to use as labels. This means that 

``` java
package com.bio4j.model;

public interface Rel<me extends Rel<me, Type>, Type extends Bio4jRelationshipType> {

  public Type getType();

  public interface Type extends Enum<Type> & Bio4jRelationshipType {}

}
```

some random stuff related with this enum thing 

#### enums

- [SO - nested enums in Java](http://stackoverflow.com/questions/7296785/using-nested-enum-types-in-java) really interesting
- [The sleek EnumMap and EnumSet](http://marxsoftware.blogspot.com.es/2010/07/sleek-enummap-and-enumset.html)
- [SO - abstract Java enum](http://stackoverflow.com/questions/2251344/abstract-java-enum)
- [SO - generic params be enums impl interface](http://stackoverflow.com/questions/1070703/how-can-i-require-a-generic-parameter-to-be-an-enum-that-implements-an-interface?rq=1)
- [SO - java interfaces containing enums](http://stackoverflow.com/questions/3242145/java-interfaces-containing-inner-enums-extending-functionality-in-implementati?rq=1) 
