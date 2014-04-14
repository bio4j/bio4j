package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.properties.Name;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Dataset extends Node<Dataset, Dataset.Type>,
	Name<Dataset,Dataset.Type>{
	
	public static Type TYPE = Type.dataset;
	
  public static enum Type implements NodeType<Dataset, Dataset.Type> {

    dataset;
    public Type value() { return dataset; }
  }

    
}
