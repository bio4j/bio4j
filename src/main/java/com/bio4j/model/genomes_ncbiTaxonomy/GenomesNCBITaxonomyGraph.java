package com.bio4j.model.genomes_ncbiTaxonomy;


import com.bio4j.model.genomes.GenomesGraph;
import com.bio4j.model.genomes.vertices.GenomesGenome;


import com.bio4j.angulillos.*;
import com.bio4j.model.genomes_ncbiTaxonomy.edges.GenomesGenomeNCBITaxon;
import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;


public abstract class GenomesNCBITaxonomyGraph<
        // untyped graph
        I extends UntypedGraph<RV, RVT, RE, RET>,
        // vertices
        RV, RVT,
        // edges
        RE, RET
        >
        implements
        TypedGraph<
                GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>,
                I, RV, RVT, RE, RET
                > {

    protected I raw = null;

    public GenomesNCBITaxonomyGraph(I graph) {
        raw = graph;
    }

    public I raw() {
        return raw;
    }

    public abstract GenomesGraph<I, RV, RVT, RE, RET> GenomesGraph();
    public abstract NCBITaxonomyGraph<I, RV, RVT, RE, RET> NCBITaxonomyGraph();

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // edges
    public abstract GenomesGenomeNCBITaxonType GenomesGenomeNCBITaxon();

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Edge types
    public final class GenomesGenomeNCBITaxonType
            extends
            GenomesNCBITaxonomyEdgeType<
                    // src
                    GenomesGenome<I, RV, RVT, RE, RET>,
                    GenomesGraph<I, RV, RVT, RE, RET>.GenomesGenomeType,
                    GenomesGraph<I, RV, RVT, RE, RET>,
                    // edge
                    GenomesGenomeNCBITaxon<I, RV, RVT, RE, RET>,
                    GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>.GenomesGenomeNCBITaxonType,
                    // tgt
                    NCBITaxon<I, RV, RVT, RE, RET>,
                    NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
                    NCBITaxonomyGraph<I, RV, RVT, RE, RET>
                    >
            implements
            TypedEdge.Type.OneToMany {


        public GenomesGenomeNCBITaxonType(RET raw) {

            super(
                    GenomesNCBITaxonomyGraph.this.GenomesGraph().GenomesGenome(),
                    raw,
                    GenomesNCBITaxonomyGraph.this.NCBITaxonomyGraph().NCBITaxon()
            );
        }


        @Override
        public GenomesGenomeNCBITaxonType value() {
            return graph().GenomesGenomeNCBITaxon();
        }

        @Override
        public GenomesGenomeNCBITaxon<I, RV, RVT, RE, RET> from(RE edge) {
            return new GenomesGenomeNCBITaxon<I, RV, RVT, RE, RET>(edge, this);
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // helper classes

    public abstract class GenomesNCBITaxonomyVertexProperty<
            V extends GenomesNCBITaxonomyVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>.GenomesNCBITaxonomyVertexType<V, VT>,
            P extends GenomesNCBITaxonomyVertexProperty<V, VT, P, PV>,
            PV
            >
            implements
            Property<V, VT, P, PV, GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        protected GenomesNCBITaxonomyVertexProperty(VT type) {

            this.type = type;
        }

        private VT type;

        @Override
        public final VT elementType() {
            return type;
        }
    }

    public abstract static class GenomesNCBITaxonomyVertex<
            V extends GenomesNCBITaxonomyVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>.GenomesNCBITaxonomyVertexType<V, VT>,
            I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
            >
            implements
            TypedVertex<V, VT, GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        private RV vertex;
        private VT type;

        protected GenomesNCBITaxonomyVertex(RV vertex, VT type) {

            this.vertex = vertex;
            this.type = type;
        }

        @Override
        public GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
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

    abstract class GenomesNCBITaxonomyVertexType<
            V extends GenomesNCBITaxonomyVertex<V, VT, I, RV, RVT, RE, RET>,
            VT extends GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>.GenomesNCBITaxonomyVertexType<V, VT>
            >
            implements
            TypedVertex.Type<V, VT, GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        private RVT raw;

        protected GenomesNCBITaxonomyVertexType(RVT raw) {
            this.raw = raw;
        }

        @Override
        public final RVT raw() {
            return raw;
        }

        @Override
        public final GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
            return GenomesNCBITaxonomyGraph.this;
        }
    }

    public abstract static class GenomesNCBITaxonomyEdge<
            // src
            S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
            ST extends TypedVertex.Type<S, ST, SG, I, RV, RVT, RE, RET>,
            SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
            // edge
            E extends GenomesNCBITaxonomyEdge<S, ST, SG, E, ET, T, TT, TG, I, RV, RVT, RE, RET>,
            ET extends GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>.GenomesNCBITaxonomyEdgeType<S, ST, SG, E, ET, T, TT, TG>,
            // tgt
            T extends TypedVertex<T, TT, TG, I, RV, RVT, RE, RET>,
            TT extends TypedVertex.Type<T, TT, TG, I, RV, RVT, RE, RET>,
            TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
            I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
            >
            implements
            TypedEdge<
                    S, ST, SG,
                    E, ET, GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
                    T, TT, TG
                    > {

        private RE edge;
        private ET type;

        protected GenomesNCBITaxonomyEdge(RE edge, ET type) {

            this.edge = edge;
            this.type = type;
        }

        @Override
        public GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
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

    abstract class GenomesNCBITaxonomyEdgeType<
            // src
            S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
            ST extends TypedVertex.Type<S, ST, SG, I, RV, RVT, RE, RET>,
            SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
            // edge
            E extends GenomesNCBITaxonomyEdge<S, ST, SG, E, ET, T, TT, TG, I, RV, RVT, RE, RET>,
            ET extends GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>.GenomesNCBITaxonomyEdgeType<S, ST, SG, E, ET, T, TT, TG>,
            // tgt
            T extends TypedVertex<T, TT, TG, I, RV, RVT, RE, RET>,
            TT extends TypedVertex.Type<T, TT, TG, I, RV, RVT, RE, RET>,
            TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
            >
            implements
            TypedEdge.Type<
                    S, ST, SG,
                    E, ET, GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
                    T, TT, TG
                    > {

        private RET raw;
        private ST srcT;
        private TT tgtT;

        protected GenomesNCBITaxonomyEdgeType(ST srcT, RET raw, TT tgtT) {

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
        public final GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
            return GenomesNCBITaxonomyGraph.this;
        }
    }

    public abstract class GenomesNCBITaxonomyEdgeProperty<
            // src
            S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
            ST extends TypedVertex.Type<S, ST, SG, I, RV, RVT, RE, RET>,
            SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
            E extends GenomesNCBITaxonomyEdge<S, ST, SG, E, ET, T, TT, TG, I, RV, RVT, RE, RET>,
            ET extends GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>.GenomesNCBITaxonomyEdgeType<S, ST, SG, E, ET, T, TT, TG>,
            // tgt
            T extends TypedVertex<T, TT, TG, I, RV, RVT, RE, RET>,
            TT extends TypedVertex.Type<T, TT, TG, I, RV, RVT, RE, RET>,
            TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
            P extends GenomesNCBITaxonomyEdgeProperty<S, ST, SG, E, ET, T, TT, TG, P, PE>,
            PE
            >
            implements
            Property<E, ET, P, PE, GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

        protected GenomesNCBITaxonomyEdgeProperty(ET type) {

            this.type = type;
        }

        private ET type;

        @Override
        public final ET elementType() {
            return type;
        }
    }
}
