/*
 * Copyright (C) 2010-2011  "Bio4j"
 *
 * This file is part of Bio4j
 *
 * Bio4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.era7.bioinfo.bio4j.blueprints.model.nodes;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.citation.*;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.reactome.ReactomeTermNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.article.ArticleProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.book.BookProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.onarticle.OnlineArticleProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.patent.PatentProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.submission.SubmissionProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.thesis.ThesisProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.uo.UnpublishedObservationProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.comment.DomainCommentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.comment.FunctionCommentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.comment.PathwayCommentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.comment.SimilarityCommentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.features.ActiveSiteFeatureRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.features.SignalPeptideFeatureRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.features.SpliceVariantFeatureRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.features.TransmembraneRegionFeatureRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.*;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.uniref.UniRef100MemberRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.uniref.UniRef50MemberRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.uniref.UniRef90MemberRel;
import com.era7.bioinfo.bio4j.model.enums.UniprotDBXref;
import com.era7.bioinfo.bio4j.model.nodes.Enzyme;
import com.era7.bioinfo.bio4j.model.nodes.GoTerm;
import com.era7.bioinfo.bio4j.model.nodes.Interpro;
import com.era7.bioinfo.bio4j.model.nodes.Keyword;
import com.era7.bioinfo.bio4j.model.nodes.Pfam;
import com.era7.bioinfo.bio4j.model.nodes.Protein;
import com.era7.bioinfo.bio4j.model.nodes.SubcellularLocation;
import com.era7.bioinfo.bio4j.model.nodes.citation.Article;
import com.era7.bioinfo.bio4j.model.nodes.citation.Book;
import com.era7.bioinfo.bio4j.model.nodes.citation.OnlineArticle;
import com.era7.bioinfo.bio4j.model.nodes.citation.Patent;
import com.era7.bioinfo.bio4j.model.nodes.citation.Submission;
import com.era7.bioinfo.bio4j.model.nodes.citation.Thesis;
import com.era7.bioinfo.bio4j.model.nodes.citation.UnpublishedObservation;
import com.era7.bioinfo.bio4j.model.nodes.reactome.ReactomeTerm;
import com.era7.bioinfo.bio4j.model.nodes.refseq.GenomeElement;
import com.era7.bioinfo.bio4j.model.relationships.comment.DomainComment;
import com.era7.bioinfo.bio4j.model.relationships.comment.FunctionComment;
import com.era7.bioinfo.bio4j.model.relationships.comment.PathwayComment;
import com.era7.bioinfo.bio4j.model.relationships.comment.SimilarityComment;
import com.era7.bioinfo.bio4j.model.relationships.features.ActiveSiteFeature;
import com.era7.bioinfo.bio4j.model.relationships.features.SignalPeptideFeature;
import com.era7.bioinfo.bio4j.model.relationships.features.SpliceVariantFeature;
import com.era7.bioinfo.bio4j.model.relationships.features.TransmembraneRegionFeature;
import com.era7.bioinfo.bio4j.model.relationships.protein.ProteinIsoformInteraction;
import com.era7.bioinfo.bio4j.model.relationships.protein.ProteinProteinInteraction;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Uniprot proteins
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ProteinNode extends BasicVertex implements Protein{

    public static final String NODE_TYPE = ProteinNode.class.getCanonicalName();
    public static final String NAME_PROPERTY = "protein_name";
    public static final String FULL_NAME_PROPERTY = "protein_full_name";
    public static final String SHORT_NAME_PROPERTY = "protein_short_name";
    public static final String ACCESSION_PROPERTY = "protein_accession";
    public static final String SEQUENCE_PROPERTY = "protein_sequence";
    public static final String MASS_PROPERTY = "protein_mass";
    public static final String LENGTH_PROPERTY = "protein_length";
    public static final String MODIFIED_DATE_PROPERTY = "protein_modified_date";
    public static final String GENE_NAMES_PROPERTY = "protein_gene_names";
    public static final String ALTERNATIVE_ACCESSIONS_PROPERTY = "protein_alternative_accessions";
    
    //---------------------DB CROSS REFERENCES----------------------------------------
    public static final String ALLERGOME_REFERENCES_PROPERTY = "protein_allergome_references";
    public static final String ARACHNOSERVER_REFERENCES_PROPERTY = "protein_arachnoserver_references";
    public static final String BGEE_REFERENCES_PROPERTY = "protein_bgee_references";
    public static final String BINDING_DB_REFERENCES_PROPERTY = "protein_binding_db_references";
    public static final String BIOCYC_REFERENCES_PROPERTY = "protein_biocyc_references";
    
    
    public static final String ENSEMBL_REFERENCES_PROPERTY = "protein_ensembl_references";
    public static final String PIR_REFERENCES_PROPERTY = "protein_pir_references";
    public static final String KEGG_REFERENCES_PROPERTY = "protein_kegg_references";
    public static final String EMBL_REFERENCES_PROPERTY = "protein_embl_references";
    public static final String REFSEQ_REFERENCES_PROPERTY = "protein_refseq_references";
    public static final String ARRAY_EXPRESS_REFERENCES_PROPERTY = "protein_array_express_references";
    public static final String UNIGENE_REFERENCES_PROPERTY = "protein_unigene_references";
    public static final String ENSEMBL_PLANTS_REFERENCES_PROPERTY = "protein_ensembl_plants_references";
    
    
    
    public static final String BRENDA_REFERENCES_PROPERTY = "protein_brenda_references";
    public static final String CAZY_REFERENCES_PROPERTY = "protein_cazy_references";
    public static final String CGD_REFERENCES_PROPERTY = "protein_cgd_references";
    public static final String CHEMBL_REFERENCES_PROPERTY = "protein_chembl_references";
    public static final String CLEANEX_REFERENCES_PROPERTY = "protein_cleanex_references";
    public static final String COMPLUYEAST_2DPAGE_REFERENCES_PROPERTY = "protein_compluyeast_2dpage_references";
    public static final String CONOSERVER_REFERENCES_PROPERTY = "protein_conoserver_references";
    public static final String CORNEA_2DPAGE_REFERENCES_PROPERTY = "protein_cornea_2dpage_references";
    public static final String CTD_REFERENCES_PROPERTY = "protein_ctd_references";
    public static final String CYGD_REFERENCES_PROPERTY = "protein_cygd_references";
    public static final String DBSNP_REFERENCES_PROPERTY = "protein_dbsnp_references";
    public static final String DDBJ_REFERENCES_PROPERTY = "protein_ddbj_references";
    public static final String DICTY_BASE_REFERENCES_PROPERTY = "protein_dicty_base_references";
    public static final String DIP_REFERENCES_PROPERTY = "protein_dip_references";
    public static final String DISPROT_REFERENCES_PROPERTY = "protein_disprot_references";
    public static final String DMDM_REFERENCES_PROPERTY = "protein_dmdm_references";
    public static final String DNASU_REFERENCES_PROPERTY = "protein_dnasu_references";
    public static final String DOSAC_COBS_2DPAGE_REFERENCES_PROPERTY = "protein_dosac_cobs_2dpage_references";
    public static final String DRUGBANK_REFERENCES_PROPERTY = "protein_drugbank_references";
    public static final String ECHOBASE_REFERENCES_PROPERTY = "protein_echobase_references";
    public static final String ECOGENE_REFERENCES_PROPERTY = "protein_ecogene_references";
    public static final String EGGNOG_REFERENCES_PROPERTY = "protein_eggnog_references";
    public static final String ENSEMBL_BACTERIA_REFERENCES_PROPERTY = "protein_ensembl_bacteria_references";
    public static final String ENSEMBL_FUNGI_REFERENCES_PROPERTY = "protein_ensembl_fungi_references";
    public static final String ENSEMBL_METAZOA_REFERENCES_PROPERTY = "protein_ensembl_metazoa_references";
    public static final String ENSEMBL_PROTISTS_REFERENCES_PROPERTY = "protein_ensembl_protists_references";
    public static final String EUHCVDB_REFERENCES_PROPERTY = "protein_euhcvdb_references";
    public static final String EUPATHDB_REFERENCES_PROPERTY = "protein_eupathdb_references";
    public static final String EVOLUTIONARY_TRACE_REFERENCES_PROPERTY = "protein_evolutionary_trace_references";
    public static final String FLYBASE_REFERENCES_PROPERTY = "protein_flybase_references";
    public static final String GENATLAS_REFERENCES_PROPERTY = "protein_genatlas_references";
    public static final String GENBANK_REFERENCES_PROPERTY = "protein_genbank_references";
    public static final String GENE3D_REFERENCES_PROPERTY = "protein_gene3d_references";
    public static final String GENECARDS_REFERENCES_PROPERTY = "protein_genecards_references";
    public static final String GENEFARM_REFERENCES_PROPERTY = "protein_genefarm_references";
    public static final String GENEID_REFERENCES_PROPERTY = "protein_geneid_references";
    public static final String GENETREE_REFERENCES_PROPERTY = "protein_genetree_references";
    public static final String GENEVESTIGATOR_REFERENCES_PROPERTY = "protein_genevestigator_references";
    public static final String GENOLIST_REFERENCES_PROPERTY = "protein_genolist_references";
    public static final String GENOME_REVIEWS_REFERENCES_PROPERTY = "protein_genome_reviews_references";
    public static final String GENOME_RNAI_REFERENCES_PROPERTY = "protein_genome_rnai_references";
    public static final String GERMONLINE_REFERENCES_PROPERTY = "protein_germonline_references";
    public static final String GLYCOSUITEDB_REFERENCES_PROPERTY = "protein_glycosuitedb_references";
    public static final String GPCRDB_REFERENCES_PROPERTY = "protein_gpcrdb_references";
    public static final String GRAMENE_REFERENCES_PROPERTY = "protein_gramene_references";
    public static final String HINVDB_REFERENCES_PROPERTY = "protein_hinvdb_references";
    public static final String HAMAP_REFERENCES_PROPERTY = "protein_hamap_references";
    public static final String HGNC_REFERENCES_PROPERTY = "protein_hgnc_references";
    public static final String HOGENOM_REFERENCES_PROPERTY = "protein_hogenom_references";
    public static final String HOVERGEN_REFERENCES_PROPERTY = "protein_hovergen_references";
    public static final String HPA_REFERENCES_PROPERTY = "protein_hpa_references";
    public static final String HSSP_REFERENCES_PROPERTY = "protein_hssp_references";
    public static final String HUGE_REFERENCES_PROPERTY = "protein_huge_references";
    public static final String IMGT_REFERENCES_PROPERTY = "protein_imgt_references";
    public static final String INPARANOID_REFERENCES_PROPERTY = "protein_inparanoid_references";
    public static final String INTACT_REFERENCES_PROPERTY = "protein_intact_references";
    public static final String IPI_REFERENCES_PROPERTY = "protein_ipi_references";
    public static final String KO_REFERENCES_PROPERTY = "protein_ko_references";
    public static final String LEGIO_LIST_REFERENCES_PROPERTY = "protein_legio_list_references";
    public static final String LEPROMA_REFERENCES_PROPERTY = "protein_leproma_references";
    public static final String MAIZE_GDB_REFERENCES_PROPERTY = "protein_maize_gdb_references";
    public static final String MEROPS_REFERENCES_PROPERTY = "protein_merops_references";
    public static final String MGI_REFERENCES_PROPERTY = "protein_mgi_references";
    public static final String MICADO_REFERENCES_PROPERTY = "protein_micado_references";
    public static final String MIM_REFERENCES_PROPERTY = "protein_mim_references";
    public static final String MINT_REFERENCES_PROPERTY = "protein_mint_references";
    public static final String MODBASE_REFERENCES_PROPERTY = "protein_modbase_references";
    public static final String MYCOCLAP_REFERENCES_PROPERTY = "protein_mycoclap_references";
    public static final String NEXTBIO_REFERENCES_PROPERTY = "protein_nextbio_references";
    public static final String NEXTPROT_REFERENCES_PROPERTY = "protein_nextprot_references";
    public static final String OGP_REFERENCES_PROPERTY = "protein_ogp_references";
    public static final String OMA_REFERENCES_PROPERTY = "protein_oma_references";
    public static final String ORPHANET_REFERENCES_PROPERTY = "protein_orphanet_references";
    public static final String ORTHODB_REFERENCES_PROPERTY = "protein_orthodb_references";
    public static final String PANTHER_REFERENCES_PROPERTY = "protein_panther_references";
    public static final String PATHWAY_INTERACTION_DB_REFERENCES_PROPERTY = "protein_pathway_interaction_db_references";
    public static final String PATRIC_REFERENCES_PROPERTY = "protein_patric_references";
    public static final String PAXDB_REFERENCES_PROPERTY = "protein_paxdb_references";
    public static final String PDB_REFERENCES_PROPERTY = "protein_pdb_references";
    public static final String PDBJ_REFERENCES_PROPERTY = "protein_pdbj_references";
    public static final String PDBSUM_REFERENCES_PROPERTY = "protein_pdbsum_references";
    public static final String PEPTIDE_ATLAS_REFERENCES_PROPERTY = "protein_peptide_atlas_references";
    public static final String PEROXIBASE_REFERENCES_PROPERTY = "protein_peroxibase_references";
    public static final String PHARMGKB_REFERENCES_PROPERTY = "protein_pharmgkb_references";
    public static final String PHCI_2DPAGE_REFERENCES_PROPERTY = "protein_phci_2dpage_references";
    public static final String PHOSPHOSITE_REFERENCES_PROPERTY = "protein_phosphosite_references";
    public static final String PHOS_SITE_REFERENCES_PROPERTY = "protein_phos_site_references";
    public static final String PHYLOME_DB_REFERENCES_PROPERTY = "protein_phylome_db_references";
    public static final String PIRSF_REFERENCES_PROPERTY = "protein_pirsf_references";
    public static final String PMAP_CUTDB_REFERENCES_PROPERTY = "protein_pmap_cutdb_references";
    public static final String PMMA_2DPAGE_REFERENCES_PROPERTY = "protein_pmma_2dpage_references";
    public static final String POMBASE_REFERENCES_PROPERTY = "protein_pombase_references";
    public static final String PPTASEDB_REFERENCES_PROPERTY = "protein_pptasedb_references";
    public static final String PRIDE_REFERENCES_PROPERTY = "protein_pride_references";
    public static final String PRINTS_REFERENCES_PROPERTY = "protein_prints_references";
    public static final String PRODOM_REFERENCES_PROPERTY = "protein_prodom_references";
    public static final String PROMEX_REFERENCES_PROPERTY = "protein_promex_references";
    public static final String PROSITE_REFERENCES_PROPERTY = "protein_prosite_references";
    public static final String PROT_CLUST_DB_REFERENCES_PROPERTY = "protein_prot_clust_db_references";
    public static final String PROTEIN_MODEL_PORTAL_REFERENCES_PROPERTY = "protein_model_portal_references";
    public static final String PROTONET_REFERENCES_PROPERTY = "protein_protonet_references";
    public static final String PSEUDO_CAP_REFERENCES_PROPERTY = "protein_pseudo_cap_references";
    public static final String RAT_HEART_2DPAGE_REFERENCES_PROPERTY = "protein_rat_heart_2dpage_references";
    public static final String RCSB_PDB_REFERENCES_PROPERTY = "protein_rcsb_pdb_references";
    public static final String REBASE_REFERENCES_PROPERTY = "protein_rebase_references";
    public static final String REPRODUCTION_2DPAGE_REFERENCES_PROPERTY = "protein_reproduction_2dpage_references";
    public static final String RGD_REFERENCES_PROPERTY = "protein_rgd_references";
    public static final String ROUGE_REFERENCES_PROPERTY = "protein_rouge_references";
    public static final String SBKB_REFERENCES_PROPERTY = "protein_sbkb_references";
    public static final String SGD_REFERENCES_PROPERTY = "protein_sgd_references";
    public static final String SIENA_2DPAGE_REFERENCES_PROPERTY = "protein_siena_2dpage_references";
    public static final String SMART_REFERENCES_PROPERTY = "protein_smart_references";
    public static final String SMR_REFERENCES_PROPERTY = "protein_smr_references";
    public static final String SOURCE_REFERENCES_PROPERTY = "protein_source_references";
    public static final String STRING_REFERENCES_PROPERTY = "protein_string_references";
    public static final String SUPFAM_REFERENCES_PROPERTY = "protein_supfam_references";
    public static final String SWISS_2DPAGE_REFERENCES_PROPERTY = "protein_swiss_2dpage_references";
    public static final String TAIR_REFERENCES_PROPERTY = "protein_tair_references";
    public static final String TCDB_REFERENCES_PROPERTY = "protein_tcb_references";
    public static final String TIGRFAMS_REFERENCES_PROPERTY = "protein_tigrfams_references";
    public static final String TUBERCULIST_REFERENCES_PROPERTY = "protein_tuberculist_references";
    public static final String UCD_2DPAGE_REFERENCES_PROPERTY = "protein_ucd_2dpage_references";
    public static final String UCSC_REFERENCES_PROPERTY = "protein_ucsc_references";
    public static final String UNIPATHWAY_REFERENCES_PROPERTY = "protein_unipathway_references";
    public static final String VECTOR_BASE_REFERENCES_PROPERTY = "protein_vector_base_references";
    public static final String WORLD_2DPAGE_REFERENCES_PROPERTY = "protein_world_2dpage_references";
    public static final String WORM_BASE_REFERENCES_PROPERTY = "protein_worm_base_references";
    public static final String XEN_BASE_REFERENCES_PROPERTY = "protein_xen_base_references";
    public static final String ZFIN_REFERENCES_PROPERTY = "protein_zfin_references";
    //-------------------------------------------------------------------------------------------------

    public ProteinNode(Vertex v) {
        super(v);
    }

    @Override
    public String getName() {
        return String.valueOf(vertex.getProperty(NAME_PROPERTY));
    }

    @Override
    public String getFullName() {
        return String.valueOf(vertex.getProperty(FULL_NAME_PROPERTY));
    }

    @Override
    public String getShortName() {
        return String.valueOf(vertex.getProperty(SHORT_NAME_PROPERTY));
    }

    @Override
    public String getAccession() {
        return String.valueOf(vertex.getProperty(ACCESSION_PROPERTY));
    }

    @Override
    public String getSequence() {
        return String.valueOf(vertex.getProperty(SEQUENCE_PROPERTY));
    }    

    @Override
    public String getModifiedDate() {
        return String.valueOf(vertex.getProperty(MODIFIED_DATE_PROPERTY));
    }

    @Override
    public float getMass() {
        return Float.parseFloat(String.valueOf(vertex.getProperty(MASS_PROPERTY)));
    }

    @Override
    public int getLength() {
        return Integer.parseInt(String.valueOf(vertex.getProperty(LENGTH_PROPERTY)));
    }

    @Override
    public String[] getGeneNames() {
        return (String[]) vertex.getProperty(GENE_NAMES_PROPERTY);
    }
    
    @Override
    public String[] getAlternativeAcessions() {
        return (String[]) vertex.getProperty(ALTERNATIVE_ACCESSIONS_PROPERTY);
    }
    
    @Override
    public String[] getReference(UniprotDBXref ref){
        return (String[]) vertex.getProperty(ref.getProteinReferencePropertyName());
    }
    
    
    @Override
    public boolean isUniref50Representant() {
        return !vertex.getEdges(Direction.IN, UniRef50MemberRel.NAME).iterator().hasNext();
    }

    @Override
    public boolean isUniref90Representant() {
        return !vertex.getEdges(Direction.IN, UniRef90MemberRel.NAME).iterator().hasNext();
    }

    @Override
    public boolean isUniref100Representant() {
        return !vertex.getEdges(Direction.IN, UniRef100MemberRel.NAME).iterator().hasNext();
    }

    @Override
    public List<Protein> getUniref50ClusterThisProteinBelongsTo() {
        List<Protein> list = new LinkedList<Protein>();
        if (isUniref50Representant()) {
            list.add(this);
            Iterator<Vertex> relIterator = vertex.getVertices(Direction.OUT, UniRef50MemberRel.NAME).iterator();
            while (relIterator.hasNext()) {
                list.add(new ProteinNode(relIterator.next()));
            }
        } else {
            ProteinNode representant = new ProteinNode(vertex.getVertices(Direction.IN, UniRef50MemberRel.NAME).iterator().next());
            return representant.getUniref50ClusterThisProteinBelongsTo();
        }
        return list;
    }

    @Override
    public List<Protein> getUniref90ClusterThisProteinBelongsTo() {
        List<Protein> list = new LinkedList<Protein>();
        if (isUniref90Representant()) {
            list.add(this);
            Iterator<Vertex> relIterator = vertex.getVertices(Direction.OUT, UniRef90MemberRel.NAME).iterator();
            while (relIterator.hasNext()) {
                list.add(new ProteinNode(relIterator.next()));
            }
        } else {
            ProteinNode representant = new ProteinNode(vertex.getVertices(Direction.IN, UniRef90MemberRel.NAME).iterator().next());
            return representant.getUniref90ClusterThisProteinBelongsTo();
        }
        return list;
    }

    @Override
    public List<Protein> getUniref100ClusterThisProteinBelongsTo() {
        List<Protein> list = new LinkedList<Protein>();
        if (isUniref100Representant()) {
            list.add(this);
            Iterator<Vertex> relIterator = vertex.getVertices(Direction.OUT, UniRef100MemberRel.NAME).iterator();
            while (relIterator.hasNext()) {
                list.add(new ProteinNode(relIterator.next()));
            }
        } else {
            ProteinNode representant = new ProteinNode(vertex.getVertices(Direction.IN, UniRef100MemberRel.NAME).iterator().next());
            return representant.getUniref100ClusterThisProteinBelongsTo();
        }
        return list;
    }

    @Override
    public OrganismNode getOrganism() {
        OrganismNode org = null;
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinOrganismRel.NAME).iterator();
        if (iterator.hasNext()) {
            org = new OrganismNode(iterator.next());
        }
        return org;
    }

    @Override
    public DatasetNode getDataset() {
        DatasetNode dataset = null;
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinDatasetRel.NAME).iterator();
        if (iterator.hasNext()) {
            dataset = new DatasetNode(iterator.next());
        }
        return dataset;
    }

    @Override
    public List<GenomeElement> getGenomeElements() {
        List<GenomeElement> list = new LinkedList<GenomeElement>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinGenomeElementRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new GenomeElementNode(iterator.next()));
        }
        return list;
    }

    @Override
    public List<SubcellularLocation> getSubcellularLocations() {
        List<SubcellularLocation> list = new LinkedList<SubcellularLocation>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinSubcellularLocationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new SubcellularLocationNode(iterator.next()));
        }
        return list;
    }

    @Override
    public List<Interpro> getInterpro() {
        List<Interpro> interpros = new LinkedList<Interpro>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinInterproRel.NAME).iterator();
        while (iterator.hasNext()) {
            InterproNode interpro = new InterproNode(iterator.next());
            interpros.add(interpro);
        }
        return interpros;
    }

    @Override
    public List<Pfam> getPfamTerms() {
        List<Pfam> pfamTerms = new LinkedList<Pfam>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinPfamRel.NAME).iterator();
        while (iterator.hasNext()) {
            PfamNode interpro = new PfamNode(iterator.next());
            pfamTerms.add(interpro);
        }
        return pfamTerms;
    }

    @Override
    public List<ReactomeTerm> getReactomeTerms() {
        List<ReactomeTerm> list = new LinkedList<ReactomeTerm>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinReactomeRel.NAME).iterator();
        while (iterator.hasNext()) {
            ReactomeTermNode reactomeTerm = new ReactomeTermNode(iterator.next());
            list.add(reactomeTerm);
        }
        return list;
    }

    @Override
    public List<Enzyme> getProteinEnzymaticActivity() {
        List<Enzyme> list = new LinkedList<Enzyme>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinEnzymaticActivityRel.NAME).iterator();
        while (iterator.hasNext()) {
            EnzymeNode enzyme = new EnzymeNode(iterator.next());
            list.add(enzyme);
        }
        return list;
    }

    @Override
    public List<GoTerm> getGOAnnotations() {
        List<GoTerm> list = new LinkedList<GoTerm>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinGoRel.NAME).iterator();
        while (iterator.hasNext()) {
            GoTermNode goTerm = new GoTermNode(iterator.next());
            list.add(goTerm);
        }
        return list;
    }

    @Override
    public List<Keyword> getKeywords() {
        List<Keyword> keywords = new LinkedList<Keyword>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinKeywordRel.NAME).iterator();
        while (iterator.hasNext()) {
            KeywordNode keyword = new KeywordNode(iterator.next());
            keywords.add(keyword);
        }
        return keywords;
    }

    @Override
    public List<SignalPeptideFeature> getSignalPeptideFeature() {
        List<SignalPeptideFeature> list = new LinkedList<SignalPeptideFeature>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, SignalPeptideFeatureRel.NAME).iterator();
        while (iterator.hasNext()) {
            SignalPeptideFeatureRel rel = new SignalPeptideFeatureRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<SpliceVariantFeature> getSpliceVariantFeature() {
        List<SpliceVariantFeature> list = new LinkedList<SpliceVariantFeature>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, SpliceVariantFeatureRel.NAME).iterator();
        while (iterator.hasNext()) {
            SpliceVariantFeatureRel rel = new SpliceVariantFeatureRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<TransmembraneRegionFeature> getTransmembraneRegionFeature() {
        List<TransmembraneRegionFeature> list = new LinkedList<TransmembraneRegionFeature>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, TransmembraneRegionFeatureRel.NAME).iterator();
        while (iterator.hasNext()) {
            TransmembraneRegionFeatureRel rel = new TransmembraneRegionFeatureRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<ActiveSiteFeature> getActiveSiteFeature() {
        List<ActiveSiteFeature> list = new LinkedList<ActiveSiteFeature>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, ActiveSiteFeatureRel.NAME).iterator();
        while (iterator.hasNext()) {
            ActiveSiteFeatureRel rel = new ActiveSiteFeatureRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<FunctionComment> getFunctionComment() {
        List<FunctionComment> list = new LinkedList<FunctionComment>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, FunctionCommentRel.NAME).iterator();
        while (iterator.hasNext()) {
            FunctionCommentRel rel = new FunctionCommentRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<PathwayComment> getPathwayComment() {
        List<PathwayComment> list = new LinkedList<PathwayComment>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, PathwayCommentRel.NAME).iterator();
        while (iterator.hasNext()) {
            PathwayCommentRel rel = new PathwayCommentRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<DomainComment> getDomainComment() {
        List<DomainComment> list = new LinkedList<DomainComment>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, DomainCommentRel.NAME).iterator();
        while (iterator.hasNext()) {
            DomainCommentRel rel = new DomainCommentRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<SimilarityComment> getSimilarityComment() {
        List<SimilarityComment> list = new LinkedList<SimilarityComment>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, SimilarityCommentRel.NAME).iterator();
        while (iterator.hasNext()) {
            SimilarityCommentRel rel = new SimilarityCommentRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    /**
     * Protein-protein outgoing interactions
     *
     * @return
     */
    @Override
    public List<ProteinProteinInteraction> getProteinOutgoingInteractions() {
        List<ProteinProteinInteraction> list = new LinkedList<ProteinProteinInteraction>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, ProteinProteinInteractionRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new ProteinProteinInteractionRel(iterator.next()));
        }

        return list;
    }

    /**
     * Protein-protein incoming interactions
     *
     * @return
     */
    @Override
    public List<ProteinProteinInteraction> getProteinIncomingInteractions() {
        List<ProteinProteinInteraction> list = new LinkedList<ProteinProteinInteraction>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.IN, ProteinProteinInteractionRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new ProteinProteinInteractionRel(iterator.next()));
        }

        return list;
    }

    /**
     * Protein-Isoform outgoing interactions
     *
     * @return
     */
    @Override
    public List<ProteinIsoformInteraction> getIsoformOutgoingInteractions() {
        List<ProteinIsoformInteraction> list = new LinkedList<ProteinIsoformInteraction>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, ProteinIsoformInteractionRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new ProteinIsoformInteractionRel(iterator.next()));
        }

        return list;
    }

    /**
     * Protein-Isoform incoming interactions
     *
     * @return
     */
    @Override
    public List<ProteinIsoformInteraction> getIsoformIncomingInteractions() {
        List<ProteinIsoformInteraction> list = new LinkedList<ProteinIsoformInteraction>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.IN, ProteinIsoformInteractionRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new ProteinIsoformInteractionRel(iterator.next()));
        }

        return list;
    }

    //---------------------------------------------------------------------------------------------
    //-------------------------------------CITATIONS-----------------------------------------------
    //---------------------------------------------------------------------------------------------
    /**
     * Protein article citations
     *
     * @return
     */
    @Override
    public List<Article> getArticleCitations() {
        List<Article> list = new LinkedList<Article>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, ArticleProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new ArticleNode(iterator.next()));
        }
        return list;
    }

    /**
     * Protein submission citations
     *
     * @return
     */
    @Override
    public List<Submission> getSubmissionCitations() {
        List<Submission> list = new ArrayList<Submission>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, SubmissionProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new SubmissionNode(iterator.next()));
        }
        return list;
    }

    /**
     * Protein Online article citations
     *
     * @return
     */
    @Override
    public List<OnlineArticle> getOnlineArticleCitations() {
        List<OnlineArticle> list = new LinkedList<OnlineArticle>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, OnlineArticleProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new OnlineArticleNode(iterator.next()));
        }
        return list;
    }

    /**
     * Protein Book citations
     *
     * @return
     */
    @Override
    public List<Book> getBookCitations() {
        List<Book> list = new LinkedList<Book>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, BookProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new BookNode(iterator.next()));
        }
        return list;
    }

    /**
     * Protein patent citations
     *
     * @return
     */
    @Override
    public List<Patent> getPatentCitations() {
        List<Patent> list = new LinkedList<Patent>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, PatentProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new PatentNode(iterator.next()));
        }
        return list;
    }

    /**
     * Protein thesis citations
     *
     * @return
     */
    @Override
    public List<Thesis> getThesisCitations() {
        List<Thesis> list = new LinkedList<Thesis>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, ThesisProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new ThesisNode(iterator.next()));
        }
        return list;
    }

    /**
     * Protein unpublished observations citations
     *
     * @return
     */
    @Override
    public List<UnpublishedObservation> getUnpublishedObservationsCitations() {
        List<UnpublishedObservation> list = new LinkedList<UnpublishedObservation>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, UnpublishedObservationProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new UnpublishedObservationNode(iterator.next()));
        }
        return list;
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------

    @Override
    public void setName(String value) {
        vertex.setProperty(NAME_PROPERTY, value);
    }

    @Override
    public void setFullName(String value) {
        vertex.setProperty(FULL_NAME_PROPERTY, value);
    }

    @Override
    public void setShortName(String value) {
        vertex.setProperty(SHORT_NAME_PROPERTY, value);
    }

    @Override
    public void setAccession(String value) {
        vertex.setProperty(ACCESSION_PROPERTY, value);
    }

    @Override
    public void setSequence(String value) {
        vertex.setProperty(SEQUENCE_PROPERTY, value);
    }

    @Override
    public void setModifiedDate(String value) {
        vertex.setProperty(MODIFIED_DATE_PROPERTY, value);
    }

    @Override
    public void setMass(float value) {
        vertex.setProperty(MASS_PROPERTY, value);
    }

    @Override
    public void setLength(int value) {
        vertex.setProperty(LENGTH_PROPERTY, value);
    }

    @Override
    public void setGeneNames(String[] value) {
        vertex.setProperty(GENE_NAMES_PROPERTY, value);
    }
    
    @Override
    public void setAlternativeAccessions(String[] value) {
        vertex.setProperty(ALTERNATIVE_ACCESSIONS_PROPERTY, value);
    }
    
    
    @Override
    public void setReference(UniprotDBXref ref, String[] value) {
        vertex.setProperty(ref.getProteinReferencePropertyName(), value);
    }

}
