package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface version extends Property {

  public static enum type implements PropertyType<type, String> {
    version;
    public type value() { return version; }
    public Class<String> getValueClass() { return String.class; }
  }

  public static type TYPE = type.version;

  public String version();
}