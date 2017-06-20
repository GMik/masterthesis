package com.vp.plugin.connectors.businessrules.innermodel;

public class SBVRFact {

	private SBVRTerm term1;
	private String term1Role;

	private String relationship;
	private RelationshipType relationshipType;

	private SBVRTerm term2;
	private String term2Role;

	private String[] factWords;

	public SBVRFact(SBVRTerm term1, String term1Role, String relationship, RelationshipType relationshipType,
			SBVRTerm term2, String term2Role, String[] factWords) {
		super();
		this.term1 = term1;
		this.term1Role = term1Role;
		this.relationship = relationship;
		this.relationshipType = relationshipType;
		this.term2 = term2;
		this.term2Role = term2Role;
		this.factWords = factWords;
	}

	@Override
	public String toString() {
		return relationship + "(" + this.term1 + "," + this.term2 + ")";
	}

	public SBVRTerm getTerm1() {
		return term1;
	}

	public void setTerm1(SBVRTerm term1) {
		this.term1 = term1;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public SBVRTerm getTerm2() {
		return term2;
	}

	public void setTerm2(SBVRTerm term2) {
		this.term2 = term2;
	}

	public RelationshipType getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(RelationshipType relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getTerm1Role() {
		return term1Role;
	}

	public void setTerm1Role(String term1Role) {
		this.term1Role = term1Role;
	}

	public String getTerm2Role() {
		return term2Role;
	}

	public void setTerm2Role(String term2Role) {
		this.term2Role = term2Role;
	}

	public String[] getFactWords() {
		return factWords;
	}

	public void setFactWords(String[] factWords) {
		this.factWords = factWords;
	}

}
