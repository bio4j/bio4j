package com.bio4j.model.uniprot.relationships;

// WARNING: moved to the enzymedb package

// import com.bio4j.model.Relationship;
// import com.bio4j.model.RelationshipType;

// import com.bio4j.model.enzymedb.nodes.Enzyme;
// import com.bio4j.model.uniprot.nodes.Protein;

// /**
//  *
//  * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
//  * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
//  */
// public interface EnzymaticActivity extends Relationship <
//   Protein, Protein.Type,
//   EnzymaticActivity, EnzymaticActivity.Type,
//   Enzyme, Enzyme.Type
// > {

//   public static Type TYPE = Type.enzymaticActivity;
//   public static enum Type implements RelationshipType <
//     Protein, Protein.Type,
//     EnzymaticActivity, EnzymaticActivity.Type,
//     Enzyme, Enzyme.Type
//   > {
//     enzymaticActivity;
//     public Type value() { return enzymaticActivity; }
//     public Arity arity() { return Arity.manyToMany; }
//   }

//   public Protein source();
//   public Enzyme target();
// }
