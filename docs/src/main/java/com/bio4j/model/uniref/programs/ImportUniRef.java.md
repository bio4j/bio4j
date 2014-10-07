
```java
package com.bio4j.model.uniref.programs;

import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.vertices.UniRef100Cluster;
import com.bio4j.model.uniref.vertices.UniRef50Cluster;
import com.bio4j.model.uniref.vertices.UniRef90Cluster;
import com.ohnosequences.typedGraphs.UntypedGraph;
import com.ohnosequences.xml.api.model.XMLElement;

import java.io.*;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.jdom2.Element;

/**
 * Imports uniref(100,90,50) clusters info into Bio4j
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class ImportUniRef<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

	public static final String ENTRY_TAG_NAME = "entry";

	private static final Logger logger = Logger.getLogger("ImportUniRef");
	private static FileHandler fh;

	protected abstract UniRefGraph<I,RV,RVT,RE,RET> config(String dbFolder);

	public void importUniRef(String[] args) {

		if (args.length != 4) {
			System.out.println("This program expects the following parameters: \n"
					+ "1. Uniref 100 xml filename \n"
					+ "2. Uniref 90 xml filename \n"
					+ "3. Uniref 50 xml filename \n"
					+ "4. Bio4j DB folder");
		} else {

			long initTime = System.nanoTime();

			File uniref100File = new File(args[0]);
			File uniref90File = new File(args[1]);
			File uniref50File = new File(args[2]);
			String dbFolder = args[3];

			BufferedWriter statsBuff = null;

			int uniref100EntryCounter =0, uniref90EntryCounter = 0, uniref50EntryCounter = 0;

			logger.log(Level.INFO, "creating manager...");

			UniRefGraph<I,RV,RVT,RE,RET> uniRefGraph = config(dbFolder);

			try {

				// This block configure the logger with handler and formatter
				fh = new FileHandler("ImportUniRefTitan.log", true);
				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);

				//---creating writer for stats file-----
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportUnirefStats.txt")));

				//------------------- UNIREF 100----------------------------
				System.out.println("Reading Uniref 100 file...");
				uniref100EntryCounter = importUnirefFile(uniRefGraph, uniref100File, 100);
				System.out.println("Done! :)");
				System.out.println("Reading Uniref 90 file...");
				uniref90EntryCounter = importUnirefFile(uniRefGraph, uniref90File, 90);
				System.out.println("Done! :)");
				System.out.println("Reading Uniref 50 file...");
				uniref50EntryCounter = importUnirefFile(uniRefGraph, uniref50File, 50);
				System.out.println("Done! :)");


			} catch (Exception ex) {
				logger.log(Level.SEVERE, ex.getMessage());
				StackTraceElement[] trace = ex.getStackTrace();
				for (StackTraceElement stackTraceElement : trace) {
					logger.log(Level.SEVERE, stackTraceElement.toString());
				}
			} finally {
				try {
					uniRefGraph.raw().commit();
					//closing logger file handler
					fh.close();
					//closing neo4j managers
					uniRefGraph.raw().shutdown();

					//-----------------writing stats file---------------------
					long elapsedTime = System.nanoTime() - initTime;
					long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
					long hours = elapsedSeconds / 3600;
					long minutes = (elapsedSeconds % 3600) / 60;
					long seconds = (elapsedSeconds % 3600) % 60;

					statsBuff.write("Statistics for program ImportUniRefTitan:\nInput files: " +
							"\nUniref 100 file: " + uniref100File.getName() +
							"\nUniref 90 file: " + uniref90File.getName() +
							"\nUniref 50 file: " + uniref50File.getName()
							+ "\nThe following number of entries was parsed:\n"
							+ "Uniref 100 --> " + uniref100EntryCounter + " entries\n"
							+ "Uniref 90 --> " + uniref90EntryCounter + " entries\n"
							+ "Uniref 50 --> " + uniref50EntryCounter + " entries\n"
							+ "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

					//---closing stats writer---
					statsBuff.close();

				} catch (Exception e) {
					logger.log(Level.SEVERE, e.getMessage());
					StackTraceElement[] trace = e.getStackTrace();
					for (StackTraceElement stackTraceElement : trace) {
						logger.log(Level.SEVERE, stackTraceElement.toString());
					}
				}

			}

			System.out.println("Program finished!! :D");


		}
	}

	private static String getRepresentantAccession(Element elem) {
		String result = null;
		Element dbReference = elem.getChild("dbReference");
		List<Element> properties = dbReference.getChildren("property");
		for (Element prop : properties) {
			if (prop.getAttributeValue("type").equals("UniProtKB accession")) {
				result = prop.getAttributeValue("value");
			}
		}

		return result;
	}

	private int importUnirefFile(UniRefGraph<I,RV,RVT,RE,RET> uniRefGraph,
	                                    File unirefFile,
	                                    int unirefClusterNumber) throws Exception {

		StringBuilder entryStBuilder = new StringBuilder();

		BufferedReader reader = new BufferedReader(new FileReader(unirefFile));
		String line;

		int entryCounter = 0;
		int limitForPrintingOut = 10000;

		while ((line = reader.readLine()) != null) {
			//----we reached a entry line-----
			if (line.trim().startsWith("<" + ENTRY_TAG_NAME)) {

				while (!line.trim().startsWith("</" + ENTRY_TAG_NAME + ">")) {
					entryStBuilder.append(line);
					line = reader.readLine();
				}
				//organism last line
				entryStBuilder.append(line);

				XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
				entryStBuilder.delete(0, entryStBuilder.length());

				String entryId = entryXMLElem.asJDomElement().getAttributeValue("id");
				String updatedDate = entryXMLElem.asJDomElement().getAttributeValue("updated");
				String name = entryXMLElem.asJDomElement().getChildText("name");

				if(unirefClusterNumber == 50){
					UniRef50Cluster<I,RV,RVT,RE,RET> cluster = uniRefGraph.UniRef50Cluster().from(uniRefGraph.raw().addVertex(null));
					cluster.set(uniRefGraph.UniRef50Cluster().id, entryId);
					cluster.set(uniRefGraph.UniRef50Cluster().updatedDate, updatedDate);
					cluster.set(uniRefGraph.UniRef50Cluster().name, name);
				}else if(unirefClusterNumber == 90){
					UniRef90Cluster<I,RV,RVT,RE,RET> cluster = uniRefGraph.UniRef90Cluster().from(uniRefGraph.raw().addVertex(null));
					cluster.set(uniRefGraph.UniRef90Cluster().id, entryId);
					cluster.set(uniRefGraph.UniRef90Cluster().updatedDate, updatedDate);
					cluster.set(uniRefGraph.UniRef90Cluster().name, name);
				}else if(unirefClusterNumber == 100){
					UniRef100Cluster<I,RV,RVT,RE,RET> cluster = uniRefGraph.UniRef100Cluster().from(uniRefGraph.raw().addVertex(null));
					cluster.set(uniRefGraph.UniRef100Cluster().id, entryId);
					cluster.set(uniRefGraph.UniRef100Cluster().updatedDate, updatedDate);
					cluster.set(uniRefGraph.UniRef100Cluster().name, name);
				}

			}

			entryCounter++;
			if ((entryCounter % limitForPrintingOut) == 0) {
				logger.log(Level.INFO, (entryCounter + " entries parsed!!"));
				uniRefGraph.raw().commit();
			}

		}
		reader.close();

		return entryCounter;
	}
}
```


------

### Index

+ src
  + main
    + java
      + com
        + bio4j
          + model
            + enums
            + enzymedb
              + [EnzymeDBGraph.java][main\java\com\bio4j\model\enzymedb\EnzymeDBGraph.java]
              + programs
                + [ImportEnzymeDB.java][main\java\com\bio4j\model\enzymedb\programs\ImportEnzymeDB.java]
              + vertices
                + [Enzyme.java][main\java\com\bio4j\model\enzymedb\vertices\Enzyme.java]
            + geninfo
              + [GenInfoGraph.java][main\java\com\bio4j\model\geninfo\GenInfoGraph.java]
              + vertices
                + [GenInfo.java][main\java\com\bio4j\model\geninfo\vertices\GenInfo.java]
            + go
              + edges
                + goSlims
                  + [GoSlim.java][main\java\com\bio4j\model\go\edges\goSlims\GoSlim.java]
                  + [PlantSlim.java][main\java\com\bio4j\model\go\edges\goSlims\PlantSlim.java]
                + [HasPartOf.java][main\java\com\bio4j\model\go\edges\HasPartOf.java]
                + [IsA.java][main\java\com\bio4j\model\go\edges\IsA.java]
                + [NegativelyRegulates.java][main\java\com\bio4j\model\go\edges\NegativelyRegulates.java]
                + [PartOf.java][main\java\com\bio4j\model\go\edges\PartOf.java]
                + [PositivelyRegulates.java][main\java\com\bio4j\model\go\edges\PositivelyRegulates.java]
                + [Regulates.java][main\java\com\bio4j\model\go\edges\Regulates.java]
                + [SubOntology.java][main\java\com\bio4j\model\go\edges\SubOntology.java]
              + [GoGraph.java][main\java\com\bio4j\model\go\GoGraph.java]
              + programs
                + [ImportGO.java][main\java\com\bio4j\model\go\programs\ImportGO.java]
              + vertices
                + [GoSlims.java][main\java\com\bio4j\model\go\vertices\GoSlims.java]
                + [GoTerm.java][main\java\com\bio4j\model\go\vertices\GoTerm.java]
                + [SubOntologies.java][main\java\com\bio4j\model\go\vertices\SubOntologies.java]
            + ncbiTaxonomy
              + edges
                + [NCBITaxonParent.java][main\java\com\bio4j\model\ncbiTaxonomy\edges\NCBITaxonParent.java]
              + [NCBITaxonomyGraph.java][main\java\com\bio4j\model\ncbiTaxonomy\NCBITaxonomyGraph.java]
              + programs
                + [ImportNCBITaxonomy.java][main\java\com\bio4j\model\ncbiTaxonomy\programs\ImportNCBITaxonomy.java]
              + vertices
                + [NCBITaxon.java][main\java\com\bio4j\model\ncbiTaxonomy\vertices\NCBITaxon.java]
            + ncbiTaxonomy_geninfo
              + edges
                + [GenInfoNCBITaxon.java][main\java\com\bio4j\model\ncbiTaxonomy_geninfo\edges\GenInfoNCBITaxon.java]
              + [NCBITaxonomyGenInfoGraph.java][main\java\com\bio4j\model\ncbiTaxonomy_geninfo\NCBITaxonomyGenInfoGraph.java]
            + uniprot
              + edges
                + [ArticleJournal.java][main\java\com\bio4j\model\uniprot\edges\ArticleJournal.java]
                + [ArticlePubmed.java][main\java\com\bio4j\model\uniprot\edges\ArticlePubmed.java]
                + [BookCity.java][main\java\com\bio4j\model\uniprot\edges\BookCity.java]
                + [BookEditor.java][main\java\com\bio4j\model\uniprot\edges\BookEditor.java]
                + [BookPublisher.java][main\java\com\bio4j\model\uniprot\edges\BookPublisher.java]
                + [InstituteCountry.java][main\java\com\bio4j\model\uniprot\edges\InstituteCountry.java]
                + [IsoformEventGenerator.java][main\java\com\bio4j\model\uniprot\edges\IsoformEventGenerator.java]
                + [IsoformProteinInteraction.java][main\java\com\bio4j\model\uniprot\edges\IsoformProteinInteraction.java]
                + [OnlineArticleOnlineJournal.java][main\java\com\bio4j\model\uniprot\edges\OnlineArticleOnlineJournal.java]
                + [OrganismTaxon.java][main\java\com\bio4j\model\uniprot\edges\OrganismTaxon.java]
                + [ProteinComment.java][main\java\com\bio4j\model\uniprot\edges\ProteinComment.java]
                + [ProteinDataset.java][main\java\com\bio4j\model\uniprot\edges\ProteinDataset.java]
                + [ProteinDisease.java][main\java\com\bio4j\model\uniprot\edges\ProteinDisease.java]
                + [ProteinEMBL.java][main\java\com\bio4j\model\uniprot\edges\ProteinEMBL.java]
                + [ProteinEnsembl.java][main\java\com\bio4j\model\uniprot\edges\ProteinEnsembl.java]
                + [ProteinFeature.java][main\java\com\bio4j\model\uniprot\edges\ProteinFeature.java]
                + [ProteinGeneLocation.java][main\java\com\bio4j\model\uniprot\edges\ProteinGeneLocation.java]
                + [ProteinInterpro.java][main\java\com\bio4j\model\uniprot\edges\ProteinInterpro.java]
                + [ProteinIsoformInteraction.java][main\java\com\bio4j\model\uniprot\edges\ProteinIsoformInteraction.java]
                + [ProteinKegg.java][main\java\com\bio4j\model\uniprot\edges\ProteinKegg.java]
                + [ProteinKeyword.java][main\java\com\bio4j\model\uniprot\edges\ProteinKeyword.java]
                + [ProteinOrganism.java][main\java\com\bio4j\model\uniprot\edges\ProteinOrganism.java]
                + [ProteinPfam.java][main\java\com\bio4j\model\uniprot\edges\ProteinPfam.java]
                + [ProteinPIR.java][main\java\com\bio4j\model\uniprot\edges\ProteinPIR.java]
                + [ProteinProteinInteraction.java][main\java\com\bio4j\model\uniprot\edges\ProteinProteinInteraction.java]
                + [ProteinReactomeTerm.java][main\java\com\bio4j\model\uniprot\edges\ProteinReactomeTerm.java]
                + [ProteinReference.java][main\java\com\bio4j\model\uniprot\edges\ProteinReference.java]
                + [ProteinRefSeq.java][main\java\com\bio4j\model\uniprot\edges\ProteinRefSeq.java]
                + [ProteinSequenceCaution.java][main\java\com\bio4j\model\uniprot\edges\ProteinSequenceCaution.java]
                + [ProteinSubcellularLocation.java][main\java\com\bio4j\model\uniprot\edges\ProteinSubcellularLocation.java]
                + [ProteinUniGene.java][main\java\com\bio4j\model\uniprot\edges\ProteinUniGene.java]
                + [ReferenceArticle.java][main\java\com\bio4j\model\uniprot\edges\ReferenceArticle.java]
                + [ReferenceAuthorConsortium.java][main\java\com\bio4j\model\uniprot\edges\ReferenceAuthorConsortium.java]
                + [ReferenceAuthorPerson.java][main\java\com\bio4j\model\uniprot\edges\ReferenceAuthorPerson.java]
                + [ReferenceBook.java][main\java\com\bio4j\model\uniprot\edges\ReferenceBook.java]
                + [ReferenceOnlineArticle.java][main\java\com\bio4j\model\uniprot\edges\ReferenceOnlineArticle.java]
                + [ReferencePatent.java][main\java\com\bio4j\model\uniprot\edges\ReferencePatent.java]
                + [ReferenceSubmission.java][main\java\com\bio4j\model\uniprot\edges\ReferenceSubmission.java]
                + [ReferenceThesis.java][main\java\com\bio4j\model\uniprot\edges\ReferenceThesis.java]
                + [ReferenceUnpublishedObservation.java][main\java\com\bio4j\model\uniprot\edges\ReferenceUnpublishedObservation.java]
                + [SubcellularLocationParent.java][main\java\com\bio4j\model\uniprot\edges\SubcellularLocationParent.java]
                + [SubmissionDB.java][main\java\com\bio4j\model\uniprot\edges\SubmissionDB.java]
                + [TaxonParent.java][main\java\com\bio4j\model\uniprot\edges\TaxonParent.java]
                + [ThesisInstitute.java][main\java\com\bio4j\model\uniprot\edges\ThesisInstitute.java]
              + programs
                + [ImportIsoformSequences.java][main\java\com\bio4j\model\uniprot\programs\ImportIsoformSequences.java]
                + [ImportProteinInteractions.java][main\java\com\bio4j\model\uniprot\programs\ImportProteinInteractions.java]
                + [ImportUniprot.java][main\java\com\bio4j\model\uniprot\programs\ImportUniprot.java]
              + [UniprotGraph.java][main\java\com\bio4j\model\uniprot\UniprotGraph.java]
              + vertices
                + [AlternativeProduct.java][main\java\com\bio4j\model\uniprot\vertices\AlternativeProduct.java]
                + [Article.java][main\java\com\bio4j\model\uniprot\vertices\Article.java]
                + [Book.java][main\java\com\bio4j\model\uniprot\vertices\Book.java]
                + [City.java][main\java\com\bio4j\model\uniprot\vertices\City.java]
                + [CommentType.java][main\java\com\bio4j\model\uniprot\vertices\CommentType.java]
                + [Consortium.java][main\java\com\bio4j\model\uniprot\vertices\Consortium.java]
                + [Country.java][main\java\com\bio4j\model\uniprot\vertices\Country.java]
                + [Dataset.java][main\java\com\bio4j\model\uniprot\vertices\Dataset.java]
                + [DB.java][main\java\com\bio4j\model\uniprot\vertices\DB.java]
                + [Disease.java][main\java\com\bio4j\model\uniprot\vertices\Disease.java]
                + [EMBL.java][main\java\com\bio4j\model\uniprot\vertices\EMBL.java]
                + [Ensembl.java][main\java\com\bio4j\model\uniprot\vertices\Ensembl.java]
                + [FeatureType.java][main\java\com\bio4j\model\uniprot\vertices\FeatureType.java]
                + [GeneLocation.java][main\java\com\bio4j\model\uniprot\vertices\GeneLocation.java]
                + [Institute.java][main\java\com\bio4j\model\uniprot\vertices\Institute.java]
                + [Interpro.java][main\java\com\bio4j\model\uniprot\vertices\Interpro.java]
                + [Isoform.java][main\java\com\bio4j\model\uniprot\vertices\Isoform.java]
                + [Journal.java][main\java\com\bio4j\model\uniprot\vertices\Journal.java]
                + [Kegg.java][main\java\com\bio4j\model\uniprot\vertices\Kegg.java]
                + [Keyword.java][main\java\com\bio4j\model\uniprot\vertices\Keyword.java]
                + [OnlineArticle.java][main\java\com\bio4j\model\uniprot\vertices\OnlineArticle.java]
                + [OnlineJournal.java][main\java\com\bio4j\model\uniprot\vertices\OnlineJournal.java]
                + [Organism.java][main\java\com\bio4j\model\uniprot\vertices\Organism.java]
                + [Patent.java][main\java\com\bio4j\model\uniprot\vertices\Patent.java]
                + [Person.java][main\java\com\bio4j\model\uniprot\vertices\Person.java]
                + [Pfam.java][main\java\com\bio4j\model\uniprot\vertices\Pfam.java]
                + [PIR.java][main\java\com\bio4j\model\uniprot\vertices\PIR.java]
                + [Protein.java][main\java\com\bio4j\model\uniprot\vertices\Protein.java]
                + [Publisher.java][main\java\com\bio4j\model\uniprot\vertices\Publisher.java]
                + [Pubmed.java][main\java\com\bio4j\model\uniprot\vertices\Pubmed.java]
                + [ReactomeTerm.java][main\java\com\bio4j\model\uniprot\vertices\ReactomeTerm.java]
                + [Reference.java][main\java\com\bio4j\model\uniprot\vertices\Reference.java]
                + [RefSeq.java][main\java\com\bio4j\model\uniprot\vertices\RefSeq.java]
                + [SequenceCaution.java][main\java\com\bio4j\model\uniprot\vertices\SequenceCaution.java]
                + [SubcellularLocation.java][main\java\com\bio4j\model\uniprot\vertices\SubcellularLocation.java]
                + [Submission.java][main\java\com\bio4j\model\uniprot\vertices\Submission.java]
                + [Taxon.java][main\java\com\bio4j\model\uniprot\vertices\Taxon.java]
                + [Thesis.java][main\java\com\bio4j\model\uniprot\vertices\Thesis.java]
                + [UniGene.java][main\java\com\bio4j\model\uniprot\vertices\UniGene.java]
                + [UnpublishedObservation.java][main\java\com\bio4j\model\uniprot\vertices\UnpublishedObservation.java]
            + uniprot_enzymedb
              + edges
                + [EnzymaticActivity.java][main\java\com\bio4j\model\uniprot_enzymedb\edges\EnzymaticActivity.java]
              + programs
                + [ImportUniprotEnzymeDB.java][main\java\com\bio4j\model\uniprot_enzymedb\programs\ImportUniprotEnzymeDB.java]
              + [UniprotEnzymeDBGraph.java][main\java\com\bio4j\model\uniprot_enzymedb\UniprotEnzymeDBGraph.java]
            + uniprot_go
              + edges
                + [GoAnnotation.java][main\java\com\bio4j\model\uniprot_go\edges\GoAnnotation.java]
              + programs
                + [ImportUniprotGo.java][main\java\com\bio4j\model\uniprot_go\programs\ImportUniprotGo.java]
              + [UniprotGoGraph.java][main\java\com\bio4j\model\uniprot_go\UniprotGoGraph.java]
            + uniprot_ncbiTaxonomy
              + edges
                + [ProteinNCBITaxon.java][main\java\com\bio4j\model\uniprot_ncbiTaxonomy\edges\ProteinNCBITaxon.java]
              + [UniprotNCBITaxonomyGraph.java][main\java\com\bio4j\model\uniprot_ncbiTaxonomy\UniprotNCBITaxonomyGraph.java]
            + uniprot_uniref
              + edges
                + [UniRef100Member.java][main\java\com\bio4j\model\uniprot_uniref\edges\UniRef100Member.java]
                + [UniRef100Representant.java][main\java\com\bio4j\model\uniprot_uniref\edges\UniRef100Representant.java]
                + [UniRef50Member.java][main\java\com\bio4j\model\uniprot_uniref\edges\UniRef50Member.java]
                + [UniRef50Representant.java][main\java\com\bio4j\model\uniprot_uniref\edges\UniRef50Representant.java]
                + [UniRef90Member.java][main\java\com\bio4j\model\uniprot_uniref\edges\UniRef90Member.java]
                + [UniRef90Representant.java][main\java\com\bio4j\model\uniprot_uniref\edges\UniRef90Representant.java]
              + programs
                + [ImportUniprotUniRef.java][main\java\com\bio4j\model\uniprot_uniref\programs\ImportUniprotUniRef.java]
              + [UniprotUniRefGraph.java][main\java\com\bio4j\model\uniprot_uniref\UniprotUniRefGraph.java]
            + uniref
              + programs
                + [ImportUniRef.java][main\java\com\bio4j\model\uniref\programs\ImportUniRef.java]
              + [UniRefGraph.java][main\java\com\bio4j\model\uniref\UniRefGraph.java]
              + vertices
                + [UniRef100Cluster.java][main\java\com\bio4j\model\uniref\vertices\UniRef100Cluster.java]
                + [UniRef50Cluster.java][main\java\com\bio4j\model\uniref\vertices\UniRef50Cluster.java]
                + [UniRef90Cluster.java][main\java\com\bio4j\model\uniref\vertices\UniRef90Cluster.java]
    + scala
  + test
    + java
    + scala

[main\java\com\bio4j\model\enzymedb\EnzymeDBGraph.java]: ..\..\enzymedb\EnzymeDBGraph.java.md
[main\java\com\bio4j\model\enzymedb\programs\ImportEnzymeDB.java]: ..\..\enzymedb\programs\ImportEnzymeDB.java.md
[main\java\com\bio4j\model\enzymedb\vertices\Enzyme.java]: ..\..\enzymedb\vertices\Enzyme.java.md
[main\java\com\bio4j\model\geninfo\GenInfoGraph.java]: ..\..\geninfo\GenInfoGraph.java.md
[main\java\com\bio4j\model\geninfo\vertices\GenInfo.java]: ..\..\geninfo\vertices\GenInfo.java.md
[main\java\com\bio4j\model\go\edges\goSlims\GoSlim.java]: ..\..\go\edges\goSlims\GoSlim.java.md
[main\java\com\bio4j\model\go\edges\goSlims\PlantSlim.java]: ..\..\go\edges\goSlims\PlantSlim.java.md
[main\java\com\bio4j\model\go\edges\HasPartOf.java]: ..\..\go\edges\HasPartOf.java.md
[main\java\com\bio4j\model\go\edges\IsA.java]: ..\..\go\edges\IsA.java.md
[main\java\com\bio4j\model\go\edges\NegativelyRegulates.java]: ..\..\go\edges\NegativelyRegulates.java.md
[main\java\com\bio4j\model\go\edges\PartOf.java]: ..\..\go\edges\PartOf.java.md
[main\java\com\bio4j\model\go\edges\PositivelyRegulates.java]: ..\..\go\edges\PositivelyRegulates.java.md
[main\java\com\bio4j\model\go\edges\Regulates.java]: ..\..\go\edges\Regulates.java.md
[main\java\com\bio4j\model\go\edges\SubOntology.java]: ..\..\go\edges\SubOntology.java.md
[main\java\com\bio4j\model\go\GoGraph.java]: ..\..\go\GoGraph.java.md
[main\java\com\bio4j\model\go\programs\ImportGO.java]: ..\..\go\programs\ImportGO.java.md
[main\java\com\bio4j\model\go\vertices\GoSlims.java]: ..\..\go\vertices\GoSlims.java.md
[main\java\com\bio4j\model\go\vertices\GoTerm.java]: ..\..\go\vertices\GoTerm.java.md
[main\java\com\bio4j\model\go\vertices\SubOntologies.java]: ..\..\go\vertices\SubOntologies.java.md
[main\java\com\bio4j\model\ncbiTaxonomy\edges\NCBITaxonParent.java]: ..\..\ncbiTaxonomy\edges\NCBITaxonParent.java.md
[main\java\com\bio4j\model\ncbiTaxonomy\NCBITaxonomyGraph.java]: ..\..\ncbiTaxonomy\NCBITaxonomyGraph.java.md
[main\java\com\bio4j\model\ncbiTaxonomy\programs\ImportNCBITaxonomy.java]: ..\..\ncbiTaxonomy\programs\ImportNCBITaxonomy.java.md
[main\java\com\bio4j\model\ncbiTaxonomy\vertices\NCBITaxon.java]: ..\..\ncbiTaxonomy\vertices\NCBITaxon.java.md
[main\java\com\bio4j\model\ncbiTaxonomy_geninfo\edges\GenInfoNCBITaxon.java]: ..\..\ncbiTaxonomy_geninfo\edges\GenInfoNCBITaxon.java.md
[main\java\com\bio4j\model\ncbiTaxonomy_geninfo\NCBITaxonomyGenInfoGraph.java]: ..\..\ncbiTaxonomy_geninfo\NCBITaxonomyGenInfoGraph.java.md
[main\java\com\bio4j\model\uniprot\edges\ArticleJournal.java]: ..\..\uniprot\edges\ArticleJournal.java.md
[main\java\com\bio4j\model\uniprot\edges\ArticlePubmed.java]: ..\..\uniprot\edges\ArticlePubmed.java.md
[main\java\com\bio4j\model\uniprot\edges\BookCity.java]: ..\..\uniprot\edges\BookCity.java.md
[main\java\com\bio4j\model\uniprot\edges\BookEditor.java]: ..\..\uniprot\edges\BookEditor.java.md
[main\java\com\bio4j\model\uniprot\edges\BookPublisher.java]: ..\..\uniprot\edges\BookPublisher.java.md
[main\java\com\bio4j\model\uniprot\edges\InstituteCountry.java]: ..\..\uniprot\edges\InstituteCountry.java.md
[main\java\com\bio4j\model\uniprot\edges\IsoformEventGenerator.java]: ..\..\uniprot\edges\IsoformEventGenerator.java.md
[main\java\com\bio4j\model\uniprot\edges\IsoformProteinInteraction.java]: ..\..\uniprot\edges\IsoformProteinInteraction.java.md
[main\java\com\bio4j\model\uniprot\edges\OnlineArticleOnlineJournal.java]: ..\..\uniprot\edges\OnlineArticleOnlineJournal.java.md
[main\java\com\bio4j\model\uniprot\edges\OrganismTaxon.java]: ..\..\uniprot\edges\OrganismTaxon.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinComment.java]: ..\..\uniprot\edges\ProteinComment.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinDataset.java]: ..\..\uniprot\edges\ProteinDataset.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinDisease.java]: ..\..\uniprot\edges\ProteinDisease.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinEMBL.java]: ..\..\uniprot\edges\ProteinEMBL.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinEnsembl.java]: ..\..\uniprot\edges\ProteinEnsembl.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinFeature.java]: ..\..\uniprot\edges\ProteinFeature.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinGeneLocation.java]: ..\..\uniprot\edges\ProteinGeneLocation.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinInterpro.java]: ..\..\uniprot\edges\ProteinInterpro.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinIsoformInteraction.java]: ..\..\uniprot\edges\ProteinIsoformInteraction.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinKegg.java]: ..\..\uniprot\edges\ProteinKegg.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinKeyword.java]: ..\..\uniprot\edges\ProteinKeyword.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinOrganism.java]: ..\..\uniprot\edges\ProteinOrganism.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinPfam.java]: ..\..\uniprot\edges\ProteinPfam.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinPIR.java]: ..\..\uniprot\edges\ProteinPIR.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinProteinInteraction.java]: ..\..\uniprot\edges\ProteinProteinInteraction.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinReactomeTerm.java]: ..\..\uniprot\edges\ProteinReactomeTerm.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinReference.java]: ..\..\uniprot\edges\ProteinReference.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinRefSeq.java]: ..\..\uniprot\edges\ProteinRefSeq.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinSequenceCaution.java]: ..\..\uniprot\edges\ProteinSequenceCaution.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinSubcellularLocation.java]: ..\..\uniprot\edges\ProteinSubcellularLocation.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinUniGene.java]: ..\..\uniprot\edges\ProteinUniGene.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceArticle.java]: ..\..\uniprot\edges\ReferenceArticle.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceAuthorConsortium.java]: ..\..\uniprot\edges\ReferenceAuthorConsortium.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceAuthorPerson.java]: ..\..\uniprot\edges\ReferenceAuthorPerson.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceBook.java]: ..\..\uniprot\edges\ReferenceBook.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceOnlineArticle.java]: ..\..\uniprot\edges\ReferenceOnlineArticle.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferencePatent.java]: ..\..\uniprot\edges\ReferencePatent.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceSubmission.java]: ..\..\uniprot\edges\ReferenceSubmission.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceThesis.java]: ..\..\uniprot\edges\ReferenceThesis.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceUnpublishedObservation.java]: ..\..\uniprot\edges\ReferenceUnpublishedObservation.java.md
[main\java\com\bio4j\model\uniprot\edges\SubcellularLocationParent.java]: ..\..\uniprot\edges\SubcellularLocationParent.java.md
[main\java\com\bio4j\model\uniprot\edges\SubmissionDB.java]: ..\..\uniprot\edges\SubmissionDB.java.md
[main\java\com\bio4j\model\uniprot\edges\TaxonParent.java]: ..\..\uniprot\edges\TaxonParent.java.md
[main\java\com\bio4j\model\uniprot\edges\ThesisInstitute.java]: ..\..\uniprot\edges\ThesisInstitute.java.md
[main\java\com\bio4j\model\uniprot\programs\ImportIsoformSequences.java]: ..\..\uniprot\programs\ImportIsoformSequences.java.md
[main\java\com\bio4j\model\uniprot\programs\ImportProteinInteractions.java]: ..\..\uniprot\programs\ImportProteinInteractions.java.md
[main\java\com\bio4j\model\uniprot\programs\ImportUniprot.java]: ..\..\uniprot\programs\ImportUniprot.java.md
[main\java\com\bio4j\model\uniprot\UniprotGraph.java]: ..\..\uniprot\UniprotGraph.java.md
[main\java\com\bio4j\model\uniprot\vertices\AlternativeProduct.java]: ..\..\uniprot\vertices\AlternativeProduct.java.md
[main\java\com\bio4j\model\uniprot\vertices\Article.java]: ..\..\uniprot\vertices\Article.java.md
[main\java\com\bio4j\model\uniprot\vertices\Book.java]: ..\..\uniprot\vertices\Book.java.md
[main\java\com\bio4j\model\uniprot\vertices\City.java]: ..\..\uniprot\vertices\City.java.md
[main\java\com\bio4j\model\uniprot\vertices\CommentType.java]: ..\..\uniprot\vertices\CommentType.java.md
[main\java\com\bio4j\model\uniprot\vertices\Consortium.java]: ..\..\uniprot\vertices\Consortium.java.md
[main\java\com\bio4j\model\uniprot\vertices\Country.java]: ..\..\uniprot\vertices\Country.java.md
[main\java\com\bio4j\model\uniprot\vertices\Dataset.java]: ..\..\uniprot\vertices\Dataset.java.md
[main\java\com\bio4j\model\uniprot\vertices\DB.java]: ..\..\uniprot\vertices\DB.java.md
[main\java\com\bio4j\model\uniprot\vertices\Disease.java]: ..\..\uniprot\vertices\Disease.java.md
[main\java\com\bio4j\model\uniprot\vertices\EMBL.java]: ..\..\uniprot\vertices\EMBL.java.md
[main\java\com\bio4j\model\uniprot\vertices\Ensembl.java]: ..\..\uniprot\vertices\Ensembl.java.md
[main\java\com\bio4j\model\uniprot\vertices\FeatureType.java]: ..\..\uniprot\vertices\FeatureType.java.md
[main\java\com\bio4j\model\uniprot\vertices\GeneLocation.java]: ..\..\uniprot\vertices\GeneLocation.java.md
[main\java\com\bio4j\model\uniprot\vertices\Institute.java]: ..\..\uniprot\vertices\Institute.java.md
[main\java\com\bio4j\model\uniprot\vertices\Interpro.java]: ..\..\uniprot\vertices\Interpro.java.md
[main\java\com\bio4j\model\uniprot\vertices\Isoform.java]: ..\..\uniprot\vertices\Isoform.java.md
[main\java\com\bio4j\model\uniprot\vertices\Journal.java]: ..\..\uniprot\vertices\Journal.java.md
[main\java\com\bio4j\model\uniprot\vertices\Kegg.java]: ..\..\uniprot\vertices\Kegg.java.md
[main\java\com\bio4j\model\uniprot\vertices\Keyword.java]: ..\..\uniprot\vertices\Keyword.java.md
[main\java\com\bio4j\model\uniprot\vertices\OnlineArticle.java]: ..\..\uniprot\vertices\OnlineArticle.java.md
[main\java\com\bio4j\model\uniprot\vertices\OnlineJournal.java]: ..\..\uniprot\vertices\OnlineJournal.java.md
[main\java\com\bio4j\model\uniprot\vertices\Organism.java]: ..\..\uniprot\vertices\Organism.java.md
[main\java\com\bio4j\model\uniprot\vertices\Patent.java]: ..\..\uniprot\vertices\Patent.java.md
[main\java\com\bio4j\model\uniprot\vertices\Person.java]: ..\..\uniprot\vertices\Person.java.md
[main\java\com\bio4j\model\uniprot\vertices\Pfam.java]: ..\..\uniprot\vertices\Pfam.java.md
[main\java\com\bio4j\model\uniprot\vertices\PIR.java]: ..\..\uniprot\vertices\PIR.java.md
[main\java\com\bio4j\model\uniprot\vertices\Protein.java]: ..\..\uniprot\vertices\Protein.java.md
[main\java\com\bio4j\model\uniprot\vertices\Publisher.java]: ..\..\uniprot\vertices\Publisher.java.md
[main\java\com\bio4j\model\uniprot\vertices\Pubmed.java]: ..\..\uniprot\vertices\Pubmed.java.md
[main\java\com\bio4j\model\uniprot\vertices\ReactomeTerm.java]: ..\..\uniprot\vertices\ReactomeTerm.java.md
[main\java\com\bio4j\model\uniprot\vertices\Reference.java]: ..\..\uniprot\vertices\Reference.java.md
[main\java\com\bio4j\model\uniprot\vertices\RefSeq.java]: ..\..\uniprot\vertices\RefSeq.java.md
[main\java\com\bio4j\model\uniprot\vertices\SequenceCaution.java]: ..\..\uniprot\vertices\SequenceCaution.java.md
[main\java\com\bio4j\model\uniprot\vertices\SubcellularLocation.java]: ..\..\uniprot\vertices\SubcellularLocation.java.md
[main\java\com\bio4j\model\uniprot\vertices\Submission.java]: ..\..\uniprot\vertices\Submission.java.md
[main\java\com\bio4j\model\uniprot\vertices\Taxon.java]: ..\..\uniprot\vertices\Taxon.java.md
[main\java\com\bio4j\model\uniprot\vertices\Thesis.java]: ..\..\uniprot\vertices\Thesis.java.md
[main\java\com\bio4j\model\uniprot\vertices\UniGene.java]: ..\..\uniprot\vertices\UniGene.java.md
[main\java\com\bio4j\model\uniprot\vertices\UnpublishedObservation.java]: ..\..\uniprot\vertices\UnpublishedObservation.java.md
[main\java\com\bio4j\model\uniprot_enzymedb\edges\EnzymaticActivity.java]: ..\..\uniprot_enzymedb\edges\EnzymaticActivity.java.md
[main\java\com\bio4j\model\uniprot_enzymedb\programs\ImportUniprotEnzymeDB.java]: ..\..\uniprot_enzymedb\programs\ImportUniprotEnzymeDB.java.md
[main\java\com\bio4j\model\uniprot_enzymedb\UniprotEnzymeDBGraph.java]: ..\..\uniprot_enzymedb\UniprotEnzymeDBGraph.java.md
[main\java\com\bio4j\model\uniprot_go\edges\GoAnnotation.java]: ..\..\uniprot_go\edges\GoAnnotation.java.md
[main\java\com\bio4j\model\uniprot_go\programs\ImportUniprotGo.java]: ..\..\uniprot_go\programs\ImportUniprotGo.java.md
[main\java\com\bio4j\model\uniprot_go\UniprotGoGraph.java]: ..\..\uniprot_go\UniprotGoGraph.java.md
[main\java\com\bio4j\model\uniprot_ncbiTaxonomy\edges\ProteinNCBITaxon.java]: ..\..\uniprot_ncbiTaxonomy\edges\ProteinNCBITaxon.java.md
[main\java\com\bio4j\model\uniprot_ncbiTaxonomy\UniprotNCBITaxonomyGraph.java]: ..\..\uniprot_ncbiTaxonomy\UniprotNCBITaxonomyGraph.java.md
[main\java\com\bio4j\model\uniprot_uniref\edges\UniRef100Member.java]: ..\..\uniprot_uniref\edges\UniRef100Member.java.md
[main\java\com\bio4j\model\uniprot_uniref\edges\UniRef100Representant.java]: ..\..\uniprot_uniref\edges\UniRef100Representant.java.md
[main\java\com\bio4j\model\uniprot_uniref\edges\UniRef50Member.java]: ..\..\uniprot_uniref\edges\UniRef50Member.java.md
[main\java\com\bio4j\model\uniprot_uniref\edges\UniRef50Representant.java]: ..\..\uniprot_uniref\edges\UniRef50Representant.java.md
[main\java\com\bio4j\model\uniprot_uniref\edges\UniRef90Member.java]: ..\..\uniprot_uniref\edges\UniRef90Member.java.md
[main\java\com\bio4j\model\uniprot_uniref\edges\UniRef90Representant.java]: ..\..\uniprot_uniref\edges\UniRef90Representant.java.md
[main\java\com\bio4j\model\uniprot_uniref\programs\ImportUniprotUniRef.java]: ..\..\uniprot_uniref\programs\ImportUniprotUniRef.java.md
[main\java\com\bio4j\model\uniprot_uniref\UniprotUniRefGraph.java]: ..\..\uniprot_uniref\UniprotUniRefGraph.java.md
[main\java\com\bio4j\model\uniref\programs\ImportUniRef.java]: ImportUniRef.java.md
[main\java\com\bio4j\model\uniref\UniRefGraph.java]: ..\UniRefGraph.java.md
[main\java\com\bio4j\model\uniref\vertices\UniRef100Cluster.java]: ..\vertices\UniRef100Cluster.java.md
[main\java\com\bio4j\model\uniref\vertices\UniRef50Cluster.java]: ..\vertices\UniRef50Cluster.java.md
[main\java\com\bio4j\model\uniref\vertices\UniRef90Cluster.java]: ..\vertices\UniRef90Cluster.java.md