package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Country;
import com.bio4j.model.uniprot.nodes.Institute;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface InstituteCountry extends Relationship <
  Institute, Institute.Type,
  InstituteCountry, InstituteCountry.Type,
  Country, Country.Type
> {

  public static Type TYPE = Type.instituteCountry;
  public static enum Type implements RelationshipType <
    Institute, Institute.Type,
    InstituteCountry, InstituteCountry.Type,
    Country, Country.Type
  > {

    instituteCountry;
    public Type value() { return instituteCountry; }
    public Arity arity() { return Arity.manyToOne; }
    public Institute.Type sourceType() { return Institute.TYPE; }
    public Country.Type targetType() { return Country.TYPE; }
  }

  // replace this by source/target??
  public Institute getInstitute();    
  public Country getCountry();
}
