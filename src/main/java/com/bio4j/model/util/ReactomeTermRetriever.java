package com.bio4j.model.util;

import com.bio4j.model.nodes.ReactomeTerm;

public interface ReactomeTermRetriever extends NodeRetriever<ReactomeTerm> {

  public ReactomeTerm getReactomeTermById(String reactomeTermId);

}
