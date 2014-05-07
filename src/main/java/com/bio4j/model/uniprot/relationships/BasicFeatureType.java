package com.bio4j.model.uniprot.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.bio4j.model.uniprot.nodes.FeatureType;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 * Base RelationshipType class for features.
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */

public interface BasicFeatureType <

  R extends BasicFeature<R,RT>, 
  RT extends Enum<RT> & BasicFeatureType<R,RT>

> extends RelationshipType<Protein,Protein.Type, R,RT, FeatureType,FeatureType.Type> {}
