package com.bio4j.model.isoforms.relationships;

import com.bio4j.model.Relationship;

import com.bio4j.model.isoforms.nodes.AlternativeProduct;
import com.bio4j.model.isoforms.nodes.Isoform;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
// TODO: migrate
public interface IsoformEventGenerator {
    
  public Isoform getIsoform();
  public AlternativeProduct getAlternativeProduct();
}
