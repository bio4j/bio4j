/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.bioinfo.bio4j.blueprints.model.relationships.go;

import com.era7.bioinfo.bio4j.blueprints.model.relationships.BasicRelationship;
import com.tinkerpop.blueprints.Edge;


/**
 *
 * GO term negatively regulates relationship
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class NegativelyRegulatesGoRel extends BasicRelationship{

    public static final String NAME = "NEGATIVELY_REGULATES_GO";

    public static final String OBOXML_RELATIONSHIP_NAME = "negatively_regulates";

    public NegativelyRegulatesGoRel(Edge e){
        super(e);
    }

    @Override
    public String getLabel() {
        return NAME;
    }

}
