package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Accession extends Property {

	  public static enum Type implements PropertyType<Type, String> {
	    accession;
	    public Type value() { return accession; }
	    public Class<String> valueClass() { return String.class; }
	  }

	  public static Type TYPE = Type.accession;

	  public String name();
}