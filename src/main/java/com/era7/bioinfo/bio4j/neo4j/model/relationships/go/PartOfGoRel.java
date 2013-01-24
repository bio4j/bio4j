/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.bioinfo.bio4j.neo4j.model.relationships.go;

import com.era7.bioinfo.bioinfoneo4j.BasicRelationship;
import org.neo4j.graphdb.Relationship;

/**
 *
 * GO term 'part_of' relationship
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class PartOfGoRel extends BasicRelationship{

    public static final String NAME = "PART_OF_GO";

    public static final String OBOXML_RELATIONSHIP_NAME = "part_of";

    public PartOfGoRel(Relationship rel){
        super(rel);
    }

    @Override
    public String name() {
        return NAME;
    }

}
