package com.vp.plugin.connectors.businessrules;

public class SBVRClassAttributeFact {

	private SBVRClassAttributeTerm classAttributeTerm;
	private ClassAttributeRelationship relationship;

	public SBVRClassAttributeFact(SBVRClassAttributeTerm classAttributeTerm, ClassAttributeRelationship relationship) {
		this.classAttributeTerm = classAttributeTerm;
		this.relationship = relationship;
	}

	public SBVRClassAttributeTerm getClassAttributeTerm() {
		return classAttributeTerm;
	}

	public void setClassAttributeTerm(SBVRClassAttributeTerm classAttributeTerm) {
		this.classAttributeTerm = classAttributeTerm;
	}

	public ClassAttributeRelationship getRelationship() {
		return relationship;
	}

	public void setRelationship(ClassAttributeRelationship relationship) {
		this.relationship = relationship;
	}

}
