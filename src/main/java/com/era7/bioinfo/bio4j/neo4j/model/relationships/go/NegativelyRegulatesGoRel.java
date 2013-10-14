/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.bioinfo.bio4j.neo4j.model.relationships.go;

import com.era7.bioinfo.bioinfoneo4j.BasicRelationship;
import org.neo4j.graphdb.Relationship;

/**
 *
 * GO term negatively regulates relationship
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class NegativelyRegulatesGoRel extends BasicRelationship{

    public static final String NAME = "NEGATIVELY_REGULATES_GO";

    public static final String OBOXML_RELATIONSHIP_NAME = "negatively_regulates";

    public NegativelyRegulatesGoRel(Relationship rel){
        super(rel);
    }

    @Override
    public String name() {
        return NAME;
    }

}
