package com.bio4j.model.refseq.nodes;

import java.util.List;

import com.bio4j.model.refseq.relationships.HasNcRNA;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface NcRNA extends RNA<NcRNA, NcRNA.Type> { 

  // hasNcRNA
  // in
  public List<? extends HasNcRNA> hasNcRNA_in();
  public List<? extends GenomeElement> hasNcRNA_inNodes();

  public static Type TYPE = Type.ncRNA; 
  public static enum Type implements RNAType<NcRNA, NcRNA.Type> {

    ncRNA;
    public Type value() { return ncRNA; }
  }   
}
