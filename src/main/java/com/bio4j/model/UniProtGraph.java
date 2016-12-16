package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class UniProtGraph<V,E> extends TypedGraph<UniProtGraph<V,E>,V,E> {

  public UniProtGraph(UntypedGraph<V,E> graph) { super(graph); }

  @Override public final UniProtGraph<V,E> self() { return this; }

  /*
    ## Proteins

    The first thing to point out as clearly as possible is: `Protein`s **do not** correspond to UniProt entries; we create a protein for every isoform present in any UniProt entry. The protein which is usually identified with the entry has true `isCanonical`.
  */
  public final class Protein extends Vertex<Protein> {

    private Protein(V vertex) { super(vertex, protein); }
    @Override public final Protein self() { return this; }
  }

  public final ProteinType protein = new ProteinType();
  public final class ProteinType extends VertexType<Protein> {

    @Override public final Protein fromRaw(V vertex) { return new Protein(vertex); }

    /*
      #### ID

      Primary IDs correspond to the UniProt isoform ID. Note that this is *never* the *entry* accession; for that we have the `accession` property.
    */
    public final ID id = new ID();
    public final class ID extends Property<String> implements FromAtMostOne, ToOne {
      private ID() { super(String.class); }
      public final Index index = new Index();
      public final class Index extends UniqueIndex<ID, String> {
        private Index() { super(ID.this); }
      }
    }

    /*
      #### Accession

      Canonical isoforms (proteins) corresponding to an entry have an accession ID, which is the standard protein ID used almost everywhere. The presence of this property is equivalent to this protein having an UniProt entry. See http://www.uniprot.org/help/accession_numbers
    */
    public final Accession accession = new Accession();
    public final class Accession extends Property<String> implements FromAtMostOne, ToAtMostOne {
      private Accession() { super(String.class); }
      public final Index index = new Index();
      public final class Index extends UniqueIndex<Accession, String> {
        private Index() { super(Accession.this); }
      }
    }

    /*
      #### Full name

      UniProt has a lot of names for each protein, and we try to use here the best one for each. This property has the full recommended name (if present), otherwise the full submitted name, plus possibly the isoform name.
    */
    public final FullName fullName = new FullName();
    public final class FullName extends Property<String> implements FromAny, ToOne {
      private FullName() { super(String.class); }
    }

    /*
      #### Dataset

      Whether this entry comes from Swiss-Prot or TrEMBL.
    */
    public final Dataset dataset = new Dataset();
    public final class Dataset extends Property<Datasets> implements FromAny, ToOne {
      private Dataset() { super(Datasets.class); }
    }

    /*
      #### Existence

      How strong is the evidence for this protein existence. This is only available for proteins which are the canonical protein of some entry.
    */
    public final Existence existence = new Existence();
    public final class Existence extends Property<ExistenceEvidence> implements FromAny {
      private Existence() { super(ExistenceEvidence.class); }
    }

    /*
      #### Sequence

      Normalized to all-caps. There are isoforms without sequence.
    */
    public final Sequence sequence = new Sequence();
    public final class Sequence extends Property<String> implements FromAny {
      private Sequence() { super(String.class); }
    }

    /*
      #### Sequence length

      The length of the sequence stored as a property.
    */
    public final SequenceLength sequenceLength = new SequenceLength();
    public final class SequenceLength extends Property<Integer> implements FromAny {
      private SequenceLength() { super(Integer.class); }
    }

    /*
      #### Sequence Mass

      I honestly don't know what's this.
    */
    public final Mass mass = new Mass();
    public final class Mass extends Property<Integer> implements FromAny, ToOne {
      private Mass() { super(Integer.class); }
    }
  }

  /*
    ## Isoforms

    The role of isoforms in UniProt data is extremely confusing, and the "model" bad designed. To name a few of the issues we face:

    1. All isoforms are declared *at least once* as part of an entry.
    2. The *same* isoform can be the canonical isoform for one entry, and a secondary one in other
    3. The "isoform of" relationship is not transitive; see [P42166](http://www.uniprot.org/uniprot/P42166#sequences) and [P42167](http://www.uniprot.org/uniprot/P42167#sequences) for example.
    4. Annotations inside the entry sometimes refer to isoforms by name, not id. This means that in most cases comments just doesn't make any sense by themselves: See for example [P04150 xml](http://www.uniprot.org/uniprot/P04150.xml). In general, most comments do not have any connection to the isoform they refer to, only as part of the comment text. In other cases, there is a `<molecule>` element, but the isoform to which it refers is just written there like `"Isoform Alpha-B"`, not using its id

    UniProt sparse documentation:

    - [What is the canonical sequence? Are all isoforms described in one entry?](http://www.uniprot.org/help/canonical_and_isoforms)
    - [Alternative products](http://www.uniprot.org/help/alternative_products)

    Given all this, here we are doing the best we can which accommodates existing data: there are protein vertices, connected between themselves through isoform edges. Proteins (which are actually UniProt entries) are connected with their canonical protein product through this edge. It is important to note that most of the data from the entry actually refers to this canonical protein product.

    Again, let me remark that the isoform relation in UniProt is not transitive; if you really want to get all isoforms of a protein you need to compute the transitive closure of this edge.
  */
  public final class Isoforms extends Edge<Protein, Isoforms, Protein> {
    private Isoforms(E edge) { super(edge, isoforms); }
    @Override public final Isoforms self() { return this; }
  }

  public final IsoformsType isoforms = new IsoformsType();
  public final class IsoformsType extends EdgeType<Protein, Isoforms, Protein> implements FromAny, ToAny {
    private IsoformsType() { super(protein, protein); }
    @Override public final Isoforms fromRaw(E edge) { return new Isoforms(edge); }
  }

  /*
    ### Protein gene names

    A name for the (a) gene codifying the protein. Corresponds, as much as possible, to the primary name in UniProt data. Note that

    1. not all proteins have an associated gene name
    2. gene names are far from unique. For example, there are 12 entries (thus at least 12 isoforms) with primary gene name `APP`
    3. *Different* genes can be connected with the same protein

    See [Uniprot help - gene name](http://www.uniprot.org/help/gene_name).

    We take the *first* primary name, or the the *first* ORF name if there are no primary names.

    The `GeneProducts` edge will give you all the proteins with an entry annotated with that gene name. This edge also contains gene location information, as a property. This is not a property of gene names due to the issues explained above.
  */
  public final class GeneName extends Vertex<GeneName> {

    private GeneName(V vertex) { super(vertex, geneName); }
    @Override public final GeneName self() { return this; }
  }

  public final GeneNameType geneName = new GeneNameType();
  public final class GeneNameType extends VertexType<GeneName> {

    @Override public final GeneName fromRaw(V vertex) { return new GeneName(vertex); }

    /*
      #### Name

      These vertices correspond to the values of their name property. It is indexed.
    */
    public final Name name = new Name();
    public final class Name extends Property<String> implements FromAtMostOne, ToOne {
      private Name() { super(String.class); }
      public final Index index = new Index();
      public final class Index extends UniqueIndex<Name, String> {
        private Index() { super(Name.this); }
      }
    }
  }

  public final class GeneProducts extends Edge<GeneName, GeneProducts, Protein> {

    private GeneProducts(E edge) { super(edge, geneProducts); }
    @Override public final GeneProducts self() { return this; }
  }

  public final GeneProductsType geneProducts = new GeneProductsType();
  public final class GeneProductsType extends EdgeType<GeneName, GeneProducts, Protein> implements FromAny, ToAny {

    private GeneProductsType() { super(geneName, protein); }
    @Override public final GeneProducts fromRaw(E edge) { return new GeneProducts(edge); }

    public final Location location = new Location();
    public final class Location extends Property<GeneLocations> implements FromAny, ToOne {
      private Location() { super(GeneLocations.class); }
    }
  }
  /*
    See ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/docs/userman.htm#OG_line
  */
  public static enum GeneLocations {

    chromosome, // TODO this the default case, which is missing in UniProt. Please fix/improve this name.
    apicoplast,
    chloroplast,
    organellar_chromatophore,
    cyanelle,
    hydrogenosome,
    mitochondrion,
    non_photosynthetic_plastid,
    nucleomorph,
    plasmid,
    plastid;
  }

  public static enum Datasets {

    swissProt,
    trEMBL;
  }

  public static enum ExistenceEvidence {

    proteinLevel,
    transcriptLevel,
    homologyInferred,
    predicted,
    uncertain;
  }

  /*
    ### Sequence Annotations (Features)

    See

    - [UniProt help - Sequence Annotation (Features)](http://www.uniprot.org/help/sequence_annotation)
    - [UniProt user manual - Features](ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/docs/userman.htm#FT_keys)

    There is a vertex for each annotation, with a property which determines its type. The location is encoded in the `annotations` edge going from proteins to annotations.
  */
  public final class Annotation extends Vertex<Annotation> {
    private Annotation(V vertex) { super(vertex, annotation); }
    @Override public final Annotation self() { return this; }
  }

  public final AnnotationType annotation = new AnnotationType();
  public final class AnnotationType extends VertexType<Annotation> {

    @Override public final Annotation fromRaw(V vertex) { return new Annotation(vertex); }

    public final FeatureType featureType = new FeatureType();
    public final class FeatureType extends Property<FeatureTypes> implements FromAtLeastOne, ToOne {
      private FeatureType() { super(FeatureTypes.class); }
    }

    /*
      #### Description

      Descriptions are not always present, but they do contain key information sometimes: the isoform corresponding to a sequence variant, or the allele which has it, for example.
    */
    public final Description description = new Description();
    public final class Description extends Property<String> implements FromAny {
      private Description() { super(String.class); }
    }
  }

  public final class Annotations extends Edge<Protein, Annotations, Annotation> {
    private Annotations(E edge) { super(edge, annotations); }
    @Override public final Annotations self() { return this; }
  }

  public final AnnotationsType annotations = new AnnotationsType();
  public final class AnnotationsType extends EdgeType<Protein, Annotations, Annotation> implements FromAtLeastOne, ToAny {
    private AnnotationsType() { super(protein, annotation); }
    @Override public final Annotations fromRaw(E edge) { return new Annotations(edge); }

    /*
      Note that these properties are here in the edge because of the limitations of current graph databases; they should be part of the annotation vertex.

      ##### UniProt position data and this

      1. Everything here is **0-based**, with `[start, end[` intervals
      2. Those `<`, `>` symbols for positions are translated to `0` and `length`
      3. uncertain endpoints are properties of the annotation vertex, if anything
    */
    public final Begin begin = new Begin();
    public final class Begin extends Property<Integer> implements FromAny, ToOne {
      private Begin() { super(Integer.class); }
    }

    public final End end = new End();
    public final class End extends Property<Integer> implements FromAny, ToOne {
      private End() { super(Integer.class); }
    }
  }

  /*
    ### Sequence annotation types

    They are an enum. As for keywords, they can be upgraded to vertices.
  */
  public static enum FeatureTypes {

    activeSite,
    bindingSite,
    calciumBindingRegion,
    chain,
    coiledCoilRegion,
    compositionallyBiasedRegion,
    crosslink,
    disulfideBond,
    DNABindingRegion,
    domain,
    glycosylationSite,
    helix,
    initiatorMethionine,
    lipidMoietyBindingRegion,
    metalIonBindingSite,
    modifiedResidue,
    mutagenesisSite,
    nonConsecutiveResidues,
    nonTerminalResidue,
    nucleotidePhosphateBindingRegion,
    peptide,
    propeptide,
    regionOfInterest,
    repeat,
    nonstandardAminoAcid,
    sequenceConflict,
    sequenceVariant,
    shortSequenceMotif,
    signalPeptide,
    site,
    spliceVariant,
    strand,
    topologicalDomain,
    transitPeptide,
    transmembraneRegion,
    turn,
    unsureResidue,
    zincFingerRegion,
    intramembraneRegion;
  }

  /*
    ### General annotations (Comments)

    See

    1. http://www.uniprot.org/help/general_annotation
    2. http://web.expasy.org/docs/userman.html#CC_line

    Most comments are just a category together with text; these correspond to the comment vertex here. Whenever comments have some structure, they are promoted to vertices with edges going from proteins to them.
  */
  public final class Comment extends Vertex<Comment> {
    private Comment(V vertex) { super(vertex, comment); }
    @Override public final Comment self() { return this; }
  }

  public final CommentType comment = new CommentType();
  public final class CommentType extends VertexType<Comment> {

    @Override public final Comment fromRaw(V vertex) { return new Comment(vertex); }

    /*
      ### Topic

      The topic, or type, of this comment. Note that the possible values are restricted to those which are not promoted to specific vertex types.
    */
    public final Topic topic = new Topic();
    public final class Topic extends Property<CommentTopics> implements FromAtLeastOne, ToOne {
      private Topic() { super(CommentTopics.class); }
    }

    /*
      #### Text

      This property contains the text of this comment.
    */
    public final Text text = new Text();
    public final class Text extends Property<String> implements FromAny, ToOne {
      private Text() { super(String.class); }
    }
  }
  /*
    #### Comments edge

    Connects a protein with its commnents.
  */
  public final class Comments extends Edge<Protein, Comments, Comment> {
    private Comments(E edge) { super(edge, comments); }
    @Override public final Comments self() { return this; }
  }
  public final CommentsType comments = new CommentsType();
  public final class CommentsType extends EdgeType<Protein, Comments, Comment> implements FromAtLeastOne, ToAny {
    private CommentsType() { super(protein, comment); }
    @Override public final Comments fromRaw(E edge) { return new Comments(edge); }
  }

  /*
    #### Comment topics

    Note that this enum contains `alternativeProducts`, which won't ever be a value of the `Topic` property: alternative products are modeled as isoforms.
  */
  public static enum CommentTopics {

    allergen,
    alternativeProducts,
    biotechnology,
    biophysicochemicalProperties,
    catalyticActivity,
    caution,
    cofactor,
    developmentalStage,
    disease,
    domain,
    disruptionPhenotype,
    enzymeRegulation,
    function,
    induction,
    miscellaneous,
    pathway,
    pharmaceutical,
    polymorphism,
    PTM,
    RNAEditing,
    similarity,
    subcellularLocation,
    sequenceCaution,
    subunit,
    tissueSpecificity,
    toxicDose,
    onlineInformation,
    massSpectrometry,
    interaction;
  }

  /*
    ### Keywords

    See

    1. http://www.uniprot.org/help/keywords
    2. ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/docs/userman.htm#KW_line
    3. ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/docs/keywlist.txt
    4. http://geneontology.org/external2go/uniprotkb_kw2go

    Note that from http://www.uniprot.org/keywords/ you can download a `tsv` file with columns ID, description, category. If we also want the name I'm afraid we'll need to extract it from http://www.uniprot.org/docs/keywlist.
  */
  public final class Keyword extends Vertex<Keyword> {
    private Keyword(V vertex) { super(vertex, keyword); }
    @Override public final Keyword self() { return this; }
  }
  public final KeywordType keyword = new KeywordType();

  public final class KeywordType extends VertexType<Keyword> {

    @Override public final Keyword fromRaw(V vertex) { return new Keyword(vertex); }

    public final ID id = new ID();
    public final class ID extends Property<String> implements FromAtMostOne, ToOne {
      private ID() { super(String.class); }
      public final Index index = new Index();
      public final class Index extends UniqueIndex<ID, String> {
        private Index() { super(ID.this); }
      }
    }

    public final Definition definition = new Definition();
    public final class Definition extends Property<String> implements FromAny, ToOne {
      private Definition() { super(String.class); }
    }

    public final Category category = new Category();
    public final class Category extends Property<KeywordCategories> implements FromAny, ToOne {
      private Category() { super(KeywordCategories.class); }
    }
  }

  /*
    ### Keyword categories

    Right now keyword categories are represented as an enum. There is a hierarchy though, similar to that found in GO, but with not a clear meaning. Given enough interest, keyword categories can be upgraded to vertices, with an edge representing this hierarchy.
  */
  public static enum KeywordCategories {

    biologicalProcess,
    cellularComponent,
    codingSequenceDiversity,
    developmentalStage,
    disease,
    domain,
    ligand,
    molecularFunction,
    PTM,
    technicalTerm;
  }

  /*
    ### Protein keywords

    An edge linking a protein with its assigned keywords.
  */
  public final class Keywords extends Edge<Protein, Keywords, Keyword> {
    private Keywords(E edge) { super(edge, keywords); }
    @Override public final Keywords self() { return this; }
  }
  public final KeywordsType keywords = new KeywordsType();
  public final class KeywordsType extends EdgeType<Protein, Keywords, Keyword> implements FromAtLeastOne, ToAny {
    private KeywordsType() { super(protein, keyword); }
    @Override public final Keywords fromRaw(E edge) { return new Keywords(edge); }
  }
}
