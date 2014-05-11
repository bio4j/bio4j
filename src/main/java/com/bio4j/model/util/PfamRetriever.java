package com.bio4j.model.util;

import com.bio4j.model.uniprot.nodes.Pfam;

public interface PfamRetriever extends NodeRetriever<Pfam>{

  public Pfam getPfamById(String pfamId);

}
