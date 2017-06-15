package com.vp.plugin.connectors.businessrules;

public class SBVRBusinessRulePart {

	// one of these MUST be null
	private SBVRFact fact;
	private SBVRClassAttributeFact classAttributeFact;
	private boolean isNegated;

	public SBVRBusinessRulePart() {

	}

	public SBVRBusinessRulePart(SBVRFact fact, SBVRClassAttributeFact classAttributeFact) {
		this.fact = fact;
		this.classAttributeFact = classAttributeFact;
	}

	public SBVRFact getFact() {
		return fact;
	}

	public void setFact(SBVRFact fact) {
		this.fact = fact;
	}

	public SBVRClassAttributeFact getClassAttributeFact() {
		return classAttributeFact;
	}

	public void setClassAttributeFact(SBVRClassAttributeFact classAttributeFact) {
		this.classAttributeFact = classAttributeFact;
	}

	public boolean isNegated() {
		return isNegated;
	}

	public void setNegated(boolean isNegated) {
		this.isNegated = isNegated;
	}

}
