package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

public interface obsolete <N extends Node<N,NT>, NT extends Enum<NT> & NodeType<N,NT>> 
  extends Property<N, NT> {

  // the property method
  public Boolean obsolete();

  // static type method
  public static <
    N extends Node<N,NT> & obsolete<N,NT>, 
    NT extends Enum<NT> & NodeType<N,NT>
  > Type<N,NT> TYPE(NT nodeType) { return new Type<N,NT>(nodeType); }

  // convenience type
  public class Type <N extends Node<N,NT> & obsolete<N,NT>, NT extends Enum<NT> & NodeType<N,NT>> 
    extends PropertyType<N, NT, obsolete<N,NT>, Type<N,NT>, Boolean> {


    Type(NT nodeType) {
      super(nodeType, "obsolete");
    }

    public Class<Boolean> valueClass() { return Boolean.class; }
  }
}

