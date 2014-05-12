package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.Patent;
import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
// patentCitesProtein
public interface PatentProteinCitation extends Relationship <
	Patent, Patent.Type,
	PatentProteinCitation, PatentProteinCitation.Type,
	Protein, Protein.Type
	> {
	
	public static Type TYPE = Type.patentProteinCitation;
	public static enum Type implements RelationshipType <
	  Patent, Patent.Type,
	  PatentProteinCitation, PatentProteinCitation.Type,
	  Protein, Protein.Type
	> {
	  patentProteinCitation;
	  public Type value() { return patentProteinCitation; }
	  public Arity arity() { return Arity.manyToMany; }
	  public Patent.Type sourceType() { return Patent.TYPE; }
	  public Protein.Type targetType() { return Protein.TYPE; }
	}
	
	public Patent source();
	public Protein target();

}
