package com.vp.plugin.utils.validation.br;

import com.vp.plugin.model.IBusinessRule;
import com.vp.plugin.utils.validation.ValidationResult;

public class BusinessRuleValidator implements IBusinessRuleValidator {

	private IBusinessRuleValidationStrategy businessRuleValidationStrategy;

	@Override
	public ValidationResult validate(IBusinessRule businessRule) {
		return businessRuleValidationStrategy.validate(businessRule);
	}

	public IBusinessRuleValidationStrategy getBusinessRuleValidationStrategy() {
		return businessRuleValidationStrategy;
	}

	public void setBusinessRuleValidationStrategy(IBusinessRuleValidationStrategy businessRuleValidationStrategy) {
		this.businessRuleValidationStrategy = businessRuleValidationStrategy;
	}

}
