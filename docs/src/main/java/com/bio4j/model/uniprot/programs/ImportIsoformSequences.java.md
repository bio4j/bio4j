
```java
package com.bio4j.model.uniprot.programs;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.Isoform;
import com.bio4j.angulillos.UntypedGraph;

import java.io.*;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * Imports the sequences of isoforms into Bio4j
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class ImportIsoformSequences<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

	private static final Logger logger = Logger.getLogger("ImportIsoformSequences");
	private static FileHandler fh;

	protected abstract UniprotGraph<I,RV,RVT,RE,RET> config(String dbFolder);

	public void importIsoformSequences(String[] args) {

		if (args.length != 2) {
			System.out.println("This program expects two parameters: \n"
					+ "1. Fasta file including all isoforms \n"
					+ "2. Bio4j DB folder");
		} else {

			long initTime = System.nanoTime();

			File inFile = new File(args[0]);
			String dbFolder = args[1];

			String isoformIdSt = null;

			BufferedWriter statsBuff = null;

			UniprotGraph<I,RV,RVT,RE,RET> uniprotGraph = config(dbFolder);

			int isoformCounter = 0;

			try {

				// This block configures the logger with handler and formatter
				fh = new FileHandler("ImporIsoformSequences.log", true);
				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);

				//---creating writer for stats file-----
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportIsoformSequencesStats.txt")));

				BufferedReader reader = new BufferedReader(new FileReader(inFile));
				String line;
				StringBuilder seqStBuilder = new StringBuilder();

				logger.log(Level.INFO, "updating isoforms data....");

				while ((line = reader.readLine()) != null) {
					if (line.trim().startsWith(">")) {

						String[] columns = line.split("\\|");
						isoformIdSt = columns[1];
						String isoformNameSt = columns[2].split("OS=")[0].trim();

						//sequence read line
						line = reader.readLine();

						while (!line.trim().startsWith(">")) {
							seqStBuilder.append(line);
							line = reader.readLine();
							if (line == null) {
								break;
							}
						}

						String sequence = seqStBuilder.toString();
						seqStBuilder.delete(0, seqStBuilder.length());

						Optional<Isoform<I,RV,RVT,RE,RET>> isoformOptional = uniprotGraph.isoformIdIndex().getVertex(isoformIdSt);

						if(isoformOptional.isPresent()){
							Isoform<I,RV,RVT,RE,RET> isoform = isoformOptional.get();
							isoform.set(uniprotGraph.Isoform().sequence, sequence);
							isoform.set(uniprotGraph.Isoform().name, isoformNameSt);
							System.out.println("Setting name for: " + isoform.id());
							isoformCounter++;
						}

					}

					if (line == null) {
						break;
					}

				}

				reader.close();

				logger.log(Level.INFO, "Done! :)");

			} catch (Exception e) {
				logger.log(Level.INFO, ("Exception retrieving isoform " + isoformIdSt));
				logger.log(Level.SEVERE, e.getMessage());
				StackTraceElement[] trace = e.getStackTrace();
				for (StackTraceElement stackTraceElement : trace) {
					logger.log(Level.SEVERE, stackTraceElement.toString());
				}
			} finally {

				uniprotGraph.raw().commit();

				//closing logger file handler
				fh.close();
				logger.log(Level.INFO, "Closing up graph....");
				// shutdown, makes sure all changes are written to disk
				uniprotGraph.raw().shutdown();

				try {

					//-----------------writing stats file---------------------
					long elapsedTime = System.nanoTime() - initTime;
					long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
					long hours = elapsedSeconds / 3600;
					long minutes = (elapsedSeconds % 3600) / 60;
					long seconds = (elapsedSeconds % 3600) % 60;

					statsBuff.write("Statistics for program ImportIsoformSequences:\nInput file: " + inFile.getName()
							+ "\nThere were " + isoformCounter + " isoforms updated.\n"
							+ "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

					//---closing stats writer---
					statsBuff.close();

				}catch(Exception e){
					e.printStackTrace();
				}

			}
		}
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
              + [EnzymeDBGraph.java][main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]
              + programs
                + [ImportEnzymeDB.java][main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]
              + vertices
                + [Enzyme.java][main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]
            + geninfo
              + [GenInfoGraph.java][main/java/com/bio4j/model/geninfo/GenInfoGraph.java]
              + vertices
                + [GenInfo.java][main/java/com/bio4j/model/geninfo/vertices/GenInfo.java]
            + go
              + edges
                + goSlims
                  + [GoSlim.java][main/java/com/bio4j/model/go/edges/goSlims/GoSlim.java]
                  + [PlantSlim.java][main/java/com/bio4j/model/go/edges/goSlims/PlantSlim.java]
                + [HasPartOf.java][main/java/com/bio4j/model/go/edges/HasPartOf.java]
                + [IsA.java][main/java/com/bio4j/model/go/edges/IsA.java]
                + [NegativelyRegulates.java][main/java/com/bio4j/model/go/edges/NegativelyRegulates.java]
                + [PartOf.java][main/java/com/bio4j/model/go/edges/PartOf.java]
                + [PositivelyRegulates.java][main/java/com/bio4j/model/go/edges/PositivelyRegulates.java]
                + [Regulates.java][main/java/com/bio4j/model/go/edges/Regulates.java]
                + [SubOntology.java][main/java/com/bio4j/model/go/edges/SubOntology.java]
              + [GoGraph.java][main/java/com/bio4j/model/go/GoGraph.java]
              + programs
                + [ImportGO.java][main/java/com/bio4j/model/go/programs/ImportGO.java]
              + vertices
                + [GoSlims.java][main/java/com/bio4j/model/go/vertices/GoSlims.java]
                + [GoTerm.java][main/java/com/bio4j/model/go/vertices/GoTerm.java]
                + [SubOntologies.java][main/java/com/bio4j/model/go/vertices/SubOntologies.java]
            + ncbiTaxonomy
              + edges
                + [NCBITaxonParent.java][main/java/com/bio4j/model/ncbiTaxonomy/edges/NCBITaxonParent.java]
              + [NCBITaxonomyGraph.java][main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]
              + programs
                + [ImportNCBITaxonomy.java][main/java/com/bio4j/model/ncbiTaxonomy/programs/ImportNCBITaxonomy.java]
              + vertices
                + [NCBITaxon.java][main/java/com/bio4j/model/ncbiTaxonomy/vertices/NCBITaxon.java]
            + ncbiTaxonomy_geninfo
              + edges
                + [GenInfoNCBITaxon.java][main/java/com/bio4j/model/ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java]
              + [NCBITaxonomyGenInfoGraph.java][main/java/com/bio4j/model/ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java]
              + programs
                + [ImportGenInfoNCBITaxonIndex.java][main/java/com/bio4j/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java]
            + uniprot
              + edges
                + [ArticleJournal.java][main/java/com/bio4j/model/uniprot/edges/ArticleJournal.java]
                + [ArticlePubmed.java][main/java/com/bio4j/model/uniprot/edges/ArticlePubmed.java]
                + [BookCity.java][main/java/com/bio4j/model/uniprot/edges/BookCity.java]
                + [BookEditor.java][main/java/com/bio4j/model/uniprot/edges/BookEditor.java]
                + [BookPublisher.java][main/java/com/bio4j/model/uniprot/edges/BookPublisher.java]
                + [InstituteCountry.java][main/java/com/bio4j/model/uniprot/edges/InstituteCountry.java]
                + [IsoformEventGenerator.java][main/java/com/bio4j/model/uniprot/edges/IsoformEventGenerator.java]
                + [IsoformProteinInteraction.java][main/java/com/bio4j/model/uniprot/edges/IsoformProteinInteraction.java]
                + [OnlineArticleOnlineJournal.java][main/java/com/bio4j/model/uniprot/edges/OnlineArticleOnlineJournal.java]
                + [OrganismTaxon.java][main/java/com/bio4j/model/uniprot/edges/OrganismTaxon.java]
                + [ProteinComment.java][main/java/com/bio4j/model/uniprot/edges/ProteinComment.java]
                + [ProteinDataset.java][main/java/com/bio4j/model/uniprot/edges/ProteinDataset.java]
                + [ProteinDisease.java][main/java/com/bio4j/model/uniprot/edges/ProteinDisease.java]
                + [ProteinEMBL.java][main/java/com/bio4j/model/uniprot/edges/ProteinEMBL.java]
                + [ProteinEnsembl.java][main/java/com/bio4j/model/uniprot/edges/ProteinEnsembl.java]
                + [ProteinFeature.java][main/java/com/bio4j/model/uniprot/edges/ProteinFeature.java]
                + [ProteinGeneLocation.java][main/java/com/bio4j/model/uniprot/edges/ProteinGeneLocation.java]
                + [ProteinInterpro.java][main/java/com/bio4j/model/uniprot/edges/ProteinInterpro.java]
                + [ProteinIsoform.java][main/java/com/bio4j/model/uniprot/edges/ProteinIsoform.java]
                + [ProteinIsoformInteraction.java][main/java/com/bio4j/model/uniprot/edges/ProteinIsoformInteraction.java]
                + [ProteinKegg.java][main/java/com/bio4j/model/uniprot/edges/ProteinKegg.java]
                + [ProteinKeyword.java][main/java/com/bio4j/model/uniprot/edges/ProteinKeyword.java]
                + [ProteinOrganism.java][main/java/com/bio4j/model/uniprot/edges/ProteinOrganism.java]
                + [ProteinPfam.java][main/java/com/bio4j/model/uniprot/edges/ProteinPfam.java]
                + [ProteinPIR.java][main/java/com/bio4j/model/uniprot/edges/ProteinPIR.java]
                + [ProteinProteinInteraction.java][main/java/com/bio4j/model/uniprot/edges/ProteinProteinInteraction.java]
                + [ProteinReactomeTerm.java][main/java/com/bio4j/model/uniprot/edges/ProteinReactomeTerm.java]
                + [ProteinReference.java][main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]
                + [ProteinRefSeq.java][main/java/com/bio4j/model/uniprot/edges/ProteinRefSeq.java]
                + [ProteinSequenceCaution.java][main/java/com/bio4j/model/uniprot/edges/ProteinSequenceCaution.java]
                + [ProteinSubcellularLocation.java][main/java/com/bio4j/model/uniprot/edges/ProteinSubcellularLocation.java]
                + [ProteinUniGene.java][main/java/com/bio4j/model/uniprot/edges/ProteinUniGene.java]
                + [ReferenceArticle.java][main/java/com/bio4j/model/uniprot/edges/ReferenceArticle.java]
                + [ReferenceAuthorConsortium.java][main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorConsortium.java]
                + [ReferenceAuthorPerson.java][main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorPerson.java]
                + [ReferenceBook.java][main/java/com/bio4j/model/uniprot/edges/ReferenceBook.java]
                + [ReferenceOnlineArticle.java][main/java/com/bio4j/model/uniprot/edges/ReferenceOnlineArticle.java]
                + [ReferencePatent.java][main/java/com/bio4j/model/uniprot/edges/ReferencePatent.java]
                + [ReferenceSubmission.java][main/java/com/bio4j/model/uniprot/edges/ReferenceSubmission.java]
                + [ReferenceThesis.java][main/java/com/bio4j/model/uniprot/edges/ReferenceThesis.java]
                + [ReferenceUnpublishedObservation.java][main/java/com/bio4j/model/uniprot/edges/ReferenceUnpublishedObservation.java]
                + [SubcellularLocationParent.java][main/java/com/bio4j/model/uniprot/edges/SubcellularLocationParent.java]
                + [SubmissionDB.java][main/java/com/bio4j/model/uniprot/edges/SubmissionDB.java]
                + [TaxonParent.java][main/java/com/bio4j/model/uniprot/edges/TaxonParent.java]
                + [ThesisInstitute.java][main/java/com/bio4j/model/uniprot/edges/ThesisInstitute.java]
              + programs
                + [ImportIsoformSequences.java][main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]
                + [ImportProteinInteractions.java][main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]
                + [ImportUniprot.java][main/java/com/bio4j/model/uniprot/programs/ImportUniprot.java]
              + [UniprotGraph.java][main/java/com/bio4j/model/uniprot/UniprotGraph.java]
              + vertices
                + [AlternativeProduct.java][main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]
                + [Article.java][main/java/com/bio4j/model/uniprot/vertices/Article.java]
                + [Book.java][main/java/com/bio4j/model/uniprot/vertices/Book.java]
                + [City.java][main/java/com/bio4j/model/uniprot/vertices/City.java]
                + [CommentType.java][main/java/com/bio4j/model/uniprot/vertices/CommentType.java]
                + [Consortium.java][main/java/com/bio4j/model/uniprot/vertices/Consortium.java]
                + [Country.java][main/java/com/bio4j/model/uniprot/vertices/Country.java]
                + [Dataset.java][main/java/com/bio4j/model/uniprot/vertices/Dataset.java]
                + [DB.java][main/java/com/bio4j/model/uniprot/vertices/DB.java]
                + [Disease.java][main/java/com/bio4j/model/uniprot/vertices/Disease.java]
                + [EMBL.java][main/java/com/bio4j/model/uniprot/vertices/EMBL.java]
                + [Ensembl.java][main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]
                + [FeatureType.java][main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]
                + [GeneLocation.java][main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]
                + [Institute.java][main/java/com/bio4j/model/uniprot/vertices/Institute.java]
                + [Interpro.java][main/java/com/bio4j/model/uniprot/vertices/Interpro.java]
                + [Isoform.java][main/java/com/bio4j/model/uniprot/vertices/Isoform.java]
                + [Journal.java][main/java/com/bio4j/model/uniprot/vertices/Journal.java]
                + [Kegg.java][main/java/com/bio4j/model/uniprot/vertices/Kegg.java]
                + [Keyword.java][main/java/com/bio4j/model/uniprot/vertices/Keyword.java]
                + [OnlineArticle.java][main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]
                + [OnlineJournal.java][main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]
                + [Organism.java][main/java/com/bio4j/model/uniprot/vertices/Organism.java]
                + [Patent.java][main/java/com/bio4j/model/uniprot/vertices/Patent.java]
                + [Person.java][main/java/com/bio4j/model/uniprot/vertices/Person.java]
                + [Pfam.java][main/java/com/bio4j/model/uniprot/vertices/Pfam.java]
                + [PIR.java][main/java/com/bio4j/model/uniprot/vertices/PIR.java]
                + [Protein.java][main/java/com/bio4j/model/uniprot/vertices/Protein.java]
                + [Publisher.java][main/java/com/bio4j/model/uniprot/vertices/Publisher.java]
                + [Pubmed.java][main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]
                + [ReactomeTerm.java][main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]
                + [Reference.java][main/java/com/bio4j/model/uniprot/vertices/Reference.java]
                + [RefSeq.java][main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]
                + [SequenceCaution.java][main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]
                + [SubcellularLocation.java][main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]
                + [Submission.java][main/java/com/bio4j/model/uniprot/vertices/Submission.java]
                + [Taxon.java][main/java/com/bio4j/model/uniprot/vertices/Taxon.java]
                + [Thesis.java][main/java/com/bio4j/model/uniprot/vertices/Thesis.java]
                + [UniGene.java][main/java/com/bio4j/model/uniprot/vertices/UniGene.java]
                + [UnpublishedObservation.java][main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]
            + uniprot_enzymedb
              + edges
                + [EnzymaticActivity.java][main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]
              + programs
                + [ImportUniprotEnzymeDB.java][main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniprotEnzymeDB.java]
              + [UniprotEnzymeDBGraph.java][main/java/com/bio4j/model/uniprot_enzymedb/UniprotEnzymeDBGraph.java]
            + uniprot_go
              + edges
                + [GoAnnotation.java][main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]
              + programs
                + [ImportUniprotGo.java][main/java/com/bio4j/model/uniprot_go/programs/ImportUniprotGo.java]
              + tests
                + [ImportUniprotGoTest.java][main/java/com/bio4j/model/uniprot_go/tests/ImportUniprotGoTest.java]
              + [UniprotGoGraph.java][main/java/com/bio4j/model/uniprot_go/UniprotGoGraph.java]
            + uniprot_ncbiTaxonomy
              + edges
                + [ProteinNCBITaxon.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]
              + programs
                + [ImportUniprotNCBITaxonomy.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/programs/ImportUniprotNCBITaxonomy.java]
              + [UniprotNCBITaxonomyGraph.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniprotNCBITaxonomyGraph.java]
            + uniprot_uniref
              + edges
                + [UniRef100Member.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]
                + [UniRef100Representant.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]
                + [UniRef50Member.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]
                + [UniRef50Representant.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]
                + [UniRef90Member.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]
                + [UniRef90Representant.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]
              + programs
                + [ImportUniprotUniRef.java][main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniprotUniRef.java]
              + [UniprotUniRefGraph.java][main/java/com/bio4j/model/uniprot_uniref/UniprotUniRefGraph.java]
            + uniref
              + programs
                + [ImportUniRef.java][main/java/com/bio4j/model/uniref/programs/ImportUniRef.java]
              + [UniRefGraph.java][main/java/com/bio4j/model/uniref/UniRefGraph.java]
              + vertices
                + [UniRef100Cluster.java][main/java/com/bio4j/model/uniref/vertices/UniRef100Cluster.java]
                + [UniRef50Cluster.java][main/java/com/bio4j/model/uniref/vertices/UniRef50Cluster.java]
                + [UniRef90Cluster.java][main/java/com/bio4j/model/uniref/vertices/UniRef90Cluster.java]

[main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]: ../../enzymedb/EnzymeDBGraph.java.md
[main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]: ../../enzymedb/programs/ImportEnzymeDB.java.md
[main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]: ../../enzymedb/vertices/Enzyme.java.md
[main/java/com/bio4j/model/geninfo/GenInfoGraph.java]: ../../geninfo/GenInfoGraph.java.md
[main/java/com/bio4j/model/geninfo/vertices/GenInfo.java]: ../../geninfo/vertices/GenInfo.java.md
[main/java/com/bio4j/model/go/edges/goSlims/GoSlim.java]: ../../go/edges/goSlims/GoSlim.java.md
[main/java/com/bio4j/model/go/edges/goSlims/PlantSlim.java]: ../../go/edges/goSlims/PlantSlim.java.md
[main/java/com/bio4j/model/go/edges/HasPartOf.java]: ../../go/edges/HasPartOf.java.md
[main/java/com/bio4j/model/go/edges/IsA.java]: ../../go/edges/IsA.java.md
[main/java/com/bio4j/model/go/edges/NegativelyRegulates.java]: ../../go/edges/NegativelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/PartOf.java]: ../../go/edges/PartOf.java.md
[main/java/com/bio4j/model/go/edges/PositivelyRegulates.java]: ../../go/edges/PositivelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/Regulates.java]: ../../go/edges/Regulates.java.md
[main/java/com/bio4j/model/go/edges/SubOntology.java]: ../../go/edges/SubOntology.java.md
[main/java/com/bio4j/model/go/GoGraph.java]: ../../go/GoGraph.java.md
[main/java/com/bio4j/model/go/programs/ImportGO.java]: ../../go/programs/ImportGO.java.md
[main/java/com/bio4j/model/go/vertices/GoSlims.java]: ../../go/vertices/GoSlims.java.md
[main/java/com/bio4j/model/go/vertices/GoTerm.java]: ../../go/vertices/GoTerm.java.md
[main/java/com/bio4j/model/go/vertices/SubOntologies.java]: ../../go/vertices/SubOntologies.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/edges/NCBITaxonParent.java]: ../../ncbiTaxonomy/edges/NCBITaxonParent.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]: ../../ncbiTaxonomy/NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/programs/ImportNCBITaxonomy.java]: ../../ncbiTaxonomy/programs/ImportNCBITaxonomy.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/vertices/NCBITaxon.java]: ../../ncbiTaxonomy/vertices/NCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java]: ../../ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java]: ../../ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java]: ../../ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticleJournal.java]: ../edges/ArticleJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticlePubmed.java]: ../edges/ArticlePubmed.java.md
[main/java/com/bio4j/model/uniprot/edges/BookCity.java]: ../edges/BookCity.java.md
[main/java/com/bio4j/model/uniprot/edges/BookEditor.java]: ../edges/BookEditor.java.md
[main/java/com/bio4j/model/uniprot/edges/BookPublisher.java]: ../edges/BookPublisher.java.md
[main/java/com/bio4j/model/uniprot/edges/InstituteCountry.java]: ../edges/InstituteCountry.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformEventGenerator.java]: ../edges/IsoformEventGenerator.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformProteinInteraction.java]: ../edges/IsoformProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/OnlineArticleOnlineJournal.java]: ../edges/OnlineArticleOnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/OrganismTaxon.java]: ../edges/OrganismTaxon.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinComment.java]: ../edges/ProteinComment.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDataset.java]: ../edges/ProteinDataset.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDisease.java]: ../edges/ProteinDisease.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEMBL.java]: ../edges/ProteinEMBL.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEnsembl.java]: ../edges/ProteinEnsembl.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinFeature.java]: ../edges/ProteinFeature.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneLocation.java]: ../edges/ProteinGeneLocation.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinInterpro.java]: ../edges/ProteinInterpro.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoform.java]: ../edges/ProteinIsoform.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoformInteraction.java]: ../edges/ProteinIsoformInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKegg.java]: ../edges/ProteinKegg.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKeyword.java]: ../edges/ProteinKeyword.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinOrganism.java]: ../edges/ProteinOrganism.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPfam.java]: ../edges/ProteinPfam.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPIR.java]: ../edges/ProteinPIR.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinProteinInteraction.java]: ../edges/ProteinProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReactomeTerm.java]: ../edges/ProteinReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]: ../edges/ProteinReference.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinRefSeq.java]: ../edges/ProteinRefSeq.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSequenceCaution.java]: ../edges/ProteinSequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSubcellularLocation.java]: ../edges/ProteinSubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinUniGene.java]: ../edges/ProteinUniGene.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceArticle.java]: ../edges/ReferenceArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorConsortium.java]: ../edges/ReferenceAuthorConsortium.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorPerson.java]: ../edges/ReferenceAuthorPerson.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceBook.java]: ../edges/ReferenceBook.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceOnlineArticle.java]: ../edges/ReferenceOnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferencePatent.java]: ../edges/ReferencePatent.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceSubmission.java]: ../edges/ReferenceSubmission.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceThesis.java]: ../edges/ReferenceThesis.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceUnpublishedObservation.java]: ../edges/ReferenceUnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/edges/SubcellularLocationParent.java]: ../edges/SubcellularLocationParent.java.md
[main/java/com/bio4j/model/uniprot/edges/SubmissionDB.java]: ../edges/SubmissionDB.java.md
[main/java/com/bio4j/model/uniprot/edges/TaxonParent.java]: ../edges/TaxonParent.java.md
[main/java/com/bio4j/model/uniprot/edges/ThesisInstitute.java]: ../edges/ThesisInstitute.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]: ImportIsoformSequences.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]: ImportProteinInteractions.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniprot.java]: ImportUniprot.java.md
[main/java/com/bio4j/model/uniprot/UniprotGraph.java]: ../UniprotGraph.java.md
[main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]: ../vertices/AlternativeProduct.java.md
[main/java/com/bio4j/model/uniprot/vertices/Article.java]: ../vertices/Article.java.md
[main/java/com/bio4j/model/uniprot/vertices/Book.java]: ../vertices/Book.java.md
[main/java/com/bio4j/model/uniprot/vertices/City.java]: ../vertices/City.java.md
[main/java/com/bio4j/model/uniprot/vertices/CommentType.java]: ../vertices/CommentType.java.md
[main/java/com/bio4j/model/uniprot/vertices/Consortium.java]: ../vertices/Consortium.java.md
[main/java/com/bio4j/model/uniprot/vertices/Country.java]: ../vertices/Country.java.md
[main/java/com/bio4j/model/uniprot/vertices/Dataset.java]: ../vertices/Dataset.java.md
[main/java/com/bio4j/model/uniprot/vertices/DB.java]: ../vertices/DB.java.md
[main/java/com/bio4j/model/uniprot/vertices/Disease.java]: ../vertices/Disease.java.md
[main/java/com/bio4j/model/uniprot/vertices/EMBL.java]: ../vertices/EMBL.java.md
[main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]: ../vertices/Ensembl.java.md
[main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]: ../vertices/FeatureType.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]: ../vertices/GeneLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Institute.java]: ../vertices/Institute.java.md
[main/java/com/bio4j/model/uniprot/vertices/Interpro.java]: ../vertices/Interpro.java.md
[main/java/com/bio4j/model/uniprot/vertices/Isoform.java]: ../vertices/Isoform.java.md
[main/java/com/bio4j/model/uniprot/vertices/Journal.java]: ../vertices/Journal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Kegg.java]: ../vertices/Kegg.java.md
[main/java/com/bio4j/model/uniprot/vertices/Keyword.java]: ../vertices/Keyword.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]: ../vertices/OnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]: ../vertices/OnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Organism.java]: ../vertices/Organism.java.md
[main/java/com/bio4j/model/uniprot/vertices/Patent.java]: ../vertices/Patent.java.md
[main/java/com/bio4j/model/uniprot/vertices/Person.java]: ../vertices/Person.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pfam.java]: ../vertices/Pfam.java.md
[main/java/com/bio4j/model/uniprot/vertices/PIR.java]: ../vertices/PIR.java.md
[main/java/com/bio4j/model/uniprot/vertices/Protein.java]: ../vertices/Protein.java.md
[main/java/com/bio4j/model/uniprot/vertices/Publisher.java]: ../vertices/Publisher.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]: ../vertices/Pubmed.java.md
[main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]: ../vertices/ReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/vertices/Reference.java]: ../vertices/Reference.java.md
[main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]: ../vertices/RefSeq.java.md
[main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]: ../vertices/SequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]: ../vertices/SubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Submission.java]: ../vertices/Submission.java.md
[main/java/com/bio4j/model/uniprot/vertices/Taxon.java]: ../vertices/Taxon.java.md
[main/java/com/bio4j/model/uniprot/vertices/Thesis.java]: ../vertices/Thesis.java.md
[main/java/com/bio4j/model/uniprot/vertices/UniGene.java]: ../vertices/UniGene.java.md
[main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]: ../vertices/UnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]: ../../uniprot_enzymedb/edges/EnzymaticActivity.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniprotEnzymeDB.java]: ../../uniprot_enzymedb/programs/ImportUniprotEnzymeDB.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/UniprotEnzymeDBGraph.java]: ../../uniprot_enzymedb/UniprotEnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]: ../../uniprot_go/edges/GoAnnotation.java.md
[main/java/com/bio4j/model/uniprot_go/programs/ImportUniprotGo.java]: ../../uniprot_go/programs/ImportUniprotGo.java.md
[main/java/com/bio4j/model/uniprot_go/tests/ImportUniprotGoTest.java]: ../../uniprot_go/tests/ImportUniprotGoTest.java.md
[main/java/com/bio4j/model/uniprot_go/UniprotGoGraph.java]: ../../uniprot_go/UniprotGoGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]: ../../uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/programs/ImportUniprotNCBITaxonomy.java]: ../../uniprot_ncbiTaxonomy/programs/ImportUniprotNCBITaxonomy.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniprotNCBITaxonomyGraph.java]: ../../uniprot_ncbiTaxonomy/UniprotNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]: ../../uniprot_uniref/edges/UniRef100Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]: ../../uniprot_uniref/edges/UniRef100Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]: ../../uniprot_uniref/edges/UniRef50Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]: ../../uniprot_uniref/edges/UniRef50Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]: ../../uniprot_uniref/edges/UniRef90Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]: ../../uniprot_uniref/edges/UniRef90Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniprotUniRef.java]: ../../uniprot_uniref/programs/ImportUniprotUniRef.java.md
[main/java/com/bio4j/model/uniprot_uniref/UniprotUniRefGraph.java]: ../../uniprot_uniref/UniprotUniRefGraph.java.md
[main/java/com/bio4j/model/uniref/programs/ImportUniRef.java]: ../../uniref/programs/ImportUniRef.java.md
[main/java/com/bio4j/model/uniref/UniRefGraph.java]: ../../uniref/UniRefGraph.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef100Cluster.java]: ../../uniref/vertices/UniRef100Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef50Cluster.java]: ../../uniref/vertices/UniRef50Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef90Cluster.java]: ../../uniref/vertices/UniRef90Cluster.java.md