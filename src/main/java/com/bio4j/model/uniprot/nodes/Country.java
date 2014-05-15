package com.bio4j.model.uniprot.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.Term.Type;
import com.bio4j.model.properties.Name;
import com.bio4j.model.uniprot.relationships.InstituteCountry;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Country extends Node<Country, Country.Type>,
	
	// properties
	Name<Country, Country.Type>
{
  
	  public static Type TYPE = Type.country;   
	  public default Type type() { return TYPE; }
	  public static enum Type implements NodeType<Country, Country.Type> {
	
	    country;
	    public Type value() { return country; }
	  }
     
	// instituteCountry
	// ingoing
	public List<InstituteCountry> instituteCountry_in(); 
	public List<Institute> instituteCountry_inNodes();
}
