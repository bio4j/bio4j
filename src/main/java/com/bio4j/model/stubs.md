# model implementation guidelines

General principles

- Entities and their types are left as interfaces
- properties can be declared at this point, and retrieving them too!


## stubs

Stub for nodes

``` java
public interface TypeName<
  N extends TypeName<N,NT>,
  NT extends TypeName.Type<N,NT>
> 
  extends Node<N,NT>,
  // properties
  Id<N,NT>,
  Name<N,NT>,
  Synonym<N,NT>,
  Definition<N,NT>,
  Comment<N,NT>
  
{

  public static interface Type <
    N extends TypeName<N,NT>,
    NT extends TypeName.Type<N,NT>
  > 
    extends NodeType<N,NT>
  {}
```


Stub for relationships


``` java
public interface RelName <
    S extends Source<S,ST>, ST extends Source.Type<S,ST>,
    R extends RelName<S,ST,R,RT,T,TT>, RT extends RelName.Type<S,ST,R,RT,T,TT>,
    T extends Target<T,TT>, TT extends Target.Type<T,TT>
  >
    extends Relationship<S,ST,R,RT,T,TT>
  {

    interface Type <
      S extends Source<S,ST>, ST extends Source.Type<S,ST>,
      R extends RelName<S,ST,R,RT,T,TT>, RT extends RelName.Type<S,ST,R,RT,T,TT>,
      T extends Target<T,TT>, TT extends Target.Type<T,TT>
    >
      extends RelationshipType<S,ST,R,RT,T,TT>
    {}
  }

}
```