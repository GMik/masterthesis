package com.vp.plugin.connectors.businessrules.innermodel;

public class SBVRClassCharacteristicFact {
	private SBVRClassCharacteristicTerm classCharacteristicTerm;
	private ClassAttributeRelationship attributeRelationship;
	private ClassStateRelationship stateRelationship;
	private CharacteristicKindOf characteristicKinfOf;
	private String[] factWords;

	public SBVRClassCharacteristicFact(SBVRClassCharacteristicTerm classCharacteristicTerm,
			ClassAttributeRelationship attributeRelationship, ClassStateRelationship stateRelationship,
			CharacteristicKindOf characteristicKinfOf, String[] factWords) {
		this.classCharacteristicTerm = classCharacteristicTerm;
		this.attributeRelationship = attributeRelationship;
		this.stateRelationship = stateRelationship;
		this.characteristicKinfOf = characteristicKinfOf;
		this.factWords = factWords;
	}

	public SBVRClassCharacteristicTerm getClassCharacteristicTerm() {
		return classCharacteristicTerm;
	}

	public void setClassCharacteristicTerm(SBVRClassCharacteristicTerm classCharacteristicTerm) {
		this.classCharacteristicTerm = classCharacteristicTerm;
	}

	public ClassAttributeRelationship getAttributeRelationship() {
		return attributeRelationship;
	}

	public void setAttributeRelationship(ClassAttributeRelationship attributeRelationship) {
		this.attributeRelationship = attributeRelationship;
	}

	public ClassStateRelationship getStateRelationship() {
		return stateRelationship;
	}

	public void setStateRelationship(ClassStateRelationship stateRelationship) {
		this.stateRelationship = stateRelationship;
	}

	public CharacteristicKindOf getCharacteristicKinfOf() {
		return characteristicKinfOf;
	}

	public void setCharacteristicKinfOf(CharacteristicKindOf characteristicKinfOf) {
		this.characteristicKinfOf = characteristicKinfOf;
	}

	public String[] getFactWords() {
		return factWords;
	}

	public void setFactWords(String[] factWords) {
		this.factWords = factWords;
	}

}
