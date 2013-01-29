/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.blueprints.model.relationships.go;

import com.era7.bioinfo.bio4j.blueprints.model.relationships.BasicRelationship;
import com.tinkerpop.blueprints.Edge;


/**
 *
 * GO term 'positively_regulates' relationship
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class PositivelyRegulatesGoRel extends BasicRelationship {

    public static final String NAME = "POSITIVELY_REGULATES_GO";
    public static final String OBOXML_RELATIONSHIP_NAME = "positively_regulates";

    public PositivelyRegulatesGoRel(Edge e) {
        super(e);
    }

    @Override
    public String getLabel() {
        return NAME;
    }
}
