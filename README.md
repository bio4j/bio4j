Bio4j
=====

Bio4j is a bioinformatics graph based DB including most data available in [Uniprot KB](http://www.uniprot.org/) (SwissProt + Trembl), [Gene Ontology](http://www.geneontology.org/) (GO), [UniRef](http://www.ebi.ac.uk/uniref/) (50,90,100), [RefSeq](http://www.ncbi.nlm.nih.gov/RefSeq/), [NCBI Taxonomy](http://www.ncbi.nlm.nih.gov/Taxonomy/), and [Expasy Enzyme DB](http://enzyme.expasy.org/). 

Bio4j provides a completely new and powerful framework for protein related information querying and management. Since it relies on a high-performance graph engine, data is stored in a way that semantically represents its own structure. 
On the contrary, traditional relational databases must flatten the data they represent into tables, creating "artificial" ids in order to connect the different tuples; which can in some cases eventually lead to domain models that have almost nothing to do with the actual structure of data.

