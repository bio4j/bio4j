package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface CatalyticActivity extends Property {

  public static enum Type implements PropertyType<Type, String> {
    catalyticActivity;
    public Type value() { return catalyticActivity; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.catalyticActivity;

  public String catalyticActivity();
}