package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.bio4j.model.uniprot.nodes.Institute;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.relationships.ThesisAuthor;
import com.bio4j.model.uniprot.relationships.ThesisInstitute;
import com.bio4j.model.uniprot.relationships.ThesisProteinCitation;

import java.util.List;

import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.GoTerm.Type;
import com.bio4j.model.properties.Title;
import com.bio4j.model.properties.Date;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Thesis extends Node<Thesis, Thesis.Type>,

	// properties
	Title<Thesis, Thesis.Type>, Date<Thesis, Thesis.Type> {

	public static Type TYPE = Type.thesis;
	public default Type type() { return TYPE; }

	public static enum Type implements NodeType<Thesis, Thesis.Type> {

		thesis;
		public Type value() {
			return thesis;
		}
	}

	// thesisInstitute
	// outgoing
	public ThesisInstitute thesisInstitute_out();
	public Institute thesisInstitute_outNodes();
	
	// thesisAuthor
	// outgoing
	public ThesisAuthor thesisAuthor_out();
	public Person thesisAuthor_outNodes();
	
	// thesisProteinCitation
	// outgoing
	public List<ThesisProteinCitation> thesisProteinCitation_out();
	public List<Protein> thesisProteinCitation_outNodes();

}
