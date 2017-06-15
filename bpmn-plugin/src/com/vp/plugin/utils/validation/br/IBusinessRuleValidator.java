package com.vp.plugin.utils.validation.br;

import com.vp.plugin.model.IBusinessRule;
import com.vp.plugin.utils.validation.ValidationResult;

public interface IBusinessRuleValidator {

	ValidationResult validate(IBusinessRule businessRule);

}
