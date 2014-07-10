package com.bio4j.model.enzymedb.vertices;

// import com.bio4j.model.enzymedb.EnzymeDBGraph.EnzymeType;
// import com.bio4j.model.enzymedb.relationships.EnzymaticActivity;
// import com.bio4j.model.go.relationships.*;
// import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.*;
import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.enzymedb.EnzymeDBGraph.*;
import com.bio4j.model.enzymedb.EnzymeDBGraph.EnzymeType.*;

import com.bio4j.model.enzymedb.edges.Friend;

import java.util.List;

public interface Enzyme <
  V extends Enzyme<V,VT,G, I,RV,RVT,RE,RET>,
  VT extends EnzymeType<V,VT,G, I,RV,RVT,RE,RET>,
  G extends EnzymeDBGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
>
extends 
  TypedVertex<V,VT, G, I,RV,RVT,RE,RET>  
{

  default String id() { 

    return get(type().id());
  }
  // public String cofactors();
  // public String officialName();
  // //public String alternateNames();
  // public String catalyticActivity();
  // public String comment();
  // public String prositeCrossReferences();

  default <
    // rel
    E extends Friend<V,VT,E,ET,G,I,RV,RVT,RE,RET>,
    ET extends FriendType<V,VT,E,ET,G,I,RV,RVT,RE,RET>
  >
  List<E> friend_out() {

    // I need explicit params here. Why?
    return outMany(graph().<V,VT,E,ET>friendT());
  }

  default <
    // rel
    E extends Friend<V,VT,E,ET,G,I,RV,RVT,RE,RET>,
    ET extends FriendType<V,VT,E,ET,G,I,RV,RVT,RE,RET>
  >
  List<V> friend_outV() {

    // I need explicit params here. Why?
    return outManyV(graph().<V,VT,E,ET>friendT());
  }



  
  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // enzymaticActivity
    // incoming
    // public List<? extends EnzymaticActivity> enzymaticActivity_in();
    // public List<? extends Protein> enzymaticActivity_inNodes();


}
