package com.vp.plugin.connectors.domainmodel.br.facts;

import com.vp.plugin.connectors.domainmodel.br.terms.Term;

public class GeneralizationFact {
	private Term superclass;
	private String verb;
	private Term subclass;

	public GeneralizationFact(Term superclass, String verb, Term subclass) {
		this.superclass = superclass;
		this.verb = verb;
		this.subclass = subclass;
	}

	public Term getSuperclass() {
		return superclass;
	}

	public void setSuperclass(Term superclass) {
		this.superclass = superclass;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public Term getSubclass() {
		return subclass;
	}

	public void setSubclass(Term subclass) {
		this.subclass = subclass;
	}

	@Override
	public String toString() {
		return verb + "(" + superclass + " <|---" + subclass + ")";
	}

}
