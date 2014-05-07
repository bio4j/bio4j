package com.bio4j.model.uniprot.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.bio4j.model.uniprot.nodes.Organism;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface ProteinOrganism extends Relationship <
	Protein, Protein.Type,
	ProteinOrganism, ProteinOrganism.Type,
	Organism, Organism.Type
	> {
	
	public static Type TYPE = Type.proteinOrganism;
	public static enum Type implements RelationshipType <
		Protein, Protein.Type,
		ProteinOrganism, ProteinOrganism.Type,
		Organism, Organism.Type
	  > {
	
		   proteinOrganism;
		   public Type value() { return proteinOrganism; }
		   public Arity arity() { return Arity.manyToMany; }
		   public Protein.Type sourceType() { return Protein.TYPE; }
		   public Organism.Type targetType() { return Organism.TYPE; }
	}
	
	public Protein source();
	public Organism target();

}
