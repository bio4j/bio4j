package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface EmblCode extends Property {

  public static enum Type implements PropertyType<Type, String> {
    emblCode;
    public Type value() { return emblCode; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.emblCode;

  public String emblCode();
}