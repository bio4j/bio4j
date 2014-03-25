package com.bio4j.model.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.nodes.SubcellularLocation;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface SubcellularLocationParent extends Relationship <
  SubcellularLocation, SubcellularLocation.type,
  SubcellularLocationParent, SubcellularLocationParent.type,
  SubcellularLocation, SubcellularLocation.type
> {
    
  enum type implements RelationshipType <
    SubcellularLocation, SubcellularLocation.type,
    SubcellularLocationParent, SubcellularLocationParent.type,
    SubcellularLocation, SubcellularLocation.type
  > {

    subcellularLocationParent;
    public type value() { return subcellularLocationParent; }
  }

}