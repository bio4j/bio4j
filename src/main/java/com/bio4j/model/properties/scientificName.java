package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface ScientificName extends Property {

  public static enum Type implements PropertyType<Type, String> {
    scientificName;
    public Type value() { return scientificName; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.scientificName;

  public String scientificName();
}