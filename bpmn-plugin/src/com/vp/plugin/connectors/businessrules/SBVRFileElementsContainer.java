package com.vp.plugin.connectors.businessrules;

import java.util.ArrayList;
import java.util.List;

import com.vp.plugin.utils.validation.ValidationResult;

public class SBVRFileElementsContainer {

	private List<SBVRClassCharacteristicTerm> characteristicTerms = new ArrayList<>();
	private List<SBVRClassCharacteristicFact> characteristicFacts = new ArrayList<>();

	private List<SBVRTerm> terms = new ArrayList<>();
	private List<SBVRFact> facts = new ArrayList<>();

	private List<SBVRBusinessRule> businessRules = new ArrayList<>();

	private List<ValidationResult> validationResults = new ArrayList<>();

	public void add(SBVRBusinessRule businessRule) {
		businessRules.add(businessRule);
	}

	public void add(ValidationResult validationResult) {
		validationResults.add(validationResult);
	}

	public void addTerm(SBVRTerm term) {
		terms.add(term);
	}

	public void addFact(SBVRFact fact) {
		facts.add(fact);
	}

	public void addAttributeTerm(SBVRClassCharacteristicTerm attributeTerm) {
		characteristicTerms.add(attributeTerm);
	}

	public void addAttributeFact(SBVRClassCharacteristicFact attributeFact) {
		characteristicFacts.add(attributeFact);
	}

	public List<SBVRClassCharacteristicTerm> getCharacteristicTerms() {
		return characteristicTerms;
	}

	public void setCharacteristicTerms(List<SBVRClassCharacteristicTerm> characteristicTerms) {
		this.characteristicTerms = characteristicTerms;
	}

	public List<SBVRClassCharacteristicFact> getCharacteristicFacts() {
		return characteristicFacts;
	}

	public void setCharacteristicFacts(List<SBVRClassCharacteristicFact> characteristicFacts) {
		this.characteristicFacts = characteristicFacts;
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

	public List<SBVRBusinessRule> getBusinessRules() {
		return businessRules;
	}

	public void setBusinessRules(List<SBVRBusinessRule> businessRules) {
		this.businessRules = businessRules;
	}

}
