package com.bio4j.model.properties;

import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.PropertyType;

import com.ohnosequences.typedGraphs.Element;
import com.ohnosequences.typedGraphs.ElementType;

public interface FullName <N extends Element<N,NT>, NT extends Enum<NT> & ElementType<N,NT>> 
  extends Property<N, NT> {

  // the property method
  public String fullName();

  // static type method
  public static <
    N extends Element<N,NT> & FullName<N,NT>, 
    NT extends Enum<NT> & ElementType<N,NT>
  > Type<N,NT> TYPE(NT elementType) { return new Type<N,NT>(elementType); }

  // convenience type
  public class Type <N extends Element<N,NT> & FullName<N,NT>, NT extends Enum<NT> & ElementType<N,NT>> 
    extends PropertyType<N, NT, FullName<N,NT>, Type<N,NT>, String> {

    public Type(NT elementType) {
      super(elementType, "fullName");
    }

    public Class<String> valueClass() { return String.class; }
  }
}