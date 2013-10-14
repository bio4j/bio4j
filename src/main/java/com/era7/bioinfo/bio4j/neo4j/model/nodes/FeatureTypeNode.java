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

package com.era7.bioinfo.bio4j.neo4j.model.nodes;

import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import org.neo4j.graphdb.Node;

/**
 * This class models precise but simple means for the annotation of sequence data.
 * It describes regions or sites of interest in the protein sequence.
 * In general it lists post-translational modifications, binding sites, enzyme active sites,
 * local secondary structure or other characteristics reported in the cited references.
 * Sequence conflicts between references are also included in this section. <br>
 * Features modelled list:
 * <br><br><b>Molecule processing</b><br>
 * <table>
 * <tr><td><b>Initiator methionine:</b></td><td>Cleavage of the initiator methionine</td></tr>
 * <tr><td><b>Signal:</b></td><td>Sequence targeting proteins to the secretory pathway or periplasmic space</td></tr>
 * <tr><td><b>Transit peptide:</b></td><td>Extent of a transit peptide for organelle targeting</td></tr>
 * <tr><td><b>Propeptide:</b></td><td>Part of a protein that is cleaved during maturation or activation </td></tr>
 * <tr><td><b>Chain:</b></td><td>Extent of a polypeptide chain in the mature protein</td></tr>
 * <tr><td><b>Peptide:</b></td><td>Extent of an active peptide in the mature protein</td></tr>
 * </table>
 * <br><b>Regions</b><br><br>
 * <table>
 * <tr><td><b>Topological domain:</b></td><td> Location of non-membrane regions of membrane-spanning proteins</td></tr>
 * <tr><td><b>Transmembrane:</b></td><td> Extent of a membrane-spanning region</td></tr>
 * <tr><td><b>Intramembrane:</b></td><td> Extent of a region located in a membrane without crossing it</td></tr>
 * <tr><td><b>Domain:</b></td><td> Position and type of each modular protein domain</td></tr>
 * <tr><td><b>Repeat:</b></td><td> Positions of repeated sequence motifs or repeated domains</td></tr>
 * <tr><td><b>Calcium binding:</b></td><td> Position(s) of calcium binding region(s) within the protein</td></tr>
 * <tr><td><b>Zinc finger:</b></td><td> Position(s) and type(s) of zinc fingers within the protein</td></tr>
 * <tr><td><b>DNA binding:</b></td><td> Position and type of a DNA-binding domain</td></tr>
 * <tr><td><b>Nucleotide binding:</b></td><td> Nucleotide phosphate binding region</td></tr>
 * <tr><td><b>Region:</b></td><td> Region of interest in the sequence</td></tr>
 * <tr><td><b>Coiled coil:</b></td><td> Positions of regions of coiled coil whithin the protein</td></tr>
 * <tr><td><b>Motif:</b></td><td> Short (up to 20 amino acids) sequence motif of biological interest</td></tr>
 * <tr><td><b>Compositional bias:</b></td><td> Region of compositional bias in the protein</td></tr>
 * </table>
 * <br><b>Sites</b><br><br>
 * <table>
 * <tr><td><b>Active site:</b></td><td> Amino acid(s) directly involved in the activity of an enzyme</td></tr>
 * <tr><td><b>Metal binding:</b></td><td> Binding site for a metal ion</td></tr>
 * <tr><td><b>Binding site:</b></td><td> Binding site for any chemical group (co-enzyme, prosthetic group, ect.)</td></tr>
 * <tr><td><b>Site:</b></td><td> Any interesting single amino acid site on the sequence</td></tr>
 * </table>
 * <br><b>Amino acid modifications</b><br><br>
 * <table>
 * <tr><td><b>Non-standard residue:</b></td><td> Occurrence of non-standard amino acids (selenocysteine an pyrrolysine) in the protein sequence</td></tr>
 * <tr><td><b>Modified residue:</b></td><td> Modified residues excluding lipids, glycans and protein cross-links</td></tr>
 * <tr><td><b>Lipidation:</b></td><td> Covalently attached lipid group(s)</td></tr>
 * <tr><td><b>Glycosylation:</b></td><td> Covalently attached glycan group(s)</td></tr>
 * <tr><td><b>Disulfide bond:</b></td><td> Cysteine residues participating in disulfide bonds</td></tr>
 * <tr><td><b>Cross-link:</b></td><td> Residues participating in covalent linkage(s) between proteins</td></tr>
 * </table>
 * <br><b>Natural variations</b><br><br>
 * <table>
 * <tr><td><b>Alternative sequence:</b></td><td> Amino acid change(s) producing alternate protein isoforms</td></tr>
 * <tr><td><b>Natural variant:</b></td><td> Description of a natural variant of the protein</td></tr>
 * </table>
 * <br><b>Experimental info</b><br><br>
 * <table>
 * <tr><td><b>Mutagenesis:</b></td><td> Site which has been experimentally altered by mutagenesis</td></tr>
 * <tr><td><b>Sequence uncertainty:</b></td><td> Regions of uncertainty in the sequence</td></tr>
 * <tr><td><b>Sequence conflict:</b></td><td> Description of sequence discrepancies of unknown origin</td></tr>
 * <tr><td><b>Non-adjacent residues:</b></td><td> Indicates that two residues in a sequence are not consecutive</td></tr>
 * <tr><td><b>Non-terminal residue:</b></td><td> The sequence is incomplete. Indicate that a residue is not the terminal residue of the complete protein</td></tr>
 * </table>
 * <br><b>Secondary structure</b><br><br>
 * <table>
 * <tr><td><b>Helix:</b></td><td> Helical regions within the experimentally determined protein structure</td></tr>
 * <tr><td><b>Turn:</b></td><td> Turns within the experimentally determined protein structure</td></tr>
 * <tr><td><b>Beta strand:</b></td><td> Beta strand regions within the experimentally determined protein structure</td></tr>
 * </table>
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class FeatureTypeNode extends BasicEntity{

    public static final String FEATURE_TYPE_NAME_INDEX = "feature_type_name_index";

    public static final String NODE_TYPE = FeatureTypeNode.class.getCanonicalName();

    /** Feature type name **/
    public static final String NAME_PROPERTY = "feature_type_name";


    public FeatureTypeNode(Node n){
        super(n);
    }


    public String getName(){    return String.valueOf(node.getProperty(NAME_PROPERTY));}


    public void setName(String value){  node.setProperty(NAME_PROPERTY, value);}


    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FeatureTypeNode){
            FeatureTypeNode other = (FeatureTypeNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "Feature type --> " + this.getName();
    }

}
