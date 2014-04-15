package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.RelationshipType.Arity;
import com.bio4j.model.uniprot.nodes.Institute;
import com.bio4j.model.uniprot.nodes.Thesis;
import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ThesisInstitute extends Relationship <
	Thesis, Thesis.Type,
	ThesisInstitute, ThesisInstitute.Type,
	Institute, Institute.Type
	> {
	
	public static Type TYPE = Type.thesisInstitute;
		enum Type implements RelationshipType <
			Thesis, Thesis.Type,
			ThesisInstitute, ThesisInstitute.Type,
			Institute, Institute.Type
			> {
			thesisInstitute;
			  public Type value() { return thesisInstitute; }
			  public Arity arity() { return Arity.manyToMany; }
			  public Thesis.Type sourceType() { return Thesis.TYPE; }
			  public Institute.Type targetType() { return Institute.TYPE; }
		
		}
		
		public Thesis source();
		public Institute target();

}
