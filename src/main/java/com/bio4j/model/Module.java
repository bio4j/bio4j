package com.bio4j.model;

import java.util.Set;

public interface Module {
  
  public Set<Module> dependencies();

  public String pkg();

  public Set<? extends NodeType> nodeTypes();
  public Set<? extends RelationshipType> relationshipTypes();
  public Set<? extends PropertyType> propertyTypes();
  // public Set<? extends NodeIndex> indexes(); 
}