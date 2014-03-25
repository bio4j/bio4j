package com.bio4j.model.indices.article;

import com.bio4j.model.indices.NodeUniqueIndex;
import com.bio4j.model.nodes.citation.Article;

public interface ByDoiId extends NodeUniqueIndex<Article, Article.type, String> {}
