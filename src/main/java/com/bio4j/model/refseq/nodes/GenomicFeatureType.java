package com.bio4j.model.refseq.nodes;

import com.ohnosequences.typedGraphs.NodeType;

public interface GenomicFeatureType <
  GF extends GenomicFeature<GF,GFT>,
  GFT extends Enum<GFT> & GenomicFeatureType<GF,GFT>
> extends NodeType<GF,GFT> {}