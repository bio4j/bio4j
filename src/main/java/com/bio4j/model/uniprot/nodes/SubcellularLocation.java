package com.bio4j.model.uniprot.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.uniprot.relationships.ProteinSubcellularLocation;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface SubcellularLocation extends
		Node<SubcellularLocation, SubcellularLocation.Type> {

	public static Type TYPE = Type.subcellularLocation;

	public static enum Type implements
			NodeType<SubcellularLocation, SubcellularLocation.Type> {

		subcellularLocation;
		public Type value() {
			return subcellularLocation;
		}
	}
	
	// proteinSubcellularLocation
    // ingoing
    public List<ProteinSubcellularLocation> proteinSubcellularLocation_in(); 
    public List<Protein> proteinSubcellularLocation_inNodes();

}
