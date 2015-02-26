package com.bio4j.model.ucsc;

import com.bio4j.model.ucsc.vertices.UCSCGenesChromosome;
import com.bio4j.model.ucsc_uniprot.UCSCGenesUniProtGraph;
import com.bio4j.angulillos.*;

public abstract class UCSCGenesGraph<
        // untyped graph
        I extends UntypedGraph<RV, RVT, RE, RET>,
        // vertices
        RV, RVT,
        // edges
        RE, RET
        >
        implements
        TypedGraph<
                UCSCGenesGraph<I, RV, RVT, RE, RET>,
                I, RV, RVT, RE, RET
                > {

    protected I raw = null;

    public UCSCGenesGraph(I graph) {
        raw = graph;
    }

    public I raw() {
        return raw;
    }

    // indices
    public abstract TypedVertexIndex.Unique<
            // vertex
            UCSCGenesChromosome<I, RV, RVT, RE, RET>, UCSCGenesChromosomeType,
            // property
            UCSCGenesChromosomeType.id, String,
            // graph
            UCSCGenesGraph<I, RV, RVT, RE, RET>,
            I, RV, RVT, RE, RET
            >
    ucscGenesChromosomeIdIndex();

    public abstract UCSCGenesUniProtGraph<I, RV, RVT, RE, RET> ucscGenesUniProtGraph();
    //public abstract UCSCGenesGenInfoGraph<I, RV, RVT, RE, RET> UCSCGenesGenInfoGraph();

    // types
    // vertices
    public abstract UCSCGenesChromosomeType UCSCGenesChromosome();

    // edges


    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Vertex types

    public final class UCSCGenesChromosomeType
            extends
            UCSCGenesVertexType<
                    UCSCGenesChromosome<I, RV, RVT, RE, RET>,
                    UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesChromosomeType
                    > {

        public final id id = new id();


        public UCSCGenesChromosomeType(RVT raw) {
            super(raw);
        }

        @Override
        public UCSCGenesChromosomeType value() {
            return graph().UCSCGenesChromosome();
        }

        @Override
        public UCSCGenesChromosome<I, RV, RVT, RE, RET> from(RV vertex) {
            return new UCSCGenesChromosome<I, RV, RVT, RE, RET>(vertex, this);
        }

        public final class id
                extends
                UCSCGenesVertexProperty<UCSCGenesChromosome<I, RV, RVT, RE, RET>, UCSCGenesChromosomeType, id, String> {
            public id() {
                super(UCSCGenesChromosomeType.this);
            }

            public Class<String> valueClass() {
                return String.class;
            }
        }

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // helper classes

    public abstract class UCSCGenesVertexProperty<
            V extends UCSCGenesVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesVertexType<V, VT>,
            P extends UCSCGenesVertexProperty<V, VT, P, PV>,
            PV
            >
            implements
            Property<V, VT, P, PV, UCSCGenesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        protected UCSCGenesVertexProperty(VT type) {

            this.type = type;
        }

        private VT type;

        @Override
        public final VT elementType() {
            return type;
        }
    }

    public abstract static class UCSCGenesVertex<
            V extends UCSCGenesVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesVertexType<V, VT>,
            I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
            >
            implements
            TypedVertex<V, VT, UCSCGenesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        private RV vertex;
        private VT type;

        protected UCSCGenesVertex(RV vertex, VT type) {

            this.vertex = vertex;
            this.type = type;
        }

        @Override
        public UCSCGenesGraph<I, RV, RVT, RE, RET> graph() {
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

    abstract class UCSCGenesVertexType<
            V extends UCSCGenesVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesVertexType<V, VT>
            >
            implements
            TypedVertex.Type<V, VT, UCSCGenesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        private RVT raw;

        protected UCSCGenesVertexType(RVT raw) {
            this.raw = raw;
        }

        @Override
        public final RVT raw() {
            return raw;
        }

        @Override
        public final UCSCGenesGraph<I, RV, RVT, RE, RET> graph() {
            return UCSCGenesGraph.this;
        }
    }

    public abstract static class UCSCGenesEdge<
            S extends UCSCGenesVertex<S, ST, I, RV, RVT, RE, RET>,
            ST extends UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesVertexType<S, ST>,
            E extends UCSCGenesEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
            ET extends UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesEdgeType<S, ST, E, ET, T, TT>,
            T extends UCSCGenesVertex<T, TT, I, RV, RVT, RE, RET>,
            TT extends UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesVertexType<T, TT>,
            I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
            >
            implements
            TypedEdge<
                    S, ST, UCSCGenesGraph<I, RV, RVT, RE, RET>,
                    E, ET, UCSCGenesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
                    T, TT, UCSCGenesGraph<I, RV, RVT, RE, RET>
                    > {

        private RE edge;
        private ET type;

        protected UCSCGenesEdge(RE edge, ET type) {

            this.edge = edge;
            this.type = type;
        }

        @Override
        public UCSCGenesGraph<I, RV, RVT, RE, RET> graph() {
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

    abstract class UCSCGenesEdgeType<
            S extends UCSCGenesVertex<S, ST, I, RV, RVT, RE, RET>,
            ST extends UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesVertexType<S, ST>,
            E extends UCSCGenesEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
            ET extends UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesEdgeType<S, ST, E, ET, T, TT>,
            T extends UCSCGenesVertex<T, TT, I, RV, RVT, RE, RET>,
            TT extends UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesVertexType<T, TT>
            >
            implements
            TypedEdge.Type<
                    S, ST, UCSCGenesGraph<I, RV, RVT, RE, RET>,
                    E, ET, UCSCGenesGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
                    T, TT, UCSCGenesGraph<I, RV, RVT, RE, RET>
                    > {

        private RET raw;
        private ST srcT;
        private TT tgtT;

        protected UCSCGenesEdgeType(ST srcT, RET raw, TT tgtT) {

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
        public final UCSCGenesGraph<I, RV, RVT, RE, RET> graph() {
            return UCSCGenesGraph.this;
        }
    }

}
