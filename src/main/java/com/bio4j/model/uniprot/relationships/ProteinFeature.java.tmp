package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.FeatureType;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/17/2014.
 */
public interface ProteinFeature <
		S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
		R extends ProteinFeature<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinFeatureType<S, ST, R, RT, T, TT>,
		T extends FeatureType<T, TT>, TT extends UniprotGraph.FeatureTypeType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {

	public String id();
	public String description();
	public String evidence();
	public String status();
	public Integer begin();
	public Integer end();
	public String original();
	public String variation();
	public String ref();

	public static interface id<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends ProteinFeature<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinFeatureType<S, ST, R, RT, T, TT>,
			T extends FeatureType<T, TT>, TT extends UniprotGraph.FeatureTypeType<T, TT>,
			P extends id<S, ST, R, RT, T, TT,  P>
			>
			extends Property<R, RT, P, String> {
		@Override
		public default String name() {
			return "id";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}
	public static interface description<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends ProteinFeature<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinFeatureType<S, ST, R, RT, T, TT>,
			T extends FeatureType<T, TT>, TT extends UniprotGraph.FeatureTypeType<T, TT>,
			P extends description<S, ST, R, RT, T, TT,  P>
			>
			extends Property<R, RT, P, String> {
		@Override
		public default String name() {
			return "description";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}
	public static interface evidence<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends ProteinFeature<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinFeatureType<S, ST, R, RT, T, TT>,
			T extends FeatureType<T, TT>, TT extends UniprotGraph.FeatureTypeType<T, TT>,
			P extends evidence<S, ST, R, RT, T, TT,  P>
			>
			extends Property<R, RT, P, String> {
		@Override
		public default String name() {
			return "evidence";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}
	public static interface status<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends ProteinFeature<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinFeatureType<S, ST, R, RT, T, TT>,
			T extends FeatureType<T, TT>, TT extends UniprotGraph.FeatureTypeType<T, TT>,
			P extends status<S, ST, R, RT, T, TT,  P>
			>
			extends Property<R, RT, P, String> {
		@Override
		public default String name() {
			return "status";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}
	public static interface begin<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends ProteinFeature<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinFeatureType<S, ST, R, RT, T, TT>,
			T extends FeatureType<T, TT>, TT extends UniprotGraph.FeatureTypeType<T, TT>,
			P extends begin<S, ST, R, RT, T, TT,  P>
			>
			extends Property<R, RT, P, Integer> {
		@Override
		public default String name() {
			return "begin";
		}
		@Override
		public default Class<Integer> valueClass() {
			return Integer.class;
		}
	}
	public static interface end<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends ProteinFeature<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinFeatureType<S, ST, R, RT, T, TT>,
			T extends FeatureType<T, TT>, TT extends UniprotGraph.FeatureTypeType<T, TT>,
			P extends end<S, ST, R, RT, T, TT,  P>
			>
			extends Property<R, RT, P, Integer> {
		@Override
		public default String name() {
			return "end";
		}
		@Override
		public default Class<Integer> valueClass() {
			return Integer.class;
		}
	}
	public static interface original<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends ProteinFeature<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinFeatureType<S, ST, R, RT, T, TT>,
			T extends FeatureType<T, TT>, TT extends UniprotGraph.FeatureTypeType<T, TT>,
			P extends original<S, ST, R, RT, T, TT,  P>
			>
			extends Property<R, RT, P, String> {
		@Override
		public default String name() {
			return "original";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}
	public static interface variation<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends ProteinFeature<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinFeatureType<S, ST, R, RT, T, TT>,
			T extends FeatureType<T, TT>, TT extends UniprotGraph.FeatureTypeType<T, TT>,
			P extends variation<S, ST, R, RT, T, TT,  P>
			>
			extends Property<R, RT, P, String> {
		@Override
		public default String name() {
			return "variation";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}
	public static interface ref<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends ProteinFeature<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinFeatureType<S, ST, R, RT, T, TT>,
			T extends FeatureType<T, TT>, TT extends UniprotGraph.FeatureTypeType<T, TT>,
			P extends ref<S, ST, R, RT, T, TT,  P>
			>
			extends Property<R, RT, P, String> {
		@Override
		public default String name() {
			return "ref";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

}
