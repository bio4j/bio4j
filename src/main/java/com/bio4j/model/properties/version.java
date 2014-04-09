package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Version extends Property {

  public static enum Type implements PropertyType<Type, String> {
    version;
    public Type value() { return version; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.version;

  public String version();
}