package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;

import java.util.List;

import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.go.nodes.Term.Type;
import com.bio4j.model.properties.Name;
import com.bio4j.model.uniprot.relationships.OnlineArticleJournal;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface OnlineJournal extends Node<OnlineJournal, OnlineJournal.Type>,

	Name<OnlineJournal, OnlineJournal.Type> {

	public static Type TYPE = Type.onlineJournal;
	public default Type type() { return TYPE; }

	public static enum Type implements
			NodeType<OnlineJournal, OnlineJournal.Type> {

		onlineJournal;
		public Type value() {
			return onlineJournal;
		}
	}
	
	// onlineArticleJournal
    // ingoing
    public List<OnlineArticleJournal> onlineArticleJournal_in(); 
    public List<OnlineArticle> onlineArticleJournal_inNodes();

	public List<OnlineArticle> getOnlineArticles();
}
