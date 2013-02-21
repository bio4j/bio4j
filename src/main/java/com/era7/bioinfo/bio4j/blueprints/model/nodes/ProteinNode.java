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
public class ProteinNode extends BasicVertex {

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
    public static final String ENSEMBL_REFERENCES_PROPERTY = "protein_ensembl_references";
    public static final String PIR_REFERENCES_PROPERTY = "protein_pir_references";
    public static final String KEGG_REFERENCES_PROPERTY = "protein_kegg_references";
    public static final String EMBL_REFERENCES_PROPERTY = "protein_embl_references";
    public static final String REFSEQ_REFERENCES_PROPERTY = "protein_refseq_references";
    public static final String ARRAY_EXPRESS_REFERENCES_PROPERTY = "protein_array_express_references";
    public static final String UNIGENE_REFERENCES_PROPERTY = "protein_unigene_references";
    public static final String ENSEMBL_PLANTS_REFERENCES_PROPERTY = "protein_ensembl_plants_references";
    public static final String DBASE_ECOLI_REFERENCES_PROPERTY = "protein_2dbase_ecoli_references";
    public static final String AARHUS_GHENT_2DPAGE_REFERENCES_PROPERTY = "protein_aarhus_ghent_2dpage_references";
    public static final String AGD_REFERENCES_PROPERTY = "protein_agd_references";
    public static final String ALLERGOME_REFERENCES_PROPERTY = "protein_allergome_references";
    public static final String ANU_2DPAGE_REFERENCES_PROPERTY = "protein_anu_2dpage_references";
    public static final String ARACHNOSERVER_REFERENCES_PROPERTY = "protein_arachnoserver_references";
    public static final String BGEE_REFERENCES_PROPERTY = "protein_bgee_references";
    public static final String BINDING_DB_REFERENCES_PROPERTY = "protein_binding_db_references";
    public static final String BIOCYC_REFERENCES_PROPERTY = "protein_biocyc_references";
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
    public static final String VECTOR_BASE_REFERENCES_PROPERTY = "protein_vector_base_references";
    public static final String WORLD_2DPAGE_REFERENCES_PROPERTY = "protein_world_2dpage_references";
    public static final String WORM_BASE_REFERENCES_PROPERTY = "protein_worm_base_references";
    public static final String XEN_BASE_REFERENCES_PROPERTY = "protein_xen_base_references";
    public static final String ZFIN_REFERENCES_PROPERTY = "protein_zfin_references";
    //-------------------------------------------------------------------------------------------------

    public ProteinNode(Vertex v) {
        super(v);
    }

    public String getName() {
        return String.valueOf(vertex.getProperty(NAME_PROPERTY));
    }

    public String getFullName() {
        return String.valueOf(vertex.getProperty(FULL_NAME_PROPERTY));
    }

    public String getShortName() {
        return String.valueOf(vertex.getProperty(SHORT_NAME_PROPERTY));
    }

    public String getAccession() {
        return String.valueOf(vertex.getProperty(ACCESSION_PROPERTY));
    }

    public String getSequence() {
        return String.valueOf(vertex.getProperty(SEQUENCE_PROPERTY));
    }

    public String getEnsemblReferences() {
        return String.valueOf(vertex.getProperty(ENSEMBL_REFERENCES_PROPERTY));
    }

    public String[] getEMBLFeferences() {
        return (String[]) vertex.getProperty(EMBL_REFERENCES_PROPERTY);
    }

    public String[] getEnsemblPlantsReferences() {
        return (String[]) vertex.getProperty(ENSEMBL_PLANTS_REFERENCES_PROPERTY);
    }

    public String[] getRefseqReferences() {
        return (String[]) vertex.getProperty(REFSEQ_REFERENCES_PROPERTY);
    }

    public String[] getAlternativeAcessions() {
        return (String[]) vertex.getProperty(ALTERNATIVE_ACCESSIONS_PROPERTY);
    }

    public String[] getPIRReferences() {
        return (String[]) vertex.getProperty(PIR_REFERENCES_PROPERTY);
    }

    public String[] getKeggReferences() {
        return (String[]) vertex.getProperty(KEGG_REFERENCES_PROPERTY);
    }

    public String[] getArrayExpressReferences() {
        return (String[]) vertex.getProperty(ARRAY_EXPRESS_REFERENCES_PROPERTY);
    }

    public String[] getUniGeneReferences() {
        return (String[]) vertex.getProperty(UNIGENE_REFERENCES_PROPERTY);
    }

    public String getModifiedDate() {
        return String.valueOf(vertex.getProperty(MODIFIED_DATE_PROPERTY));
    }

    public float getMass() {
        return Float.parseFloat(String.valueOf(vertex.getProperty(MASS_PROPERTY)));
    }

    public int getLength() {
        return Integer.parseInt(String.valueOf(vertex.getProperty(LENGTH_PROPERTY)));
    }

    public String[] getGeneNames() {
        return (String[]) vertex.getProperty(GENE_NAMES_PROPERTY);
    }

    public boolean isUniref50Representant() {
        return !vertex.getEdges(Direction.IN, UniRef50MemberRel.NAME).iterator().hasNext();
    }

    public boolean isUniref90Representant() {
        return !vertex.getEdges(Direction.IN, UniRef90MemberRel.NAME).iterator().hasNext();
    }

    public boolean isUniref100Representant() {
        return !vertex.getEdges(Direction.IN, UniRef100MemberRel.NAME).iterator().hasNext();
    }

    public List<ProteinNode> getUniref50ClusterThisProteinBelongsTo() {
        List<ProteinNode> list = new LinkedList<ProteinNode>();
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

    public List<ProteinNode> getUniref90ClusterThisProteinBelongsTo() {
        List<ProteinNode> list = new LinkedList<ProteinNode>();
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

    public List<ProteinNode> getUniref100ClusterThisProteinBelongsTo() {
        List<ProteinNode> list = new LinkedList<ProteinNode>();
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

    public OrganismNode getOrganism() {
        OrganismNode org = null;
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinOrganismRel.NAME).iterator();
        if (iterator.hasNext()) {
            org = new OrganismNode(iterator.next());
        }
        return org;
    }

    public DatasetNode getDataset() {
        DatasetNode dataset = null;
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinDatasetRel.NAME).iterator();
        if (iterator.hasNext()) {
            dataset = new DatasetNode(iterator.next());
        }
        return dataset;
    }

    public List<GenomeElementNode> getGenomeElements() {
        List<GenomeElementNode> list = new ArrayList<GenomeElementNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinGenomeElementRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new GenomeElementNode(iterator.next()));
        }
        return list;
    }

    public List<SubcellularLocationNode> getSubcellularLocations() {
        List<SubcellularLocationNode> list = new ArrayList<SubcellularLocationNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinSubcellularLocationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new SubcellularLocationNode(iterator.next()));
        }
        return list;
    }

    public List<InterproNode> getInterpro() {
        List<InterproNode> interpros = new ArrayList<InterproNode>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinInterproRel.NAME).iterator();
        while (iterator.hasNext()) {
            InterproNode interpro = new InterproNode(iterator.next());
            interpros.add(interpro);
        }
        return interpros;
    }

    public List<PfamNode> getPfamTerms() {
        List<PfamNode> pfamTerms = new ArrayList<PfamNode>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinPfamRel.NAME).iterator();
        while (iterator.hasNext()) {
            PfamNode interpro = new PfamNode(iterator.next());
            pfamTerms.add(interpro);
        }
        return pfamTerms;
    }

    public List<ReactomeTermNode> getReactomeTerms() {
        List<ReactomeTermNode> list = new LinkedList<ReactomeTermNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinReactomeRel.NAME).iterator();
        while (iterator.hasNext()) {
            ReactomeTermNode reactomeTerm = new ReactomeTermNode(iterator.next());
            list.add(reactomeTerm);
        }
        return list;
    }

    public List<EnzymeNode> getProteinEnzymaticActivity() {
        List<EnzymeNode> list = new LinkedList<EnzymeNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinEnzymaticActivityRel.NAME).iterator();
        while (iterator.hasNext()) {
            EnzymeNode enzyme = new EnzymeNode(iterator.next());
            list.add(enzyme);
        }
        return list;
    }

    public List<GoTermNode> getGOAnnotations() {
        List<GoTermNode> list = new ArrayList<GoTermNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinGoRel.NAME).iterator();
        while (iterator.hasNext()) {
            GoTermNode goTerm = new GoTermNode(iterator.next());
            list.add(goTerm);
        }
        return list;
    }

    public List<KeywordNode> getKeywords() {
        List<KeywordNode> keywords = new ArrayList<KeywordNode>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinKeywordRel.NAME).iterator();
        while (iterator.hasNext()) {
            KeywordNode keyword = new KeywordNode(iterator.next());
            keywords.add(keyword);
        }
        return keywords;
    }

    public List<SignalPeptideFeatureRel> getSignalPeptideFeature() {
        List<SignalPeptideFeatureRel> list = new ArrayList<SignalPeptideFeatureRel>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, SignalPeptideFeatureRel.NAME).iterator();
        while (iterator.hasNext()) {
            SignalPeptideFeatureRel rel = new SignalPeptideFeatureRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    public List<SpliceVariantFeatureRel> getSpliceVariantFeature() {
        List<SpliceVariantFeatureRel> list = new ArrayList<SpliceVariantFeatureRel>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, SpliceVariantFeatureRel.NAME).iterator();
        while (iterator.hasNext()) {
            SpliceVariantFeatureRel rel = new SpliceVariantFeatureRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    public List<TransmembraneRegionFeatureRel> getTransmembraneRegionFeature() {
        List<TransmembraneRegionFeatureRel> list = new ArrayList<TransmembraneRegionFeatureRel>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, TransmembraneRegionFeatureRel.NAME).iterator();
        while (iterator.hasNext()) {
            TransmembraneRegionFeatureRel rel = new TransmembraneRegionFeatureRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    public List<ActiveSiteFeatureRel> getActiveSiteFeature() {
        List<ActiveSiteFeatureRel> list = new ArrayList<ActiveSiteFeatureRel>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, ActiveSiteFeatureRel.NAME).iterator();
        while (iterator.hasNext()) {
            ActiveSiteFeatureRel rel = new ActiveSiteFeatureRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    public List<FunctionCommentRel> getFunctionComment() {
        List<FunctionCommentRel> list = new ArrayList<FunctionCommentRel>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, FunctionCommentRel.NAME).iterator();
        while (iterator.hasNext()) {
            FunctionCommentRel rel = new FunctionCommentRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    public List<PathwayCommentRel> getPathwayComment() {
        List<PathwayCommentRel> list = new ArrayList<PathwayCommentRel>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, PathwayCommentRel.NAME).iterator();
        while (iterator.hasNext()) {
            PathwayCommentRel rel = new PathwayCommentRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    public List<DomainCommentRel> getDomainComment() {
        List<DomainCommentRel> list = new ArrayList<DomainCommentRel>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, DomainCommentRel.NAME).iterator();
        while (iterator.hasNext()) {
            DomainCommentRel rel = new DomainCommentRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    public List<SimilarityCommentRel> getSimilarityComment() {
        List<SimilarityCommentRel> list = new ArrayList<SimilarityCommentRel>();

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
    public List<ProteinProteinInteractionRel> getProteinOutgoingInteractions() {
        List<ProteinProteinInteractionRel> list = new ArrayList<ProteinProteinInteractionRel>();

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
    public List<ProteinProteinInteractionRel> getProteinIncomingInteractions() {
        List<ProteinProteinInteractionRel> list = new ArrayList<ProteinProteinInteractionRel>();

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
    public List<ProteinIsoformInteractionRel> getIsoformOutgoingInteractions() {
        List<ProteinIsoformInteractionRel> list = new ArrayList<ProteinIsoformInteractionRel>();

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
    public List<ProteinIsoformInteractionRel> getIsoformIncomingInteractions() {
        List<ProteinIsoformInteractionRel> list = new ArrayList<ProteinIsoformInteractionRel>();

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
    public List<ArticleNode> getArticleCitations() {
        List<ArticleNode> list = new ArrayList<ArticleNode>();

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
    public List<SubmissionNode> getSubmissionCitations() {
        List<SubmissionNode> list = new ArrayList<SubmissionNode>();

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
    public List<OnlineArticleNode> getOnlineArticleCitations() {
        List<OnlineArticleNode> list = new ArrayList<OnlineArticleNode>();

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
    public List<BookNode> getBookCitations() {
        List<BookNode> list = new ArrayList<BookNode>();

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
    public List<PatentNode> getPatentCitations() {
        List<PatentNode> list = new ArrayList<PatentNode>();

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
    public List<ThesisNode> getThesisCitations() {
        List<ThesisNode> list = new ArrayList<ThesisNode>();

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
    public List<UnpublishedObservationNode> getUnpublishedObservationsCitations() {
        List<UnpublishedObservationNode> list = new ArrayList<UnpublishedObservationNode>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, UnpublishedObservationProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new UnpublishedObservationNode(iterator.next()));
        }
        return list;
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------

    public void setName(String value) {
        vertex.setProperty(NAME_PROPERTY, value);
    }

    public void setFullName(String value) {
        vertex.setProperty(FULL_NAME_PROPERTY, value);
    }

    public void setShortName(String value) {
        vertex.setProperty(SHORT_NAME_PROPERTY, value);
    }

    public void setAccession(String value) {
        vertex.setProperty(ACCESSION_PROPERTY, value);
    }

    public void setSequence(String value) {
        vertex.setProperty(SEQUENCE_PROPERTY, value);
    }

    public void setModifiedDate(String value) {
        vertex.setProperty(MODIFIED_DATE_PROPERTY, value);
    }

    public void setMass(float value) {
        vertex.setProperty(MASS_PROPERTY, value);
    }

    public void setLength(int value) {
        vertex.setProperty(LENGTH_PROPERTY, value);
    }

    public void setGeneNames(String[] value) {
        vertex.setProperty(GENE_NAMES_PROPERTY, value);
    }

    public void setEnsemblReferences(String[] value) {
        vertex.setProperty(ENSEMBL_REFERENCES_PROPERTY, value);
    }

    public void setKeggReferences(String[] value) {
        vertex.setProperty(KEGG_REFERENCES_PROPERTY, value);
    }

    public void setPIRReferences(String[] value) {
        vertex.setProperty(PIR_REFERENCES_PROPERTY, value);
    }

    public void setEMBLreferences(String[] value) {
        vertex.setProperty(EMBL_REFERENCES_PROPERTY, value);
    }

    public void setEnsemblPlantsReferences(String[] value) {
        vertex.setProperty(ENSEMBL_PLANTS_REFERENCES_PROPERTY, value);
    }

    public void setRefseqReferences(String[] value) {
        vertex.setProperty(REFSEQ_REFERENCES_PROPERTY, value);
    }

    public void setAlternativeAccessions(String[] value) {
        vertex.setProperty(ALTERNATIVE_ACCESSIONS_PROPERTY, value);
    }

    public void setArrayExpressReferences(String[] value) {
        vertex.setProperty(ARRAY_EXPRESS_REFERENCES_PROPERTY, value);
    }

    public void setUniGeneReferences(String[] value) {
        vertex.setProperty(UNIGENE_REFERENCES_PROPERTY, value);
    }
    
    public void setDBaseEcoliReferences(String[] value) {
        vertex.setProperty(DBASE_ECOLI_REFERENCES_PROPERTY, value);
    }
    
    public void setAarhusGhent2DPageReferences(String[] value) {
        vertex.setProperty(AARHUS_GHENT_2DPAGE_REFERENCES_PROPERTY, value);
    }
    
    public void setAGDReferences(String[] value) {
        vertex.setProperty(AGD_REFERENCES_PROPERTY, value);
    }
    
    public void setAllergomeReferences(String[] value) {
        vertex.setProperty(ALLERGOME_REFERENCES_PROPERTY, value);
    }
    
    public void setAnu2DPageReferences(String[] value) {
        vertex.setProperty(ANU_2DPAGE_REFERENCES_PROPERTY, value);
    }
    
    public void setArachnoserverReferences(String[] value) {
        vertex.setProperty(ARACHNOSERVER_REFERENCES_PROPERTY, value);
    }
    
    public void setBGeeReferences(String[] value) {
        vertex.setProperty(BGEE_REFERENCES_PROPERTY, value);
    }
    
    public void setBindingDBReferences(String[] value) {
        vertex.setProperty(BINDING_DB_REFERENCES_PROPERTY, value);
    }
    
    public void setBiocycReferences(String[] value) {
        vertex.setProperty(BIOCYC_REFERENCES_PROPERTY, value);
    }
    
    public void setBrendaReferences(String[] value) {
        vertex.setProperty(BRENDA_REFERENCES_PROPERTY, value);
    }
    
    public void setCazyReferences(String[] value) {
        vertex.setProperty(CAZY_REFERENCES_PROPERTY, value);
    }
    
    public void setCGDReferences(String[] value) {
        vertex.setProperty(CGD_REFERENCES_PROPERTY, value);
    }
    
    public void setCHEmblReferences(String[] value) {
        vertex.setProperty(CHEMBL_REFERENCES_PROPERTY, value);
    }
    
    public void setCleanexReferences(String[] value) {
        vertex.setProperty(CLEANEX_REFERENCES_PROPERTY, value);
    }
    
    public void setCompluYeastReferences(String[] value) {
        vertex.setProperty(COMPLUYEAST_2DPAGE_REFERENCES_PROPERTY, value);
    }
    
    public void setConserverReferences(String[] value) {
        vertex.setProperty(CONOSERVER_REFERENCES_PROPERTY, value);
    }
    
    public void setCornea2DPageReferences(String[] value) {
        vertex.setProperty(CORNEA_2DPAGE_REFERENCES_PROPERTY, value);
    }
    
    public void setCTDReferences(String[] value) {
        vertex.setProperty(CTD_REFERENCES_PROPERTY, value);
    }
    
    public void setCYGDReferences(String[] value) {
        vertex.setProperty(CYGD_REFERENCES_PROPERTY, value);
    }
    
    public void setDBSNPReferences(String[] value) {
        vertex.setProperty(DBSNP_REFERENCES_PROPERTY, value);
    }
    
    public void setDDBJReferences(String[] value) {
        vertex.setProperty(DDBJ_REFERENCES_PROPERTY, value);
    }
    
    public void setDictyBaseReferences(String[] value) {
        vertex.setProperty(DICTY_BASE_REFERENCES_PROPERTY, value);
    }
    
    public void setDIPReferences(String[] value) {
        vertex.setProperty(DIP_REFERENCES_PROPERTY, value);
    }
    
    public void setDisprotReferences(String[] value) {
        vertex.setProperty(DISPROT_REFERENCES_PROPERTY, value);
    }
    
    public void setDMDMReferences(String[] value) {
        vertex.setProperty(DMDM_REFERENCES_PROPERTY, value);
    }
    
    public void setDnasuReferences(String[] value) {
        vertex.setProperty(DNASU_REFERENCES_PROPERTY, value);
    }
    
    public void setDosacCobs2DPageReferences(String[] value) {
        vertex.setProperty(DOSAC_COBS_2DPAGE_REFERENCES_PROPERTY, value);
    }
    
    public void setDrugbankReferences(String[] value) {
        vertex.setProperty(DRUGBANK_REFERENCES_PROPERTY, value);
    }
    
    public void setEchoBaseReferences(String[] value) {
        vertex.setProperty(ECHOBASE_REFERENCES_PROPERTY, value);
    }
    
    public void setEcogeneReferences(String[] value) {
        vertex.setProperty(ECOGENE_REFERENCES_PROPERTY, value);
    }
    
    public void setEggnogReferences(String[] value) {
        vertex.setProperty(EGGNOG_REFERENCES_PROPERTY, value);
    }
    
    public void setEnsemblBacteriaReferences(String[] value) {
        vertex.setProperty(ENSEMBL_BACTERIA_REFERENCES_PROPERTY, value);
    }
    
    public void setEnsemblFungiReferences(String[] value) {
        vertex.setProperty(ENSEMBL_FUNGI_REFERENCES_PROPERTY, value);
    }
    
    public void setEnsemblMetazoaReferences(String[] value) {
        vertex.setProperty(ENSEMBL_METAZOA_REFERENCES_PROPERTY, value);
    }
    
    public void setEnsemblProtistsReferences(String[] value) {
        vertex.setProperty(ENSEMBL_PROTISTS_REFERENCES_PROPERTY, value);
    }
    
    public void setEuhcvDBReferences(String[] value) {
        vertex.setProperty(EUHCVDB_REFERENCES_PROPERTY, value);
    }
    
    public void setEupathDBReferences(String[] value) {
        vertex.setProperty(EUPATHDB_REFERENCES_PROPERTY, value);
    }
    
    public void setEvolutionaryTraceReferences(String[] value) {
        vertex.setProperty(EVOLUTIONARY_TRACE_REFERENCES_PROPERTY, value);
    }
    
    public void setFlyBaseReferences(String[] value) {
        vertex.setProperty(FLYBASE_REFERENCES_PROPERTY, value);
    }
    
    public void setGenAtlasReferences(String[] value) {
        vertex.setProperty(GENATLAS_REFERENCES_PROPERTY, value);
    }
    
    public void setGenBankReferences(String[] value) {
        vertex.setProperty(GENBANK_REFERENCES_PROPERTY, value);
    }
    
    public void setGene3DReferences(String[] value) {
        vertex.setProperty(GENE3D_REFERENCES_PROPERTY, value);
    }
    
    public void setGeneCardsReferences(String[] value) {
        vertex.setProperty(GENECARDS_REFERENCES_PROPERTY, value);
    }
    
    public void setGeneFarmReferences(String[] value) {
        vertex.setProperty(GENEFARM_REFERENCES_PROPERTY, value);
    }
    
    public void setGeneIDReferences(String[] value) {
        vertex.setProperty(GENEID_REFERENCES_PROPERTY, value);
    }
    
    public void setGeneTreeReferences(String[] value) {
        vertex.setProperty(GENETREE_REFERENCES_PROPERTY, value);
    }
    
    public void setGenevestigatorReferences(String[] value) {
        vertex.setProperty(GENEVESTIGATOR_REFERENCES_PROPERTY, value);
    }
    
    public void setGenolistReferences(String[] value) {
        vertex.setProperty(GENOLIST_REFERENCES_PROPERTY, value);
    }
    
    public void setGenomeReviewsReferences(String[] value) {
        vertex.setProperty(GENOME_REVIEWS_REFERENCES_PROPERTY, value);
    }
    
    public void setGenomeRNAIReferences(String[] value) {
        vertex.setProperty(GENOME_RNAI_REFERENCES_PROPERTY, value);
    }
    
    public void setGermOnlineReferences(String[] value) {
        vertex.setProperty(GERMONLINE_REFERENCES_PROPERTY, value);
    }
    
    public void setGlycoSuiteDBReferences(String[] value) {
        vertex.setProperty(GLYCOSUITEDB_REFERENCES_PROPERTY, value);
    }
    
    public void setGPCRDBReferences(String[] value) {
        vertex.setProperty(GPCRDB_REFERENCES_PROPERTY, value);
    }
    
    public void setGrameneReferences(String[] value) {
        vertex.setProperty(GRAMENE_REFERENCES_PROPERTY, value);
    }
    
    public void setHINVDBReferences(String[] value) {
        vertex.setProperty(HINVDB_REFERENCES_PROPERTY, value);
    }
    
    public void setHamapReferences(String[] value) {
        vertex.setProperty(HAMAP_REFERENCES_PROPERTY, value);
    }
    
    public void setHGNCReferences(String[] value) {
        vertex.setProperty(HGNC_REFERENCES_PROPERTY, value);
    }
    
    public void setHogenomReferences(String[] value) {
        vertex.setProperty(HOGENOM_REFERENCES_PROPERTY, value);
    }
    
    public void setHovergenReferences(String[] value) {
        vertex.setProperty(HOVERGEN_REFERENCES_PROPERTY, value);
    }
    
    public void setHpaReferences(String[] value) {
        vertex.setProperty(HPA_REFERENCES_PROPERTY, value);
    }
    
    public void setHsspReferences(String[] value) {
        vertex.setProperty(HSSP_REFERENCES_PROPERTY, value);
    }
    
    public void setHugeReferences(String[] value) {
        vertex.setProperty(HUGE_REFERENCES_PROPERTY, value);
    }
    
    public void setIMGTReferences(String[] value) {
        vertex.setProperty(IMGT_REFERENCES_PROPERTY, value);
    }
    
    public void setINPARANOIDReferences(String[] value) {
        vertex.setProperty(INPARANOID_REFERENCES_PROPERTY, value);
    }
    
    public void setIntactReferences(String[] value) {
        vertex.setProperty(INTACT_REFERENCES_PROPERTY, value);
    }
    
    public void setIPIReferences(String[] value) {
        vertex.setProperty(IPI_REFERENCES_PROPERTY, value);
    }
    
    public void setKOReferences(String[] value) {
        vertex.setProperty(KO_REFERENCES_PROPERTY, value);
    }
    
    public void setLegioListReferences(String[] value) {
        vertex.setProperty(LEGIO_LIST_REFERENCES_PROPERTY, value);
    }
    
    public void setLepromaReferences(String[] value) {
        vertex.setProperty(LEPROMA_REFERENCES_PROPERTY, value);
    }
    
    public void setMaizeGDBReferences(String[] value) {
        vertex.setProperty(MAIZE_GDB_REFERENCES_PROPERTY, value);
    }
    
    public void setMeropsReferences(String[] value) {
        vertex.setProperty(MEROPS_REFERENCES_PROPERTY, value);
    }
    
    public void setMGIReferences(String[] value) {
        vertex.setProperty(MGI_REFERENCES_PROPERTY, value);
    }
    
    
    
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
    public static final String VECTOR_BASE_REFERENCES_PROPERTY = "protein_vector_base_references";
    public static final String WORLD_2DPAGE_REFERENCES_PROPERTY = "protein_world_2dpage_references";
    public static final String WORM_BASE_REFERENCES_PROPERTY = "protein_worm_base_references";
    public static final String XEN_BASE_REFERENCES_PROPERTY = "protein_xen_base_references";
    public static final String ZFIN_REFERENCES_PROPERTY = "protein_zfin_references";
}
