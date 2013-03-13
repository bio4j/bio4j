/*
 * Copyright (C) 2010-2011  "Bio4j"
 *
 * This file is part of Bio4j
 *
 * Bio4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.submission;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.citation.SubmissionNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.BasicRelationship;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;

/**
 * Proteins referenced by a submission
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class SubmissionProteinCitationRel extends BasicRelationship{

    public static final String NAME = "SUBMISSION_PROTEIN_CITATION";

    public SubmissionProteinCitationRel(Edge e){
        super(e);
    }
    
    public ProteinNode getProtein(){
        return new ProteinNode(getVertex(Direction.OUT));
    }
    
    public SubmissionNode getSubmission(){
        return new SubmissionNode(getVertex(Direction.IN));
    }

    @Override
    public String getLabel() {
        return NAME;
    }

}
