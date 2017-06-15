package com.vp.plugin.connectors.businessrules;

public enum ClassAttributeRelationship {

	HAS("has"), IS_PROPERTY_OF("is property of");

	private String value;

	private ClassAttributeRelationship(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
