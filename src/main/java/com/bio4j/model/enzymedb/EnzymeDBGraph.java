package com.bio4j.model.enzymedb;

import com.ohnosequences.typedGraphs.*;
import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.enzymedb.edges.Friend;

public interface EnzymeDBGraph <
  G extends EnzymeDBGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
> 
extends 
  TypedGraph<G,I,RV,RVT,RE,RET>
{

  // Enzyme /////////////////////////////////////////////////////////////////////////////////////////////////////
  <
    V extends Enzyme<V,VT,G,I,RV,RVT,RE,RET>,
    VT extends EnzymeType<V,VT,G,I,RV,RVT,RE,RET>
  > 
  VT enzymeT();

  public interface EnzymeType <
    V extends Enzyme<V,VT,G,I,RV,RVT,RE,RET>,
    VT extends EnzymeType<V,VT,G,I,RV,RVT,RE,RET>,
    G extends EnzymeDBGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  >
  extends 
    TypedVertex.Type<V,VT, G, I,RV,RVT,RE,RET> 
  {

    <P extends id<V,VT, P, G,I,RV,RVT,RE,RET>> P id();

    // properties
    public interface id <
      V extends Enzyme<V,VT,G,I,RV,RVT,RE,RET>,
      VT extends EnzymeType<V,VT,G,I,RV,RVT,RE,RET>,
      P extends id<V,VT, P, G,I,RV,RVT,RE,RET>,
      G extends EnzymeDBGraph<G,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
    >
    extends 
      Property<V,VT, P,String, G,I,RV,RVT,RE,RET> 
    {
      @Override
      default String name() {

        return "id";
      }

      @Override
      default Class<String> valueClass() {
        
        return String.class;
      }
    }
  }


  // Friend /////////////////////////////////////////////////////////////////////////////////////////
  <
    V extends Enzyme<V,VT,G,I,RV,RVT,RE,RET>,
    VT extends EnzymeType<V,VT,G,I,RV,RVT,RE,RET>,
    E extends Friend<V,VT,E,ET,G,I,RV,RVT,RE,RET>,
    ET extends FriendType<V,VT,E,ET,G,I,RV,RVT,RE,RET>
  > 
  ET friendT();

  public interface FriendType <
    // src and tgt
    S extends Enzyme<S,ST,G,I,RV,RVT,RE,RET>, 
    ST extends EnzymeType<S,ST,G,I,RV,RVT,RE,RET>, 
    // rel
    E extends Friend<S,ST,E,ET,G,I,RV,RVT,RE,RET>,
    ET extends FriendType<S,ST,E,ET,G,I,RV,RVT,RE,RET>, 
    G extends EnzymeDBGraph<G,I,RV,RVT,RE,RET>, 
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  >
  extends
    TypedEdge.Type.OneToMany<S,ST,G, E,ET, G,I,RV,RVT,RE,RET, S,ST,G> 
  {}

















































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