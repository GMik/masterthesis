package com.vp.plugin.connectors.businessrules;

public class SBVRClassAttributeTerm {

	private SBVRTerm term;
	private String attributeTerm;

	public SBVRClassAttributeTerm(SBVRTerm term, String attributeTerm) {
		this.term = term;
		this.attributeTerm = attributeTerm;
	}

	public SBVRTerm getTerm() {
		return term;
	}

	public void setTerm(SBVRTerm term) {
		this.term = term;
	}

	public String getAttributeTerm() {
		return attributeTerm;
	}

	public void setAttributeTerm(String attributeTerm) {
		this.attributeTerm = attributeTerm;
	}

}
