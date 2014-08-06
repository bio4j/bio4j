package com.bio4j.model.uniprot;

import com.bio4j.model.uniprot.nodes.*;
import com.bio4j.model.uniprot.relationships.*;
import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniprotNCBITaxonomyGraph;
import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.ohnosequences.typedGraphs.*;

import java.util.Date;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class UniprotGraph<
		// untyped graph
		I extends UntypedGraph<RV, RVT, RE, RET>,
		// vertices
		RV, RVT,
		// edges
		RE, RET
		>
		implements
		TypedGraph<
				UniprotGraph<I, RV, RVT, RE, RET>,
				I, RV, RVT, RE, RET
				> {


	public abstract UniprotUniRefGraph uniprotUniRefGraph();
	public abstract UniprotGoGraph uniprotGoGraph();
	public abstract UniprotEnzymeDBGraph uniprotEnzymeDBGraph();
	public abstract UniprotNCBITaxonomyGraph uniprotNCBITaxonomyGraph();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// types
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// vertices
	public abstract ArticleType Article();

	public abstract BookType Book();

	public abstract CityType City();

	public abstract CommentTypeType CommentType();

	//public abstract ConsortiumType Consortium();

	public abstract DatasetType Dataset();

	public abstract CountryType Country();

	public abstract DBType DB();

	public abstract EMBLType EMBL();

	public abstract EnsemblType Ensembl();

	public abstract FeatureTypeType FeatureType();

	public abstract InstituteType Institute();

	public abstract InterproType Interpro();

	public abstract JournalType Journal();

	public abstract KeggType Kegg();

	public abstract KeywordType Keyword();

	public abstract OnlineArticleType OnlineArticle();

	public abstract OrganismType Organism();

	public abstract OnlineJournalType OnlineJournal();

	public abstract PatentType Patent();

	//public abstract PersonType Person();

	public abstract PfamType Pfam();

	public abstract PIRType PIR();

	public abstract ProteinType Protein();

	public abstract PublisherType Publisher();

	public abstract PubmedType Pubmed();

	public abstract ReactomeTermType ReactomeTerm();

	public abstract ReferenceType Reference();

	public abstract RefSeqType RefSeq();

	//public abstract SequenceCautionType SequenceCaution();

	public abstract SubcellularLocationType SubcellularLocation();

	public abstract SubmissionType Submission();

	public abstract TaxonType Taxon();

	public abstract ThesisType Thesis();

	public abstract UniGeneType UniGene();

	public abstract UnpublishedObservationType UnpublishedObservation();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// edges

	public abstract ArticleJournalType ArticleJournal();

	public abstract ArticlePubmedType ArticlePubmed();

	public abstract ArticleJournalType ArticleJournalType();

	public abstract BookCityType BookCity();

	public abstract BookPublisherType BookPublisher();

	public abstract InstituteCountryType InstituteCountry();

	public abstract OnlineArticleOnlineJournalType OnlineArticleOnlineJournal();

	public abstract OrganismTaxonType OrganismTaxon();

	public abstract ProteinCommentType ProteinComment();

	public abstract ProteinDatasetType ProteinDataset();

	public abstract ProteinEMBLType ProteinEMBL();

	public abstract ProteinEnsemblType ProteinEnsembl();

	public abstract ProteinFeatureType ProteinFeature();

	public abstract ProteinInterproType ProteinInterpro();

	public abstract ProteinKeggType ProteinKegg();

	public abstract ProteinKeywordType ProteinKeyword();

	public abstract ProteinOrganismType ProteinOrganism();

	public abstract ProteinPfamType ProteinPfam();

	public abstract ProteinPIRType ProteinPIR();

	public abstract ProteinReactomeTermType ProteinReactomeTerm();

	public abstract ProteinSubcellularLocationType ProteinSubcellularLocation();

	public abstract ProteinUniGeneType ProteinUniGene();

	public abstract ProteinRefSeqType ProteinRefSeq();

	public abstract ProteinReferenceType ProteinReference();

	public abstract ReferenceArticleType ReferenceArticle();

	public abstract ReferenceBookType ReferenceBook();

	public abstract ReferenceOnlineArticleType ReferenceOnlineArticle();

	public abstract ReferencePatentType ReferencePatent();

	public abstract ReferenceThesisType ReferenceThesis();

	public abstract ReferenceSubmissionType ReferenceSubmission();

	public abstract ReferenceUnpublishedObservationType ReferenceUnpublishedObservation();

	public abstract SubmissionDBType SubmissionDB();

	public abstract SubcellularLocationParentType SubcellularLocationParent();

	public abstract TaxonParentType TaxonParent();

	public abstract ThesisInstituteType ThesisInstitute();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Vertex types

	public final class ArticleType
			extends
			UniprotVertexType<
					Article<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.ArticleType
					> {

		public final doId doId = new doId();
		public final title title = new title();

		protected ArticleType(RVT raw) {
			super(raw);
		}

		@Override
		public ArticleType value() {
			return graph().Article();
		}

		@Override
		public Article<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Article<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class doId
				extends
				UniprotVertexProperty<Article<I, RV, RVT, RE, RET>, ArticleType, doId, String> {
			public doId() {
				super(ArticleType.this.graph().Article());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class title
				extends
				UniprotVertexProperty<Article<I, RV, RVT, RE, RET>, ArticleType, title, String> {
			public title() {
				super(ArticleType.this.graph().Article());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}


	public final class BookType
			extends
			UniprotVertexType<
					Book<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.BookType
					> {

		public final name name = new name();

		protected BookType(RVT raw) {
			super(raw);
		}

		@Override
		public BookType value() {
			return graph().Book();
		}

		@Override
		public Book<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Book<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<Book<I, RV, RVT, RE, RET>, BookType, name, String> {
			public name() {
				super(BookType.this.graph().Book());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}

	public final class CityType
			extends
			UniprotVertexType<
					City<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.CityType
					> {

		public final name name = new name();

		protected CityType(RVT raw) {
			super(raw);
		}

		@Override
		public CityType value() {
			return graph().City();
		}

		@Override
		public City<I, RV, RVT, RE, RET> from(RV vertex) {
			return new City<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<City<I, RV, RVT, RE, RET>, CityType, name, String> {
			public name() {
				super(CityType.this.graph().City());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}

	public final class CommentTypeType
			extends
			UniprotVertexType<
					CommentType<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.CommentTypeType
					> {

		public final name name = new name();

		protected CommentTypeType(RVT raw) {
			super(raw);
		}

		@Override
		public CommentTypeType value() {
			return graph().CommentType();
		}

		@Override
		public CommentType<I, RV, RVT, RE, RET> from(RV vertex) {
			return new CommentType<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<CommentType<I, RV, RVT, RE, RET>, CommentTypeType, name, String> {
			public name() {
				super(CommentTypeType.this.graph().CommentType());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}

	public final class CountryType
			extends
			UniprotVertexType<
					Country<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.CountryType
					> {

		public final name name = new name();

		protected CountryType(RVT raw) {
			super(raw);
		}

		@Override
		public CountryType value() {
			return graph().Country();
		}

		@Override
		public Country<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Country<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<Country<I, RV, RVT, RE, RET>, CountryType, name, String> {
			public name() {
				super(CountryType.this.graph().Country());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}

	public final class DatasetType
			extends
			UniprotVertexType<
					Dataset<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.DatasetType
					> {

		public final name name = new name();

		protected DatasetType(RVT raw) {
			super(raw);
		}

		@Override
		public DatasetType value() {
			return graph().Dataset();
		}

		@Override
		public Dataset<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Dataset<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<Dataset<I, RV, RVT, RE, RET>, DatasetType, name, String> {
			public name() {
				super(DatasetType.this.graph().Dataset());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}

	public final class DBType
			extends
			UniprotVertexType<
					DB<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.DBType
					> {

		public final name name = new name();

		protected DBType(RVT raw) {
			super(raw);
		}

		@Override
		public DBType value() {
			return graph().DB();
		}

		@Override
		public DB<I, RV, RVT, RE, RET> from(RV vertex) {
			return new DB<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<DB<I, RV, RVT, RE, RET>, DBType, name, String> {
			public name() {
				super(DBType.this.graph().DB());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}

	public final class EMBLType
			extends
			UniprotVertexType<
					EMBL<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.EMBLType
					> {

		public final id id = new id();
		public final proteinSequenceId proteinSequenceId = new proteinSequenceId();
		public final moleculeType moleculeType = new moleculeType();

		protected EMBLType(RVT raw) {
			super(raw);
		}

		@Override
		public EMBLType value() {
			return graph().EMBL();
		}

		@Override
		public EMBL<I, RV, RVT, RE, RET> from(RV vertex) {
			return new EMBL<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
				extends
				UniprotVertexProperty<EMBL<I, RV, RVT, RE, RET>, EMBLType, id, String> {
			public id() {
				super(EMBLType.this.graph().EMBL());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class proteinSequenceId
				extends
				UniprotVertexProperty<EMBL<I, RV, RVT, RE, RET>, EMBLType, proteinSequenceId, String> {
			public proteinSequenceId() {
				super(EMBLType.this.graph().EMBL());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class moleculeType
				extends
				UniprotVertexProperty<EMBL<I, RV, RVT, RE, RET>, EMBLType, moleculeType, String> {
			public moleculeType() {
				super(EMBLType.this.graph().EMBL());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}

	public final class EnsemblType
			extends
			UniprotVertexType<
					Ensembl<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.EnsemblType
					> {

		public final id id = new id();
		public final proteinSequenceId proteinSequenceId = new proteinSequenceId();
		public final moleculeId moleculeId = new moleculeId();
		public final geneId geneId = new geneId();

		protected EnsemblType(RVT raw) {
			super(raw);
		}

		@Override
		public EnsemblType value() {
			return graph().Ensembl();
		}

		@Override
		public Ensembl<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Ensembl<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
				extends
				UniprotVertexProperty<Ensembl<I, RV, RVT, RE, RET>, EnsemblType, id, String> {
			public id() {
				super(EnsemblType.this.graph().Ensembl());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class proteinSequenceId
				extends
				UniprotVertexProperty<Ensembl<I, RV, RVT, RE, RET>, EnsemblType, proteinSequenceId, String> {
			public proteinSequenceId() {
				super(EnsemblType.this.graph().Ensembl());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class moleculeId
				extends
				UniprotVertexProperty<Ensembl<I, RV, RVT, RE, RET>, EnsemblType, moleculeId, String> {
			public moleculeId() {
				super(EnsemblType.this.graph().Ensembl());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class geneId
				extends
				UniprotVertexProperty<Ensembl<I, RV, RVT, RE, RET>, EnsemblType, geneId, String> {
			public geneId() {
				super(EnsemblType.this.graph().Ensembl());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class FeatureTypeType
			extends
			UniprotVertexType<
					FeatureType<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.FeatureTypeType
					> {

		public final name name = new name();

		protected FeatureTypeType(RVT raw) {
			super(raw);
		}

		@Override
		public FeatureTypeType value() {
			return graph().FeatureType();
		}

		@Override
		public FeatureType<I, RV, RVT, RE, RET> from(RV vertex) {
			return new FeatureType<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<FeatureType<I, RV, RVT, RE, RET>, FeatureTypeType, name, String> {
			public name() {
				super(FeatureTypeType.this.graph().FeatureType());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class InterproType
			extends
			UniprotVertexType<
					Interpro<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.InterproType
					> {

		public final name name = new name();
		public final id id = new id();

		protected InterproType(RVT raw) {
			super(raw);
		}

		@Override
		public InterproType value() {
			return graph().Interpro();
		}

		@Override
		public Interpro<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Interpro<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<Interpro<I, RV, RVT, RE, RET>, InterproType, name, String> {
			public name() {
				super(InterproType.this.graph().Interpro());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class id
				extends
				UniprotVertexProperty<Interpro<I, RV, RVT, RE, RET>, InterproType, id, String> {
			public id() {
				super(InterproType.this.graph().Interpro());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class InstituteType
			extends
			UniprotVertexType<
					Institute<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.InstituteType
					> {

		public final name name = new name();

		protected InstituteType(RVT raw) {
			super(raw);
		}

		@Override
		public InstituteType value() {
			return graph().Institute();
		}

		@Override
		public Institute<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Institute<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<Institute<I, RV, RVT, RE, RET>, InstituteType, name, String> {
			public name() {
				super(InstituteType.this.graph().Institute());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}

	public final class JournalType
			extends
			UniprotVertexType<
					Journal<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.JournalType
					> {

		public final name name = new name();

		protected JournalType(RVT raw) {
			super(raw);
		}

		@Override
		public JournalType value() {
			return graph().Journal();
		}

		@Override
		public Journal<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Journal<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<Journal<I, RV, RVT, RE, RET>, JournalType, name, String> {
			public name() {
				super(JournalType.this.graph().Journal());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class KeggType
			extends
			UniprotVertexType<
					Kegg<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.KeggType
					> {

		public final id id = new id();

		protected KeggType(RVT raw) {
			super(raw);
		}

		@Override
		public KeggType value() {
			return graph().Kegg();
		}

		@Override
		public Kegg<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Kegg<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
				extends
				UniprotVertexProperty<Kegg<I, RV, RVT, RE, RET>, KeggType, id, String> {
			public id() {
				super(KeggType.this.graph().Kegg());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class KeywordType
			extends
			UniprotVertexType<
					Keyword<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.KeywordType
					> {

		public final name name = new name();
		public final id id = new id();

		protected KeywordType(RVT raw) {
			super(raw);
		}

		@Override
		public KeywordType value() {
			return graph().Keyword();
		}

		@Override
		public Keyword<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Keyword<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<Keyword<I, RV, RVT, RE, RET>, KeywordType, name, String> {
			public name() {
				super(KeywordType.this.graph().Keyword());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class id
				extends
				UniprotVertexProperty<Keyword<I, RV, RVT, RE, RET>, KeywordType, id, String> {
			public id() {
				super(KeywordType.this.graph().Keyword());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class OnlineArticleType
			extends
			UniprotVertexType<
					OnlineArticle<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.OnlineArticleType
					> {

		public final title title = new title();

		protected OnlineArticleType(RVT raw) {
			super(raw);
		}

		@Override
		public OnlineArticleType value() {
			return graph().OnlineArticle();
		}

		@Override
		public OnlineArticle<I, RV, RVT, RE, RET> from(RV vertex) {
			return new OnlineArticle<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class title
				extends
				UniprotVertexProperty<OnlineArticle<I, RV, RVT, RE, RET>, OnlineArticleType, title, String> {
			public title() {
				super(OnlineArticleType.this.graph().OnlineArticle());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class OnlineJournalType
			extends
			UniprotVertexType<
					OnlineJournal<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.OnlineJournalType
					> {

		public final name name = new name();

		protected OnlineJournalType(RVT raw) {
			super(raw);
		}

		@Override
		public OnlineJournalType value() {
			return graph().OnlineJournal();
		}

		@Override
		public OnlineJournal<I, RV, RVT, RE, RET> from(RV vertex) {
			return new OnlineJournal<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<OnlineJournal<I, RV, RVT, RE, RET>, OnlineJournalType, name, String> {
			public name() {
				super(OnlineJournalType.this.graph().OnlineJournal());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class OrganismType
			extends
			UniprotVertexType<
					Organism<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.OrganismType
					> {

		public final scientificName scientificName = new scientificName();
		public final commonName commonName = new commonName();
		public final synonymName synonymName = new synonymName();

		protected OrganismType(RVT raw) {
			super(raw);
		}

		@Override
		public OrganismType value() {
			return graph().Organism();
		}

		@Override
		public Organism<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Organism<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class scientificName
				extends
				UniprotVertexProperty<Organism<I, RV, RVT, RE, RET>, OrganismType, scientificName, String> {
			public scientificName() {
				super(OrganismType.this.graph().Organism());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class commonName
				extends
				UniprotVertexProperty<Organism<I, RV, RVT, RE, RET>, OrganismType, commonName, String> {
			public commonName() {
				super(OrganismType.this.graph().Organism());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class synonymName
				extends
				UniprotVertexProperty<Organism<I, RV, RVT, RE, RET>, OrganismType, synonymName, String> {
			public synonymName() {
				super(OrganismType.this.graph().Organism());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class PatentType
			extends
			UniprotVertexType<
					Patent<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.PatentType
					> {

		public final title title = new title();
		public final number number = new number();

		protected PatentType(RVT raw) {
			super(raw);
		}

		@Override
		public PatentType value() {
			return graph().Patent();
		}

		@Override
		public Patent<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Patent<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class title
				extends
				UniprotVertexProperty<Patent<I, RV, RVT, RE, RET>, PatentType, title, String> {
			public title() {
				super(PatentType.this.graph().Patent());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class number
				extends
				UniprotVertexProperty<Patent<I, RV, RVT, RE, RET>, PatentType, number, String> {
			public number() {
				super(PatentType.this.graph().Patent());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}

	public final class PfamType
			extends
			UniprotVertexType<
					Pfam<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.PfamType
					> {

		public final name name = new name();
		public final id id = new id();

		protected PfamType(RVT raw) {
			super(raw);
		}

		@Override
		public PfamType value() {
			return graph().Pfam();
		}

		@Override
		public Pfam<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Pfam<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<Pfam<I, RV, RVT, RE, RET>, PfamType, name, String> {
			public name() {
				super(PfamType.this.graph().Pfam());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class id
				extends
				UniprotVertexProperty<Pfam<I, RV, RVT, RE, RET>, PfamType, id, String> {
			public id() {
				super(PfamType.this.graph().Pfam());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class PubmedType
			extends
			UniprotVertexType<
					Pubmed<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.PubmedType
					> {

		public final id id = new id();

		protected PubmedType(RVT raw) {
			super(raw);
		}

		@Override
		public PubmedType value() {
			return graph().Pubmed();
		}

		@Override
		public Pubmed<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Pubmed<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
				extends
				UniprotVertexProperty<Pubmed<I, RV, RVT, RE, RET>, PubmedType, id, String> {
			public id() {
				super(PubmedType.this.graph().Pubmed());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class PIRType
			extends
			UniprotVertexType<
					PIR<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.PIRType
					> {

		public final id id = new id();

		protected PIRType(RVT raw) {
			super(raw);
		}

		@Override
		public PIRType value() {
			return graph().PIR();
		}

		@Override
		public PIR<I, RV, RVT, RE, RET> from(RV vertex) {
			return new PIR<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
				extends
				UniprotVertexProperty<PIR<I, RV, RVT, RE, RET>, PIRType, id, String> {
			public id() {
				super(PIRType.this.graph().PIR());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}


	public final class ProteinType
			extends
			UniprotVertexType<
					Protein<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.ProteinType
					> {

		public final accession accession = new accession();
		public final shortName shortName = new shortName();
		public final sequence sequence = new sequence();
		public final fullname fullname = new fullname();
		public final modifiedDate modifiedDate = new modifiedDate();
		public final createdDate createdDate = new createdDate();
		public final mass mass = new mass();
		public final version version = new version();
		public final length length = new length();


		protected ProteinType(RVT raw) {
			super(raw);
		}

		@Override
		public ProteinType value() {
			return graph().Protein();
		}

		@Override
		public Protein<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Protein<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class accession
				extends
				UniprotVertexProperty<Protein<I, RV, RVT, RE, RET>, ProteinType, accession, String> {
			public accession() {
				super(ProteinType.this.graph().Protein());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class shortName
				extends
				UniprotVertexProperty<Protein<I, RV, RVT, RE, RET>, ProteinType, shortName, String> {
			public shortName() {
				super(ProteinType.this.graph().Protein());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class sequence
				extends
				UniprotVertexProperty<Protein<I, RV, RVT, RE, RET>, ProteinType, sequence, String> {
			public sequence() {
				super(ProteinType.this.graph().Protein());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class fullname
				extends
				UniprotVertexProperty<Protein<I, RV, RVT, RE, RET>, ProteinType, fullname, String> {
			public fullname() {
				super(ProteinType.this.graph().Protein());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class modifiedDate
				extends
				UniprotVertexProperty<Protein<I, RV, RVT, RE, RET>, ProteinType, modifiedDate, Date> {
			public modifiedDate() {
				super(ProteinType.this.graph().Protein());
			}

			public Class<Date> valueClass() {
				return Date.class;
			}
		}
		public final class createdDate
				extends
				UniprotVertexProperty<Protein<I, RV, RVT, RE, RET>, ProteinType, createdDate, Date> {
			public createdDate() {
				super(ProteinType.this.graph().Protein());
			}

			public Class<Date> valueClass() {
				return Date.class;
			}
		}
		public final class mass
				extends
				UniprotVertexProperty<Protein<I, RV, RVT, RE, RET>, ProteinType, mass, String> {
			public mass() {
				super(ProteinType.this.graph().Protein());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class version
				extends
				UniprotVertexProperty<Protein<I, RV, RVT, RE, RET>, ProteinType, version, Integer> {
			public version() {
				super(ProteinType.this.graph().Protein());
			}

			public Class<Integer> valueClass() {
				return Integer.class;
			}
		}
		public final class length
				extends
				UniprotVertexProperty<Protein<I, RV, RVT, RE, RET>, ProteinType, length, Integer> {
			public length() {
				super(ProteinType.this.graph().Protein());
			}

			public Class<Integer> valueClass() {
				return Integer.class;
			}
		}

	}

	public final class PublisherType
			extends
			UniprotVertexType<
					Publisher<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.PublisherType
					> {

		public final name name = new name();

		protected PublisherType(RVT raw) {
			super(raw);
		}

		@Override
		public PublisherType value() {
			return graph().Publisher();
		}

		@Override
		public Publisher<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Publisher<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<Publisher<I, RV, RVT, RE, RET>, PublisherType, name, String> {
			public name() {
				super(PublisherType.this.graph().Publisher());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class ReactomeTermType
			extends
			UniprotVertexType<
					ReactomeTerm<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.ReactomeTermType
					> {

		public final id id = new id();
		public final pathwayName pathwayName = new pathwayName();

		protected ReactomeTermType(RVT raw) {
			super(raw);
		}

		@Override
		public ReactomeTermType value() {
			return graph().ReactomeTerm();
		}

		@Override
		public ReactomeTerm<I, RV, RVT, RE, RET> from(RV vertex) {
			return new ReactomeTerm<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
				extends
				UniprotVertexProperty<ReactomeTerm<I, RV, RVT, RE, RET>, ReactomeTermType, id, String> {
			public id() {
				super(ReactomeTermType.this.graph().ReactomeTerm());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class pathwayName
				extends
				UniprotVertexProperty<ReactomeTerm<I, RV, RVT, RE, RET>, ReactomeTermType, pathwayName, String> {
			public pathwayName() {
				super(ReactomeTermType.this.graph().ReactomeTerm());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class ReferenceType
			extends
			UniprotVertexType<
					Reference<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType
					> {

		public final name name = new name();

		protected ReferenceType(RVT raw) {
			super(raw);
		}

		@Override
		public ReferenceType value() {
			return graph().Reference();
		}

		@Override
		public Reference<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Reference<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<Reference<I, RV, RVT, RE, RET>, ReferenceType, name, String> {
			public name() {
				super(ReferenceType.this.graph().Reference());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class RefSeqType
			extends
			UniprotVertexType<
					RefSeq<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.RefSeqType
					> {

		public final id id = new id();
		public final nucleotideSequenceId nucleotideSequenceId = new nucleotideSequenceId();

		protected RefSeqType(RVT raw) {
			super(raw);
		}

		@Override
		public RefSeqType value() {
			return graph().RefSeq();
		}

		@Override
		public RefSeq<I, RV, RVT, RE, RET> from(RV vertex) {
			return new RefSeq<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
				extends
				UniprotVertexProperty<RefSeq<I, RV, RVT, RE, RET>, RefSeqType, id, String> {
			public id() {
				super(RefSeqType.this.graph().RefSeq());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
		public final class nucleotideSequenceId
				extends
				UniprotVertexProperty<RefSeq<I, RV, RVT, RE, RET>, RefSeqType, nucleotideSequenceId, String> {
			public nucleotideSequenceId() {
				super(RefSeqType.this.graph().RefSeq());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class SubcellularLocationType
			extends
			UniprotVertexType<
					SubcellularLocation<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.SubcellularLocationType
					> {

		public final name name = new name();

		protected SubcellularLocationType(RVT raw) {
			super(raw);
		}

		@Override
		public SubcellularLocationType value() {
			return graph().SubcellularLocation();
		}

		@Override
		public SubcellularLocation<I, RV, RVT, RE, RET> from(RV vertex) {
			return new SubcellularLocation<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<SubcellularLocation<I, RV, RVT, RE, RET>, SubcellularLocationType, name, String> {
			public name() {
				super(SubcellularLocationType.this.graph().SubcellularLocation());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class SubmissionType
			extends
			UniprotVertexType<
					Submission<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.SubmissionType
					> {

		public final title title = new title();

		protected SubmissionType(RVT raw) {
			super(raw);
		}

		@Override
		public SubmissionType value() {
			return graph().Submission();
		}

		@Override
		public Submission<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Submission<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class title
				extends
				UniprotVertexProperty<Submission<I, RV, RVT, RE, RET>, SubmissionType, title, String> {
			public title() {
				super(SubmissionType.this.graph().Submission());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class TaxonType
			extends
			UniprotVertexType<
					Taxon<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.TaxonType
					> {

		public final name name = new name();

		protected TaxonType(RVT raw) {
			super(raw);
		}

		@Override
		public TaxonType value() {
			return graph().Taxon();
		}

		@Override
		public Taxon<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Taxon<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				UniprotVertexProperty<Taxon<I, RV, RVT, RE, RET>, TaxonType, name, String> {
			public name() {
				super(TaxonType.this.graph().Taxon());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}

	public final class ThesisType
			extends
			UniprotVertexType<
					Thesis<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.ThesisType
					> {

		public final title title = new title();

		protected ThesisType(RVT raw) {
			super(raw);
		}

		@Override
		public ThesisType value() {
			return graph().Thesis();
		}

		@Override
		public Thesis<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Thesis<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class title
				extends
				UniprotVertexProperty<Thesis<I, RV, RVT, RE, RET>, ThesisType, title, String> {
			public title() {
				super(ThesisType.this.graph().Thesis());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}

	public final class UniGeneType
			extends
			UniprotVertexType<
					UniGene<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.UniGeneType
					> {

		public final id id = new id();

		protected UniGeneType(RVT raw) {
			super(raw);
		}

		@Override
		public UniGeneType value() {
			return graph().UniGene();
		}

		@Override
		public UniGene<I, RV, RVT, RE, RET> from(RV vertex) {
			return new UniGene<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
				extends
				UniprotVertexProperty<UniGene<I, RV, RVT, RE, RET>, UniGeneType, id, String> {
			public id() {
				super(UniGeneType.this.graph().UniGene());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}

	public final class UnpublishedObservationType
			extends
			UniprotVertexType<
					UnpublishedObservation<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.UnpublishedObservationType
					> {

		protected UnpublishedObservationType(RVT raw) {
			super(raw);
		}

		@Override
		public UnpublishedObservationType value() {
			return graph().UnpublishedObservation();
		}

		@Override
		public UnpublishedObservation<I, RV, RVT, RE, RET> from(RV vertex) {
			return new UnpublishedObservation<I, RV, RVT, RE, RET>(vertex, this);
		}
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Edge types

	public final class ArticleJournalType
			extends
			UniprotEdgeType<
					Article<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ArticleType,
					ArticleJournal<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ArticleJournalType,
					Journal<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.JournalType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected ArticleJournalType(RET raw) {
			super(UniprotGraph.this.Article(), raw, UniprotGraph.this.Journal());
		}

		@Override
		public ArticleJournalType value() {
			return graph().ArticleJournal();
		}

		@Override
		public ArticleJournal<I, RV, RVT, RE, RET> from(RE edge) {
			return new ArticleJournal<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ArticlePubmedType
			extends
			UniprotEdgeType<
					Article<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ArticleType,
					ArticlePubmed<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ArticlePubmedType,
					Pubmed<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.PubmedType
					>
			implements
			TypedEdge.Type.OneToOne {

		protected ArticlePubmedType(RET raw) {
			super(UniprotGraph.this.Article(), raw, UniprotGraph.this.Pubmed());
		}

		@Override
		public ArticlePubmedType value() {
			return graph().ArticlePubmed();
		}

		@Override
		public ArticlePubmed<I, RV, RVT, RE, RET> from(RE edge) {
			return new ArticlePubmed<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class BookCityType
			extends
			UniprotEdgeType<
					Book<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.BookType,
					BookCity<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.BookCityType,
					City<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.CityType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected BookCityType(RET raw) {
			super(UniprotGraph.this.Book(), raw, UniprotGraph.this.City());
		}

		@Override
		public BookCityType value() {
			return graph().BookCity();
		}

		@Override
		public BookCity<I, RV, RVT, RE, RET> from(RE edge) {
			return new BookCity<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class BookPublisherType
			extends
			UniprotEdgeType<
					Book<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.BookType,
					BookPublisher<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.BookPublisherType,
					Publisher<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.PublisherType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected BookPublisherType(RET raw) {
			super(UniprotGraph.this.Book(), raw, UniprotGraph.this.Publisher());
		}

		@Override
		public BookPublisherType value() {
			return graph().BookPublisher();
		}

		@Override
		public BookPublisher<I, RV, RVT, RE, RET> from(RE edge) {
			return new BookPublisher<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class InstituteCountryType
			extends
			UniprotEdgeType<
					Institute<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.InstituteType,
					InstituteCountry<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.InstituteCountryType,
					Country<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.CountryType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected InstituteCountryType(RET raw) {
			super(UniprotGraph.this.Institute(), raw, UniprotGraph.this.Country());
		}

		@Override
		public InstituteCountryType value() {
			return graph().InstituteCountry();
		}

		@Override
		public InstituteCountry<I, RV, RVT, RE, RET> from(RE edge) {
			return new InstituteCountry<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class OnlineArticleOnlineJournalType
			extends
			UniprotEdgeType<
					OnlineArticle<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OnlineArticleType,
					OnlineArticleOnlineJournal<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OnlineArticleOnlineJournalType,
					OnlineJournal<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OnlineJournalType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected OnlineArticleOnlineJournalType(RET raw) {
			super(UniprotGraph.this.OnlineArticle(), raw, UniprotGraph.this.OnlineJournal());
		}

		@Override
		public OnlineArticleOnlineJournalType value() {
			return graph().OnlineArticleOnlineJournal();
		}

		@Override
		public OnlineArticleOnlineJournal<I, RV, RVT, RE, RET> from(RE edge) {
			return new OnlineArticleOnlineJournal<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class OrganismTaxonType
			extends
			UniprotEdgeType<
					Organism<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OrganismType,
					OrganismTaxon<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OrganismTaxonType,
					Taxon<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.TaxonType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected OrganismTaxonType(RET raw) {
			super(UniprotGraph.this.Organism(), raw, UniprotGraph.this.Taxon());
		}

		@Override
		public OrganismTaxonType value() {
			return graph().OrganismTaxon();
		}

		@Override
		public OrganismTaxon<I, RV, RVT, RE, RET> from(RE edge) {
			return new OrganismTaxon<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinCommentType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinComment<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinCommentType,
					CommentType<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.CommentTypeType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinCommentType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.CommentType());
		}

		@Override
		public ProteinCommentType value() {
			return graph().ProteinComment();
		}

		@Override
		public ProteinComment<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinComment<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinDatasetType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinDataset<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinDatasetType,
					Dataset<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.DatasetType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected ProteinDatasetType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.Dataset());
		}

		@Override
		public ProteinDatasetType value() {
			return graph().ProteinDataset();
		}

		@Override
		public ProteinDataset<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinDataset<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinEMBLType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinEMBL<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinEMBLType,
					EMBL<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.EMBLType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinEMBLType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.EMBL());
		}

		@Override
		public ProteinEMBLType value() {
			return graph().ProteinEMBL();
		}

		@Override
		public ProteinEMBL<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinEMBL<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinEnsemblType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinEnsembl<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinEnsemblType,
					Ensembl<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.EnsemblType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinEnsemblType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.Ensembl());
		}

		@Override
		public ProteinEnsemblType value() {
			return graph().ProteinEnsembl();
		}

		@Override
		public ProteinEnsembl<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinEnsembl<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinFeatureType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinFeature<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinFeatureType,
					FeatureType<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.FeatureTypeType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinFeatureType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.FeatureType());
		}

		@Override
		public ProteinFeatureType value() {
			return graph().ProteinFeature();
		}

		@Override
		public ProteinFeature<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinFeature<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinInterproType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinInterpro<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinInterproType,
					Interpro<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.InterproType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinInterproType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.Interpro());
		}

		@Override
		public ProteinInterproType value() {
			return graph().ProteinInterpro();
		}

		@Override
		public ProteinInterpro<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinInterpro<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinKeggType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinKegg<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinKeggType,
					Kegg<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.KeggType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinKeggType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.Kegg());
		}

		@Override
		public ProteinKeggType value() {
			return graph().ProteinKegg();
		}

		@Override
		public ProteinKegg<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinKegg<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinKeywordType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinKeyword<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinKeywordType,
					Keyword<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.KeywordType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinKeywordType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.Keyword());
		}

		@Override
		public ProteinKeywordType value() {
			return graph().ProteinKeyword();
		}

		@Override
		public ProteinKeyword<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinKeyword<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinOrganismType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinOrganism<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinOrganismType,
					Organism<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OrganismType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected ProteinOrganismType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.Organism());
		}

		@Override
		public ProteinOrganismType value() {
			return graph().ProteinOrganism();
		}

		@Override
		public ProteinOrganism<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinOrganism<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinPfamType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinPfam<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinPfamType,
					Pfam<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.PfamType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinPfamType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.Pfam());
		}

		@Override
		public ProteinPfamType value() {
			return graph().ProteinPfam();
		}

		@Override
		public ProteinPfam<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinPfam<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinPIRType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinPIR<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinPIRType,
					PIR<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.PIRType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinPIRType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.PIR());
		}

		@Override
		public ProteinPIRType value() {
			return graph().ProteinPIR();
		}

		@Override
		public ProteinPIR<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinPIR<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinReactomeTermType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinReactomeTerm<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinReactomeTermType,
					ReactomeTerm<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReactomeTermType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinReactomeTermType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.ReactomeTerm());
		}

		@Override
		public ProteinReactomeTermType value() {
			return graph().ProteinReactomeTerm();
		}

		@Override
		public ProteinReactomeTerm<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinReactomeTerm<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinReferenceType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinReference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinReferenceType,
					Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinReferenceType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.Reference());
		}

		@Override
		public ProteinReferenceType value() {
			return graph().ProteinReference();
		}

		@Override
		public ProteinReference<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinReference<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinRefSeqType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinRefSeq<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinRefSeqType,
					RefSeq<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.RefSeqType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinRefSeqType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.RefSeq());
		}

		@Override
		public ProteinRefSeqType value() {
			return graph().ProteinRefSeq();
		}

		@Override
		public ProteinRefSeq<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinRefSeq<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinSubcellularLocationType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinSubcellularLocation<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinSubcellularLocationType,
					SubcellularLocation<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.SubcellularLocationType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinSubcellularLocationType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.SubcellularLocation());
		}

		@Override
		public ProteinSubcellularLocationType value() {
			return graph().ProteinSubcellularLocation();
		}

		@Override
		public ProteinSubcellularLocation<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinSubcellularLocation<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ProteinUniGeneType
			extends
			UniprotEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					ProteinUniGene<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinUniGeneType,
					UniGene<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.UniGeneType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected ProteinUniGeneType(RET raw) {
			super(UniprotGraph.this.Protein(), raw, UniprotGraph.this.UniGene());
		}

		@Override
		public ProteinUniGeneType value() {
			return graph().ProteinUniGene();
		}

		@Override
		public ProteinUniGene<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinUniGene<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ReferenceArticleType
			extends
			UniprotEdgeType<
					Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
					ReferenceArticle<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceArticleType,
					Article<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ArticleType
					>
			implements
			TypedEdge.Type.OneToOne {

		protected ReferenceArticleType(RET raw) {
			super(UniprotGraph.this.Reference(), raw, UniprotGraph.this.Article());
		}

		@Override
		public ReferenceArticleType value() {
			return graph().ReferenceArticle();
		}

		@Override
		public ReferenceArticle<I, RV, RVT, RE, RET> from(RE edge) {
			return new ReferenceArticle<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ReferenceBookType
			extends
			UniprotEdgeType<
					Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
					ReferenceBook<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceBookType,
					Book<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.BookType
					>
			implements
			TypedEdge.Type.OneToOne {

		protected ReferenceBookType(RET raw) {
			super(UniprotGraph.this.Reference(), raw, UniprotGraph.this.Book());
		}

		@Override
		public ReferenceBookType value() {
			return graph().ReferenceBook();
		}

		@Override
		public ReferenceBook<I, RV, RVT, RE, RET> from(RE edge) {
			return new ReferenceBook<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ReferencePatentType
			extends
			UniprotEdgeType<
					Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
					ReferencePatent<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferencePatentType,
					Patent<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.PatentType
					>
			implements
			TypedEdge.Type.OneToOne {

		protected ReferencePatentType(RET raw) {
			super(UniprotGraph.this.Reference(), raw, UniprotGraph.this.Patent());
		}

		@Override
		public ReferencePatentType value() {
			return graph().ReferencePatent();
		}

		@Override
		public ReferencePatent<I, RV, RVT, RE, RET> from(RE edge) {
			return new ReferencePatent<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ReferenceThesisType
			extends
			UniprotEdgeType<
					Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
					ReferenceThesis<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceThesisType,
					Thesis<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ThesisType
					>
			implements
			TypedEdge.Type.OneToOne {

		protected ReferenceThesisType(RET raw) {
			super(UniprotGraph.this.Reference(), raw, UniprotGraph.this.Thesis());
		}

		@Override
		public ReferenceThesisType value() {
			return graph().ReferenceThesis();
		}

		@Override
		public ReferenceThesis<I, RV, RVT, RE, RET> from(RE edge) {
			return new ReferenceThesis<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ReferenceSubmissionType
			extends
			UniprotEdgeType<
					Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
					ReferenceSubmission<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceSubmissionType,
					Submission<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.SubmissionType
					>
			implements
			TypedEdge.Type.OneToOne {

		protected ReferenceSubmissionType(RET raw) {
			super(UniprotGraph.this.Reference(), raw, UniprotGraph.this.Submission());
		}

		@Override
		public ReferenceSubmissionType value() {
			return graph().ReferenceSubmission();
		}

		@Override
		public ReferenceSubmission<I, RV, RVT, RE, RET> from(RE edge) {
			return new ReferenceSubmission<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ReferenceUnpublishedObservationType
			extends
			UniprotEdgeType<
					Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
					ReferenceUnpublishedObservation<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceUnpublishedObservationType,
					UnpublishedObservation<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.UnpublishedObservationType
					>
			implements
			TypedEdge.Type.OneToOne {

		protected ReferenceUnpublishedObservationType(RET raw) {
			super(UniprotGraph.this.Reference(), raw, UniprotGraph.this.UnpublishedObservation());
		}

		@Override
		public ReferenceUnpublishedObservationType value() {
			return graph().ReferenceUnpublishedObservation();
		}

		@Override
		public ReferenceUnpublishedObservation<I, RV, RVT, RE, RET> from(RE edge) {
			return new ReferenceUnpublishedObservation<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ReferenceOnlineArticleType
			extends
			UniprotEdgeType<
					Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
					ReferenceOnlineArticle<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceOnlineArticleType,
					OnlineArticle<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OnlineArticleType
					>
			implements
			TypedEdge.Type.OneToOne {

		protected ReferenceOnlineArticleType(RET raw) {
			super(UniprotGraph.this.Reference(), raw, UniprotGraph.this.OnlineArticle());
		}

		@Override
		public ReferenceOnlineArticleType value() {
			return graph().ReferenceOnlineArticle();
		}

		@Override
		public ReferenceOnlineArticle<I, RV, RVT, RE, RET> from(RE edge) {
			return new ReferenceOnlineArticle<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class SubcellularLocationParentType
			extends
			UniprotEdgeType<
					SubcellularLocation<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.SubcellularLocationType,
					SubcellularLocationParent<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.SubcellularLocationParentType,
					SubcellularLocation<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.SubcellularLocationType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected SubcellularLocationParentType(RET raw) {
			super(UniprotGraph.this.SubcellularLocation(), raw, UniprotGraph.this.SubcellularLocation());
		}

		@Override
		public SubcellularLocationParentType value() {
			return graph().SubcellularLocationParent();
		}

		@Override
		public SubcellularLocationParent<I, RV, RVT, RE, RET> from(RE edge) {
			return new SubcellularLocationParent<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class SubmissionDBType
			extends
			UniprotEdgeType<
					Submission<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.SubmissionType,
					SubmissionDB<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.SubmissionDBType,
					DB<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.DBType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected SubmissionDBType(RET raw) {
			super(UniprotGraph.this.Submission(), raw, UniprotGraph.this.DB());
		}

		@Override
		public SubmissionDBType value() {
			return graph().SubmissionDB();
		}

		@Override
		public SubmissionDB<I, RV, RVT, RE, RET> from(RE edge) {
			return new SubmissionDB<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class TaxonParentType
			extends
			UniprotEdgeType<
					Taxon<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.TaxonType,
					TaxonParent<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.TaxonParentType,
					Taxon<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.TaxonType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected TaxonParentType(RET raw) {
			super(UniprotGraph.this.Taxon(), raw, UniprotGraph.this.Taxon());
		}

		@Override
		public TaxonParentType value() {
			return graph().TaxonParent();
		}

		@Override
		public TaxonParent<I, RV, RVT, RE, RET> from(RE edge) {
			return new TaxonParent<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class ThesisInstituteType
			extends
			UniprotEdgeType<
					Thesis<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ThesisType,
					ThesisInstitute<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ThesisInstituteType,
					Institute<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.InstituteType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected ThesisInstituteType(RET raw) {
			super(UniprotGraph.this.Thesis(), raw, UniprotGraph.this.Institute());
		}

		@Override
		public ThesisInstituteType value() {
			return graph().ThesisInstitute();
		}

		@Override
		public ThesisInstitute<I, RV, RVT, RE, RET> from(RE edge) {
			return new ThesisInstitute<I, RV, RVT, RE, RET>(edge, this);
		}
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class UniprotVertexProperty<
			V extends UniprotVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotGraph<I, RV, RVT, RE, RET>.UniprotVertexType<V, VT>,
			P extends UniprotVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, UniprotGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		protected UniprotVertexProperty(VT type) {

			this.type = type;
		}

		private VT type;

		@Override
		public final VT elementType() {
			return type;
		}
	}

	public abstract static class UniprotVertex<
			V extends UniprotVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotGraph<I, RV, RVT, RE, RET>.UniprotVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, UniprotGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected UniprotVertex(RV vertex, VT type) {

			this.vertex = vertex;
			this.type = type;
		}

		@Override
		public UniprotGraph<I, RV, RVT, RE, RET> graph() {
			return type().graph();
		}

		@Override
		public RV raw() {
			return this.vertex;
		}

		@Override
		public VT type() {
			return type;
		}
	}

	abstract class UniprotVertexType<
			V extends UniprotVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotGraph<I, RV, RVT, RE, RET>.UniprotVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, UniprotGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected UniprotVertexType(RVT raw) {
			this.raw = raw;
		}

		@Override
		public final RVT raw() {
			return raw;
		}

		@Override
		public final UniprotGraph<I, RV, RVT, RE, RET> graph() {
			return UniprotGraph.this;
		}
	}

	public abstract static class UniprotEdge<
			S extends UniprotVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends UniprotGraph<I, RV, RVT, RE, RET>.UniprotVertexType<S, ST>,
			E extends UniprotEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends UniprotGraph<I, RV, RVT, RE, RET>.UniprotEdgeType<S, ST, E, ET, T, TT>,
			T extends UniprotVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends UniprotGraph<I, RV, RVT, RE, RET>.UniprotVertexType<T, TT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedEdge<
					S, ST, UniprotGraph<I, RV, RVT, RE, RET>,
					E, ET, UniprotGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, UniprotGraph<I, RV, RVT, RE, RET>
					> {

		private RE edge;
		private ET type;

		protected UniprotEdge(RE edge, ET type) {

			this.edge = edge;
			this.type = type;
		}

		@Override
		public UniprotGraph<I, RV, RVT, RE, RET> graph() {
			return type().graph();
		}

		@Override
		public RE raw() {
			return this.edge;
		}

		@Override
		public ET type() {
			return type;
		}
	}

	abstract class UniprotEdgeType<
			S extends UniprotVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends UniprotGraph<I, RV, RVT, RE, RET>.UniprotVertexType<S, ST>,
			E extends UniprotEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends UniprotGraph<I, RV, RVT, RE, RET>.UniprotEdgeType<S, ST, E, ET, T, TT>,
			T extends UniprotVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends UniprotGraph<I, RV, RVT, RE, RET>.UniprotVertexType<T, TT>
			>
			implements
			TypedEdge.Type<
					S, ST, UniprotGraph<I, RV, RVT, RE, RET>,
					E, ET, UniprotGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, UniprotGraph<I, RV, RVT, RE, RET>
					> {

		private RET raw;
		private ST srcT;
		private TT tgtT;

		protected UniprotEdgeType(ST srcT, RET raw, TT tgtT) {

			this.raw = raw;
			this.srcT = srcT;
			this.tgtT = tgtT;
		}

		@Override
		public final ST sourceType() {
			return srcT;
		}

		@Override
		public final TT targetType() {
			return tgtT;
		}

		@Override
		public final RET raw() {
			return raw;
		}

		@Override
		public final UniprotGraph<I, RV, RVT, RE, RET> graph() {
			return UniprotGraph.this;
		}
	}


}
