package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Sequence extends Property {

  public static enum Type implements PropertyType<Type, String> {
    sequence;
    public Type value() { return sequence; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.sequence;

  public String sequence();
}