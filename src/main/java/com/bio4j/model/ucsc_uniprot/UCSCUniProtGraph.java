package com.bio4j.model.ucsc_uniprot;


import com.bio4j.model.ucsc.UCSCGraph;
import com.bio4j.model.ucsc.vertices.UCSCChromosome;
import com.bio4j.model.ucsc_uniprot.edges.UCSCChromosomeProtein;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.*;


public abstract class UCSCUniProtGraph<
        // untyped graph
        I extends UntypedGraph<RV, RVT, RE, RET>,
        // vertices
        RV, RVT,
        // edges
        RE, RET
        >
        implements
        TypedGraph<
                UCSCUniProtGraph<I, RV, RVT, RE, RET>,
                I, RV, RVT, RE, RET
                > {

    protected I raw = null;

    public UCSCUniProtGraph(I graph) {
        raw = graph;
    }

    public I raw() {
        return raw;
    }

    public abstract UniProtGraph<I, RV, RVT, RE, RET> uniProtGraph();

    public abstract UCSCGraph<I, RV, RVT, RE, RET> UCSCGraph();

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // edges
    public abstract UCSCChromosomeProteinType UCSCChromosomeProtein();

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Edge types
    public final class UCSCChromosomeProteinType
            extends
            UCSCUniProtEdgeType<
                    // src
                    UCSCChromosome<I, RV, RVT, RE, RET>,
                    UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                    UCSCGraph<I, RV, RVT, RE, RET>,
                    // edge
                    UCSCChromosomeProtein<I, RV, RVT, RE, RET>,
                    UCSCUniProtGraph<I, RV, RVT, RE, RET>.UCSCChromosomeProteinType,
                    // tgt
                    Protein<I, RV, RVT, RE, RET>,
                    UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
                    UniProtGraph<I, RV, RVT, RE, RET>
                    >
            implements
            TypedEdge.Type.OneToMany {


        public UCSCChromosomeProteinType(RET raw) {

            super(
                    UCSCUniProtGraph.this.UCSCGraph().UCSCChromosome(),
                    raw,
                    UCSCUniProtGraph.this.uniProtGraph().Protein()
            );
        }


        @Override
        public UCSCChromosomeProteinType value() {
            return graph().UCSCChromosomeProtein();
        }

        @Override
        public UCSCChromosomeProtein<I, RV, RVT, RE, RET> from(RE edge) {
            return new UCSCChromosomeProtein<I, RV, RVT, RE, RET>(edge, this);
        }

        public final alignID alignID = new alignID();
        public final class alignID
                extends
                UCSCUniProtEdgeProperty<
                        UCSCChromosome<I, RV, RVT, RE, RET>,
                        UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                        UCSCGraph<I, RV, RVT, RE, RET>,

                        UCSCChromosomeProtein<I, RV, RVT, RE, RET>,
                        UCSCChromosomeProteinType,

                        Protein<I, RV, RVT, RE, RET>,
                        UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
                        UniProtGraph<I, RV, RVT, RE, RET>,
                        alignID,
                        String> {

            public alignID() {
                super(UCSCChromosomeProteinType.this);
            }

            public Class<String> valueClass() {
                return String.class;
            }
        }

        public final txStart txStart = new txStart();
        public final class txStart
                extends
                UCSCUniProtEdgeProperty<
                        UCSCChromosome<I, RV, RVT, RE, RET>,
                        UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                        UCSCGraph<I, RV, RVT, RE, RET>,

                        UCSCChromosomeProtein<I, RV, RVT, RE, RET>,
                        UCSCChromosomeProteinType,

                        Protein<I, RV, RVT, RE, RET>,
                        UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
                        UniProtGraph<I, RV, RVT, RE, RET>,
                        txStart,
                        Long> {

            public txStart() {
                super(UCSCChromosomeProteinType.this);
            }

            public Class<Long> valueClass() {
                return Long.class;
            }
        }

        public final txEnd txEnd = new txEnd();
        public final class txEnd
                extends
                UCSCUniProtEdgeProperty<
                        UCSCChromosome<I, RV, RVT, RE, RET>,
                        UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                        UCSCGraph<I, RV, RVT, RE, RET>,

                        UCSCChromosomeProtein<I, RV, RVT, RE, RET>,
                        UCSCChromosomeProteinType,

                        Protein<I, RV, RVT, RE, RET>,
                        UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
                        UniProtGraph<I, RV, RVT, RE, RET>,
                        txEnd,
                        Long> {

            public txEnd() {
                super(UCSCChromosomeProteinType.this);
            }

            public Class<Long> valueClass() {
                return Long.class;
            }
        }

        public final cdsStart cdsStart = new cdsStart();
        public final class cdsStart
                extends
                UCSCUniProtEdgeProperty<
                        UCSCChromosome<I, RV, RVT, RE, RET>,
                        UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                        UCSCGraph<I, RV, RVT, RE, RET>,

                        UCSCChromosomeProtein<I, RV, RVT, RE, RET>,
                        UCSCChromosomeProteinType,

                        Protein<I, RV, RVT, RE, RET>,
                        UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
                        UniProtGraph<I, RV, RVT, RE, RET>,
                        cdsStart,
                        Long> {

            public cdsStart() {
                super(UCSCChromosomeProteinType.this);
            }

            public Class<Long> valueClass() {
                return Long.class;
            }
        }

        public final csdEnd csdEnd = new csdEnd();
        public final class csdEnd
                extends
                UCSCUniProtEdgeProperty<
                        UCSCChromosome<I, RV, RVT, RE, RET>,
                        UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                        UCSCGraph<I, RV, RVT, RE, RET>,

                        UCSCChromosomeProtein<I, RV, RVT, RE, RET>,
                        UCSCChromosomeProteinType,

                        Protein<I, RV, RVT, RE, RET>,
                        UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
                        UniProtGraph<I, RV, RVT, RE, RET>,
                        csdEnd,
                        Long> {

            public csdEnd() {
                super(UCSCChromosomeProteinType.this);
            }

            public Class<Long> valueClass() {
                return Long.class;
            }
        }

        public final strand strand = new strand();
        public final class strand
                extends
                UCSCUniProtEdgeProperty<
                        UCSCChromosome<I, RV, RVT, RE, RET>,
                        UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                        UCSCGraph<I, RV, RVT, RE, RET>,

                        UCSCChromosomeProtein<I, RV, RVT, RE, RET>,
                        UCSCChromosomeProteinType,

                        Protein<I, RV, RVT, RE, RET>,
                        UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
                        UniProtGraph<I, RV, RVT, RE, RET>,
                        strand,
                        String> {

            public strand() {
                super(UCSCChromosomeProteinType.this);
            }

            public Class<String> valueClass() {
                return String.class;
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // helper classes

    public abstract class UCSCUniProtVertexProperty<
            V extends UCSCUniProtVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends UCSCUniProtGraph<I, RV, RVT, RE, RET>.UCSCUniProtVertexType<V, VT>,
            P extends UCSCUniProtVertexProperty<V, VT, P, PV>,
            PV
            >
            implements
            Property<V, VT, P, PV, UCSCUniProtGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

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
            VT extends UCSCUniProtGraph<I, RV, RVT, RE, RET>.UCSCUniProtVertexType<V, VT>,
            I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
            >
            implements
            TypedVertex<V, VT, UCSCUniProtGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        private RV vertex;
        private VT type;

        protected UCSCUniProtVertex(RV vertex, VT type) {

            this.vertex = vertex;
            this.type = type;
        }

        @Override
        public UCSCUniProtGraph<I, RV, RVT, RE, RET> graph() {
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
            VT extends UCSCUniProtGraph<I, RV, RVT, RE, RET>.UCSCUniProtVertexType<V, VT>
            >
            implements
            TypedVertex.Type<V, VT, UCSCUniProtGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        private RVT raw;

        protected UCSCUniProtVertexType(RVT raw) {
            this.raw = raw;
        }

        @Override
        public final RVT raw() {
            return raw;
        }

        @Override
        public final UCSCUniProtGraph<I, RV, RVT, RE, RET> graph() {
            return UCSCUniProtGraph.this;
        }
    }

    public abstract static class UCSCUniProtEdge<
            // src
            S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
            ST extends TypedVertex.Type<S, ST, SG, I, RV, RVT, RE, RET>,
            SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
            // edge
            E extends UCSCUniProtEdge<S, ST, SG, E, ET, T, TT, TG, I, RV, RVT, RE, RET>,
            ET extends UCSCUniProtGraph<I, RV, RVT, RE, RET>.UCSCUniProtEdgeType<S, ST, SG, E, ET, T, TT, TG>,
            // tgt
            T extends TypedVertex<T, TT, TG, I, RV, RVT, RE, RET>,
            TT extends TypedVertex.Type<T, TT, TG, I, RV, RVT, RE, RET>,
            TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
            I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
            >
            implements
            TypedEdge<
                    S, ST, SG,
                    E, ET, UCSCUniProtGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
                    T, TT, TG
                    > {

        private RE edge;
        private ET type;

        protected UCSCUniProtEdge(RE edge, ET type) {

            this.edge = edge;
            this.type = type;
        }

        @Override
        public UCSCUniProtGraph<I, RV, RVT, RE, RET> graph() {
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
            ET extends UCSCUniProtGraph<I, RV, RVT, RE, RET>.UCSCUniProtEdgeType<S, ST, SG, E, ET, T, TT, TG>,
            // tgt
            T extends TypedVertex<T, TT, TG, I, RV, RVT, RE, RET>,
            TT extends TypedVertex.Type<T, TT, TG, I, RV, RVT, RE, RET>,
            TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
            >
            implements
            TypedEdge.Type<
                    S, ST, SG,
                    E, ET, UCSCUniProtGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
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
        public final UCSCUniProtGraph<I, RV, RVT, RE, RET> graph() {
            return UCSCUniProtGraph.this;
        }
    }

    public abstract class UCSCUniProtEdgeProperty<
            // src
            S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
            ST extends TypedVertex.Type<S, ST, SG, I, RV, RVT, RE, RET>,
            SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
            E extends UCSCUniProtEdge<S, ST, SG, E, ET, T, TT, TG, I, RV, RVT, RE, RET>,
            ET extends UCSCUniProtGraph<I, RV, RVT, RE, RET>.UCSCUniProtEdgeType<S, ST, SG, E, ET, T, TT, TG>,
            // tgt
            T extends TypedVertex<T, TT, TG, I, RV, RVT, RE, RET>,
            TT extends TypedVertex.Type<T, TT, TG, I, RV, RVT, RE, RET>,
            TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
            P extends UCSCUniProtEdgeProperty<S, ST, SG, E, ET, T, TT, TG, P, PE>,
            PE
            >
            implements
            Property<E, ET, P, PE, UCSCUniProtGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

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
