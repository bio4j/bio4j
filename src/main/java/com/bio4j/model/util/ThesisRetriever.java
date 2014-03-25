package com.bio4j.model.util;

import java.util.List;
import com.bio4j.model.nodes.citation.Thesis;

public interface ThesisRetriever extends NodeRetriever<Thesis> {

  public List<? extends Thesis> getThesisByTitle(String thesisTitle);

}
