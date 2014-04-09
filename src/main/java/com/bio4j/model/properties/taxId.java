package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface TaxId extends Property {

  public static enum Type implements PropertyType<Type, String> {
    taxId;
    public Type value() { return taxId; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.taxId;

  public String taxId();
}