package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.RelationshipType.Arity;
import com.bio4j.model.properties.Evidence;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.SubcellularLocation;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinSubcellularLocation extends Relationship <
	Protein, Protein.Type,
	ProteinSubcellularLocation, ProteinSubcellularLocation.Type,
	SubcellularLocation, SubcellularLocation.Type
	>,
	Evidence<ProteinSubcellularLocation, ProteinSubcellularLocation.Type>
	{
	
	public static Type TYPE = Type.proteinSubcellularLocation;
	public static enum Type implements RelationshipType <
		Protein, Protein.Type,
		ProteinSubcellularLocation, ProteinSubcellularLocation.Type,
		SubcellularLocation, SubcellularLocation.Type
		  > {
		
			proteinSubcellularLocation;
		   public Type value() { return proteinSubcellularLocation; }
		   public Arity arity() { return Arity.manyToMany; }
		   public Protein.Type sourceType() { return Protein.TYPE; }
		   public SubcellularLocation.Type targetType() { return SubcellularLocation.TYPE; }
	}
		
	public Protein source();
	public SubcellularLocation target();

  public String getStatus();
  public String getTopology();
  public String getTopologyStatus();

  public void setStatus(String value);
  public void setTopology(String value);
  public void setTopologyStatus(String value);
}

