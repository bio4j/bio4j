package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface FullName extends Property {

	  public static enum Type implements PropertyType<Type, String> {
	    fullName;
	    public Type value() { return fullName; }
	    public Class<String> valueClass() { return String.class; }
	  }

	  public static Type TYPE = Type.fullName;

	  public String name();
}