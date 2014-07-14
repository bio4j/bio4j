package com.bio4j.model.enzymedb;

import com.ohnosequences.typedGraphs.*;
import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.enzymedb.edges.Friend;

public abstract class EnzymeDBGraph <I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET> 
implements 
  TypedGraph<EnzymeDBGraph<I,RV,RVT,RE,RET>,I,RV,RVT,RE,RET>
{

  EnzymeDBGraph(I raw) {

    this.raw = raw;
  }

  protected I raw;

  public I raw() {

    return raw;
  }

  // Enzyme /////////////////////////////////////////////////////////////////////////////////////////////////////
  public abstract EnzymeType enzymeT();

  public final class EnzymeType
  implements 
    TypedVertex.Type <
      Enzyme<I,RV,RVT,RE,RET>,
      EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType,
      EnzymeDBGraph<I,RV,RVT,RE,RET>,
      I,RV,RVT,RE,RET
    > 
  {

    EnzymeType(RVT raw) {

      this.raw = raw;
    }

    RVT raw;

    public RVT raw() { 

      return raw;
    }

    public EnzymeType value() { 

      return graph().enzymeT();
    }

    public EnzymeDBGraph<I,RV,RVT,RE,RET> graph() { 

      return EnzymeDBGraph.this; 
    }

    public Enzyme<I,RV,RVT,RE,RET> from(RV vertex) {

      return new Enzyme<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final id id = new id();

    // properties
    public final class id
    implements 
      Property <
        Enzyme<I,RV,RVT,RE,RET>, 
        EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType, 
        id, String, 
        EnzymeDBGraph<I,RV,RVT,RE,RET>,
        I,RV,RVT,RE,RET
      >
    {

      public id() {}

      @Override
      public String name() {

        return "id";
      }

      public EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType elementType() {

        return EnzymeType.this;
      }

      @Override
      public Class<String> valueClass() {
        
        return String.class;
      }
    }
  }


  // Friend /////////////////////////////////////////////////////////////////////////////////////////
  public abstract FriendType friendT();

  public final class FriendType
  implements
    TypedEdge.Type.OneToMany <
      Enzyme<I,RV,RVT,RE,RET>,
      EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType,
      EnzymeDBGraph<I,RV,RVT,RE,RET>,

      Friend<I,RV,RVT,RE,RET>,
      EnzymeDBGraph<I,RV,RVT,RE,RET>.FriendType,
      EnzymeDBGraph<I,RV,RVT,RE,RET>,
      I,RV,RVT,RE,RET,

      Enzyme<I,RV,RVT,RE,RET>,
      EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType,
      EnzymeDBGraph<I,RV,RVT,RE,RET>      
    > 
  {

    FriendType(RET raw) {

      this.raw = raw;
    }

    RET raw;

    public RET raw() { 

      return raw;
    }

    public FriendType value() { 

      return graph().friendT();
    }

    public EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType targetType() {

      return graph().enzymeT();
    }

    public EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType sourceType() {

      return graph().enzymeT();
    }

    public EnzymeDBGraph<I,RV,RVT,RE,RET> graph() { 

      return EnzymeDBGraph.this; 
    }

    public Friend<I,RV,RVT,RE,RET> from(RE edge) {

      return new Friend<I,RV,RVT,RE,RET>(edge, this);
    }

  }

















































    // public static interface cofactors<
    //     N extends Enzyme<N, NT>,
    //     NT extends EnzymeType<N, NT>,
    //     P extends cofactors<N, NT, P>
    //     >
    //     extends Property<N, NT, P, String> {
    //   @Override
    //   public default String name() {
    //     return "cofactors";
    //   }

    //   @Override
    //   public default Class<String> valueClass() {
    //     return String.class;
    //   }
    // }

    // public static interface officialName<
    //     N extends Enzyme<N, NT>,
    //     NT extends EnzymeType<N, NT>,
    //     P extends officialName<N, NT, P>
    //     >
    //     extends Property<N, NT, P, String> {
    //   @Override
    //   public default String name() {
    //     return "officialName";
    //   }

    //   @Override
    //   public default Class<String> valueClass() {
    //     return String.class;
    //   }
    // }

  // public static interface EnzymaticActivityType<
  //     S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
  //     R extends EnzymaticActivity<S, ST, R, RT, T, TT>, RT extends EnzymaticActivityType<S, ST, R, RT, T, TT>,
  //     T extends Enzyme<T, TT>, TT extends EnzymeType<T, TT>
  //     >
  //     extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
  // }
}