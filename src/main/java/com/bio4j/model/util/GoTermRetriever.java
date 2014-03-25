package com.bio4j.model.util;

import com.bio4j.model.nodes.GoTerm;

public interface GoTermRetriever extends NodeRetriever<GoTerm>{

  public GoTerm getGoTermById(String goId); 
  public GoTerm getMolecularFunctionGoTerm();
  public GoTerm getBiologicalProcessGoTerm();
  public GoTerm getCellularComponentGoTerm();
  
}
