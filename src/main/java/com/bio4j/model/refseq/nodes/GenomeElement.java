package com.bio4j.model.refseq.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

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
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
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
}
