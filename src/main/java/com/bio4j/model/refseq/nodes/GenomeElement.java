package com.bio4j.model.refseq.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.uniprot.nodes.Protein;
import java.util.List;

// properties
import com.bio4j.model.properties.Comment;
import com.bio4j.model.properties.Definition;
import com.bio4j.model.properties.Version;

// relationships
import com.bio4j.model.refseq.relationships.HasMRNA;
import com.bio4j.model.refseq.relationships.HasMiscRNA;
import com.bio4j.model.refseq.relationships.HasNcRNA;
import com.bio4j.model.refseq.relationships.HasTRNA;
import com.bio4j.model.refseq.relationships.HasTmRNA;
import com.bio4j.model.refseq.relationships.HasRRNA;

import com.bio4j.model.refseq.relationships.HasGene;
import com.bio4j.model.refseq.relationships.HasCDS;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface GenomeElement extends Node<GenomeElement, GenomeElement.Type>,

  // properties
  Comment<GenomeElement, GenomeElement.Type>,
  Definition<GenomeElement, GenomeElement.Type>,
  Version<GenomeElement, GenomeElement.Type>

{
  
  // hasCDS
  // out
  public List<? extends HasCDS> hasCDS_out();
  public List<? extends CDS> hasCDS_outNodes();

  // hasGene
  // out
  public List<? extends HasGene> hasGene_out();
  public List<? extends Gene> hasGene_outNodes();

  // RNAs

  // mRNAs
  // outgoing
  public List<? extends HasMRNA> hasMRNA_out();
  public List<? extends MRNA> hasMRNA_outNodes();

  // miscRNAs
  // outgoing
  public List<? extends HasMiscRNA> hasMiscRNA_out();
  public List<? extends MiscRNA> hasMiscRNA_outNodes();

  // ncRNAs
  // outgoing
  public List<? extends HasNcRNA> hasNcRNA_out();
  public List<? extends NcRNA> hasNcRNA_outNodes();

  // RRNAs
  // outgoing
  public List<? extends HasRRNA> hasRRNA_out();
  public List<? extends RRNA> hasRRNA_outNodes();

  // TRNAs
  // outgoing
  public List<? extends HasTRNA> hasTRNA_out();
  public List<? extends TRNA> hasTRNA_outNodes();

  // TmRNAs
  // outgoing
  public List<? extends HasTmRNA> hasTmRNA_out();
  public List<? extends TmRNA> hasTmRNA_outNodes();




  public static Type TYPE = Type.genomeElement;
  public static enum Type implements NodeType<GenomeElement, GenomeElement.Type> {

    genomeElement;
    public Type value() { return genomeElement; }
  }

  // TODO move to Protein
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
