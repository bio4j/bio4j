package com.bio4j.model;

import java.util.Set;
import java.util.HashSet;

import com.bio4j.model.go.GoModule;

// TODO maybe?
/*
  - http://java.dzone.com/articles/enum-tricks-hierarchical-data
  - https://stackoverflow.com/questions/7296785/using-nested-enum-types-in-java
  - https://stackoverflow.com/questions/19680418/how-to-use-enum-with-grouping-and-subgrouping-hierarchy-nesting
*/
public enum modules {

  GeneOntology(GoModule.go);
  // TODO add the others

  private Module module = null; 
  
  // just for decorative purposes
  private Set<Module> dependencies = null;
  private String pkg = null;

  private modules(Module module) {

    this.module = module;
    this.pkg = module.pkg();
    this.dependencies = module.dependencies();
  }
}