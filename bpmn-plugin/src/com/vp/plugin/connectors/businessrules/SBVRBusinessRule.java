package com.vp.plugin.connectors.businessrules;

import java.util.List;

public class SBVRBusinessRule {

	private String text;
	private List<SBVRBusinessRulePart> businessRuleParts;

	public SBVRBusinessRule(String text, List<SBVRBusinessRulePart> businessRuleParts) {
		this.text = text;
		this.businessRuleParts = businessRuleParts;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<SBVRBusinessRulePart> getBusinessRuleParts() {
		return businessRuleParts;
	}

	public void setBusinessRuleParts(List<SBVRBusinessRulePart> businessRuleParts) {
		this.businessRuleParts = businessRuleParts;
	}

}
