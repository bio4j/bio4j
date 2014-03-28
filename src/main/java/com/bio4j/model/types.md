

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
- [SO - abstract Java enum](http://stackoverflow.com/questions/2251344/abstract-java-enum)\r\n
- [SO - generic params be enums impl interface](http://stackoverflow.com/questions/1070703/how-can-i-require-a-generic-parameter-to-be-an-enum-that-implements-an-interface?rq=1)
- [SO - java interfaces containing enums](http://stackoverflow.com/questions/3242145/java-interfaces-containing-inner-enums-extending-functionality-in-implementati?rq=1) 
