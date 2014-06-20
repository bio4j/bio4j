package com.bio4j.model.go.nodes;

import java.util.List;

// base types
import com.bio4j.model.go.relationships.SubOntology;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import com.bio4j.model.go.GoGraph.SubOntologiesType;

/**
 * Rels into this singleton node represent subontologies.
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface SubOntologies<
        N extends SubOntologies<N, NT>,
        NT extends SubOntologiesType<N, NT>
        >
        extends Node<N, NT> {

	public String name();

    public static interface name <
            N extends SubOntologies<N,NT>,
            NT extends SubOntologiesType<N,NT>,
            P extends name<N,NT,P>
            >
            extends Property<N,NT,P,String>
    {
        @Override public default String name() { return "name"; }
        @Override public default Class<String> valueClass() { return String.class; }
    }

    // SubOntology
    // incoming
    public List<? extends SubOntology> subOntology_in();

    public List<? extends Term> term_inNodes();

}
