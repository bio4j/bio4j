package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.RelationshipType.Arity;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.Thesis;
import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ThesisProteinCitation extends Relationship <
	Thesis, Thesis.Type,
	ThesisProteinCitation, ThesisProteinCitation.Type,
	Protein, Protein.Type
	> {
	
	public static Type TYPE = Type.thesisProteinCitation;
		enum Type implements RelationshipType <
			Thesis, Thesis.Type,
			ThesisProteinCitation, ThesisProteinCitation.Type,
			Protein, Protein.Type
			> {
			thesisProteinCitation;
			  public Type value() { return thesisProteinCitation; }
			  public Arity arity() { return Arity.manyToMany; }
			  public Thesis.Type sourceType() { return Thesis.TYPE; }
			  public Protein.Type targetType() { return Protein.TYPE; }
		
		}
		
		public Thesis source();
		public Protein target();

}
