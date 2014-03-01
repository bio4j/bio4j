package com.ohnosequences.bio4j.model.relationships;

import com.ohnosequences.bio4j.model.Relationship;

import com.ohnosequences.bio4j.model.nodes.AlternativeProduct;
import com.ohnosequences.bio4j.model.nodes.Isoform;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface IsoformEventGenerator {
    
    public Isoform getIsoform();
    public AlternativeProduct getAlternativeProduct();
}
