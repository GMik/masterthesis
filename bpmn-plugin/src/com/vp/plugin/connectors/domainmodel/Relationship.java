package com.vp.plugin.connectors.domainmodel;

import java.util.List;

import com.vp.plugin.model.IClass;

public class Relationship {

	private String name;
	private List<IClass> classes;

	public Relationship() {

	}

	public Relationship(String name, List<IClass> classes) {
		this.name = name;
		this.classes = classes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<IClass> getClasses() {
		return classes;
	}

	public void setClasses(List<IClass> classes) {
		this.classes = classes;
	}

}
