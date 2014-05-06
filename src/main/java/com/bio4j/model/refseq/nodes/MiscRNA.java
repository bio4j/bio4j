package com.bio4j.model.refseq.nodes;

import java.util.List;
import com.bio4j.model.refseq.relationships.HasMiscRNA;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface MiscRNA extends RNA<MiscRNA, MiscRNA.Type> {

  // hasMiscRNA
  // in
  public List<? extends HasMiscRNA> hasMiscRNA_in();
  public List<? extends GenomeElement> hasMiscRNA_inNodes();

  public static Type TYPE = Type.miscRNA;
  enum Type implements RNAType<MiscRNA, MiscRNA.Type> {

    miscRNA;
    public Type value() { return miscRNA; }
  }   
}
