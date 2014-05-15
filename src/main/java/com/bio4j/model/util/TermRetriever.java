package com.bio4j.model.util;

import com.bio4j.model.go.nodes.Term;

public interface TermRetriever extends NodeRetriever<Term>{

  public Term getTermById(String goId); 
  public Term getMolecularFunctionTerm();
  public Term getBiologicalProcessTerm();
  public Term getCellularComponentTerm();
}
