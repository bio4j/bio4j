package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface PrositeCrossReferences extends Property {

  public static enum Type implements PropertyType<Type, String[]> {
    prositeCrossReferences;
    public Type value() { return prositeCrossReferences; }
    public Class<String[]> valueClass() { return String[].class; }
  }

  public static Type TYPE = Type.prositeCrossReferences;

  public String[] prositeCrossReferences();
}