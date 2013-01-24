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

package com.era7.bioinfo.bio4j.neo4j.model.nodes.citation;

import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.onarticle.OnlineArticleJournalRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * Online journals where online article protein citations are published
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class OnlineJournalNode extends BasicEntity{

    public static final String ONLINE_JOURNAL_NAME_INDEX = "online_journal_name_index";

    public static final String NODE_TYPE = OnlineJournalNode.class.getCanonicalName();

    public static final String NAME_PROPERTY = "online_journal_name";


    public OnlineJournalNode(Node n){
        super(n);
    }


    public String getName(){    return String.valueOf(node.getProperty(NAME_PROPERTY));}


    public void setName(String value){  node.setProperty(NAME_PROPERTY, value);}

    
    public List<OnlineArticleNode> getOnlineArticles(){
        List<OnlineArticleNode> list = new LinkedList<OnlineArticleNode>();
        Iterator<Relationship> iterator = node.getRelationships(new OnlineArticleJournalRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new OnlineArticleNode(iterator.next().getStartNode()));
        }
        return list;
    }
    

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof OnlineJournalNode){
            OnlineJournalNode other = (OnlineJournalNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "name = " + getName();
    }

}

