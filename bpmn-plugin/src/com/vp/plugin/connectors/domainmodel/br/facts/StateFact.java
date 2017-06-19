package com.vp.plugin.connectors.domainmodel.br.facts;

import com.vp.plugin.connectors.domainmodel.br.terms.Term;

public class StateFact {
	private Term term;

	private String state;

	public StateFact(Term term, String state) {
		this.term = term;
		this.state = state;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "class: " + term + ", state: " + state;
	}
}
