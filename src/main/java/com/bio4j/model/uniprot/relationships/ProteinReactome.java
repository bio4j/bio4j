package com.bio4j.model.uniprot.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.RelationshipType.Arity;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.ReactomeTerm;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface ProteinReactome extends Relationship <
	Protein, Protein.Type,
	ProteinReactome, ProteinReactome.Type,
	ReactomeTerm, ReactomeTerm.Type
	> {
	
	public static Type TYPE = Type.proteinReactome;
	public static enum Type implements RelationshipType <
		Protein, Protein.Type,
		ProteinReactome, ProteinReactome.Type,
		ReactomeTerm, ReactomeTerm.Type
		  > {
		
		   proteinReactome;
		   public Type value() { return proteinReactome; }
		   public Arity arity() { return Arity.manyToMany; }
		   public Protein.Type sourceType() { return Protein.TYPE; }
		   public ReactomeTerm.Type targetType() { return ReactomeTerm.TYPE; }
	}
		
	public Protein source();
	public ReactomeTerm target();

}
