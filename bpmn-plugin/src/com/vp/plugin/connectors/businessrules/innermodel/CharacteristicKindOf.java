package com.vp.plugin.connectors.businessrules.innermodel;

public enum CharacteristicKindOf {
	ATTRIBUTE("attribute"), STATE("state");

	private String value;

	private CharacteristicKindOf(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
