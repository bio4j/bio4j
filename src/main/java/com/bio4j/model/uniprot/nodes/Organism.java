package com.bio4j.model.uniprot.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.properties.NCBITaxonomyId;
import com.bio4j.model.properties.ScientificName;
import com.bio4j.model.properties.CommonName;
import com.bio4j.model.properties.SynonymName;
import com.bio4j.model.uniprot.relationships.ProteinOrganism;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Organism extends
		Node<Organism, Organism.Type>,

		// properties
		ScientificName<Organism, Organism.Type>,
		CommonName<Organism, Organism.Type>,
		SynonymName<Organism, Organism.Type>,
		NCBITaxonomyId<Organism, Organism.Type>{

	public static Type TYPE = Type.organism;

	public static enum Type implements NodeType<Organism, Organism.Type> {

		organism;
		public Type value() {
			return organism;
		}
	}

	// proteinOrganism
    // ingoing
    public List<ProteinOrganism> proteinOrganism_in(); 
    public List<Organism> proteinOrganism_inNodes();
	

}
