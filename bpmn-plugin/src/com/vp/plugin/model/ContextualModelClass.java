package com.vp.plugin.model;

import org.apache.commons.lang.StringUtils;

public class ContextualModelClass {

	private long id;

	private String name;

	public ContextualModelClass(String name) {
		if (name == null || StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("Specified class name can not be null or blank");
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
