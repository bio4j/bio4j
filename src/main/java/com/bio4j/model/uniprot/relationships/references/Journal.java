package com.bio4j.model.uniprot.relationships.references;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

// properties
import com.bio4j.model.properties.DoId;
import com.bio4j.model.properties.MedlineId;
import com.bio4j.model.properties.PubmedId;
import com.bio4j.model.properties.Title;

import com.bio4j.model.uniprot.nodes.references.Journals;
import com.bio4j.model.uniprot.nodes.references.Publication;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Journal extends Relationship <

  Publication, Publication.Type,
  Journal, Journal.Type,
  Journals, Journals.Type

>,
  // properties
  Title<Journal, Journal.Type>, // TODO maybe common to all references?
  PubmedId<Journal, Journal.Type>,
  MedlineId<Journal, Journal.Type>, 
  DoId<Journal, Journal.Type> 

{

  public static Type TYPE = Type.journal;

  public static enum Type implements RelationshipType<

    Publication, Publication.Type, 
    Journal, Journal.Type, 
    Journals, Journals.Type
    
  >
  {

    journal;

    // there is only one Journals node => many to one.
    public Arity arity() {

      return Arity.manyToOne;
    }

    public Publication.Type sourceType()  { return Publication.TYPE;  }
    public Journals.Type targetType()   { return Journals.TYPE;   }

    public Type value() { return journal; }
  }
}
