package com.bio4j.model.enzymedb.indexes;

import com.bio4j.model.NodeUniqueIndex;

import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.properties.id;

public interface ById extends NodeUniqueIndex<Enzyme, Enzyme.Type, id.Type, String> {
  
  @Override
  public Enzyme getNode(String byValue);
}