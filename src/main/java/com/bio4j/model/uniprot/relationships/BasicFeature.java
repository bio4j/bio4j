package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.uniprot.nodes.FeatureType;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface BasicFeature extends Relationship <
Protein, Protein.Type,
BasicFeature, BasicFeature.Type,
FeatureType, FeatureType.Type
>{
    
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
    
    public static Type TYPE = Type.basicFeature;
    public static enum Type implements RelationshipType <
      Protein, Protein.Type,
      BasicFeature, BasicFeature.Type,
      FeatureType, FeatureType.Type
    > {
      basicFeature;
      public Type value() { return basicFeature; }
      public Arity arity() { return Arity.manyToMany; }
      public Protein.Type sourceType() { return Protein.TYPE; }
      public FeatureType.Type targetType() { return FeatureType.TYPE; }
    }

    public Protein source();
    public FeatureType target();
    
}
