package com.vp.plugin.connectors.businessrules;

import java.util.ArrayList;
import java.util.List;

import com.vp.plugin.utils.validation.ValidationResult;

public class SBVRFileElementsContainer {

	private List<SBVRClassAttributeTerm> attributeTerms = new ArrayList<>();
	private List<SBVRClassAttributeFact> attributeFacts = new ArrayList<>();

	private List<SBVRTerm> terms = new ArrayList<>();
	private List<SBVRFact> facts = new ArrayList<>();

	private List<ValidationResult> validationResults = new ArrayList<>();

	public void add(ValidationResult validationResult) {
		validationResults.add(validationResult);
	}

	public void addTerm(SBVRTerm term) {
		terms.add(term);
	}

	public void addFact(SBVRFact fact) {
		facts.add(fact);
	}

	public void addAttributeTerm(SBVRClassAttributeTerm attributeTerm) {
		attributeTerms.add(attributeTerm);
	}

	public void addAttributeFact(SBVRClassAttributeFact attributeFact) {
		attributeFacts.add(attributeFact);
	}

	public List<SBVRClassAttributeTerm> getAttributeTerms() {
		return attributeTerms;
	}

	public void setAttributeTerms(List<SBVRClassAttributeTerm> attributeTerms) {
		this.attributeTerms = attributeTerms;
	}

	public List<SBVRClassAttributeFact> getAttributeFacts() {
		return attributeFacts;
	}

	public void setAttributeFacts(List<SBVRClassAttributeFact> attributeFacts) {
		this.attributeFacts = attributeFacts;
	}

	public List<SBVRTerm> getTerms() {
		return terms;
	}

	public void setTerms(List<SBVRTerm> terms) {
		this.terms = terms;
	}

	public List<SBVRFact> getFacts() {
		return facts;
	}

	public void setFacts(List<SBVRFact> facts) {
		this.facts = facts;
	}

	public List<ValidationResult> getValidationResults() {
		return validationResults;
	}

	public void setValidationResults(List<ValidationResult> validationResults) {
		this.validationResults = validationResults;
	}

}
