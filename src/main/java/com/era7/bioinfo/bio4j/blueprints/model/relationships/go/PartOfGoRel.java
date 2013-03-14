/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.bioinfo.bio4j.blueprints.model.relationships.go;

import com.era7.bioinfo.bio4j.blueprints.model.relationships.BasicRelationshipBlueprints;
import com.tinkerpop.blueprints.Edge;

/**
 *
 * GO term 'part_of' relationship
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class PartOfGoRel extends BasicRelationshipBlueprints{

    public static final String NAME = "PART_OF_GO";

    public static final String OBOXML_RELATIONSHIP_NAME = "part_of";

    public PartOfGoRel(Edge e){
        super(e);
    }

    @Override
    public String getLabel() {
        return NAME;
    }

}
