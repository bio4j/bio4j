## Auxiliary relationships

Auxiliary relationships go from the graph reference node and are a means of positioning in representative nodes that can be used as entry points. _(Use [Bio4jExplorer](http://gotools.bio4j.com:8080/Bio4jExplorerServer/Bio4jExplorer.html) if you'd rather have a more complete and visual idea of what's inside Bio4j)_

The current auxiliary relationships are the following:

FIXME: ALL links are broken!


### Gene Ontology top-level nodes

* [Molecular function](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/go/MolecularFunctionRel.html)
* [Cellular component](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/go/CellularComponentRel.html)
* [Biological process](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/go/BiologicalProcessRel.html)
* [Main GO](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/go/MainGoRel.html) _(this relationship goes to the three of them)_


### Datasets

Like Swiss-Prot or Trembl.

* [Main dataset](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/MainDatasetRel.html) _(this relationship goes to every main dataset)_


### Taxonomy

* [Main Taxon](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/sc/ErroneousInitiationRel.html) _(this relationship goes to the top-level nodes of the taxonomy tree)_


### Alternative products

* [Alternative product splicing](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/aproducts/AlternativeProductSplicingRel.html)
* [Alternative product ribosomal frameshifting](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/aproducts/AlternativeProductRibosomalFrameshiftingRel.html)
* [Alternative product promoter](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/aproducts/AlternativeProductPromoterRel.html)
* [Alternative product initiation](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/aproducts/AlternativeProductInitiationRel.html)


### Sequence caution nodes

* [Erroneous initiation](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/sc/ErroneousInitiationRel.html)
* [Erroneous translation](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/sc/ErroneousTranslationRel.html)
* [Erroneous termination](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/sc/ErroneousTerminationRel.html)
* [Erroneous gene model prediction](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/sc/ErroneousGeneModelPredictionRel.html)
* [Frameshift](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/sc/FrameshiftRel.html)
* [Miscellaneous discrepancy](http://www.bio4j.com/docs/bio4jmodel/apidocs/com/era7/bioinfo/bio4jmodel/relationships/sc/MiscellaneousDiscrepancyRel.html)
