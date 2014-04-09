package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface TaxonomicRank extends Property {

  public static enum Type implements PropertyType<Type, String> {
    taxonomicRank;
    public Type value() { return taxonomicRank; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.taxonomicRank;

  public String taxonomicRank();
}