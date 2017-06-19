package com.vp.plugin.connectors.businessrules;

public class SBVRClassCharacteristicTerm {

	// class
	private SBVRTerm term;

	// attribute/state
	private String characteristicTerm;
	private CharacteristicKindOf characteristicKindOf;

	public SBVRClassCharacteristicTerm(SBVRTerm term, String characteristicTerm,
			CharacteristicKindOf characteristicKindOf) {
		this.term = term;
		this.characteristicTerm = characteristicTerm;
		this.characteristicKindOf = characteristicKindOf;
	}

	public CharacteristicKindOf getCharacteristicKindOf() {
		return characteristicKindOf;
	}

	public void setCharacteristicKindOf(CharacteristicKindOf characteristicKindOf) {
		this.characteristicKindOf = characteristicKindOf;
	}

	public SBVRTerm getTerm() {
		return term;
	}

	public void setTerm(SBVRTerm term) {
		this.term = term;
	}

	public String getCharacteristicTerm() {
		return characteristicTerm;
	}

	public void setCharacteristicTerm(String characteristicTerm) {
		this.characteristicTerm = characteristicTerm;
	}

}
