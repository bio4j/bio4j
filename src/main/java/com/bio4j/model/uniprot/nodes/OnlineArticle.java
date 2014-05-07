package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.bio4j.model.uniprot.nodes.Consortium;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.relationships.OnlineArticleJournal;
import com.bio4j.model.uniprot.relationships.OnlineArticleProteinCitation;

import java.util.List;

import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.properties.Title;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface OnlineArticle extends Node<OnlineArticle, OnlineArticle.Type>,

	// properties
	Title<OnlineArticle, OnlineArticle.Type> {

	public static Type TYPE = Type.onlineArticle;

	public static enum Type implements
			NodeType<OnlineArticle, OnlineArticle.Type> {

		onlineArticle;
		public Type value() {
			return onlineArticle;
		}
	}
	
	// onlineArticleJournal
    // outGoing
    public List<OnlineArticleJournal> onlineArticleJournal_out(); 
    public List<OnlineJournal> onlineArticleJournal_outNodes();

    // onlineArticleProteinCitation
    // outGoing
    public List<OnlineArticleProteinCitation> onlineArticleProteinCitation_out(); 
    public List<Protein> onlineArticleProteinCitation_outNodes();

	public List<Consortium> getConsortiumAuthors();

	public List<Person> getPersonAuthors();

}
