package dao;

import java.util.List;

import relation.Relation;

public interface Dao {
	public List<Relation> extractRelationByCodeId(String id);
}
