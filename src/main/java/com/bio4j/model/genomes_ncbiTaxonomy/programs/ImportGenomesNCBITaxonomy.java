package com.bio4j.model.genomes_ncbiTaxonomy.programs;

import com.bio4j.model.genomes_ncbiTaxonomy.GenomesNCBITaxonomyGraph;
import com.bio4j.angulillos.UntypedGraph;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.GZIPInputStream;

public abstract class ImportGenomesNCBITaxonomy<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET> {

    private static final Logger logger = Logger.getLogger("ImportGenomesNCBITaxonomy");
    private static FileHandler fh;


    protected abstract ImportGenomesNCBITaxonomy<I, RV, RVT, RE, RET> config(String dbFolder, String propertiesFile);


    public void importGenomesNCBITaxonomy(String[] args) {


    }

}