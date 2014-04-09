package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Definition extends Property {

  public static enum Type implements PropertyType<Type, String> {
    definition;
    public Type value() { return definition; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.definition;

  public String definition();
}