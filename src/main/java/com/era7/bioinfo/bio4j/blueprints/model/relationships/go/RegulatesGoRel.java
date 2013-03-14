/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.bioinfo.bio4j.blueprints.model.relationships.go;

import com.era7.bioinfo.bio4j.blueprints.model.relationships.BasicRelationshipBlueprints;
import com.tinkerpop.blueprints.Edge;

/**
 *
 * GO term 'regulates' relationship
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class RegulatesGoRel extends BasicRelationshipBlueprints{

    public static final String NAME = "REGULATES_GO";

    public static final String OBOXML_RELATIONSHIP_NAME = "regulates";

    public RegulatesGoRel(Edge e){
        super(e);
    }

    @Override
    public String getLabel() {
        return NAME;
    }

}
