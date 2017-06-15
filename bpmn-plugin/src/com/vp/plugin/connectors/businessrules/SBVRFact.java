package com.vp.plugin.connectors.businessrules;

public class SBVRFact {

	private SBVRTerm term1;
	private String association;
	private SBVRTerm term2;

	public SBVRFact(SBVRTerm term1, String association, SBVRTerm term2) {
		this.term1 = term1;
		this.association = association;
		this.term2 = term2;
	}

	public SBVRTerm getTerm1() {
		return term1;
	}

	public void setTerm1(SBVRTerm term1) {
		this.term1 = term1;
	}

	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
	}

	public SBVRTerm getTerm2() {
		return term2;
	}

	public void setTerm2(SBVRTerm term2) {
		this.term2 = term2;
	}

}
