package com.bio4j.model.genomes;

import com.bio4j.model.genomes.edges.GenomesGenomeChromosome;
import com.bio4j.model.genomes.vertices.GenomesGenome;
import com.bio4j.model.genomes.vertices.GenomesChromosome;
import com.bio4j.angulillos.*;

public abstract class GenomesGraph<
        // untyped graph
        I extends UntypedGraph<RV, RVT, RE, RET>,
        // vertices
        RV, RVT,
        // edges
        RE, RET
        >
        implements
        TypedGraph<
                GenomesGraph<I, RV, RVT, RE, RET>,
                I, RV, RVT, RE, RET
                > {

    protected I raw = null;

    public GenomesGraph(I graph) {
        raw = graph;
    }

    public I raw() {
        return raw;
    }

    // indices
    public abstract TypedVertexIndex.Unique<
            // vertex
            GenomesChromosome<I, RV, RVT, RE, RET>, GenomesChromosomeType,
            // property
            GenomesChromosomeType.id, String,
            // graph
            GenomesGraph<I, RV, RVT, RE, RET>,
            I, RV, RVT, RE, RET
            >
    GenomesChromosomeIdIndex();

    //public abstract UCSCUniProtGraph<I, RV, RVT, RE, RET> UCSCUniProtGraph();

    // types
    // vertices
    public abstract GenomesChromosomeType GenomesChromosome();
    public abstract GenomesGenomeType GenomesGenome();

    // edges
    public abstract GenomesGenomeChromosomeType GenomesGenomeChromosome();

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Vertex types

    public final class GenomesChromosomeType
            extends
            GenomesVertexType<
                    GenomesChromosome<I, RV, RVT, RE, RET>,
                    GenomesGraph<I, RV, RVT, RE, RET>.GenomesChromosomeType
                    > {

        public final id id = new id();


        public GenomesChromosomeType(RVT raw) {
            super(raw);
        }

        @Override
        public GenomesChromosomeType value() {
            return graph().GenomesChromosome();
        }

        @Override
        public GenomesChromosome<I, RV, RVT, RE, RET> from(RV vertex) {
            return new GenomesChromosome<I, RV, RVT, RE, RET>(vertex, this);
        }

        public final class id
                extends
                GenomesVertexProperty<GenomesChromosome<I, RV, RVT, RE, RET>, GenomesChromosomeType, id, String> {
            public id() {
                super(GenomesChromosomeType.this);
            }

            public Class<String> valueClass() {
                return String.class;
            }
        }

    }

    public final class GenomesGenomeType
            extends
            GenomesVertexType<
                    GenomesGenome<I, RV, RVT, RE, RET>,
                    GenomesGraph<I, RV, RVT, RE, RET>.GenomesGenomeType
                    > {

        public final id id = new id();


        public GenomesGenomeType(RVT raw) {
            super(raw);
        }

        @Override
        public GenomesGenomeType value() {
            return graph().GenomesGenome();
        }

        @Override
        public GenomesGenome<I, RV, RVT, RE, RET> from(RV vertex) {
            return new GenomesGenome<I, RV, RVT, RE, RET>(vertex, this);
        }

        public final class id
                extends
                GenomesVertexProperty<GenomesGenome<I, RV, RVT, RE, RET>, GenomesGenomeType, id, String> {
            public id() {
                super(GenomesGenomeType.this);
            }

            public Class<String> valueClass() {
                return String.class;
            }
        }

    }

    public final class GenomesGenomeChromosomeType
            extends
            GenomesEdgeType<
                    // src
                    GenomesGenome<I, RV, RVT, RE, RET>,
                    GenomesGraph<I, RV, RVT, RE, RET>.GenomesGenomeType,
                    // edge
                    GenomesGenomeChromosome<I, RV, RVT, RE, RET>,
                    GenomesGraph<I, RV, RVT, RE, RET>.GenomesGenomeChromosomeType,
                    // tgt
                    GenomesChromosome<I, RV, RVT, RE, RET>,
                    GenomesGraph<I, RV, RVT, RE, RET>.GenomesChromosomeType
                    >
            implements
            TypedEdge.Type.OneToMany {


        public GenomesGenomeChromosomeType(RET raw) {

            super(
                    GenomesGraph.this.GenomesGenome(),
                    raw,
                    GenomesGraph.this.GenomesChromosome()
            );
        }


        @Override
        public GenomesGenomeChromosomeType value() {
            return graph().GenomesGenomeChromosome();
        }

        @Override
        public GenomesGenomeChromosome<I, RV, RVT, RE, RET> from(RE edge) {
            return new GenomesGenomeChromosome<I, RV, RVT, RE, RET>(edge, this);
        }

    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // helper classes

    public abstract class GenomesVertexProperty<
            V extends GenomesVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends GenomesGraph<I, RV, RVT, RE, RET>.GenomesVertexType<V, VT>,
            P extends GenomesVertexProperty<V, VT, P, PV>,
            PV
            >
            implements
            Property<V, VT, P, PV, GenomesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        protected GenomesVertexProperty(VT type) {

            this.type = type;
        }

        private VT type;

        @Override
        public final VT elementType() {
            return type;
        }
    }

    public abstract static class GenomesVertex<
            V extends GenomesVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends GenomesGraph<I, RV, RVT, RE, RET>.GenomesVertexType<V, VT>,
            I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
            >
            implements
            TypedVertex<V, VT, GenomesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        private RV vertex;
        private VT type;

        protected GenomesVertex(RV vertex, VT type) {

            this.vertex = vertex;
            this.type = type;
        }

        @Override
        public GenomesGraph<I, RV, RVT, RE, RET> graph() {
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

    abstract class GenomesVertexType<
            V extends GenomesVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends GenomesGraph<I, RV, RVT, RE, RET>.GenomesVertexType<V, VT>
            >
            implements
            TypedVertex.Type<V, VT, GenomesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        private RVT raw;

        protected GenomesVertexType(RVT raw) {
            this.raw = raw;
        }

        @Override
        public final RVT raw() {
            return raw;
        }

        @Override
        public final GenomesGraph<I, RV, RVT, RE, RET> graph() {
            return GenomesGraph.this;
        }
    }

    public abstract static class GenomesEdge<
            S extends GenomesVertex<S, ST, I, RV, RVT, RE, RET>,
            ST extends GenomesGraph<I, RV, RVT, RE, RET>.GenomesVertexType<S, ST>,

            E extends GenomesEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
            ET extends GenomesGraph<I, RV, RVT, RE, RET>.GenomesEdgeType<S, ST, E, ET, T, TT>,

            T extends GenomesVertex<T, TT, I, RV, RVT, RE, RET>,
            TT extends GenomesGraph<I, RV, RVT, RE, RET>.GenomesVertexType<T, TT>,

            I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
            >
            implements
            TypedEdge<
                    S, ST, GenomesGraph<I, RV, RVT, RE, RET>,
                    E, ET, GenomesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
                    T, TT, GenomesGraph<I, RV, RVT, RE, RET>
                    > {

        private RE edge;
        private ET type;

        protected GenomesEdge(RE edge, ET type) {

            this.edge = edge;
            this.type = type;
        }

        @Override
        public GenomesGraph<I, RV, RVT, RE, RET> graph() {
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

    abstract class GenomesEdgeType<
            S extends GenomesVertex<S, ST, I, RV, RVT, RE, RET>,
            ST extends GenomesGraph<I, RV, RVT, RE, RET>.GenomesVertexType<S, ST>,

            E extends GenomesEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
            ET extends GenomesGraph<I, RV, RVT, RE, RET>.GenomesEdgeType<S, ST, E, ET, T, TT>,

            T extends GenomesVertex<T, TT, I, RV, RVT, RE, RET>,
            TT extends GenomesGraph<I, RV, RVT, RE, RET>.GenomesVertexType<T, TT>
            >
            implements
            TypedEdge.Type<
                    S, ST, GenomesGraph<I, RV, RVT, RE, RET>,
                    E, ET, GenomesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
                    T, TT, GenomesGraph<I, RV, RVT, RE, RET>
                    > {

        private RET raw;
        private ST srcT;
        private TT tgtT;

        protected GenomesEdgeType(ST srcT, RET raw, TT tgtT) {

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
        public final GenomesGraph<I, RV, RVT, RE, RET> graph() {
            return GenomesGraph.this;
        }
    }



}
