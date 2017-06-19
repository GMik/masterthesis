package com.vp.plugin.connectors.businessrules;

public class SBVRBusinessRulePart {

	// currently not supported
	private boolean isNegated;

	// one of these MUST be null
	private SBVRFact fact;
	private SBVRClassCharacteristicFact classAttributeFact;

	public SBVRBusinessRulePart(SBVRFact fact, SBVRClassCharacteristicFact classAttributeFact) {
		this.fact = fact;
		this.classAttributeFact = classAttributeFact;
	}

	public SBVRFact getFact() {
		return fact;
	}

	public void setFact(SBVRFact fact) {
		this.fact = fact;
	}

	public SBVRClassCharacteristicFact getClassAttributeFact() {
		return classAttributeFact;
	}

	public void setClassAttributeFact(SBVRClassCharacteristicFact classAttributeFact) {
		this.classAttributeFact = classAttributeFact;
	}

	public boolean isNegated() {
		return isNegated;
	}

	public void setNegated(boolean isNegated) {
		this.isNegated = isNegated;
	}

}
