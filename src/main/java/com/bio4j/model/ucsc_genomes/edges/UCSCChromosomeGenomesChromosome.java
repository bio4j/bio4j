package com.bio4j.model.ucsc_genomes.edges;

import com.bio4j.model.genomes.GenomesGraph;
import com.bio4j.model.genomes.vertices.GenomesChromosome;
import com.bio4j.model.ucsc.UCSCGraph;
import com.bio4j.model.ucsc.vertices.UCSCChromosome;
import com.bio4j.model.ucsc_genomes.UCSCGenomesGraph;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

public final class UCSCChromosomeGenomesChromosome<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends
        UCSCGenomesGraph.UCSCUniProtEdge<
                // src
                UCSCChromosome<I, RV, RVT, RE, RET>,
                UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                UCSCGraph<I, RV, RVT, RE, RET>,
                // edge
                UCSCChromosomeGenomesChromosome<I, RV, RVT, RE, RET>,
                UCSCGenomesGraph<I, RV, RVT, RE, RET>.UCSCChromosomeGenomesChromosomeType,
                //tgt
                GenomesChromosome<I, RV, RVT, RE, RET>,
                GenomesGraph<I, RV, RVT, RE, RET>.GenomesChromosomeType,
                GenomesGraph<I, RV, RVT, RE, RET>,
                // raw stuff
                I, RV, RVT, RE, RET
                > {

    public UCSCChromosomeGenomesChromosome(RE edge, UCSCGenomesGraph<I, RV, RVT, RE, RET>.UCSCChromosomeGenomesChromosomeType type) {
        super(edge, type);
    }


    @Override
    public UCSCChromosomeGenomesChromosome<I, RV, RVT, RE, RET> self() {
        return this;
    }
}