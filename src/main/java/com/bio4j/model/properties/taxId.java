package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface TaxId extends Property {

  public static enum type implements PropertyType<type, String> {
    taxId;
    public type value() { return taxId; }
    public Class<String> valueClass() { return String.class; }
  }

  public static type TYPE = type.taxId;

  public String taxId();
}