
package com.bio4j.model.nodes.refseq;

import com.bio4j.model.Node;
import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.refseq.rna.MRNA;
import com.bio4j.model.nodes.refseq.rna.MiscRNA;
import com.bio4j.model.nodes.refseq.rna.NcRNA;
import com.bio4j.model.nodes.refseq.rna.RRNA;
import com.bio4j.model.nodes.refseq.rna.TRNA;
import com.bio4j.model.nodes.refseq.rna.TmRNA;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface GenomeElement extends Node<GenomeElement, GenomeElement.type> {
    
  enum type implements NodeType<GenomeElement, GenomeElement.type> {

    genomeElement;
    public type value() { return genomeElement; }
  }
  
    
    //--------GETTERS-----------
    public String getVersion();
    public String getComment();
    public String getDefinition();
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
