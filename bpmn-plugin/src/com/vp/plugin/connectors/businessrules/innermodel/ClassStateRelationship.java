package com.vp.plugin.connectors.businessrules.innermodel;

public enum ClassStateRelationship {
	HAS_STATE("has state"), IS_STATE_OF("is state of");

	private String value;

	private ClassStateRelationship(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
