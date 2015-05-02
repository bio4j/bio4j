package com.bio4j.model.ucsc_genomes.programs;

import com.bio4j.model.ucsc.vertices.UCSCChromosome;
import com.bio4j.model.ucsc_genomes.UCSCGenomesGraph;
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

public abstract class ImportUCSCGenomes<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET> {

    private static final Logger logger = Logger.getLogger("ImportUCSCGenomes");
    private static FileHandler fh;


    protected abstract UCSCGenomesGraph<I, RV, RVT, RE, RET> config(String dbFolder, String propertiesFile);


    public void importUCSCGenomes(String[] args) {


    }

}