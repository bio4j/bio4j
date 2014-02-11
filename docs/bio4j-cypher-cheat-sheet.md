## Retrieving basic info from nodes

Get protein by its accession number and return its full name and sequence

``` bash
 START p=node:protein_accession_index(protein_accession_index = "P12345")
 return p.protein_accession, p.protein_full_name, p.protein_sequence
``` 

Get Keyword by its ID:

``` script
 START k=node:keyword_id_index(keyword_id_index = "KW-0181")
 return k.keyword_name, k.keyword_id
``` 

## Basic traversals

Get proteins (accession and names) associated to an interpro motif (limited to 10 results)

``` script
 START i=node:interpro_id_index(interpro_id_index = "IPR023306")
 MATCH i <-[:PROTEIN_INTERPRO]- p
 return p.protein_accession, p.protein_full_name, p.protein_name, p.protein_short_name
 limit 10
``` 

## Advanced traversals

Protein interaction circuits of length 3 with proteins belonging to Swiss-Prot dataset (limited to 5 results)

``` script
 START d=node:dataset_name_index(dataset_name_index = "Swiss-Prot")
 MATCH d <-[r:PROTEIN_DATASET]- p, 
 circuit = (p) -[:PROTEIN_PROTEIN_INTERACTION]-> (p2) -[:PROTEIN_PROTEIN_INTERACTION]-> (p3) -[:PROTEIN_PROTEIN_INTERACTION]-> (p)
 return p.protein_accession, p2.protein_accession, p3.protein_accession, p.protein_accession
 limit 5
``` 

