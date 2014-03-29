package com.bio4j.model.refseq.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.refseq.nodes.rna.MRNA;
import com.bio4j.model.refseq.nodes.rna.MiscRNA;
import com.bio4j.model.refseq.nodes.rna.NcRNA;
import com.bio4j.model.refseq.nodes.rna.RRNA;
import com.bio4j.model.refseq.nodes.rna.TRNA;
import com.bio4j.model.refseq.nodes.rna.TmRNA;
import java.util.List;

// properties
import com.bio4j.model.properties.comment;
import com.bio4j.model.properties.definition;
import com.bio4j.model.properties.version;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface GenomeElement extends Node<GenomeElement, GenomeElement.type>,
  // properties
  comment,
  definition,
  version
{
    
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

  //--------SETTERS---------------
  public void setVersion(String value);
  public void setComment(String value);
  public void setDefinition(String value);
}
