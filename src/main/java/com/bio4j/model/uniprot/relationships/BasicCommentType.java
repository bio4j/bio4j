package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.RelationshipType;

public interface BasicCommentType <

S extends Node<S,ST>,
ST extends Enum<ST> & NodeType<S,ST>,
R extends BasicComment<S,ST,R,RT,T,TT>, 
RT extends Enum<RT> & BasicCommentType<S,ST,R,RT,T,TT>,
T extends Node<T,TT>,
TT extends Enum<TT> & NodeType<T,TT>

> extends RelationshipType<S,ST, R,RT, T,TT> {}