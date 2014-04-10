package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.uniprot.nodes.FeatureType;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 * Base Relationship class for features. The source is bounded by this to be a Protein; same for the target: FeatureType.
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */

public interface BasicFeature <

  R extends BasicFeature<R,RT>, 
  RT extends Enum<RT> & BasicFeatureType<R,RT>

> extends Relationship<Protein,Protein.Type, R,RT, FeatureType,FeatureType.Type> {
    
    //------------GETTERS----------------
    public String getDescription();
    public String getStatus();
    public String getEvidence();
    public String getId();
    public String getOriginal();
    public String getVariation();
    public String getRef();
    public String getBegin();
    public String getEnd();
            
    //------------SETTERS-------------------
    public void setDescription(String value);
    public void setId(String value);
    public void setEvidence(String value);
    public void setStatus(String value);
    public void setRef(String value);
    public void setBegin(String value);
    public void setEnd(String value);
    public void setOriginal(String value);
    public void setVariation(String value);

    public Protein source();
    public FeatureType target();
    
}
