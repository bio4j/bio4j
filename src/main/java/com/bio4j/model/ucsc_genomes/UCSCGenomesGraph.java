package com.bio4j.model.ucsc_genomes;


import com.bio4j.model.genomes.GenomesGraph;
import com.bio4j.model.genomes.vertices.GenomesChromosome;
import com.bio4j.model.genomes.vertices.GenomesGenome;
import com.bio4j.model.ucsc.UCSCGraph;
import com.bio4j.model.ucsc.vertices.UCSCAssembly;
import com.bio4j.model.ucsc.vertices.UCSCChromosome;
import com.bio4j.model.ucsc_genomes.edges.UCSCAssemblyGenomesGenome;
import com.bio4j.model.ucsc_genomes.edges.UCSCChromosomeGenomesChromosome;

import com.bio4j.angulillos.*;


public abstract class UCSCGenomesGraph<
        // untyped graph
        I extends UntypedGraph<RV, RVT, RE, RET>,
        // vertices
        RV, RVT,
        // edges
        RE, RET
        >
        implements
        TypedGraph<
                UCSCGenomesGraph<I, RV, RVT, RE, RET>,
                I, RV, RVT, RE, RET
                > {

    protected I raw = null;

    public UCSCGenomesGraph(I graph) {
        raw = graph;
    }

    public I raw() {
        return raw;
    }

    public abstract GenomesGraph<I, RV, RVT, RE, RET> GenomesGraph();
    public abstract UCSCGraph<I, RV, RVT, RE, RET> UCSCGraph();

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // edges
    public abstract UCSCChromosomeGenomesChromosomeType UCSCChromosomeGenomesChromosome();
    public abstract UCSCAssemblyGenomesGenomeType UCSCAssemblyGenomesGenome();

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Edge types
    public final class UCSCChromosomeGenomesChromosomeType
            extends
            UCSCUniProtEdgeType<
                    // src
                    UCSCChromosome<I, RV, RVT, RE, RET>,
                    UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                    UCSCGraph<I, RV, RVT, RE, RET>,
                    // edge
                    UCSCChromosomeGenomesChromosome<I, RV, RVT, RE, RET>,
                    UCSCGenomesGraph<I, RV, RVT, RE, RET>.UCSCChromosomeGenomesChromosomeType,
                    // tgt
                    GenomesChromosome<I, RV, RVT, RE, RET>,
                    GenomesGraph<I, RV, RVT, RE, RET>.GenomesChromosomeType,
                    GenomesGraph<I, RV, RVT, RE, RET>
                    >
            implements
            TypedEdge.Type.OneToMany {


        public UCSCChromosomeGenomesChromosomeType(RET raw) {

            super(
                    UCSCGenomesGraph.this.UCSCGraph().UCSCChromosome(),
                    raw,
                    UCSCGenomesGraph.this.GenomesGraph().GenomesChromosome()
            );
        }


        @Override
        public UCSCChromosomeGenomesChromosomeType value() {
            return graph().UCSCChromosomeGenomesChromosome();
        }

        @Override
        public UCSCChromosomeGenomesChromosome<I, RV, RVT, RE, RET> from(RE edge) {
            return new UCSCChromosomeGenomesChromosome<I, RV, RVT, RE, RET>(edge, this);
        }
    }

    public final class UCSCAssemblyGenomesGenomeType
            extends
            UCSCUniProtEdgeType<
                    // src
                    UCSCAssembly<I, RV, RVT, RE, RET>,
                    UCSCGraph<I, RV, RVT, RE, RET>.UCSCAssemblyType,
                    UCSCGraph<I, RV, RVT, RE, RET>,
                    // edge
                    UCSCAssemblyGenomesGenome<I, RV, RVT, RE, RET>,
                    UCSCGenomesGraph<I, RV, RVT, RE, RET>.UCSCAssemblyGenomesGenomeType,
                    // tgt
                    GenomesGenome<I, RV, RVT, RE, RET>,
                    GenomesGraph<I, RV, RVT, RE, RET>.GenomesGenomeType,
                    GenomesGraph<I, RV, RVT, RE, RET>
                    >
            implements
            TypedEdge.Type.OneToMany {


        public UCSCAssemblyGenomesGenomeType(RET raw) {

            super(
                    UCSCGenomesGraph.this.UCSCGraph().UCSCAssembly(),
                    raw,
                    UCSCGenomesGraph.this.GenomesGraph().GenomesGenome()
            );
        }


        @Override
        public UCSCAssemblyGenomesGenomeType value() {
            return graph().UCSCAssemblyGenomesGenome();
        }

        @Override
        public UCSCAssemblyGenomesGenome<I, RV, RVT, RE, RET> from(RE edge) {
            return new UCSCAssemblyGenomesGenome<I, RV, RVT, RE, RET>(edge, this);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // helper classes

    public abstract class UCSCUniProtVertexProperty<
            V extends UCSCUniProtVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends UCSCGenomesGraph<I, RV, RVT, RE, RET>.UCSCUniProtVertexType<V, VT>,
            P extends UCSCUniProtVertexProperty<V, VT, P, PV>,
            PV
            >
            implements
            Property<V, VT, P, PV, UCSCGenomesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        protected UCSCUniProtVertexProperty(VT type) {

            this.type = type;
        }

        private VT type;

        @Override
        public final VT elementType() {
            return type;
        }
    }

    public abstract static class UCSCUniProtVertex<
            V extends UCSCUniProtVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends UCSCGenomesGraph<I, RV, RVT, RE, RET>.UCSCUniProtVertexType<V, VT>,
            I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
            >
            implements
            TypedVertex<V, VT, UCSCGenomesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        private RV vertex;
        private VT type;

        protected UCSCUniProtVertex(RV vertex, VT type) {

            this.vertex = vertex;
            this.type = type;
        }

        @Override
        public UCSCGenomesGraph<I, RV, RVT, RE, RET> graph() {
            return type().graph();
        }

        @Override
        public RV raw() {
            return this.vertex;
        }

        @Override
        public VT type() {
            return type;
        }
    }

    abstract class UCSCUniProtVertexType<
            V extends UCSCUniProtVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends UCSCGenomesGraph<I, RV, RVT, RE, RET>.UCSCUniProtVertexType<V, VT>
            >
            implements
            TypedVertex.Type<V, VT, UCSCGenomesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        private RVT raw;

        protected UCSCUniProtVertexType(RVT raw) {
            this.raw = raw;
        }

        @Override
        public final RVT raw() {
            return raw;
        }

        @Override
        public final UCSCGenomesGraph<I, RV, RVT, RE, RET> graph() {
            return UCSCGenomesGraph.this;
        }
    }

    public abstract static class UCSCUniProtEdge<
            // src
            S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
            ST extends TypedVertex.Type<S, ST, SG, I, RV, RVT, RE, RET>,
            SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
            // edge
            E extends UCSCUniProtEdge<S, ST, SG, E, ET, T, TT, TG, I, RV, RVT, RE, RET>,
            ET extends UCSCGenomesGraph<I, RV, RVT, RE, RET>.UCSCUniProtEdgeType<S, ST, SG, E, ET, T, TT, TG>,
            // tgt
            T extends TypedVertex<T, TT, TG, I, RV, RVT, RE, RET>,
            TT extends TypedVertex.Type<T, TT, TG, I, RV, RVT, RE, RET>,
            TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
            I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
            >
            implements
            TypedEdge<
                    S, ST, SG,
                    E, ET, UCSCGenomesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
                    T, TT, TG
                    > {

        private RE edge;
        private ET type;

        protected UCSCUniProtEdge(RE edge, ET type) {

            this.edge = edge;
            this.type = type;
        }

        @Override
        public UCSCGenomesGraph<I, RV, RVT, RE, RET> graph() {
            return type().graph();
        }

        @Override
        public RE raw() {
            return this.edge;
        }

        @Override
        public ET type() {
            return type;
        }
    }

    abstract class UCSCUniProtEdgeType<
            // src
            S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
            ST extends TypedVertex.Type<S, ST, SG, I, RV, RVT, RE, RET>,
            SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
            // edge
            E extends UCSCUniProtEdge<S, ST, SG, E, ET, T, TT, TG, I, RV, RVT, RE, RET>,
            ET extends UCSCGenomesGraph<I, RV, RVT, RE, RET>.UCSCUniProtEdgeType<S, ST, SG, E, ET, T, TT, TG>,
            // tgt
            T extends TypedVertex<T, TT, TG, I, RV, RVT, RE, RET>,
            TT extends TypedVertex.Type<T, TT, TG, I, RV, RVT, RE, RET>,
            TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
            >
            implements
            TypedEdge.Type<
                    S, ST, SG,
                    E, ET, UCSCGenomesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
                    T, TT, TG
                    > {

        private RET raw;
        private ST srcT;
        private TT tgtT;

        protected UCSCUniProtEdgeType(ST srcT, RET raw, TT tgtT) {

            this.raw = raw;
            this.srcT = srcT;
            this.tgtT = tgtT;
        }

        @Override
        public final ST sourceType() {
            return srcT;
        }

        @Override
        public final TT targetType() {
            return tgtT;
        }

        @Override
        public final RET raw() {
            return raw;
        }

        @Override
        public final UCSCGenomesGraph<I, RV, RVT, RE, RET> graph() {
            return UCSCGenomesGraph.this;
        }
    }

    public abstract class UCSCUniProtEdgeProperty<
            // src
            S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
            ST extends TypedVertex.Type<S, ST, SG, I, RV, RVT, RE, RET>,
            SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
            E extends UCSCUniProtEdge<S, ST, SG, E, ET, T, TT, TG, I, RV, RVT, RE, RET>,
            ET extends UCSCGenomesGraph<I, RV, RVT, RE, RET>.UCSCUniProtEdgeType<S, ST, SG, E, ET, T, TT, TG>,
            // tgt
            T extends TypedVertex<T, TT, TG, I, RV, RVT, RE, RET>,
            TT extends TypedVertex.Type<T, TT, TG, I, RV, RVT, RE, RET>,
            TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
            P extends UCSCUniProtEdgeProperty<S, ST, SG, E, ET, T, TT, TG, P, PE>,
            PE
            >
            implements
            Property<E, ET, P, PE, UCSCGenomesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        protected UCSCUniProtEdgeProperty(ET type) {

            this.type = type;
        }

        private ET type;

        @Override
        public final ET elementType() {
            return type;
        }
    }
}
