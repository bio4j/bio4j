package com.bio4j.model.relationships.refseq;

import com.bio4j.model.refseq.nodes.CDS;
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface GenomeElementCDS{
  
  public GenomeElement getGenomeElement();    
  public CDS getCDS();   
}