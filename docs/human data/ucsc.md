### UCSC tables

#### Fragments

http://hgdownload.soe.ucsc.edu/goldenPath/hg38/bigZips/hg38.agp.gz

Schema:


|field	    |example	  |SQL type	      |info	                                  |description                |UCSCChromosomeFragment edge property     |
|-----------|:----------|:--------------|:--------------------------------------|:--------|:------|
|bin	      |585	      |smallint(6)	  |range	                                |Indexing field to speed chromosome range queries.|  |
|chrom	    |chr1	      |varchar(255)	  |values	                                |Reference sequence chromosome or scaffold|chrom|
|chromStart	|10000	    |int(10) unsigned|range	                                |start position in chromosome|chromStart|
|chromEnd	  |10615	    |int(10) unsigned|range	                                |end position in chromosome|chromEnd|
|ix	        |2	        |int(11)	      |range	                                |ix of this fragment (useless)||
|type	      |F	        |char(1)	      |values	                                |(W)GS contig, (P)redraft, (D)raft, (F)inished or (O)ther|fragmentType|
|frag	      |AP006221.1	|varchar(255)	  |values	                                |which fragment|frag|
|fragStart	|36116	    |int(10) unsigned|range	                                |start position in frag|fragStart|
|fragEnd	  |36731	    |int(10) unsigned|range	                                |end position in frag|fragEnd|
|strand	    |- 	        |char(1)	      |values	                               |+ or - (orientation of fragment)|strand|


We are going to ignore all information related to "chrUn_*".

#### UCSC Genes Annotation

http://hgdownload.soe.ucsc.edu/goldenPath/hg38/database/knownGene.txt.gz

Schema:

```
field	example	SQL type	info	description
name	uc001aaa.3	varchar(255)	values	Name of gene
chrom	chr1	varchar(255)	values	Reference sequence chromosome or scaffold
strand	+	char(1)	values	+ or - for strand
txStart	11873	int(10) unsigned	range	Transcription start position
txEnd	14409	int(10) unsigned	range	Transcription end position
cdsStart	11873	int(10) unsigned	range	Coding region start
cdsEnd	11873	int(10) unsigned	range	Coding region end
exonCount	3	int(10) unsigned	range	Number of exons
exonStarts	11873,12612,13220,	longblob	 	Exon start positions
exonEnds	12227,12721,14409,	longblob	 	Exon end positions
proteinID	 	varchar(40)	values	UniProt display ID for Known Genes, UniProt accession or RefSeq protein ID for UCSC Genes
alignID	uc001aaa.3	varchar(255)	values	Unique identifier for each (known gene, alignment position) pair
```

We are going to ignore all information related to "chrUn_*".
