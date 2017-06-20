package com.vp.plugin.utils.validation.sbvr.to.dm;

import java.util.ArrayList;
import java.util.List;

import com.vp.plugin.connectors.businessrules.innermodel.SBVRClassCharacteristicFact;
import com.vp.plugin.connectors.domainmodel.br.facts.ClassFact;
import com.vp.plugin.connectors.domainmodel.br.facts.StateFact;
import com.vp.plugin.utils.validation.ValidationResult;
import com.vp.plugin.utils.validation.br.DMToSBVRValidationMessages;

//class - attributes, states
public class BRClassValidator {

	// public List<ValidationResult>
	// validate(DomainModelSBVRRelevantElementsContainer
	// domainModelElementsContainer,
	// SBVRFileElementsContainer sbvrElementsContainer) {
	//
	// List<ValidationResult> validationResults = new ArrayList<>();
	//
	// List<ClassFact> domainModelClassFacts =
	// domainModelElementsContainer.getClassFacts();
	// List<SBVRClassCharacteristicFact> sbvrClassFacts =
	// sbvrElementsContainer.getCharacteristicFacts().stream()
	// .filter(fact ->
	// fact.getCharacteristicKinfOf().equals(CharacteristicKindOf.ATTRIBUTE))
	// .collect(Collectors.toList());
	// validationResults.addAll(validateAttributes(domainModelClassFacts,
	// sbvrClassFacts));
	//
	// List<StateFact> domainModelStateFacts =
	// domainModelElementsContainer.getStateFacts();
	// List<SBVRClassCharacteristicFact> sbvrStateFacts =
	// sbvrElementsContainer.getCharacteristicFacts().stream()
	// .filter(fact ->
	// fact.getCharacteristicKinfOf().equals(CharacteristicKindOf.STATE))
	// .collect(Collectors.toList());
	// validationResults.addAll(validateStates(domainModelStateFacts,
	// sbvrStateFacts));
	//
	// return validationResults;
	// }

	public List<ValidationResult> validateAttributes(List<ClassFact> domainModelClassFacts,
			List<SBVRClassCharacteristicFact> sbvrClassFacts) {

		List<ValidationResult> validationResults = new ArrayList<>();

		for (ClassFact classFact : domainModelClassFacts) {

			boolean matching = false;
			for (SBVRClassCharacteristicFact sbvrClassCharacteristicFact : sbvrClassFacts) {

				String dmClass = classFact.getTerm().getName();
				String dmAttribute = classFact.getProperty();

				String sbvrClass = sbvrClassCharacteristicFact.getClassCharacteristicTerm().getTerm().getTerm();
				String sbvrAttribute = sbvrClassCharacteristicFact.getClassCharacteristicTerm().getCharacteristicTerm();

				if (dmClass.equals(sbvrClass) && dmAttribute.equals(sbvrAttribute)) {
					matching = true;
					break;
				}
			}
			if (!matching) {
				validationResults.add(new ValidationResult(false, message(
						DMToSBVRValidationMessages.DOMAIN_MODEL_ELEMENT_HAS_NO_MATCHING_BR, classFact.toString())));
			}
		}

		return validationResults;
	}

	public List<ValidationResult> validateStates(List<StateFact> domainModelClassStateFacts,
			List<SBVRClassCharacteristicFact> sbvrClassFacts) {

		List<ValidationResult> validationResults = new ArrayList<>();

		for (StateFact stateFact : domainModelClassStateFacts) {
			boolean matching = false;

			for (SBVRClassCharacteristicFact sbvrFact : sbvrClassFacts) {
				String dmClass = stateFact.getTerm().getName();
				String dmState = stateFact.getState();

				String sbvrClass = sbvrFact.getClassCharacteristicTerm().getTerm().getTerm();
				String sbvrState = sbvrFact.getClassCharacteristicTerm().getCharacteristicTerm();

				if (dmClass.equals(sbvrClass) && dmState.equals(sbvrState)) {
					System.out.println("Success - class and state - " + dmClass + ", " + dmState);
					matching = true;
					break;
				}
			}
			if (!matching) {
				validationResults.add(new ValidationResult(false, message(
						DMToSBVRValidationMessages.DOMAIN_MODEL_ELEMENT_HAS_NO_MATCHING_BR, stateFact.toString())));
			}
		}

		return validationResults;
	}

	private String message(String message, String... params) {
		String result = new String(message);
		int position = 0;
		for (String param : params) {
			result = result.replace("{" + (position++) + "}", param);
		}
		return result;
	}

}
