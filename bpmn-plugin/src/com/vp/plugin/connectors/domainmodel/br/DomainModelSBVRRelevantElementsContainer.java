package com.vp.plugin.connectors.domainmodel.br;

import java.util.List;

import com.vp.plugin.connectors.domainmodel.br.facts.ClassFact;
import com.vp.plugin.connectors.domainmodel.br.facts.RelationshipFact;
import com.vp.plugin.connectors.domainmodel.br.terms.Term;

public class DomainModelSBVRRelevantElementsContainer {

	private List<Term> terms;

	private List<ClassFact> classFacts;

	private List<RelationshipFact> relationshipFacts;

	public List<Term> getTerms() {
		return terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	public List<ClassFact> getClassFacts() {
		return classFacts;
	}

	public void setClassFacts(List<ClassFact> classFacts) {
		this.classFacts = classFacts;
	}

	public List<RelationshipFact> getRelationshipFacts() {
		return relationshipFacts;
	}

	public void setRelationshipFacts(List<RelationshipFact> relationshipFacts) {
		this.relationshipFacts = relationshipFacts;
	}

}
