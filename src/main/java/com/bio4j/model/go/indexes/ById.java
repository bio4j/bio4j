package com.bio4j.model.go.indexes;

import com.bio4j.model.NodeUniqueIndex;

import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.properties.id;

public interface ById extends NodeUniqueIndex<GoTerm, GoTerm.type, id.type, String> {
  
  @Override
  public GoTerm getNode(String byValue);
}

