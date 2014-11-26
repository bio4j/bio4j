package com.bio4j.model.ncbiTaxonomy_geninfo.programs;

import com.bio4j.model.geninfo.vertices.GenInfo;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.ncbiTaxonomy_geninfo.NCBITaxonomyGenInfoGraph;
import com.bio4j.angulillos.UntypedGraph;

import java.io.*;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class ImportGenInfoNCBITaxonIndex<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

	private static final Logger logger = Logger.getLogger("ImportGenInfoNCBITaxonIndex");
	private static FileHandler fh;

	protected abstract NCBITaxonomyGenInfoGraph<I,RV,RVT,RE,RET> config(String dbFolder);


	public void importGenInfoNCBITaxonIndex(String[] args) {

		if (args.length != 2) {
			System.out.println("This program expects the following parameters: \n"
					+ "1. GI tax Id association filename \n"
					+ "2. Bio4j DB folder \n");
		} else {

			long initTime = System.nanoTime();

			File inFile = new File(args[0]);
			String dbFolder = args[1];


			NCBITaxonomyGenInfoGraph<I,RV,RVT,RE,RET> ncbiTaxonomyGenInfoGraph = config(dbFolder);

			BufferedWriter statsBuff = null;
			BufferedWriter incorrectPairsBuff = null;

			int genInfoCounter = 0;
			int limitForPrintingOut = 10000;
			int lineCounter = 0;

			try {

				// This block configures the logger with handler and formatter
				fh = new FileHandler("ImportNCBITaxonGenInfoIndex.log", false);

				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);

				//---creating writer for stats file-----
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportNCBITaxonGenInfoIndexStats.txt")));
				incorrectPairsBuff = new BufferedWriter(new FileWriter(new File("incorrectGiTaxIdPairs.txt")));

				BufferedReader reader = new BufferedReader(new FileReader(inFile));
				StringBuilder entryStBuilder = new StringBuilder();
				String line;

				while ((line = reader.readLine()) != null) {

					String[] columns = line.split("\t");

					String genInfoId = columns[0];
					String taxId = columns[1];

					Optional<NCBITaxon<I, RV, RVT, RE, RET>> ncbiTaxonOptional = ncbiTaxonomyGenInfoGraph.ncbiTaxonomyGraph().nCBITaxonIdIndex().getVertex(taxId);

					if (ncbiTaxonOptional.isPresent()) {

						NCBITaxon<I, RV, RVT, RE, RET> ncbiTaxon = ncbiTaxonOptional.get();
						Optional<GenInfo<I, RV, RVT, RE, RET>> genInfoOptional = ncbiTaxonomyGenInfoGraph.genInfoGraph().genInfoIdIndex().getVertex(genInfoId);
						GenInfo<I, RV, RVT, RE, RET> genInfo = null;
						if(!genInfoOptional.isPresent()){
							genInfo = ncbiTaxonomyGenInfoGraph.genInfoGraph().addVertex(ncbiTaxonomyGenInfoGraph.genInfoGraph().GenInfo());
							genInfo.set(ncbiTaxonomyGenInfoGraph.genInfoGraph().GenInfo().id, genInfoId);
							ncbiTaxonomyGenInfoGraph.raw().commit();
						}else{
							genInfo = genInfoOptional.get();
						}

						ncbiTaxon.addInEdge( genInfo, ncbiTaxonomyGenInfoGraph.GenInfoNCBITaxon());

					} else {
						incorrectPairsBuff.write(genInfoId + "\t" + taxId + "\n");
					}

					lineCounter++;

					if (lineCounter % 100000 == 0) {
						logger.log(Level.INFO, (lineCounter + " lines parsed..."));
						incorrectPairsBuff.flush();
					}
				}
				reader.close();


			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
				StackTraceElement[] trace = e.getStackTrace();
				for (StackTraceElement stackTraceElement : trace) {
					logger.log(Level.SEVERE, stackTraceElement.toString());
				}
			} finally {

				try {
					//------closing writers-------

					// shutdown, makes sure all changes are written to disk
					ncbiTaxonomyGenInfoGraph.raw().shutdown();

					// closing logger file handler
					fh.close();

					//-----------------writing stats file---------------------
					long elapsedTime = System.nanoTime() - initTime;
					long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
					long hours = elapsedSeconds / 3600;
					long minutes = (elapsedSeconds % 3600) / 60;
					long seconds = (elapsedSeconds % 3600) % 60;

					statsBuff.write("Statistics for program ImportNCBITaxonGenInfoIndex:\nInput file: " + inFile.getName()
							+ "\nThere were " + genInfoCounter + " Gen Info elements inserted.\n"
							+ "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

					//---closing stats writer---
					statsBuff.close();
					//---closing incorrect pairs writer---
					incorrectPairsBuff.close();


				} catch (IOException ex) {
					Logger.getLogger(ImportGenInfoNCBITaxonIndex.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		}

	}

}