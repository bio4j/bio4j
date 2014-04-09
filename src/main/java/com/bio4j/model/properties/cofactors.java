package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Cofactors extends Property {

  public static enum type implements PropertyType<type, String[]> {
    cofactors;
    public type value() { return cofactors; }
    public Class<String[]> valueClass() { return String[].class; }
  }

  public static type TYPE = type.cofactors;

  public String[] cofactors();
}