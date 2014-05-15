package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.relationships.PatentAuthor;

import java.util.List;

import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.Term.Type;
import com.bio4j.model.properties.Date;
import com.bio4j.model.properties.Number;
import com.bio4j.model.properties.Title;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Patent extends Node<Patent, Patent.Type>,
  
  // properties
	Number<Patent, Patent.Type>,
	Title<Patent, Patent.Type>,
	Date<Patent, Patent.Type>
{
    
	public static Type TYPE = Type.patent; 
	public default Type type() { return TYPE; }
	public static enum Type implements NodeType<Patent, Patent.Type> {
	
	    patent;
	    public Type value() { return patent; }
	}
		
       
    public List<Protein> getProteinCitations();
    
    // patentAuthor
    // outgoing
    public List<PatentAuthor> patentAuthor_out(); 
    public List<Person> patentAuthor_outNodes();
    
    // patentProteinCitation
    // outgoing
    public List<PatentAuthor> patentProteinCitation_out(); 
    public List<Person> patentProteinCitation_outNodes();
 
    
}
