package com.bio4j.model.uniprot.relationships;

// WARNING: moved to the enzymedb package

// import com.bio4j.model.Relationship;
// import com.bio4j.model.RelationshipType;

// import com.bio4j.model.enzymedb.nodes.Enzyme;
// import com.bio4j.model.uniprot.nodes.Protein;

// /**
//  *
//  * @author Pablo Pareja Tobes <ppareja@era7.com>
//  * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
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