package com.bio4j.model.uniprot.relationships.references;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// properties
import com.bio4j.model.properties.DoId;
import com.bio4j.model.properties.MedlineId;
import com.bio4j.model.properties.PubmedId;
import com.bio4j.model.properties.Title;

import com.bio4j.model.uniprot.nodes.references.Patents;
import com.bio4j.model.uniprot.nodes.references.Reference;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Patent extends Relationship <

  Reference, Reference.Type,
  Patent, Patent.Type,
  Patents, Patents.Type

>,
  // properties
  Title<Patent, Patent.Type> // TODO maybe common to all references?

{

  public static Type TYPE = Type.article;

  public static enum Type implements RelationshipType<

    Reference, Reference.Type, 
    Patent, Patent.Type, 
    Patents, Patents.Type
    
  >
  {

    article;

    // there is only one Patents node => many to one.
    public Arity arity() {

      return Arity.manyToOne;
    }

    public Reference.Type sourceType()  { return Reference.TYPE;  }
    public Patents.Type targetType()   { return Patents.TYPE;   }

    public Type value() { return article; }
  }
}
