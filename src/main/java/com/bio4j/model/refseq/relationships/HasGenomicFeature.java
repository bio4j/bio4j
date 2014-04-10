package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.GenomicFeature;
import com.bio4j.model.refseq.nodes.GenomicFeatureType;
/*
  
*/
public interface HasGenomicFeature <
  R extends HasGenomicFeature<R,RT,GF,GFT>, 
  RT extends Enum<RT> & HasGenomicFeatureType<R,RT,GF,GFT>,
  GF extends GenomicFeature<GF,GFT>,
  GFT extends Enum<GFT> & GenomicFeatureType<GF,GFT>
> extends Relationship<GenomeElement, GenomeElement.Type, R,RT, GF,GFT> {}