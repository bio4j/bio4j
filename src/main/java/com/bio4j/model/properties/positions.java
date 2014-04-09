package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Positions extends Property {

  public static enum Type implements PropertyType<Type, String> {
    positions;
    public Type value() { return positions; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.positions;

  public String positions();
}