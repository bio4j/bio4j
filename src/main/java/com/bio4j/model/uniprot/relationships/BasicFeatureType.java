package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.uniprot.nodes.FeatureType;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 * Base RelationshipType class for features.
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */

public interface BasicFeatureType <

  R extends BasicFeature<R,RT>, 
  RT extends Enum<RT> & BasicFeatureType<R,RT>

> extends RelationshipType<Protein,Protein.Type, R,RT, FeatureType,FeatureType.Type> {}
