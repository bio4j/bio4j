package com.bio4j.model.uniprot.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.properties.Id;
import com.bio4j.model.properties.Name;
import com.bio4j.model.uniprot.relationships.ProteinInterpro;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Interpro extends Node<Interpro, Interpro.Type>,
	
	// properties
	Id<Interpro, Interpro.Type>,
	Name<Interpro, Interpro.Type>
{
    
	public static Type TYPE = Type.interpro;
	public static enum Type implements NodeType<Interpro, Interpro.Type> {
	
	    interpro;
	    public Type value() { return interpro; }
	}
  
	// proteinInterpro
	// ingoing
	public List<ProteinInterpro> proteinInterpro_in(); 
	public List<Protein> proteinInterpro_inNodes();
    
    
}
