package com.bio4j.model.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.uniprot.nodes.SubcellularLocation;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface SubcellularLocationParent extends Relationship <
  SubcellularLocation, SubcellularLocation.Type,
  SubcellularLocationParent, SubcellularLocationParent.Type,
  SubcellularLocation, SubcellularLocation.Type
> {
    
  public static enum Type implements RelationshipType <
    SubcellularLocation, SubcellularLocation.Type,
    SubcellularLocationParent, SubcellularLocationParent.Type,
    SubcellularLocation, SubcellularLocation.Type
  > {

    subcellularLocationParent;
    public Type value() { return subcellularLocationParent; }
    public Arity arity() { return Arity.manyToOne; }
    public SubcellularLocation.Type sourceType() { return SubcellularLocation.TYPE; }
    public SubcellularLocation.Type targetType() { return SubcellularLocation.TYPE; }
  }

}