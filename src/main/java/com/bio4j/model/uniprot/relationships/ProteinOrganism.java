package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.uniprot.nodes.Organism;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
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