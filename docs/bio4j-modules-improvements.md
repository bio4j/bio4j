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

1. should we split the code into different packages/artifacts/projects?
2. how users can create new modules?
3. does it make sense to create module-specific interfaces for `Node`s, `Relationship`s?