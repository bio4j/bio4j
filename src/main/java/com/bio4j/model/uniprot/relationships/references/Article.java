package com.bio4j.model.uniprot.relationships.references;

import java.util.List;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// properties
import com.bio4j.model.properties.DoId;
import com.bio4j.model.properties.MedlineId;
import com.bio4j.model.properties.PubmedId;
import com.bio4j.model.properties.Title;

import com.bio4j.model.uniprot.nodes.references.Articles;
import com.bio4j.model.uniprot.nodes.references.Reference;

/**
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Article extends Relationship<

Reference, Reference.Type, Article, Article.Type, Articles, Articles.Type

	>,

		// properties
		Title<Article, Article.Type>, PubmedId<Article, Article.Type>,
		MedlineId<Article, Article.Type>, DoId<Article, Article.Type> {

	public static Type TYPE = Type.article;

	public static enum Type implements RelationshipType<

	Reference, Reference.Type, Article, Article.Type, Articles, Articles.Type> {
		article;

		// there is only one Articles node => many to one.
		public Arity arity() {
			return Arity.manyToOne;
		}

		public Reference.Type sourceType() {
			return Reference.TYPE;
		}

		public Articles.Type targetType() {
			return Articles.TYPE;
		}

		public Type value() {
			return article;
		}
	}
}
