package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.properties.Name;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Dataset extends Node<Dataset, Dataset.Type>,
  
  // properties
	Name<Dataset,Dataset.Type>
{
	
	public static Type TYPE = Type.dataset;
	
  public static enum Type implements NodeType<Dataset, Dataset.Type> {

    dataset;
    public Type value() { return dataset; }
  }

    
}
