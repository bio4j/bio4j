package com.bio4j.model.relationships.refseq;

import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.rna.MiscRNA;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface GenomeElementMiscRna{
    
  public GenomeElement getGenomeElement();    
  public MiscRNA getMiscRNA();
}
