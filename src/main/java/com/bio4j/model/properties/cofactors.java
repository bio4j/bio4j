package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

public interface cofactors <

  N extends Node<N,NT> & cofactors<N,NT>, 
  NT extends Enum<NT> & NodeType<N,NT>

> extends Property<N, NT> {

  // the property method
  public String[] cofactors();

  // static type method
  public static <
    N extends Node<N,NT> & cofactors<N,NT>, 
    NT extends Enum<NT> & NodeType<N,NT>
  > Type<N,NT> TYPE(NT nodeType) { 

    return new Type<N,NT>(nodeType); 
  }

  // convenience type
  public class Type <

    N extends Node<N,NT> & cofactors<N,NT>, NT extends Enum<NT> & NodeType<N,NT>
    // T extends PropertyType<N, NT, cofactors<N,NT>, T, String[]>
  
  > extends PropertyType<N, NT, cofactors<N,NT>, Type<N,NT>, String[]> {


    Type(NT nodeType) {
      super(nodeType, "cofactors");
    }

    public Class<String[]> valueClass() { return String[].class; }
  }

}

// so, once you have a node type with the corresponding property you can call cofactors.TYPE(Enzyme.TYPE) and get the right type object.
// this way you can reuse the same property for different nodes while maintaining a scoped property key.