package com.bio4j.model.refseq.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.uniprot.nodes.Protein;
import java.util.List;

// properties
import com.bio4j.model.properties.comment;
import com.bio4j.model.properties.definition;
import com.bio4j.model.properties.version;

// relationships
import com.bio4j.model.refseq.relationships.MRNAs;
import com.bio4j.model.refseq.relationships.MiscRNAs;
import com.bio4j.model.refseq.relationships.NcRNAs;
import com.bio4j.model.refseq.relationships.TRNAs;
import com.bio4j.model.refseq.relationships.TmRNAs;
import com.bio4j.model.refseq.relationships.RRNAs;

import com.bio4j.model.refseq.relationships.Genes;
import com.bio4j.model.refseq.relationships.CDSs;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface GenomeElement extends Node<GenomeElement, GenomeElement.type>,

  // properties
  comment,
  definition,
  version

{
  
  // mRNAs
  // outgoing
  public List<? extends MRNAs> out_mRNAs();
  public List<? extends MRNA> out_mRNAs_nodes();

  // miscRNAs
  // outgoing
  public List<? extends MiscRNAs> out_miscRNAs();
  public List<? extends MiscRNA> out_miscRNAs_nodes();

  // ncRNAs
  // outgoing
  public List<? extends NcRNAs> out_ncRNAs();
  public List<? extends NcRNA> out_ncRNAs_nodes();

  // RRNAs
  // outgoing
  public List<? extends RRNAs> out_rRNAs();
  public List<? extends RRNA> out_rRNAs_nodes();

  // TRNAs
  // outgoing
  public List<? extends TRNAs> out_tRNAs();
  public List<? extends TRNA> out_tRNAs_nodes();

  // TmRNAs
  // outgoing
  public List<? extends TmRNAs> out_tmRNAs();
  public List<? extends TmRNA> out_tmRNAs_nodes();
  // TODO all the other RNAs

  public static type TYPE = type.genomeElement;
  public static enum type implements NodeType<GenomeElement, GenomeElement.type> {

    genomeElement;
    public type value() { return genomeElement; }
  }

  public List<Protein> getAssociatedProteins();
  public List<CDS> getCDS();
  public List<Gene> getGenes();
  public List<MRNA> getMRnas();
  public List<MiscRNA> getMiscRnas();
  public List<NcRNA> getNcRnas();
  public List<RRNA> getRRnas();
  public List<TRNA> getTRnas();
  public List<TmRNA> getTmRnas();
}
