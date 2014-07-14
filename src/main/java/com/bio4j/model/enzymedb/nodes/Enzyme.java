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

public final class Enzyme<I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET>
implements
  TypedVertex <
    Enzyme<I,RV,RVT,RE,RET>,
    EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType,
    EnzymeDBGraph<I,RV,RVT,RE,RET>,
    I,RV,RVT,RE,RET
  >  
{

  public Enzyme(RV vertex, EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType type) {

    this.vertex = vertex;
    this.type = type;
  }

  private RV vertex;
  private EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType type;

  public EnzymeDBGraph<I,RV,RVT,RE,RET> graph() { 

    return type().graph(); 
  }
  public RV raw() { 

    return this.vertex; 
  }

  public EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType type() { 

    return type; 
  } 

  public Enzyme<I,RV,RVT,RE,RET> self() { 

    return this; 
  }


  public String id() { 

    return get(type().id);
  }
  // public String cofactors();
  // public String officialName();
  // //public String alternateNames();
  // public String catalyticActivity();
  // public String comment();
  // public String prositeCrossReferences();

  public List<Friend<I,RV,RVT,RE,RET>> friend_out() {

    return outMany( graph().friendT() );
  }

  public List<Enzyme<I,RV,RVT,RE,RET>> friend_outV() {

    return outManyV( graph().friendT() );
  }

  

  
  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // enzymaticActivity
    // incoming
    // public List<? extends EnzymaticActivity> enzymaticActivity_in();
    // public List<? extends Protein> enzymaticActivity_inNodes();


}
