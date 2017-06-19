package com.vp.plugin.utils.validation.sbvr.to.dm;

import java.util.stream.Collectors;

import com.vp.plugin.connectors.businessrules.SBVRFileElementsContainer;
import com.vp.plugin.utils.validation.ValidationResult;
import com.vp.plugin.utils.validation.br.BRValidationMessages;

public class SBVRToBusinessRulesValidator {

	private SBVRFileElementsContainer sbvrElementsContainer;

	public SBVRToBusinessRulesValidator(SBVRFileElementsContainer sbvrElementsContainer) {
		this.sbvrElementsContainer = sbvrElementsContainer;
	}

	public ValidationResult validateBusinessRule(String id) {

		boolean ruleExists = !sbvrElementsContainer.getBusinessRules().stream().filter(rule -> rule.getId().equals(id))
				.collect(Collectors.toList()).isEmpty();

		return ruleExists ? null
				: new ValidationResult(ruleExists, message(BRValidationMessages.BR_WITH_GIVEN_ID_DOES_NOT_EXIST, id));
	}

	public SBVRFileElementsContainer getSbvrElementsContainer() {
		return sbvrElementsContainer;
	}

	public void setSbvrElementsContainer(SBVRFileElementsContainer sbvrElementsContainer) {
		this.sbvrElementsContainer = sbvrElementsContainer;
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
