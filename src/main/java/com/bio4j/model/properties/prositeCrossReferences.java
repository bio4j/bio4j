package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface prositeCrossReferences extends Property {

  public static enum type implements PropertyType<type, String[]> {
    prositeCrossReferences;
    public type value() { return prositeCrossReferences; }
    public Class<String[]> valueClass() { return String[].class; }
  }

  public static type TYPE = type.prositeCrossReferences;

  public String[] prositeCrossReferences();
}