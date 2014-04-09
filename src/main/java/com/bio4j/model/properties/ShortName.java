package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface ShortName extends Property {

	  public static enum Type implements PropertyType<Type, String> {
	    shortName;
	    public Type value() { return shortName; }
	    public Class<String> valueClass() { return String.class; }
	  }

	  public static Type TYPE = Type.shortName;

	  public String name();
}