
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.properties.DoId;
import com.bio4j.model.properties.MedlineId;
import com.bio4j.model.properties.PubmedId;
import com.bio4j.model.properties.Title;
import com.bio4j.model.uniprot.nodes.Consortium;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.relationships.article.ArticleAuthor;
import com.bio4j.model.uniprot.relationships.article.ArticleJournal;
import com.bio4j.model.uniprot.relationships.article.ArticleProteinCitation;

import java.util.List;

import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Article extends Node<Article, Article.Type>,
	Title<Article, Article.Type>,
	PubmedId<Article, Article.Type>,
	MedlineId<Article, Article.Type>,
	DoId<Article, Article.Type>
	{
  
	  public static Type TYPE = Type.article;
	  
	  public static enum Type implements NodeType<Article, Article.Type> {
	    article;
	    public Type value() { return article; }
	  }   
	  
	// articleProteinCitation
	// outgoing
	public List<ArticleProteinCitation> articleProteinCitation_out(); 
	public List<Protein> articleProteinCitation_outNodes();
	
	// articleJournal
	// outgoing
	public ArticleJournal articleJournal_out(); 
	public Journal articleJournal_outNodes();
	
	// articleAuthor
	// outgoing
	public List<ArticleAuthor> articleAuthor_out(); 
	// this does not make any sense
	public <T extends Person & Consortium> T articleAuthor_outNodes();
	       
    
    
}
