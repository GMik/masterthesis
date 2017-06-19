package com.vp.plugin.connectors.businessrules;

import java.util.HashMap;
import java.util.Map;

public class RelationshipSelector {

	private static final Map<String, RelationshipType> map = new HashMap<>();

	static {
		map.put("owns", RelationshipType.COMPOSITION);
		map.put("is owned by", RelationshipType.COMPOSITION);
		map.put("extends", RelationshipType.GENERALIZATION);
		map.put("is extended by", RelationshipType.GENERALIZATION);

	}

	public static RelationshipType retrieveFrom(String relationship) {
		return map.get(relationship) == null ? RelationshipType.ASSOCIATION : map.get(relationship);
	}

}
