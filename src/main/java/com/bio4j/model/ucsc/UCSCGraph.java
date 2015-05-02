package com.bio4j.model.ucsc;

import com.bio4j.model.ucsc.edges.UCSCAssemblyChromosome;
import com.bio4j.model.ucsc.edges.UCSCChromosomeFragment;
import com.bio4j.model.ucsc.vertices.UCSCAssembly;
import com.bio4j.model.ucsc.vertices.UCSCChromosome;
import com.bio4j.model.ucsc.vertices.UCSCFragment;
import com.bio4j.model.ucsc_uniprot.UCSCUniProtGraph;
import com.bio4j.angulillos.*;

public abstract class UCSCGraph<
        // untyped graph
        I extends UntypedGraph<RV, RVT, RE, RET>,
        // vertices
        RV, RVT,
        // edges
        RE, RET
        >
        implements
        TypedGraph<
                UCSCGraph<I, RV, RVT, RE, RET>,
                I, RV, RVT, RE, RET
                > {

    protected I raw = null;

    public UCSCGraph(I graph) {
        raw = graph;
    }

    public I raw() {
        return raw;
    }

    // indices
    public abstract TypedVertexIndex.Unique<
            // vertex
            UCSCChromosome<I, RV, RVT, RE, RET>, UCSCChromosomeType,
            // property
            UCSCChromosomeType.id, String,
            // graph
            UCSCGraph<I, RV, RVT, RE, RET>,
            I, RV, RVT, RE, RET
            >
    UCSCChromosomeIdIndex();

    public abstract UCSCUniProtGraph<I, RV, RVT, RE, RET> UCSCUniProtGraph();

    // types
    // vertices
    public abstract UCSCChromosomeType UCSCChromosome();
    public abstract UCSCAssemblyType UCSCAssembly();
    public abstract UCSCFragmentType UCSCFragment();


    // edges
    public abstract UCSCAssemblyChromosomeType UCSCAssemblyChromosome();
    public abstract UCSCChromosomeFragmentType UCSCChromosomeFragment();

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Vertex types

    public final class UCSCChromosomeType
            extends
            UCSCVertexType<
                    UCSCChromosome<I, RV, RVT, RE, RET>,
                    UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType
                    > {

        public final id id = new id();


        public UCSCChromosomeType(RVT raw) {
            super(raw);
        }

        @Override
        public UCSCChromosomeType value() {
            return graph().UCSCChromosome();
        }

        @Override
        public UCSCChromosome<I, RV, RVT, RE, RET> from(RV vertex) {
            return new UCSCChromosome<I, RV, RVT, RE, RET>(vertex, this);
        }

        public final class id
                extends
                UCSCVertexProperty<UCSCChromosome<I, RV, RVT, RE, RET>, UCSCChromosomeType, id, String> {
            public id() {
                super(UCSCChromosomeType.this);
            }

            public Class<String> valueClass() {
                return String.class;
            }
        }

    }

    public final class UCSCAssemblyType
            extends
            UCSCVertexType<
                    UCSCAssembly<I, RV, RVT, RE, RET>,
                    UCSCGraph<I, RV, RVT, RE, RET>.UCSCAssemblyType
                    > {

        public final id id = new id();


        public UCSCAssemblyType(RVT raw) {
            super(raw);
        }

        @Override
        public UCSCAssemblyType value() {
            return graph().UCSCAssembly();
        }

        @Override
        public UCSCAssembly<I, RV, RVT, RE, RET> from(RV vertex) {
            return new UCSCAssembly<I, RV, RVT, RE, RET>(vertex, this);
        }

        public final class id
                extends
                UCSCVertexProperty<UCSCAssembly<I, RV, RVT, RE, RET>, UCSCAssemblyType, id, String> {
            public id() {
                super(UCSCAssemblyType.this);
            }

            public Class<String> valueClass() {
                return String.class;
            }
        }

    }

    public final class UCSCFragmentType
            extends
            UCSCVertexType<
                    UCSCFragment<I, RV, RVT, RE, RET>,
                    UCSCGraph<I, RV, RVT, RE, RET>.UCSCFragmentType
                    > {

        public final id id = new id();


        public UCSCFragmentType(RVT raw) {
            super(raw);
        }

        @Override
        public UCSCFragmentType value() {
            return graph().UCSCFragment();
        }

        @Override
        public UCSCFragment<I, RV, RVT, RE, RET> from(RV vertex) {
            return new UCSCFragment<I, RV, RVT, RE, RET>(vertex, this);
        }

        public final class id
                extends
                UCSCVertexProperty<UCSCFragment<I, RV, RVT, RE, RET>, UCSCFragmentType, id, String> {
            public id() {
                super(UCSCFragmentType.this);
            }

            public Class<String> valueClass() {
                return String.class;
            }
        }

    }

    public final class UCSCAssemblyChromosomeType
            extends
            UCSCEdgeType<
                    // src
                    UCSCAssembly<I, RV, RVT, RE, RET>,
                    UCSCGraph<I, RV, RVT, RE, RET>.UCSCAssemblyType,
                    // edge
                    UCSCAssemblyChromosome<I, RV, RVT, RE, RET>,
                    UCSCGraph<I, RV, RVT, RE, RET>.UCSCAssemblyChromosomeType,
                    // tgt
                    UCSCChromosome<I, RV, RVT, RE, RET>,
                    UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType
                    >
            implements
            TypedEdge.Type.OneToMany {


        public UCSCAssemblyChromosomeType(RET raw) {

            super(
                    UCSCGraph.this.UCSCAssembly(),
                    raw,
                    UCSCGraph.this.UCSCChromosome()
            );
        }


        @Override
        public UCSCAssemblyChromosomeType value() {
            return graph().UCSCAssemblyChromosome();
        }

        @Override
        public UCSCAssemblyChromosome<I, RV, RVT, RE, RET> from(RE edge) {
            return new UCSCAssemblyChromosome<I, RV, RVT, RE, RET>(edge, this);
        }

    }


    public final class UCSCChromosomeFragmentType
            extends
            UCSCEdgeType<
                    // src
                    UCSCChromosome<I, RV, RVT, RE, RET>,
                    UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                    // edge
                    UCSCChromosomeFragment<I, RV, RVT, RE, RET>,
                    UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeFragmentType,
                    // tgt
                    UCSCFragment<I, RV, RVT, RE, RET>,
                    UCSCGraph<I, RV, RVT, RE, RET>.UCSCFragmentType
                    >
            implements
            TypedEdge.Type.OneToMany {


        public UCSCChromosomeFragmentType(RET raw) {

            super(
                    UCSCGraph.this.UCSCChromosome(),
                    raw,
                    UCSCGraph.this.UCSCFragment()
            );
        }


        @Override
        public UCSCChromosomeFragmentType value() {
            return graph().UCSCChromosomeFragment();
        }

        @Override
        public UCSCChromosomeFragment<I, RV, RVT, RE, RET> from(RE edge) {
            return new UCSCChromosomeFragment<I, RV, RVT, RE, RET>(edge, this);
        }

    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // helper classes

    public abstract class UCSCVertexProperty<
            V extends UCSCVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends UCSCGraph<I, RV, RVT, RE, RET>.UCSCVertexType<V, VT>,
            P extends UCSCVertexProperty<V, VT, P, PV>,
            PV
            >
            implements
            Property<V, VT, P, PV, UCSCGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        protected UCSCVertexProperty(VT type) {

            this.type = type;
        }

        private VT type;

        @Override
        public final VT elementType() {
            return type;
        }
    }

    public abstract static class UCSCVertex<
            V extends UCSCVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends UCSCGraph<I, RV, RVT, RE, RET>.UCSCVertexType<V, VT>,
            I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
            >
            implements
            TypedVertex<V, VT, UCSCGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        private RV vertex;
        private VT type;

        protected UCSCVertex(RV vertex, VT type) {

            this.vertex = vertex;
            this.type = type;
        }

        @Override
        public UCSCGraph<I, RV, RVT, RE, RET> graph() {
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

    abstract class UCSCVertexType<
            V extends UCSCVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends UCSCGraph<I, RV, RVT, RE, RET>.UCSCVertexType<V, VT>
            >
            implements
            TypedVertex.Type<V, VT, UCSCGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        private RVT raw;

        protected UCSCVertexType(RVT raw) {
            this.raw = raw;
        }

        @Override
        public final RVT raw() {
            return raw;
        }

        @Override
        public final UCSCGraph<I, RV, RVT, RE, RET> graph() {
            return UCSCGraph.this;
        }
    }

    public abstract static class UCSCEdge<
            S extends UCSCVertex<S, ST, I, RV, RVT, RE, RET>,
            ST extends UCSCGraph<I, RV, RVT, RE, RET>.UCSCVertexType<S, ST>,

            E extends UCSCEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
            ET extends UCSCGraph<I, RV, RVT, RE, RET>.UCSCEdgeType<S, ST, E, ET, T, TT>,

            T extends UCSCVertex<T, TT, I, RV, RVT, RE, RET>,
            TT extends UCSCGraph<I, RV, RVT, RE, RET>.UCSCVertexType<T, TT>,

            I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
            >
            implements
            TypedEdge<
                    S, ST, UCSCGraph<I, RV, RVT, RE, RET>,
                    E, ET, UCSCGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
                    T, TT, UCSCGraph<I, RV, RVT, RE, RET>
                    > {

        private RE edge;
        private ET type;

        protected UCSCEdge(RE edge, ET type) {

            this.edge = edge;
            this.type = type;
        }

        @Override
        public UCSCGraph<I, RV, RVT, RE, RET> graph() {
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

    abstract class UCSCEdgeType<
            S extends UCSCVertex<S, ST, I, RV, RVT, RE, RET>,
            ST extends UCSCGraph<I, RV, RVT, RE, RET>.UCSCVertexType<S, ST>,

            E extends UCSCEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
            ET extends UCSCGraph<I, RV, RVT, RE, RET>.UCSCEdgeType<S, ST, E, ET, T, TT>,

            T extends UCSCVertex<T, TT, I, RV, RVT, RE, RET>,
            TT extends UCSCGraph<I, RV, RVT, RE, RET>.UCSCVertexType<T, TT>
            >
            implements
            TypedEdge.Type<
                    S, ST, UCSCGraph<I, RV, RVT, RE, RET>,
                    E, ET, UCSCGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
                    T, TT, UCSCGraph<I, RV, RVT, RE, RET>
                    > {

        private RET raw;
        private ST srcT;
        private TT tgtT;

        protected UCSCEdgeType(ST srcT, RET raw, TT tgtT) {

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
        public final UCSCGraph<I, RV, RVT, RE, RET> graph() {
            return UCSCGraph.this;
        }
    }



}
