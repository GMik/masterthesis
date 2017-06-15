package com.vp.plugin.utils.validation.br;

import com.vp.plugin.model.IBusinessRule;
import com.vp.plugin.utils.validation.ValidationResult;

public class BusinessRuleValidationStrategy implements IBusinessRuleValidationStrategy {

	@Override
	public ValidationResult validate(IBusinessRule businessRule) {

		return null;

		// Objects.requireNonNull(businessRule);
		// ValidationResult validationResult = new ValidationResult();
		//
		// IFact[] businessRuleFacts = (IFact[]) businessRule.toFactArray();
		//
		// if (businessRuleFacts == null || businessRuleFacts.length == 0) {
		//
		// }

		// for (IBusinessRule businessRule : businessRules) {
		// System.out.println("BR: " + br.getName());
		//
		// IBusinessRule ibr = (IBusinessRule) br;
		//
		// System.out.println(ibr.getRuleText());
		// ITerm[] brTerms = (ITerm[]) ibr.toTermArray();
		// IFact[] brFacts = (IFact[]) ibr.toFactArray();
		//
		// if (brTerms != null) {
		// for (ITerm t : ibr.toTermArray()) {
		// System.out.println(br.getName() + " TERM: " + t.getName());
		// }
		// }
		//
		// if (brFacts != null) {
		// for (IFact f : ibr.toFactArray()) {
		// System.out.println(br.getName() + " FACT: " + f.getName());
		//
		// IFactRole[] factRoles = f.toFactRoleArray();
		// if (factRoles != null) {
		// for (IFactRole fr : factRoles) {
		// System.out.println(br.getName() + " FACT role: " + fr.getName());
		// }
		// }
		//
		// }
		// }
		//
		// }

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
