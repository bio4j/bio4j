/*
 * Copyright (C) 2010-2013  "Bio4j"
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
package com.era7.bioinfo.bio4j.blueprints;

import com.era7.bioinfo.bio4j.model.nodes.*;
import com.era7.bioinfo.bio4j.model.nodes.citation.*;
import com.era7.bioinfo.bio4j.model.nodes.ncbi.NCBITaxonNode;
import com.era7.bioinfo.bio4j.model.nodes.reactome.ReactomeTermNode;
import com.era7.bioinfo.bio4j.model.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bio4j.model.util.Bio4jManager;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.bioinfoxml.genome.GenomeElement;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.wrappers.batch.BatchGraph;
import java.util.ArrayList;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class InitBio4jDBBP implements Executable {
    
    @Override
    public void execute(ArrayList<String> array) {
        String[] args = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            args[i] = array.get(i);
        }
        main(args);
    }
    
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("This program expects the following parameters:\n"
                    + "1. Bio4j DB folder \n");
        } else {
            
            
            String folder = args[0];
            
            //----config---
            Configuration conf = new BaseConfiguration();
            conf.setProperty("storage.directory", folder);
            conf.setProperty("storage.backend", "local");
            
            TitanGraph graph = TitanFactory.open(conf);
                   
            //------------------------------------------------------------------------
            //--------------------------creating indices------------------------------
            
            //---NODE TYPE---
            graph.createKeyIndex(BasicEntity.NODE_TYPE_PROPERTY,Vertex.class);
            
            //---ARTICLE----
            graph.createKeyIndex(ArticleNode.TITLE_PROPERTY, Vertex.class);
            graph.createKeyIndex(ArticleNode.DOI_ID_PROPERTY, Vertex.class);
            graph.createKeyIndex(ArticleNode.MEDLINE_ID_PROPERTY, Vertex.class);
            graph.createKeyIndex(ArticleNode.PUBMED_ID_PROPERTY, Vertex.class);
            
            //---BOOK----
            graph.createKeyIndex(BookNode.NAME_PROPERTY, Vertex.class);
            
            //---DB----
            graph.createKeyIndex(DBNode.NAME_PROPERTY, Vertex.class);
            
            //---JOURNAL---
            graph.createKeyIndex(JournalNode.NAME_PROPERTY, Vertex.class);
            
            //---ONLINE ARTICLE---
            graph.createKeyIndex(OnlineArticleNode.TITLE_PROPERTY, Vertex.class);
            
            //---ONLINE JOURNAL---
            graph.createKeyIndex(OnlineJournalNode.NAME_PROPERTY, Vertex.class);
            
            //---PATENT---
            graph.createKeyIndex(PatentNode.NUMBER_PROPERTY, Vertex.class);
            
            //---PUBLISHER---
            graph.createKeyIndex(PublisherNode.NAME_PROPERTY, Vertex.class);
            
            //---SUBMISSION---
            graph.createKeyIndex(SubmissionNode.TITLE_PROPERTY, Vertex.class);
            
            //---THESIS----
            graph.createKeyIndex(ThesisNode.TITLE_PROPERTY, Vertex.class);
            
            //---NCBI TAXON--
            graph.createKeyIndex(NCBITaxonNode.TAX_ID_PROPERTY, Vertex.class);
            //gi index is temporarily missing
            
            //---REACTOME TERM---
            graph.createKeyIndex(ReactomeTermNode.ID_PROPERTY, Vertex.class);
            
            //---GENOME ELEMENT---
            graph.createKeyIndex(GenomeElementNode.VERSION_PROPERTY, Vertex.class);
            
            //---ALTERNATIVE PRODUCT---
            graph.createKeyIndex(AlternativeProductNode.NAME_PROPERTY, Vertex.class);
            
            //---CITY---
            graph.createKeyIndex(CityNode.NAME_PROPERTY, Vertex.class);
            
            //---COMMENT TYPE---
            graph.createKeyIndex(CommentTypeNode.NAME_PROPERTY, Vertex.class);
            
            //---CONSORTIUM---
            graph.createKeyIndex(ConsortiumNode.NAME_PROPERTY, Vertex.class);
            
            //---COUNTRY----
            graph.createKeyIndex(CountryNode.NAME_PROPERTY, Vertex.class);
            
            //---DATASET---
            graph.createKeyIndex(DatasetNode.NAME_PROPERTY, Vertex.class);
            
            //---ENZYME---            
            graph.createKeyIndex(EnzymeNode.ID_PROPERTY, Vertex.class);
            
            //---FEATURE TYPE--
            graph.createKeyIndex(FeatureTypeNode.NAME_PROPERTY, Vertex.class);
            
            //---GO TERM----
            graph.createKeyIndex(GoTermNode.ID_PROPERTY, Vertex.class);
            
            //---INSTITUTE---
            graph.createKeyIndex(InstituteNode.NAME_PROPERTY, Vertex.class);
            
            //---INTERPRO---
            graph.createKeyIndex(InterproNode.ID_PROPERTY, Vertex.class);
            
            //---ISOFORM---
            graph.createKeyIndex(IsoformNode.ID_PROPERTY, Vertex.class);
            
            
        }
    }
    
}
