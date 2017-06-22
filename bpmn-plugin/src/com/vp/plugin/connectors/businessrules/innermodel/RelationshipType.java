package com.vp.plugin.connectors.businessrules.innermodel;

public enum RelationshipType {
	ASSOCIATION("Association"), COMPOSITION("Composition"), AGGREGATION("Aggregation"), GENERALIZATION(
			"Generalization");

	private RelationshipType(String value) {
		this.value = value;
	}

	private String value;

	@Override
	public String toString() {
		return value;
	}

}
