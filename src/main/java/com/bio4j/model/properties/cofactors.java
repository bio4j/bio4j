package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Cofactors extends Property {

  public static enum Type implements PropertyType<Type, String[]> {
    cofactors;
    public Type value() { return cofactors; }
    public Class<String[]> valueClass() { return String[].class; }
  }

  public static Type TYPE = Type.cofactors;

  public String[] cofactors();
}