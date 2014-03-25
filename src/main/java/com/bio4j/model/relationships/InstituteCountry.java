package com.bio4j.model.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.nodes.Country;
import com.bio4j.model.nodes.Institute;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface InstituteCountry extends Relationship <
  Institute, Institute.type,
  InstituteCountry, InstituteCountry.type,
  Country, Country.type
> {
  
  enum type implements RelationshipType <
    Institute, Institute.type,
    InstituteCountry, InstituteCountry.type,
    Country, Country.type
  > {

    instituteCountry;
    public type value() { return instituteCountry; }
  }

  // replace this by source/target??
  public Institute getInstitute();    
  public Country getCountry();
}
