package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.CommentType;

public interface BasicCommentType <
  R extends BasicComment<R,RT>, 
  RT extends Enum<RT> & BasicCommentType<R,RT>
> extends RelationshipType<Protein, Protein.Type, R, RT, CommentType, CommentType.Type> {}