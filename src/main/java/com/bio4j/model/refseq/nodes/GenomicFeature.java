package com.bio4j.model.refseq.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.refseq.nodes.GenomeElement;

import com.bio4j.model.properties.Note;
import com.bio4j.model.properties.Positions;

/**
 * A base node for genomic features: Genes, RNAs, CDSs.
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface GenomicFeature <
  GF extends GenomicFeature<GF,GFT>,
  GFT extends Enum<GFT> & GenomicFeatureType<GF,GFT>
> extends Node<GF,GFT>,
  
  // properties
  Note<GF, GFT>,
  Positions<GF, GFT> // TODO why not a pair of numbers? and why not at the rel level?

{}
