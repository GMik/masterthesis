package com.vp.plugin.connectors.domainmodel.br.facts;

import com.vp.plugin.connectors.domainmodel.br.terms.Term;

/**
 * 
 * @author Grzegorz Mikitowicz
 *
 */
public class RelationshipFact extends Fact {

	private AggregationKind aggregationKind;

	private Term leftTerm;
	private String leftTermRole;
	private String leftMultiplicity;
	private String verb;
	private String rightMultiplicity;
	private Term rightTerm;
	private String rightTermRole;

	public RelationshipFact(AggregationKind aggregationKind, Term leftTerm, String leftTermRole,
			String leftMultiplicity, String verb, String rightMultiplicity, Term rightTerm, String rightTermRole) {
		this.aggregationKind = aggregationKind;
		this.leftTerm = leftTerm;
		this.leftTermRole = leftTermRole;
		this.leftMultiplicity = leftMultiplicity;
		this.verb = verb;
		this.rightMultiplicity = rightMultiplicity;
		this.rightTerm = rightTerm;
		this.rightTermRole = rightTermRole;
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

	public void setAggregationKind(AggregationKind aggregationKind) {
		this.aggregationKind = aggregationKind;
	}

	public AggregationKind getAggregationKind() {
		return aggregationKind;
	}

	public String getLeftTermRole() {
		return leftTermRole;
	}

	public void setLeftTermRole(String leftTermRole) {
		this.leftTermRole = leftTermRole;
	}

	public String getRightTermRole() {
		return rightTermRole;
	}

	public void setRightTermRole(String rightTermRole) {
		this.rightTermRole = rightTermRole;
	}

	@Override
	public String toString() {
		return aggregationKind + " - " + verb + "(" + this.leftTerm + "(role: " + leftTermRole + ")" + ","
				+ this.rightTerm + "(role: " + rightTermRole + "))";
	}

	public static class RelationshipFactBuilder {
		private Term leftTerm;
		private String leftRole;
		private String leftMultiplicity;
		private String verb;
		private String rightMultiplicity;
		private Term rightTerm;
		private String rightRole;
		private AggregationKind aggregationKind;

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

		public RelationshipFactBuilder aggregationKind(AggregationKind aggregationKind) {
			this.aggregationKind = aggregationKind;
			return this;
		}

		public RelationshipFactBuilder leftRole(String leftRole) {
			this.leftRole = leftRole;
			return this;
		}

		public RelationshipFactBuilder rightRole(String rightRole) {
			this.rightRole = rightRole;
			return this;
		}

		public RelationshipFact build() {
			return new RelationshipFact(aggregationKind, leftTerm, leftRole, leftMultiplicity, verb, rightMultiplicity,
					rightTerm, rightRole);
		}

	}

}
