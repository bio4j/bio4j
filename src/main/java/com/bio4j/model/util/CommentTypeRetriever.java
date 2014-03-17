package com.bio4j.model.util;

import com.bio4j.model.nodes.CommentType;

public interface CommentTypeRetriever extends NodeRetriever<CommentType> {

  public CommentType getCommentTypeByName(String commentTypeName);

}
