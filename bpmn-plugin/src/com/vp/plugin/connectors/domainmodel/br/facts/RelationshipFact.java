package com.vp.plugin.connectors.domainmodel.br.facts;

import com.vp.plugin.connectors.domainmodel.br.terms.Term;

/**
 * 
 * @author Grzegorz Mikitowicz
 *
 */
public class RelationshipFact extends Fact {

	private Term leftTerm;
	private String leftMultiplicity;
	private String verb;
	private String rightMultiplicity;
	private Term rightTerm;

	private RelationshipFact(Term leftTerm, String leftMultiplicity, String verb, String rightMultiplicity,
			Term rightTerm) {
		this.leftTerm = leftTerm;
		this.leftMultiplicity = leftMultiplicity;
		this.verb = verb;
		this.rightMultiplicity = rightMultiplicity;
		this.rightTerm = rightTerm;
	}

	public Term getLeftTerm() {
		return leftTerm;
	}

	public void setLeftTerm(Term leftTerm) {
		this.leftTerm = leftTerm;
	}

	public String getLeftMultiplicity() {
		return leftMultiplicity;
	}

	public void setLeftMultiplicity(String leftMultiplicity) {
		this.leftMultiplicity = leftMultiplicity;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public String getRightMultiplicity() {
		return rightMultiplicity;
	}

	public void setRightMultiplicity(String rightMultiplicity) {
		this.rightMultiplicity = rightMultiplicity;
	}

	public Term getRightTerm() {
		return rightTerm;
	}

	public void setRightTerm(Term rightTerm) {
		this.rightTerm = rightTerm;
	}

	public static class RelationshipFactBuilder {
		private Term leftTerm;
		private String leftMultiplicity;
		private String verb;
		private String rightMultiplicity;
		private Term rightTerm;

		public RelationshipFactBuilder leftTerm(Term term) {
			this.leftTerm = term;
			return this;
		}

		public RelationshipFactBuilder rightTerm(Term term) {
			this.rightTerm = term;
			return this;
		}

		public RelationshipFactBuilder verb(String verb) {
			this.verb = verb;
			return this;
		}

		public RelationshipFactBuilder leftMultiplicity(String multiplicity) {
			this.leftMultiplicity = multiplicity;
			return this;
		}

		public RelationshipFactBuilder rightMultiplicity(String multiplicity) {
			this.rightMultiplicity = multiplicity;
			return this;
		}

		public RelationshipFact build() {
			return new RelationshipFact(leftTerm, leftMultiplicity, verb, rightMultiplicity, rightTerm);
		}

	}

}
