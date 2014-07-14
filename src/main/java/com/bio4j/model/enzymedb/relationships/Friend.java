package com.bio4j.model.enzymedb.edges;

// import com.bio4j.model.enzymedb.EnzymeDBGraph.EnzymeType;
// import com.bio4j.model.enzymedb.relationships.EnzymaticActivity;
// import com.bio4j.model.go.relationships.*;
// import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.*;
import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.enzymedb.EnzymeDBGraph.*;
import com.bio4j.model.enzymedb.vertices.Enzyme;
import java.util.List;

public final class Friend<I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET>
implements
  TypedEdge <
    // src
    Enzyme<I,RV,RVT,RE,RET>,
    EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType,
    EnzymeDBGraph<I,RV,RVT,RE,RET>,

    // edge
    Friend<I,RV,RVT,RE,RET>,
    EnzymeDBGraph<I,RV,RVT,RE,RET>.FriendType,
    EnzymeDBGraph<I,RV,RVT,RE,RET>,
    I,RV,RVT,RE,RET,

    // tgt
    Enzyme<I,RV,RVT,RE,RET>,
    EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType,
    EnzymeDBGraph<I,RV,RVT,RE,RET>
  >  
{

  public Friend(RE edge, EnzymeDBGraph<I,RV,RVT,RE,RET>.FriendType type) {

    this.edge = edge;
    this.type = type;
  }

  private RE edge;
  private EnzymeDBGraph<I,RV,RVT,RE,RET>.FriendType type;

  public EnzymeDBGraph<I,RV,RVT,RE,RET> graph() { 

    return type().graph(); 
  }
  public RE raw() { 

    return this.edge; 
  }

  public EnzymeDBGraph<I,RV,RVT,RE,RET>.FriendType type() { 

    return type; 
  } 

  public Friend<I,RV,RVT,RE,RET> self() { 

    return this; 
  }
}







// public interface Friend <
//   // src and tgt
//   S extends Enzyme<S,ST,G,I,RV,RVT,RE,RET>, 
//   ST extends EnzymeType<S,ST,G,I,RV,RVT,RE,RET>, 
//   // rel
//   E extends Friend<S,ST,E,ET,G,I,RV,RVT,RE,RET>,
//   ET extends FriendType<S,ST,E,ET,G,I,RV,RVT,RE,RET>, 
//   G extends EnzymeDBGraph<G,I,RV,RVT,RE,RET>, 
//   I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
// >
// extends
//   TypedEdge<S,ST,G, E,ET, G,I,RV,RVT,RE,RET, S,ST,G>
// {
  

// }
