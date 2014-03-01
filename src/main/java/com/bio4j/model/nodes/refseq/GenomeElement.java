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
package com.bio4j.model.nodes.refseq;

import com.bio4j.model.Node;
import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.refseq.rna.MRNA;
import com.bio4j.model.nodes.refseq.rna.MiscRNA;
import com.bio4j.model.nodes.refseq.rna.NcRNA;
import com.bio4j.model.nodes.refseq.rna.RRNA;
import com.bio4j.model.nodes.refseq.rna.TRNA;
import com.bio4j.model.nodes.refseq.rna.TmRNA;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface GenomeElement extends Node{
    
    
    //--------GETTERS-----------
    public String getVersion();
    public String getComment();
    public String getDefinition();
    public List<Protein> getAssociatedProteins();
    public List<CDS> getCDS();
    public List<Gene> getGenes();
    public List<MRNA> getMRnas();
    public List<MiscRNA> getMiscRnas();
    public List<NcRNA> getNcRnas();
    public List<RRNA> getRRnas();
    public List<TRNA> getTRnas();
    public List<TmRNA> getTmRnas();

    //--------SETTERS---------------
    public void setVersion(String value);
    public void setComment(String value);
    public void setDefinition(String value);

    
}
