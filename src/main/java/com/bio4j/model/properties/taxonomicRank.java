package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface taxonomicRank extends Property {

  public static enum type implements PropertyType<type, String> {
    taxonomicRank;
    public type value() { return taxonomicRank; }
    public Class<String> valueClass() { return String.class; }
  }

  public static type TYPE = type.taxonomicRank;

  public String taxonomicRank();
}