package com.vp.plugin.connectors.businessrules.utils;

public enum SBVRLogicalOperations {

	AND("and"), OR("or"), IF("if"), THEN("then"), NEG("it is not the case that");

	private String value;

	private SBVRLogicalOperations(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
