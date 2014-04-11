
package com.bio4j.model.uniprot.relationships.patent;

import com.bio4j.model.RelationshipType.Arity;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.Patent;
import com.bio4j.model.uniprot.relationships.patent.PatentAuthor.Type;
import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface PatentProteinCitation extends Relationship <
	Patent, Patent.Type,
	PatentProteinCitation, PatentProteinCitation.Type,
	Protein, Protein.Type
	> {
	
	public static Type TYPE = Type.patentProteinCitation;
	enum Type implements RelationshipType <
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

}
