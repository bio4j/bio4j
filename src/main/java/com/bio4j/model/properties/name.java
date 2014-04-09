package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Name extends Property {

  public static enum Type implements PropertyType<Type, String> {
    name;
    public Type value() { return name; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.name;

  public String name();
}