package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Id extends Property {

  public static enum Type implements PropertyType<Type, String> {
    id;
    public Type value() { return id; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.id;

  public String id();
}