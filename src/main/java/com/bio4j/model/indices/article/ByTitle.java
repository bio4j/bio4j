package com.bio4j.model.indices.article;

import com.bio4j.model.indices.NodeListIndex;
import com.bio4j.model.uniprot.nodes.Article;

public interface ByTitle extends NodeListIndex<Article, Article.type, String> {}
