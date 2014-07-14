//package com.bio4j.model.refseq.nodes;
//
//import com.ohnosequences.typedGraphs.Node;
//import com.ohnosequences.typedGraphs.Property;
//
///**
// *
// * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
// * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
// *         Pareja-Tobes</a>
// */
//public interface GenomeElement<N extends GenomeElement<N, NT>, NT extends GenomeElement.Type<N, NT>>
//		extends Node<N, NT> {
//
//	// genome element type
//	public static interface Type<N extends GenomeElement<N, NT>, NT extends GenomeElement.Type<N, NT>>
//			extends Node.Type<N, NT> {
//	}
//
//	// properties
//
//	public static interface version <
//	    N extends GenomeElement<N,NT>,
//	    NT extends GenomeElement.Type<N,NT>,
//	    P extends version<N,NT,P>
//	 >
//	    extends Property<N,NT,P,String>
//	 {
//	   @Override public default String name() { return "version"; }
//	   @Override public default Class<String> valueClass() { return String.class; }
//	 }
//
//	public static interface comment <
//	    N extends GenomeElement<N,NT>,
//	    NT extends GenomeElement.Type<N,NT>,
//	    P extends comment<N,NT,P>
//	 >
//	    extends Property<N,NT,P,String>
//	 {
//	   @Override public default String name() { return "comment"; }
//	   @Override public default Class<String> valueClass() { return String.class; }
//	 }
//
//	public static interface definition <
//	    N extends GenomeElement<N,NT>,
//	    NT extends GenomeElement.Type<N,NT>,
//	    P extends definition<N,NT,P>
//	 >
//	    extends Property<N,NT,P,String>
//	 {
//	   @Override public default String name() { return "definition"; }
//	   @Override public default Class<String> valueClass() { return String.class; }
//	 }
//
//}
