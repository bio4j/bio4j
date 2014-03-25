# Bio4j modules

- each module has dependencies
- we get nodes through indexes, and then using methods on the returned objects

At the interface def level, they could live in different packages (one per module) with dependencies being expressed through `import`s. Adding features to existing nodes could be done through extending the corresponding interfaces. This way the uber-method-rich `Protein` node would be split into a set of (hopefully non-conflicting) interfaces declaring the corresponding module-specific methods. But what about the types and all that? it works as follows

``` java
// extend Taxon nodes
public interface CoolerTaxon extends Taxon {
  
  public void doCoolSuff();
}
// refine the TaxonParent rel
public interface CoolerTaxonParent extends TaxonParent {

  CoolerTaxon getSource();
  CoolerTaxon getTarget();
}
```

If you want to add new `Node`s or `Relationship`s then you need to extend the corresponding abstract interfaces.

Questions:

1. should we split the code into different packages/artifacts/projects? probably packages is enough
2. how users can create new modules? In principle, just adhering to a simple set of conventions should work.
3. does it make sense to create module-specific interfaces for `Node`s, `Relationship`s?

## indexes

My opinion on this is that we should group them by (return) type, that is the `Node` type that is indexed. Then, each module can declare interfaces for different indexes, and extend the corresponding retriever for that type with methods using those indexes. Example:

``` java
// if needed add indexes as params
// or transform into an interface
public abstract class Retriever<N extends Node<N,T>, T extends Enum<T> & NodeType<N,T>> {

  public <V, I extends NodeUniqueIndex<N,T,V>> N getNodeFrom(I index, V value) { 

    return index.getNode(value); 
  }
  public <V, I extends NodeListIndex<N,T,V>> List<? extends N> getNodesFrom(I index, V value) {

    return index.getNodes(value);
  }
}

public interface TaxonRetriever extends Retriever<Taxon, Taxon.type> {}
```
