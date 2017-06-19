package com.vp.plugin.connectors.domainmodel.br.facts;

import com.vp.plugin.connectors.domainmodel.br.terms.Term;

public class ClassFact extends Fact {

	private Term term;

	private String property;

	public ClassFact(Term term, String property) {
		this.term = term;
		this.property = property;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return "class: " + term + ", attribute: " + property;
	}

}
