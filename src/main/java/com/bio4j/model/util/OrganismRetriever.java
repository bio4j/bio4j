package com.bio4j.model.util;

import com.bio4j.model.nodes.Organism;

public interface OrganismRetriever extends NodeRetriever<Organism>{

  public Organism getOrganismByScientificName(String scientificName);
  public Organism getOrganismByNCBITaxonomyId(String ncbiTaxonomyId);

}
