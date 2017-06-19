package com.vp.plugin.connectors.domainmodel.br.facts;

public enum AggregationKind {
	ASSOCIATION("None"), COMPOSITION("Composited"), AGGREGATION("Aggregated");

	private String value;

	private AggregationKind(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
